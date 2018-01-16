package qq.doas;

/**
 *
 * @author SKR
 */
public class Question {

   private String question, opt1, opt2, opt3, opt4;
   private int questionID, anwser;

   public Question() {
   }

   public Question(int questionID, String question, String opt1, String opt2, String opt3, String opt4, int anwser) {
      this.questionID = questionID;
      this.question = question;
      this.opt1 = opt1;
      this.opt2 = opt2;
      this.opt3 = opt3;
      this.opt4 = opt4;
      this.anwser = anwser;
   }

   public int getQuestionID() {
      return questionID;
   }

   public void setQuestionID(int questionID) {
      this.questionID = questionID;
   }

   public String getQuestion() {
      return question;
   }

   public void setQuestion(String question) {
      this.question = question;
   }

   public String getOpt1() {
      return opt1;
   }

   public void setOpt1(String opt1) {
      this.opt1 = opt1;
   }

   public String getOpt2() {
      return opt2;
   }

   public void setOpt2(String opt2) {
      this.opt2 = opt2;
   }

   public String getOpt3() {
      return opt3;
   }

   public void setOpt3(String opt3) {
      this.opt3 = opt3;
   }

   public String getOpt4() {
      return opt4;
   }

   public void setOpt4(String opt4) {
      this.opt4 = opt4;
   }

   public int getAnwser() {
      return anwser;
   }

   public void setAnwser(int anwser) {
      this.anwser = anwser;
   }

   public void randOptions() {

      java.util.Random rand = new java.util.Random();
      String ans = this.getOpt1();

      String[] opts = {this.getOpt1(), this.getOpt2(), this.getOpt3(), this.getOpt4()};

      String temp = null;
      for (int i = 0; i < opts.length; i++) {
         int position = rand.nextInt(opts.length - i) + i;
         temp = opts[i];
         opts[i] = opts[position];
         opts[position] = temp;
      }

      this.setOpt1(opts[0]);
      this.setOpt2(opts[1]);
      this.setOpt3(opts[2]);
      this.setOpt4(opts[3]);

      for (int i = 0; i < opts.length; i++) {
         if (opts[i].equals(ans)) {
            this.setAnwser(i + 1);
            break;
         }
      }
   }
}
