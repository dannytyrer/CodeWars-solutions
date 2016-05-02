# Determine if the order of a string of braces is valid
# Link: http://www.codewars.com/kata/valid-braces/python

def validBraces(string):
  bracketsMap = {')':'(','}':'{',']':'['}
  openBrackets = bracketsMap.values()
  closedBrackets = bracketsMap.keys()
  stack = []
  for char in string:
      if char in openBrackets:
          stack.append(char)
      elif char in closedBrackets:
          #if the stack is empty then there is no matching open 
          #bracket
          if not stack:
              return False
          else:
              #get the last character if it doesn't match the
              #current one then the string is invalid
              lastChar = stack.pop()
              if bracketsMap[char]!=lastChar:
                  return False
  #if the stack is empty then all open brackets have been 
  #matched, if not then they haven't
  if not stack:
      return True
  else:
      return False
  pass