package qq.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qq.doas.AllResults;
import qq.utils.Ajaxer;
import qq.utils.DB;

/**
 *
 * @author SKR
 */
@WebServlet(name = "ResultsAll", urlPatterns = {"/ResultsAll"})
public class ResultsAll extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;

   private final String landingPage = "ResultsAll.jsp";
   private final String objectName = "reqResultsAll";
   private final String MSG = "reqMSG";

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      req.setAttribute("quizDetailList", qq.utils.FetchQuizDetailsInList.getQuizDetails());
      req.getRequestDispatcher(this.landingPage).forward(req, res);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      int quizNo = 0;
      try {
         quizNo = Integer.parseInt(req.getParameter("quizNo"));
      } catch (Exception e) {
         quizNo = 0;
      }

      if (quizNo == 0) {
         req.setAttribute(this.MSG, "Requested quiz result not found!");
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      java.sql.Connection con = DB.getDataBaseConnection();
      if (con == null) {
         req.setAttribute(this.MSG, Ajaxer.CONECTION_FAILED);
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;
      AllResults ar = null;

      try {
         con.setAutoCommit(false);
         String user = req.getParameter("user");

         pst = con.prepareStatement("SELECT * FROM `results` WHERE `user`=?");
         pst.setString(1, user);

         System.out.println(pst);

         rs = pst.executeQuery();
         boolean empty = true;
         while (rs.next()) {
            System.out.println("rs.getInt(\"quiz_no\"): "+ rs.getInt("quiz_no"));
            System.out.println("quizNo: "+ quizNo);
            if (rs.getInt("quiz_no") == quizNo) {
               empty = false;
               ar = new AllResults(rs.getInt("result_id"), quizNo, rs.getInt("correct"), rs.getInt("incorrect"), user);
            }
         }

         if (empty) {
            req.setAttribute(this.MSG, "No result found, you might never took this quiz..");
         } else {
            req.setAttribute(this.objectName, ar);
         }

      } catch (Exception yh) {
         req.setAttribute(this.MSG, Ajaxer.showException(yh));
      } finally {
         DB.disConnectDB(con, null, pst);
      }

      req.setAttribute("quizDetailList", qq.utils.FetchQuizDetailsInList.getQuizDetails());
      req.getRequestDispatcher(this.landingPage).forward(req, res);
   }
}
