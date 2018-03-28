import random


def personMatching(groupNumList, stuIdNumList, nameList):
    # 获取学生总人数
    stuNum = len(groupNumList)
    # 设置随机数种子,省略参数，意味着使用当前系统时间生成随机数
    random.seed()
    randNum = random.randint(1, stuNum-1)
    # 将文件中已经出现过的随机数都存放在randNumHistory中
    fr = open('randNumHistory.txt', 'r')
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
    # 生成一个没有在randNumHistroy中出现过的随机数
    fr = open('randNumHistory.txt', 'a')
    length = len(randNumHistory)
    num = round(stuNum/2) + 1
    if length >= num:
        fr.close()
        fr = open('randNumHistory.txt', 'w')
        fr.truncate()
        fr.close()
        fr = open('randNumHistory.txt', 'a')

    while randNum in randNumHistory:
        randNum = random.randint(1, stuNum-1)
    fr.write(str(randNum)+" ")
    fr.close()
    groupNumListMatch = [0]*stuNum
    stuIdNumListMatch = [' ']*stuNum
    nameListMatch = [' ']*stuNum
    for i in range(stuNum):
        matchIndex = (i + randNum) % stuNum
        groupNumListMatch[matchIndex] = groupNumList[i]
        stuIdNumListMatch[matchIndex] = stuIdNumList[i]
        nameListMatch[matchIndex] = nameList[i]
    return groupNumListMatch, stuIdNumListMatch, nameListMatch
