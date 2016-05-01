/* Link: http://www.codewars.com/kata/52a8f768a8ddc5070c000ca2
*/

function crackHash(h, y) {
  // Find any integer x >= 0 such that h(x) = y
  // h:R->R is a non-decreasing (not necessarily continuous) function over the Reals
  // y is not necessarily an integer
  // at least one valid value x is guaranteed to exists
  // Remember if you call h more than 10+2*ceil(log_2(x)) times you fail
  var upperBound =-1;
  var lowerBound =-1;
  var trySolution =h(Math.round(y));
  if(trySolution==y){
  
      return Math.round(y);
  }
  else if(trySolution>y){
 
      upperBound = Math.round(y);
  } 
  else {
      lowerBound=Math.round(y);
  }
  //Maximum range covered finding the bounds is of order 2^31 for the allotted 10 tries
  //Covering that of the safe max integer value 2^31-1
  //find the lower bound by dividing the solution by 2000 until it is lower than y 
  while(lowerBound==-1){
      trySolution =Math.round(upperBound/2000);
      if(h(trySolution)<y){
      
          lowerBound=trySolution;
      } else {
      
          upperBound=trySolution;
          if(upperBound==0){
              return 0;
          }
      }
  }
  //Find the upper bound by multiplying the lower bound by 2000 until it is greater than y
  while(upperBound==-1){
      trySolution = Math.round(lowerBound*2000);
      if(h(trySolution)>y){
      
          upperBound=trySolution;
      } else {
      
          lowerBound=trySolution;
      }
  }
  var finalSolution=-1;
  var solutionFound =false;
  
  //While the solution has not been found divide the difference upper bound and lower bound by 2 to find a
  //new try solution pivoting in this way leads to a time complexity of O(log2(n))
  while(!solutionFound) {
      var difference=upperBound-lowerBound;
      var newSolution=lowerBound+difference/2;
      var value=h(Math.round(newSolution));
      if(value==y){
  
          solutionFound=true;
          finalSolution= Math.round(newSolution);
      } else if(value>y){
  
          upperBound=Math.round(newSolution);
      } else {
  
          lowerBound=Math.round(newSolution);
      }
  }
  return finalSolution;
}  