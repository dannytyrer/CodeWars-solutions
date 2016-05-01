/*Print a diamond to screen where n is the number of *'s in
  the middle line.
  Link: http://www.codewars.com/kata/give-me-diamond/java
*/
public class Diamond {
  
  public static String print(int n) {
        
        if(n%2==0|| n<0) {
        
            return null;
        }
        String string1="";
        String string2="";
        string1 = PrintTop(n, string1, 0);
        string2 = PrintBottom(n, string2, 0, n);
        return string1+string2;
    }
    
  /*Function responsible for building the first half of the diamond
  */
  private static String PrintTop(int n, String diamondString, int  maxCount) {
  
      if(n==1){
           
           return printHelper(n, diamondString, maxCount);
      } else {
      
         diamondString=PrintTop(n-2,diamondString, maxCount+1) + printHelper(n, diamondString, maxCount); 
      }
    return diamondString;
  }
  
  /*Function responsible for building the second half of the diamond
   */
  private static String PrintBottom(int n, String diamondString, int noOfSpaces, int maxCount) {
  
    if(n==1) {
    
        return printHelper(n, diamondString,noOfSpaces); 
    } else {
      if (maxCount ==n) {
      
          return PrintBottom(n-2, diamondString,noOfSpaces+1, maxCount);
      } else {
      
          diamondString = printHelper(n,diamondString,noOfSpaces) + PrintBottom(n-2, diamondString,noOfSpaces+1, maxCount);
       }
       return diamondString;
    }
  
  }
  private static String printHelper(int n, String diamondString, int noOfSpaces) {
      int temp=n;
      while(noOfSpaces>0){
      
        diamondString+=" ";
        noOfSpaces--;
      }
      while(temp>0) {
          diamondString+="*";
          temp--;
       }
       diamondString+="\n";
       return diamondString;
  }
}