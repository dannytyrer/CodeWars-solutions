#Convert an integer to its english representation
#Link: http://www.codewars.com/kata/53c94a82689f84c2dd00007d

import re
units =["one","two","three","four","five","six","seven","eight","nine"]
teens = ["ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen",
         "seventeen", "eighteen","nineteen"]
tens = ["twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety","hundred"]
threes=["","thousand","million","billion","trillion","quadrillion","quintillion",
        "sextillion","septillion"]

#convert a power of three into its word representation by looking up in the threes list
def threePowerToString(power):
    index = power/3
    threePowerString=threes[index]
    return threePowerString

#convert a unit into its word representation by looking up in the units list
def unitToString(unit):
    string = ""
    if unit!=0:
        string=units[unit-1]
    return  " "+string

#convert a ten into its word representation, by looking in the teens list, tens list and units list depending
#upon input
def tenToString(ten,unit):
    string=""
    if ten==0:
        string=unitToString(unit)
    elif ten==1:
        string=teens[unit]
    else:
        string=tens[ten-2]
        unitString=unitToString(unit)
        string+=unitString
    return " "+string

#convert a hundred into its word representation by looking in the appropriate list    
def hundredToString(hundred, ten, unit):
    string =""
    if hundred!=0:
        string =unitToString(hundred)+" hundred"
    tenString=tenToString(ten,unit)
    string+=tenString
    return " " + string

#called recursively decrementing the power to the next factor of 3 on each pass
def int_to_english_helper(returnString, numberString, power):
    unit=""
    ten=""
    hundred=""
    threePow=""
    decrement=0
    if power%3==0:
        decrement=1
        returnString+=unitToString(int(numberString[:1]))
    elif power%3==1:
        decrement=2
        returnString+=tenToString(int(numberString[:1]),int(numberString[1:2]))
    else:
        returnString+=hundredToString(int(numberString[:1]),int(numberString[1:2]),int(numberString[2:3]))
        decrement=3
        
    if power<3:
        return returnString
    else:
        returnString+=" "+threePowerToString(power)    
        return int_to_english_helper(returnString, numberString[decrement:],power-decrement)
        
#entry point to the program if the number is zero simpy return it else proceed to int_to_english_helper
#providing the current power of ten of the number the returnString to append to and the string representation
#of the input number
def int_to_english(n):
    if n==0:
        return "zero"
    else:
        returnString=""
        numberString=str(n)
        numberLength = len(str(n))
        power=numberLength-1
        returnString = int_to_english_helper(returnString, numberString, power)
        return re.sub(' +',' ', returnString.strip())
		
#Test Cases:
Test.expect(int_to_english(15) == 'fifteen', "'fifteen' expected")
Test.expect(int_to_english(47) == 'forty seven' or int_to_english(47) == 'forty-seven', "'forty seven' or 'forty-seven' expected")
Test.expect(int_to_english(536) == 'five hundred thirty six' or int_to_english(536) == 'five hundred and thirty-six', "'five hundred thirty six' or 'five hundred and thirty-six' expected")
Test.expect(int_to_english(12356) == 'twelve thousand three hundred fifty six' or int_to_english(12356) == 'twelve thousand three hundred and fifty-six', "'twelve thousand three hundred fifty six' or 'twelve thousand three hundred and fifty-six' expected")
