/*Create pascals triangle given the depth of the triangle
  Link: http://www.codewars.com/kata/52945ce49bb38560fe0001d9
*/

public class PascalsTriangle {
  public static int[][] pascal(int depth) {
	  
    int[][] returnArr= new int[depth][];
    for(int n=0; n<depth; n++){
      //assign length of the array level
      returnArr[n] = new int[n+1];
      for(int r=0;r<=n;r++){
        //n choose r calculation
        returnArr[n][r]=(calculateFactorial(n)/(calculateFactorial(n-r)*calculateFactorial(r)));
      }
    }
    return returnArr;
  }
  //calculate factorial recursively
  public static int calculateFactorial(int num) { 
    if(num==0){
      return 1;
    } else {
      return num*calculateFactorial(num-1);
    }
  }
}