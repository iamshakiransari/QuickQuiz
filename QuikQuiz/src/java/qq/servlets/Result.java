package qq.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qq.doas.Quiz;
import qq.doas.ResultDOA;
import qq.utils.Ajaxer;
import qq.utils.DB;

/**
 *
 * @author SKR
 */
@WebServlet(name = "Result", urlPatterns = {"/Result"})
public class Result extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;

   private final String landingPage = "Result.jsp";
   private final String objectName = "reqResult";
   private final String status = "status";
   private final String MSG = "reqMSG";

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

      //Ajaxer.ajaxDelay(3000L);
      int quizNo = 0;
      try {
         quizNo = Integer.parseInt(req.getParameter("quizNo"));
      } catch (Exception e) {
         quizNo = 0;
      }

      String uuid = "" + req.getParameter("UUID");

      if (quizNo == 0 || (!uuid.equals(new Quiz().getQuizUUIDByQuizID(quizNo)))) {
         req.setAttribute(this.status, false);
         req.setAttribute(this.MSG, "Requested quiz result not found!");
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      int totalQuestions = 0;
      try {
         totalQuestions = Integer.parseInt(req.getParameter("totalQuestions"));
      } catch (Exception e) {
         totalQuestions = 0;
      }

      if (totalQuestions < 1) {
         req.setAttribute(this.status, false);
         req.setAttribute(this.MSG, "No question found");
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      java.sql.Connection con = DB.getDataBaseConnection();
      if (con == null) {
         req.setAttribute(this.status, false);
         req.setAttribute(this.MSG, Ajaxer.CONECTION_FAILED);
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      int corrects = 0, incorrects = 0;
      java.util.ArrayList<ResultDOA> resultList = new java.util.ArrayList<>();
      for (int i = 1; i <= totalQuestions; i++) {
         String userAnswer = "" + req.getParameter("que" + i);
         String systemAnswer = "" + req.getParameter("qa_us" + i);

         if (userAnswer.equals(systemAnswer)) {
            resultList.add(new ResultDOA(i, true));
            corrects++;
         } else {
            resultList.add(new ResultDOA(i, false));
            incorrects++;
         }
      }

      java.sql.PreparedStatement pst = null;
      try {

         con.setAutoCommit(false);
         pst = con.prepareStatement("INSERT INTO `results` (`quiz_no`, `user`, `correct`, `incorrect`) VALUES (?,?,?,?);");
         pst.setInt(1, quizNo);
         pst.setString(2, req.getParameter("user"));
         pst.setInt(3, corrects);
         pst.setInt(4, incorrects);

         int insert = pst.executeUpdate();

         if (insert == 1) {
            con.commit();
            req.setAttribute(this.objectName, resultList);
            req.setAttribute(this.status, true);
            req.setAttribute("totalCorrects", corrects);
            req.setAttribute("totalIncorrects", incorrects);

            float per = ((float) corrects / (float) totalQuestions * 100f);
            req.setAttribute("scorePer", per + " %");
         } else {
            resultList.clear();
            req.setAttribute(this.status, false);
            req.setAttribute(this.MSG, Ajaxer.showRowCountError(insert));
         }

      } catch (Exception yh) {
         resultList.clear();
         req.setAttribute(this.status, false);
         req.setAttribute(this.MSG, Ajaxer.showException(yh));
      } finally {
         DB.disConnectDB(con, null, pst);
      }

      req.getRequestDispatcher(this.landingPage).forward(req, res);
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      req.getRequestDispatcher("/ResultsAll").forward(req, res);
   }

}
