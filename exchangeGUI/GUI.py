from tkinter import *
import tkinter.filedialog
import readExcel
import groupMatching
import personMatching

def xz():
    global filename
    filename.set(tkinter.filedialog.askopenfilename())


def selectPath():
    global path
    path.set(tkinter.filedialog.askdirectory())


def exchange():
    print("根据excel进行匹配，然后在选择的路径中更改文件命名")
    #获取excel表中的相关学生信息
    groupNumList, stuIdNumList, nameList = readExcel.readExcel(filename.get())
    #根据交换选项进行交叉批改的名单匹配
    if groupExchangeFlag.get() == 0:
        groupNumListMatch, stuIdNumListMatch, nameListMatch = personMatching.personMatching(groupNumList, stuIdNumList, nameList)
    elif groupExchangeFlag.get() == 1:
        groupNumListMatch, stuIdNumListMatch, nameListMatch = groupMatching.groupMatching(groupNumList, stuIdNumList, nameList)
    # #####debug
    print(filename.get())
    print(path.get())
    print(groupNumList)
    print(stuIdNumList)
    print(nameList)
    print(groupNumListMatch)
    print(stuIdNumList)
    print(nameListMatch)

def getoptions():
    global groupExchangeFlag
    print('\n groupExchangeFlag is ')
    print(groupExchangeFlag.get())


root = Tk()
filename = StringVar()
path = StringVar()
groupExchangeFlag = IntVar()

Label(root, text='目标文件').grid(row=0, column=0)
entry = Entry(root, textvariable=filename).grid(row=0, column=1)
Button(root, text="文件选择", command=xz).grid(row=0, column=2)

Label(root, text="目标路径:").grid(row=1, column=0)
entry1 = Entry(root, textvariable=path).grid(row=1, column=1)
Button(root, text='目录选择', command=selectPath).grid(row=1, column=2)


Checkbutton(root, text='组间匹配', variable=groupExchangeFlag, onvalue=1, offvalue=0, command=getoptions).grid(row=2, column=0)
Button(root, text='开始匹配', command=exchange).grid(row=2, column=2)


root.mainloop()
