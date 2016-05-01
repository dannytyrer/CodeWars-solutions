#Return the sum of all the multiples of 3 or 5
#below the number passed in
#Link: http://www.codewars.com/kata/multiples-of-3-and-5/csharp  
using System.Collections.Generic;
using System.Linq;
using System;
public static class Kata
{
  public static int Solution(int value)
  {
    //Write to set to remove duplicates
    HashSet<int> values = new HashSet<int>();
    for(int counter=value-1; counter>0; counter--){
      if(counter%3==0) {
        values.Add(counter);
      }
      if(counter%5==0) {
        values.Add(counter);
      }
    }
    return values.Sum();
  }
}