package qq.doas;

import java.sql.Date;
import qq.utils.Tools;

/**
 *
 * @author SKR
 */
public class Quiz {

   private String quizName, quizUUID;
   private int quizID, level, attempts;
   private Date sqlDate;

   public Quiz() {
      this.quizName = this.quizUUID = null;
      this.quizID = this.level = this.attempts = 0;
      this.sqlDate = null;
   }

   public Quiz(String quizName, int quizID, int level, int attempts, Date sqlDate) {
      this.quizName = quizName;
      this.quizUUID = null;
      this.quizID = quizID;
      this.level = level;
      this.attempts = attempts;
      this.sqlDate = sqlDate;
   }

   public Quiz(int quizID, String quizName) {
      this.quizID = quizID;
      this.quizName = quizName;
   }

   public int getLevel() {
      return level;
   }

   public String getLevelByName() {
      int levels = this.getLevel();
      String[] QuizLevel = new String[]{"Low", "Medium", "High", "Very High"};
      return (levels > QuizLevel.length || levels < 1) ? QuizLevel[0] : QuizLevel[levels - 1];
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public int getAttempts() {
      return attempts;
   }

   public void setAttempts(int attempts) {
      this.attempts = attempts;
   }

   public Date getSqlDate() {
      return sqlDate;
   }

   public String getSqlDateFormatted () {
      return Tools.sqlDateForJqueryDatePicker(this.getSqlDate());
   }

   public void setSqlDate(Date sqlDate) {
      this.sqlDate = sqlDate;
   }

   public String getQuizName() {
      return this.quizName;
   }

   public void setQuizName(String quizName) {
      this.quizName = quizName;
   }

   public String getQuizUUID() {
      return this.getQuizUUIDByQuizID(this.getQuizID());
   }

   public String getQuizUUIDByQuizID(int quizID) {
      this.quizUUID = java.util.UUID.nameUUIDFromBytes(String.valueOf(quizID + 22).getBytes()).toString();
      return this.quizUUID;
   }

   public void setQuizUUID(String quizUUID) {
      this.quizUUID = quizUUID;
   }

   public int getQuizID() {
      return this.quizID;
   }

   public void setQuizID(int quizID) {
      this.quizID = quizID;
   }
}
