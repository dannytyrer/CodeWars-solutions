/* Write a class called User that is used to calculate the amount that a user will progress through a ranking system similar to the one Codewars uses.
Link: http://www.codewars.com/kata/51fda2d95d6efda45e00004e

*/
//User class object: functionality defined in the instructions
public class User {

  //variable to hold the rank
  public int  rank;
  //variable to hold the progress
  public int  progress;
  //variable to hold if the users rank is -ve 
  private Boolean negativeRank;
  //variable to store if the maximum rank has been reached
  private Boolean reachedMaxRank;
  //defines the minimum progress value for any given rank
  private final int minimumRankProgress;
  //defines the maximum progress value for any give rank
  private final int maximumRankProgress;
  //defines the maximum rank a user can reach
  private final int maximumRank;
  //defines the minimum rank a user can reach
  private final int minimumRank;
  //defines the rank a new user has
  private final int startingRankValue;
  //defines the progress a new user has
  private final int startingProgressValue;
  //defines the amount of progress received when the user completes
  //a task of the same rank as the user
  private final int sameRankProgress;  
  //defines the amount of progress received when the user completes
  //a task one rank lower than the users rank
  private final int singleRankLowerProgress;
  //defines the amount of progress received when the user completes
  //a task more than one rank lower than the users rank
  private final int multipleRankLowerProgress; 
  
  /* Default constructor for a User
  */
  public User() {
  
    this.minimumRankProgress       = 0;
    this.maximumRankProgress       = 100;
    this.maximumRank               = 8;
    this.minimumRank               = -8;
    this.startingRankValue         = -8;
    this.startingProgressValue     = 0;
    this.sameRankProgress          = 3;
    this.singleRankLowerProgress   = 1;
    this.multipleRankLowerProgress = 0;
    this.rank         = this.startingRankValue;
    this.progress     = this.startingProgressValue;
    this.negativeRank = this.rank<0;
    this.reachedMaxRank = false;
  }
  
  /*
  incProgress function throws a runtime exception if the rank argument is not valid
  as per specification
  */
  public void  incProgress(int taskRank) throws RuntimeException {
  
      if(taskRank<this.minimumRank||taskRank>this.maximumRank||taskRank==0) {
      
         throw new RuntimeException("Incorrect value of task rank");
      }
      if (!this.reachedMaxRank) {
      
          int increment = CalculateProgressIncrease(taskRank);
          this.progress+=increment;
          if(adjustRank()) {
          
            System.out.println("Congrats you increased your rank");
          }
      }
  }
  
  /*
  Function to adjust the rank for the user after progress has been added
  */
  private Boolean adjustRank() {
  
      Boolean increasedRank = false;
      while(this.progress>=this.maximumRankProgress) {
      
          this.incrementRank();
          this.progress=this.reachedMaxRank? this.minimumRankProgress:this.progress-this.maximumRankProgress;
          increasedRank = true;
      }
      return increasedRank;
  }
  
  /*
  Function to progress the user to the next rank
  */
  private void incrementRank() {
  
      if (this.rank != this.maximumRank) {
        if (this.rank==-1){
        
           this.rank+=2;
        } else {
        
            this.rank++;
        }
      }
      this.negativeRank = this.rank<0;
      this.reachedMaxRank = this.rank==this.maximumRank;
  }
  
  /*
  Calculates the amount to increase the progress value by .
  */
  private int CalculateProgressIncrease(int taskRank) {
    
      int increment;
      int rankDiff = this.CalculateRankDiff(taskRank);
      if(rankDiff == 0) {
    
          increment = this.sameRankProgress;
      } else if(rankDiff==-1) {
    
          increment = this.singleRankLowerProgress;
      } else if(rankDiff<-1) {
    
          increment = this.multipleRankLowerProgress;
      } else {
    
          increment = CalculateHigherRankProgress(rankDiff);
      }
      return increment;
  }
  
  /*
  Calculate the progress whent he task value is greater than the users value
  */
  private int CalculateHigherRankProgress(int diff) {
  
      return 10*diff*diff;
  }
  
  /*
  Calculates the difference in rank between the task and the user 
  */
  private int CalculateRankDiff(int taskRank) {
      
      int rankDiff = taskRank-this.rank;
      if(taskRank>0&&negativeRank) {
      
          rankDiff--;
      } else if(taskRank<0&&!negativeRank) {
          
          rankDiff++;
      }
      return rankDiff;    
  }
}