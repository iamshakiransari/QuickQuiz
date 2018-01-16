package qq.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qq.doas.AllResults;
import qq.utils.Ajaxer;
import qq.utils.DB;
import qq.utils.Tools;

/**
 *
 * @author SKR
 */
@WebServlet(name = "ResetQuizAttempt", urlPatterns = {"/admin/ResetQuizAttempt"})
public class ResetQuizAttempt extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;
   private final String landingPage = "resetQuizAttempt.jsp";

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      req.setAttribute("quizDetailList", qq.utils.FetchQuizDetailsInList.getQuizDetails());
      req.getRequestDispatcher("resetQuizAttempt.jsp").forward(req, res);
   }

   //-----------------------------------------------------------------------------------------------------------------------------
   private String resetUserAttempt(java.sql.Connection con, HttpServletRequest req) {

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      String rd = "";
      try {
         con.setAutoCommit(false);

         pst = con.prepareStatement("DELETE FROM `results` WHERE `quiz_no`=? AND `user`=?");

         pst.setInt(1, Integer.parseInt(req.getParameter("quizNo")));
         pst.setString(2, req.getParameter("user"));

         int delete = pst.executeUpdate();

         if (1 == delete) {
            con.commit();
            rd = Ajaxer.DONE;
         } else {
            rd = "Unable to delete!<br>Please check username or selected quiz name again.!";
         }

      } catch (Exception yh) {
         rd = Ajaxer.showException(yh);
      } finally {
         DB.disConnectDB(con, rs, pst);
      }
      return rd;
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      Ajaxer.ajaxDelay();

      if (!Tools.isValidRequest(req)) {
         Ajaxer.sendHtmlOutput(res, Ajaxer.UN_AUTHORIZED_ACCESS);
         return;
      }

      java.sql.Connection con = DB.getDataBaseConnection();
      if (con == null) {
         Ajaxer.sendHtmlOutput(res, Ajaxer.CONECTION_FAILED);
         return;
      }

      String rd = this.resetUserAttempt(con, req);
      DB.closeCon(con);
      Ajaxer.sendHtmlOutput(res, rd);

   }

}
