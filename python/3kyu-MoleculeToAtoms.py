#Given a chemical formula as a string count the number of atoms of each element contained
#Link: http://www.codewars.com/kata/52f831fa9d332c6591000511

def parse_molecule (formula):
    formula.split()
    openBrackets  = ['(','[','{']
    closeBrackets = [')',']','}']
    bracketMap = {')':'(',']':'[','}':'{'}
    count = {}
    stack = []
    i = 0
    
    #First pass of parser
    while i < len(formula):
        symbolString = ""
        if formula[i].isalpha():
            symbolString = formula[i]
            nextIndex = i+1
            if nextIndex>=len(formula):
                stack.append(symbolString)
                count[symbolString] = ""
                break
            while formula[nextIndex].isalpha() and formula[nextIndex].islower():
                symbolString+=formula[nextIndex]
                nextIndex+=1
            i = nextIndex-1
            count[symbolString] = ""
        elif formula[i] in openBrackets or formula[i] in closeBrackets:
            symbolString = formula[i]
        else:
            symbolString = formula[i]
            nextIndex = i+1
            if nextIndex<len(formula):
                while isInt(formula[nextIndex]):
                    symbolString+=formula[nextIndex]
                    nextIndex+=1
            i = nextIndex - 1
        stack.append(symbolString)
        i+=1
        
    #Second pass of parser to reduce number of molecules
    reducedStack = []
    j = 0
    for j in range(0, len(stack)):
        if stack[j].isalpha() :
            symbol = stack[j]
            if j+1>= len(stack):
                reducedStack.append(symbol)
                break
            lookahead = stack[j+1]
            if isInt(lookahead):
                lookahead = int(lookahead)
            else:
                lookahead = 1
            for k in range(0,lookahead):
                reducedStack.append(stack[j])
        elif stack[j] in openBrackets:
            reducedStack.append(stack[j])
        elif stack[j] in closeBrackets:
            if isInt(stack[j+1]):
                 lookahead=int(stack[j+1])
            else:
                 lookahead = 1
            element = stack[j]
            tempStore =[]
            value = ""
            k = 0
            while k<len(reducedStack):
                value = reducedStack.pop()
                if value==bracketMap[element]:
                    for z in range(0,lookahead):
                        for y in range(0, len(tempStore)):
                            reducedStack.append(tempStore[y])
                    break
                else:
                    tempStore.append(value)
        j+=1
    #Count the number of times each symbol appears in the reduced stack
    for value in count.keys():
        count[value]=reducedStack.count(value)
    return count

def isInt (intString):
    try:
        int(intString)
        return True
    except ValueError:
        return False
        




#Test Cases:

water = 'H2O'
parse_molecule(water)                 # return {H: 2, O: 1}

magnesium_hydroxide = 'Mg(OH)2'
parse_molecule(magnesium_hydroxide)   # return {Mg: 1, O: 2, H: 2}

var fremy_salt = 'K4[ON(SO3)2]2'
parse_molecule(fremySalt)             # return {K: 4, O: 14, N: 2, S: 4}