package qq.doas;

/**
 *
 * @author SKR
 */
public class ResultDOA {

   private int questionCountNo;
   private boolean correct;

   public ResultDOA(int questionCountNo, boolean correct) {
      this.questionCountNo = questionCountNo;
      this.correct = correct;
   }

   public int getQuestionCountNo() {
      return questionCountNo;
   }

   public void setQuestionCountNo(int questionCountNo) {
      this.questionCountNo = questionCountNo;
   }

   public boolean isCorrect() {
      return correct;
   }

   public void setCorrect(boolean correct) {
      this.correct = correct;
   }

}
