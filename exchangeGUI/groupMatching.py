import random


def groupMatching(groupNumList, stuIdNumList, nameList):
    # 获取小组总数
    groupNum = max(groupNumList)
    # 设置随机数种子,省略参数，意味着使用当前系统时间生成随机数
    random.seed()
    randNum = random.randint(1, groupNum - 1)
    # 将文件中已经出现过的随机数都存放在randNumHistory中
    fr = open('randNumHistoryForGroup.txt', 'r')
    randNumHistory = []
    intTemp = ''
    for char in fr.read():
        if char == ' ':
            # 将intTemp转化为整型数值
            randNumHistory.append(int(intTemp))
            intTemp = ''
            continue
        intTemp += char
    fr.close()
    # 生成一个没有在randNumHistory中出现过的随机数
    fr = open('randNumHistoryForGroup.txt', 'a')
    length = len(randNumHistory)
    num = round(groupNum / 2) - 1
    if length >= num:
        fr.close()
        fr = open('randNumHistoryForGroup.txt', 'w')
        fr.truncate()
        fr.close()
        fr = open('randNumHistoryForGroup.txt', 'a')

    while randNum in randNumHistory:
        randNum = random.randint(1, groupNum - 1)
    fr.write(str(randNum) + " ")
    fr.close()
    groupNumListMatch = [0] * groupNum
    stuIdNumListMatch = [' '] * groupNum
    nameListMatch = [' '] * groupNum
    for i in range(groupNum):
        matchGroupNum = (i + randNum) % groupNum + 1
        groupNumListMatch[i] = matchGroupNum
        stuIdNumListMatch[i] = stuIdNumList[groupNumList.index(matchGroupNum)]
        nameListMatch[i] = nameList[groupNumList.index(matchGroupNum)]
    return groupNumListMatch, stuIdNumListMatch, nameListMatch


# l1 = [1,1,1,2,2,2,3,3,3]
# l2 = [1,2,3,4,5,6,7,8,9]
# l3 = [1,2,3,4,5,6,7,8,9]
# print(l1)
# print(l2)
# print(l3)
# r1,r2,r3 = groupMatching(l1,l2,l3)
# print(r1)
# print(r2)
# print(r3)


