package qq.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qq.doas.Question;
import qq.doas.PaperList;
import qq.doas.Quiz;
import qq.utils.Ajaxer;
import qq.utils.DB;

/**
 *
 * @author SKR
 */
@WebServlet(name = "GetQuestionsIntoQuizPaper", urlPatterns = {"/GetQuestionsIntoQuizPaper"})
public class GetQuestionsIntoQuizPaper extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;
   private final String landingPage = "getQuestionsIntoQuizPaper.jsp";
   private final String objectName = "reqPaperList";

   private String checkQuizLastDate(java.sql.Connection con, int quizNo) {
      String rd = null;

      try {
         java.sql.PreparedStatement pst = null;
         java.sql.ResultSet rs = null;

         pst = con.prepareStatement("select `last_date` from `quiz_list` WHERE `quiz_no` = " + quizNo);
         rs = pst.executeQuery();

         if (rs.next()) {
            java.sql.Date sqlDate = rs.getDate("last_date");

            rd = (sqlDate.getTime() > new java.util.Date().getTime()) ? Ajaxer.DONE : "End date for requested quiz was: " + sqlDate.toString();

         } else {
            rd = "Requested quiz not found.";
         }

      } catch (Exception yh) {
         rd = "" + yh;
      }

      return rd;
   }

   private boolean checkAlreadyTaken(java.sql.Connection con, int quizNo) {
      try {
         java.sql.PreparedStatement pst = null;
         pst = con.prepareStatement("select `user` from `results` WHERE `quiz_no` = " + quizNo);
         return pst.executeQuery().next();

      } catch (Exception yh) {
         return false;
      }
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

      //Ajaxer.ajaxDelay(3000L);

      int quizNo = 0;
      try {
         quizNo = Integer.parseInt(req.getParameter("quizNo"));
      } catch (Exception e) {
         quizNo = 0;
      }

      String uuid = "" + req.getParameter("UUID");
      PaperList paperList = new PaperList();

      if (quizNo == 0 || (!uuid.equals(new Quiz().getQuizUUIDByQuizID(quizNo)))) {
         paperList.setErrorStatusMessage("Requested quiz not found!");

         req.setAttribute(this.objectName, paperList);
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      java.sql.Connection con = DB.getDataBaseConnection();
      if (con == null) {
         paperList.setErrorStatusMessage(Ajaxer.CONECTION_FAILED);
         req.setAttribute(this.objectName, paperList);
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }


      if (this.checkAlreadyTaken(con, quizNo)) {
         paperList.setErrorStatusMessage("You already took this quiz.");
         req.setAttribute(this.objectName, paperList);
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      String checkLasteDate = this.checkQuizLastDate(con, quizNo);
      if (!checkLasteDate.equals(Ajaxer.DONE)) {
         paperList.setErrorStatusMessage(checkLasteDate);
         req.setAttribute(this.objectName, paperList);
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      boolean empty = true;
      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      try {

         con.setAutoCommit(false);
         pst = con.prepareStatement("SELECT * FROM `questions` WHERE `quiz_no` = ?");
         pst.setInt(1, quizNo);

         rs = pst.executeQuery();

         while (rs.next()) {
            empty = false;
            Question paper = new Question(rs.getInt("question_no"), rs.getString("question"), rs.getString("opt1"), rs.getString("opt2"), rs.getString("opt3"), rs.getString("opt4"), 1);
            paper.randOptions();
            paperList.addIntoList(paper);
         }

      } catch (Exception yh) {
         paperList.setErrorStatusMessage("" + yh);
      } finally {
         DB.disConnectDB(con, rs, pst);
      }

      req.setAttribute(this.objectName, paperList);
      req.getRequestDispatcher(this.landingPage).forward(req, res);

   }
}
