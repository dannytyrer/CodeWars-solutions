/* Write a morse code decoder for a wired electical telegraph
   Link: http://www.codewars.com/kata/54b72c16cd7f5154e9000457
*/

public class MorseCodeDecoder {
    private static int waitLong;
    private static int waitMid;
    private static int signalLong;
    
    public static String decodeBits(String bits) {
      
      int length=bits.length();
      String returnString="";
      int smallestConsecutive = findSmallestConsecutive(bits);
      waitMid=smallestConsecutive*3;
      signalLong=smallestConsecutive*3;
      waitLong=smallestConsecutive*7;
      int index=0;
      int[] signalWait= new int[2];
      int[] endWait = new int[2];
      String waitType="";
      String appendChar="";
      Boolean first=true;
      int waitLength =0 ;
      while(index<length&&index!=-1){
        signalWait=wait(bits, index, length, '1');
        index=signalWait[1];
        if(index==-1){
          break;
        }
        waitLength=signalWait[0];
        if(first) {
        
          first=false;
        } else {
        
          waitType=interpretWait(waitLength);
          switch(waitType) {
          
          case "WORD": returnString+="   "; break;
          
          case "CHAR": returnString+=" "; break;
          }
          }
          endWait = wait(bits,index,length,'0');
          index=endWait[1];
          waitLength=endWait[0];
          appendChar=interpretSignal(waitLength);
          returnString+=appendChar;
      }
      return returnString;
    }
    
    //Returns the length of the wait period and the index reached
    public static int[] wait(String bits, int startIndex, int codeLength, char signal) {
        int[] returnArr = new int[2];
        int currentIndex=startIndex;
        while(currentIndex<codeLength){
        
          if(bits.charAt(currentIndex)==signal){
          
            returnArr[0] = currentIndex-startIndex;
            returnArr[1] = currentIndex;
            return returnArr;
          }
          currentIndex++;  
        }
        returnArr[0]=currentIndex-startIndex;
        returnArr[1]=-1;
        return returnArr;
    }
    //Interprets the type of wait
    public static String interpretWait(int length){
    
      if(length<waitMid) {
          
        return "BIT";
      } else if(length<waitLong) {
      
        return "CHAR";
      } else {
      
        return "WORD";
      }
    }
    //Interprets the type of signal
    public static String interpretSignal(int length){
    
      if(length<signalLong) {
      
        return ".";
      } else {
      
        return "-";
      }
    }
    //Finds the smallest consecutive sequence of 1s or 0s
    public static int findSmallestConsecutive(String signal) {
        
        signal = signal.replace("10","1,0");
        signal =signal.replace("01","0,1");
        String[] signals = signal.split(",");
        
        int startPoint =0;
        int endPoint = signals.length;
        if(signals[0].contains("0")){
            
            startPoint=1;
        }
        if(signals[endPoint-1].contains("0")){
        
          endPoint--;
        }
        int smallestSignal = 100000000;
        int smallestWait = 100000000;
        for(int i=startPoint; i<endPoint; i++){
          if(signals[i].contains("0")){
          
            if(signals[i].length()<smallestWait){
            
              smallestWait=signals[i].length();
            }
          } else {
            if(signals[i].length()<smallestSignal){
            
              smallestSignal = signals[i].length();
            }
           }      
        }
        if(smallestSignal<=smallestWait){   
		
          return smallestSignal;
        } else {
        
          return smallestWait;
        }
    }
   //Taken from answer for part one of this challenge 
    public static String decodeMorse(String morseCode) {
      //trim leading and trailing whitespace form string
      morseCode=morseCode.trim();
      //Split into individual words
      String[] wordArr  = morseCode.split("(\\s){3}");
      String returnString="";
      //iterate through the words
      for(int i=0; i<wordArr.length;i++) {
          //split the words into individual letters
          String[] letterArr = wordArr[i].split("(\\s){1}");
          //iterate through the letters
          for(int j=0; j<letterArr.length;j++){
            String currentLetter= letterArr[j];
            String temp = MorseCode.get(currentLetter);
            if(temp!=null){
              //append the letter to the return string  
              returnString+=MorseCode.get(currentLetter);
            }
           }
          if(i!=wordArr.length-1){
            returnString+=" ";
           }
       }
      return returnString;  
    }
}