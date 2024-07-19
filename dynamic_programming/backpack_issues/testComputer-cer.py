import sys
import unicodedata
import codecs

# 目的：将字符串分割成字符或标记的列表，跳过标点和空格，并处理特定标签或序列。
# 功能：
#   遍历输入字符串的每个字符。
#   跳过在puncts列表中定义的标点。
#   使用Unicode类别来跳过空格或未定义的字符。
#   分离被特定标签如<unk>包围的标记。
#   将被视为字母或特殊序列的字符或标记收集到结果列表中。
remove_tag = True
spacelist = [' ', '\t', '\r', '\n']
puncts = ['!', ',', '?',
          '、', '。', '！', '，', '；', '？',
          '：', '「', '」', '︰', '『', '』', '《', '》']


def characterize(string):
    res = []
    i = 0
    while i < len(string):
        char = string[i]
        if char in puncts:
            i += 1
            continue
        # 在 puncts 中的不要
        cat1 = unicodedata.category(char)
        # https://unicodebook.readthedocs.io/unicode.html#unicode-categories
        if cat1 == 'Zs' or cat1 == 'Cn' or char in spacelist:  # space or not assigned
            i += 1
            continue
        # 在 spacelist 中的不要，Zs:各种空格符号不要 Cn:未被分配的字符不要
        if cat1 == 'Lo':  # letter-other
            res.append(char)
            i += 1
        # 除了英文字符意外的语言文字 单独一个放进列表
        else:
            # 将输入 "<unk><noise>" 分割成两个单词。
            sep = ' '
            if char == '<':
                sep = '>'
            j = i + 1
            while j < len(string):
                c = string[j]
                # 如果 c 是ASCII范围外的字符、空格列表中的字符或者等于 sep，循环就会终止
                if ord(c) >= 128 or (c in spacelist) or (c == sep):
                    break
                j += 1
            if j < len(string) and string[j] == '>':
                j += 1
            res.append(string[i:j])
            # 左闭右开
            i = j
    return res


# idckf . & <我日尼玛> !! .. hhhh
# 分割结果: ['idckf', '.', '&', '<', '我', '日', '尼', '玛', '>', '..', 'hhhh']

# idckf . & <我日尼玛 !! .. hhhh
# 分割结果: ['idckf', '.', '&', '<', '我', '日', '尼', '玛', '..', 'hhhh']

# 《是的是的》 098876 我hi范德萨发你 零零零零 ‘；【】！@#￥%……
#  ['是', '的', '是', '的', '098876', '我', 'hi', '范', '德', '萨', '发', '你', '零', '零', '零', '零', '‘', '【', '】', '@#', '￥%', '…', '…']
def main1():
    # 假设从命令行接收输入，可以改成任何你想要处理的字符串
    if len(sys.argv) > 1:
        input_string = sys.argv[1]
    else:
        input_string = input("请输入字符串: ")

    # 调用 characterize 函数
    result = characterize(input_string)

    # 打印结果
    print("分割结果:", result)


# ------------------------------------ #

# 目的：从字符串中移除类似HTML的标签。
# 功能：
#   遍历每个字符，跳过<和>之间的内容以从返回的字符串中排除标签。

# X 是一个字符串，目的是从字符串中移除html标签
def stripoff_tags(x):
    if not x:
        return ''
    # 检查字符串x是否为空，如果为空就返回一个空字符串
    chars = []
    i = 0
    T = len(x)
    while i < T:
        if x[i] == '<':
            while i < T and x[i] != '>':
                i += 1
            i += 1
            # 直接跳过<table>标签
        else:
            chars.append(x[i])
            i += 1
    return ''.join(chars)
    # 使用 join() 方法将列表 chars 中的所有字符合并成一个字符串并返回


# ------------------------------------ #

# 目的：处理一个标记列表通过规范化它们，可选地过滤掉忽略词汇，处理大小写，并将特定标记拆分为多个标记。
# 功能：
#   遍历句子中的每个标记。
#   根据大小写敏感参数（cs），可能将标记转换为大写。
#   过滤掉在忽略词汇列表中的标记。
#   移除标记中的HTML标签（如果启用了remove_tag）。
#   如果提供了split字典，将特定标记拆分为多个标记。
#   分别处理每个标记，如果它是字母数字的，则将其分解为单独的字符。

