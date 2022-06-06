#Describe what the following snippets do.

#1)
list_a = [1, 6, 8, 3, 5, 9, 7, 0]
list_b = [9, 0, 6]
res = {i: list_a.index(i) for i in list_b}
print(res)

"""
For question 1, we are analyzing #1, It is easier to break this
down backwards
for i in list_b will set the i, aka the key in i: list_a.index(i)
as each element in list_b
then we analyze list_a.index(i) to understand what the values
at each of the keys
the value will the result of the index of the key as it appears in list_a
"""

#2)
nums = range(10,20)
res = {num:max([div for div in range(1,10) if num % div == 0]) for num in nums}
print(res)

"""
nums will be set to a list of numbers incrementing by one starting from 
10 to 20-1, aka 19
staring from the outer shell of the res variable we are creating another dictionary
the keys will be all the numbers in nums due to for num in nums
then the value at nums will be the max of the elements in div
then div is a list of numbers 1-9, but the numbers will only be added 
to the list div, if the key of res modulous the number in the div range is 0 
"""

#3)
def func(x, l=list()):
    l.append([a for a in range(0, x)])
    return l

print(func(2))
print(func(3))

"""
In this problem we have a function and we call it twice
when we pass in a number the temp list is initialized
Then we append a list of items that is 0- the number-1
then it is returned
"""