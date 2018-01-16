package qq.doas;

import java.util.ArrayList;

/**
 *
 * @author SKR
 */
public class PaperList {

   private ArrayList<Question> list;

   private boolean status;
   private String statusMessage;

   public PaperList() {
      this.list = new ArrayList<>();
      this.status = true;  // init with true always..
      this.statusMessage = "Initial state: 221";
   }

   public PaperList(ArrayList<Question> list) {
      this.list = list;
      this.status = false;
      this.statusMessage = "Initial state: 221";
   }

   public PaperList(ArrayList<Question> list, boolean status, String statusMessage) {
      this.list = list;
      this.status = status;
      this.statusMessage = statusMessage;
   }

   public ArrayList<Question> getList() {
      return list;
   }

   public void setList(ArrayList<Question> list) {
      this.list = list;
   }

   public void addIntoList(Question paper) {
      if (this.list == null || this.list.isEmpty()) {
         this.list = new java.util.ArrayList<>();
      }
      //this.setStatus(true);
      this.list.add(paper);
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public String getStatusMessage() {
      return statusMessage;
   }

   public void setStatusMessage(String statusMessage) {
      this.statusMessage = statusMessage;
   }

   //--
   public void setErrorStatusMessage(String statusMessage) {
      this.setStatus(false);

      if (this.list != null || !this.list.isEmpty()) {
         this.list = null;
      }

      this.statusMessage = statusMessage;
   }

   public int getQuestionCount() {
      return (this.getList() == null) ? 0 : this.getList().size();
   }

}
