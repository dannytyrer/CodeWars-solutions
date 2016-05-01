#Return the depth of the deepest nested list within a given list.
#Return 1 if no nested lists.
#Link: http://www.codewars.com/kata/nested-list-depth/python

def list_depthHelper(l, level, deepest):
#Iterate through each element in this level
    for element in l:
    #if the element is a list, step into that level
        if isinstance(element,list):
            deepest=list_depthHelper(element, level+1,deepest)
    #if this level is deeper then set new deepest
    #if not return the original value
    if level > deepest:   
        return level
    else:
        return deepest
        
def list_depth(l):
    return list_depthHelper(l,1,1) 

#Test Cases
test.assert_equals(list_depth([1, [2, [3, [4, [5, [6], 5], 4], 3], 2], 1]), 6)
test.assert_equals(list_depth([True]), 1)
test.assert_equals(list_depth([]), 1)
test.assert_equals(list_depth([2, "yes", [True, False]]), 2)
test.assert_equals(list_depth([2.0, [2, 0], 3.7, [3, 7], 6.7, [6, 7]]), 2)
