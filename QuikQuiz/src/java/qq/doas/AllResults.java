package qq.doas;

/**
 *
 * @author SKR
 */
public class AllResults {

   private int resultID, quizNo, corrects, incorrects;
   private String user;

   public AllResults() {

   }

   public AllResults(int resultID, int quizNo, int corrects, int incorrects, String user) {
      this.resultID = resultID;
      this.quizNo = quizNo;
      this.corrects = corrects;
      this.incorrects = incorrects;
      this.user = user;
   }

   public int getResultID() {
      return resultID;
   }

   public void setResultID(int resultID) {
      this.resultID = resultID;
   }

   public int getQuizNo() {
      return quizNo;
   }

   public void setQuizNo(int quizNo) {
      this.quizNo = quizNo;
   }

   public int getCorrects() {
      return corrects;
   }

   public void setCorrects(int corrects) {
      this.corrects = corrects;
   }

   public int getIncorrects() {
      return incorrects;
   }

   public void setIncorrects(int incorrects) {
      this.incorrects = incorrects;
   }

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   //--
   public float getPercentage() {
      return ((float) this.getCorrects() / (float) (this.getTotalQuestions())) * 100f;
   }
   public int getTotalQuestions() {
      return this.getCorrects() + this.getIncorrects();
   }

}