def normalize(sentence, ignore_words, cs, split=None):
    """
        sentence：一个字符串，传进来的句子
        ignore_words：一个集合或列表，包含需要忽略的单词。
        cs：一个布尔值，表示是否大小写敏感（True 表示大小写敏感，False 表示不区分大小写）。
        split：可选参数，默认为 None，用于指定如何拆分特定的单词。
    """
    new_sentence = []
    for token in sentence:
        x = token
        if not cs:
            x = x.upper()
        # 大小写不敏感就全都转化成大写
        if x in ignore_words:
            continue
        # x在 ignore_words 列表中，直接跳过
        if remove_tag:
            x = stripoff_tags(x)
        # 如果要从字符串中移除类似HTML的标签 则调用此函数
        if not x:
            continue
        # 如果x是空字符串，则直接跳过
        if split and x in split:
            new_sentence += split[x]
        if x.isalnum():
            for k in x:
                new_sentence.append(k)  # 如果 x 是字母数字的（只包含字母和数字），则将 x 中的每个字符逐个添加到 new_sentence。
        else:
            new_sentence.append(x)
    return new_sentence


# ------------------------------------ #

# 目的：计算和管理与WER相关的数据，提供详细的错误类型统计和反向追踪编辑操作。
# 功能：
#   初始化一系列编辑操作的成本和存储空间。
#   计算标签和记录之间的编辑距离，并标记错误类型。
#   提供一个反向追踪过程，以从计算出的编辑距离中生成对齐的标签和记录。
#   提供整体和特定数据集（如词类集）的错误统计。

