package qq.servlets;

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
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePassword extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;

   private String doChangePassword(java.sql.Connection con, HttpServletRequest req) throws ServletException, IOException {

      String rd = Ajaxer.INITIAL_STATE;

      java.sql.PreparedStatement pst = null;

      try {
         con.setAutoCommit(false);

         String sql = "UPDATE `user_details` SET `password`=? WHERE `username` = ?";
         pst = con.prepareStatement(sql);

         pst.setString(1, req.getParameter("newPassword"));
         pst.setString(2, req.getParameter("username"));
         

         System.out.println("---------------- sql\n" + pst);

         int update = pst.executeUpdate();

         if (1 == update) {
            rd = Ajaxer.DONE;
            con.commit();
         } else {
            rd = Ajaxer.showRowCountError(update);
         }
      } catch (Exception yh) {
         rd = Ajaxer.EXCEPTION + ": " + yh.getMessage();
      } finally {
         DB.disConnectDB(con, null, pst);
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

      String rd = Ajaxer.INITIAL_STATE;

      rd = this.doChangePassword(con, req);

      DB.closeCon(con);

      Ajaxer.sendHtmlOutput(res, rd);
   }
}
