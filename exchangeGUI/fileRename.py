import os
import re
import shutil


def fileRename(dir, stuIdNumList, stuIdNumListMatch, asgroup, groupNumList, groupNumListMatch):
    filenames = os.listdir(dir)
    filenameLog = {}
    if asgroup == 0:
        os.mkdir(dir+"\\check")
    else:
        os.mkdir(dir+"\\checkAsGroup")
    for filename in filenames:
        num = re.match("\\d+", filename)
        if asgroup == 0:
            index = stuIdNumList.index(num.group())
            newName = stuIdNumListMatch[index]+filename[13:]
            shutil.copy(dir+"\\"+filename, dir+"\\check\\"+newName)
        else:
            groupnumIndex = stuIdNumList.index(num.group())
            index = groupNumListMatch.index(groupNumList[groupnumIndex])
            newName = stuIdNumListMatch[index]+filename[13:]
            if newName in filenameLog.keys():
                filenameLog[newName] += 1
                newName = '('+str(filenameLog[newName])+')'+newName
            else:
                filenameLog[newName] = 0
            shutil.copy(dir+"\\"+filename, dir+"\\checkAsGroup\\"+newName)