# 测量两个字符串的相似度
class Calculator:
    def __init__(self):
        self.data = {}
        self.space = []
        self.cost = {}
        self.cost['cor'] = 0 # 正确匹配成本
        self.cost['sub'] = 1 # 更新成本
        self.cost['del'] = 1 # 删除成本
        self.cost['ins'] = 1 # 插入成本

    # dp数组以及数组下标的含义：
    #       以i-1为结尾的word1和以j-1为结尾的word2最少操作次数是dp[i][j]
    def calculate(self, lab, rec):
        # Initialization
        lab.insert(0, '')
        rec.insert(0, '')
        while len(self.space) < len(lab):
            self.space.append([])
        for row in self.space:
            for element in row:
                element['dist'] = 0
                element['error'] = 'non'
            while len(row) < len(rec):
                row.append({'dist': 0, 'error': 'non'})
        # 初始化
        # dp[i][0] : 以i-1为结尾的word1和以-1为结尾的word2（空字符串）最少操作次数是 i -> 一直减干净就行 / 一直加上
        # dp[0][i] : 以-1为结尾的word1(空字符串)和以j-1为结尾的word2最少操作次数是 j -> 一直减干净就行 / 一直加上
        for i in range(len(lab)):
            self.space[i][0]['dist'] = i
            self.space[i][0]['error'] = 'del'
        for j in range(len(rec)):
            self.space[0][j]['dist'] = j
            self.space[0][j]['error'] = 'ins'
        self.space[0][0]['error'] = 'non'

        for token in lab:
            if token not in self.data and len(token) > 0:
                self.data[token] = {'all': 0, 'cor': 0, 'sub': 0,
                                    'ins': 0, 'del': 0}
        for token in rec:
            if token not in self.data and len(token) > 0:
                self.data[token] = {'all': 0, 'cor': 0, 'sub': 0,
                                    'ins': 0, 'del': 0}
        # 如果这个元素是新的（即之前没有被记录过），为它在 self.data 中创建一个新条目。这个新条目是一个字典，包含几个键：all, cor, sub, ins, del，
        # 它们的初始值都设为0，用来分别统计：
        # all：元素总出现次数
        # cor：元素正确匹配的次数
        # sub：元素被替换的次数
        # ins：元素被插入的次数
        # del：元素被删除的次数

        # Computing edit distance
        for i, lab_token in enumerate(lab):
            for j, rec_token in enumerate(rec):
                if i == 0 or j == 0:
                    continue
                    # i = 0 || j = 0时跳过，初始化时已经搞好了
                min_dist = sys.maxsize
                min_error = 'none'
                # 删除lab中的1个元素
                dist = self.space[i - 1][j]['dist'] + self.cost['del'] # self.cost['del'] -> 删除操作的成本
                error = 'del'
                if dist < min_dist:
                    min_dist = dist
                    min_error = error
                # 增加lab中一个元素 == 减少rec一个元素
                dist = self.space[i][j - 1]['dist'] + self.cost['ins']
                error = 'ins'
                if dist < min_dist:
                    min_dist = dist
                    min_error = error
                # 正确匹配
                if lab_token == rec_token:
                    dist = self.space[i - 1][j - 1]['dist'] + self.cost['cor']
                    error = 'cor'
                else: # 替换
                    dist = self.space[i - 1][j - 1]['dist'] + self.cost['sub']
                    error = 'sub'
                if dist < min_dist:
                    min_dist = dist
                    min_error = error
                self.space[i][j]['dist'] = min_dist
                self.space[i][j]['error'] = min_error
        # Tracing back
        result = {'lab': [], 'rec': [], 'all': 0, 'cor': 0, 'sub': 0,
                  'ins': 0, 'del': 0}
        i = len(lab) - 1
        j = len(rec) - 1
        while True:
            if self.space[i][j]['error'] == 'cor':  # correct
                if len(lab[i]) > 0:
                    self.data[lab[i]]['all'] += 1 # 对当前元素lab[i]总操作次数加1
                    self.data[lab[i]]['cor'] += 1 # 对当前元素lab[i]正确匹配次数加1
                    result['all'] += 1 # 结果中总操作次数加1
                    result['cor'] += 1 # 结果中正确匹配次数加1
                result['lab'].insert(0, lab[i])
                result['rec'].insert(0, rec[j])
                i = i - 1
                j = j - 1
            elif self.space[i][j]['error'] == 'sub':  # substitution
                if len(lab[i]) > 0:
                    self.data[lab[i]]['all'] = self.data[lab[i]]['all'] + 1
                    self.data[lab[i]]['sub'] = self.data[lab[i]]['sub'] + 1
                    result['all'] = result['all'] + 1
                    result['sub'] = result['sub'] + 1
                result['lab'].insert(0, lab[i])
                result['rec'].insert(0, rec[j])
                i = i - 1
                j = j - 1
            elif self.space[i][j]['error'] == 'del':  # deletion
                if len(lab[i]) > 0:
                    self.data[lab[i]]['all'] = self.data[lab[i]]['all'] + 1
                    self.data[lab[i]]['del'] = self.data[lab[i]]['del'] + 1
                    result['all'] = result['all'] + 1
                    result['del'] = result['del'] + 1
                result['lab'].insert(0, lab[i])
                result['rec'].insert(0, "") # 在 rec 的对应位置插入空字符串表示删除
                i = i - 1
            elif self.space[i][j]['error'] == 'ins':  # insertion
                if len(rec[j]) > 0:
                    self.data[rec[j]]['ins'] = self.data[rec[j]]['ins'] + 1 # 对当前元素rec[j]加操作次数加一
                    result['ins'] = result['ins'] + 1
                    # result['all'] += 1;
                result['lab'].insert(0, "")
                result['rec'].insert(0, rec[j])
                j = j - 1
            elif self.space[i][j]['error'] == 'non':  # starting point
                break
            else:  # shouldn't reach here
                print('this should not happen , i={i} , j={j} , \
                      error={error}'.
                      format(i=i, j=j, error=self.space[i][j]['error']))
        return result # 包含编辑操作的具体步骤和操作次数统计

    def overall(self):
        result = {'all': 0, 'cor': 0, 'sub': 0, 'ins': 0, 'del': 0}
        for token in self.data:
            result['all'] = result['all'] + self.data[token]['all']
            result['cor'] = result['cor'] + self.data[token]['cor']
            result['sub'] = result['sub'] + self.data[token]['sub']
            result['ins'] = result['ins'] + self.data[token]['ins']
            result['del'] = result['del'] + self.data[token]['del']
        return result
        # 计算总的操作次数，总共加了多少次，减了多少次 .......

    def cluster(self, data):
        result = {'all': 0, 'cor': 0, 'sub': 0, 'ins': 0, 'del': 0}
        for token in data:
            if token in self.data:
                result['all'] = result['all'] + self.data[token]['all']
                result['cor'] = result['cor'] + self.data[token]['cor']
                result['sub'] = result['sub'] + self.data[token]['sub']
                result['ins'] = result['ins'] + self.data[token]['ins']
                result['del'] = result['del'] + self.data[token]['del']
        return result

    def keys(self):
        return list(self.data.keys())
