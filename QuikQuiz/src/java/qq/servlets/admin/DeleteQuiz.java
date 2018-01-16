package qq.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qq.utils.Ajaxer;
import qq.utils.DB;
import qq.utils.Tools;

/**
 *
 * @author SKR
 */
@WebServlet(name = "DeleteQuiz", urlPatterns = {"/admin/DeleteQuiz"})
public class DeleteQuiz extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;

   private String deleteQuiz(java.sql.Connection con, HttpServletRequest req) {

      String rd = "";
      java.sql.ResultSet rs = null;
      java.sql.PreparedStatement pst = null;

      try {
         con.setAutoCommit(false);

         pst = con.prepareStatement("UPDATE `quiz_list` SET `deleted`= 1 WHERE `quiz_no` = ?");

         pst.setInt(1, Integer.parseInt(req.getParameter("quizNo")));

         int update = pst.executeUpdate();

         if (1 == update) {
            con.commit();
            rd = Ajaxer.DONE;
         } else {
            rd = Ajaxer.showRowCountError(update);
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

      String rd = this.deleteQuiz(con, req);
      //String rd = "Hello from UpdateQuizDetails servlet..";
      DB.closeCon(con);
      Ajaxer.sendHtmlOutput(res, rd);
   }
}
