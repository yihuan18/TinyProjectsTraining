解题思路
遍历字符串，用一个数组来存储每个字符遍历的序号，
遍历时，将字符对应的序号依次记录为1，2，3…

	1. 用previous记录当前不重复字符串的起始位置，用right来记录当前遍历的序号
	2. 开始遍历操作
	3. 当遍历到某一个字符，如果它已经存在一个序号，并且这个序号是大于previous的时候，就说明这是一个重复字符。
	4. 此时，该不重复子串的长度为right-previous。
	5. 更新最长长度为当前最长与该长度的较大值。
	6. 将previous修改为重复字符的序号，并继续执行遍历操作，直到遍历完毕