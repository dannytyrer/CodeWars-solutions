/*Write a simple morse code decoder
  Link: http://www.codewars.com/kata/54b724efac3d5402db00065e
*/
public class MorseCodeDecoder {
    public static String decode(String morseCode) {
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