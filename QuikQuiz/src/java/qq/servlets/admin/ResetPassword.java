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
@WebServlet(name = "ResetPassword", urlPatterns = {"/ResetPassword"})
public class ResetPassword extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;

   private String createTableRow(String username) {
      return "\n"
              + "<tr id='idTableRowReset" + username + "'>"
              + "<td style='width:70%'><input type='text' value='" + username + "' name='username' class='form-control disabled' disabled='true'></td>"
              + "<td style='width:25%'><button type='button' onclick=\"resetThisUser(this, '" + username + "');\" class='btn btn-danger' style='width:100%'><i class='fa fa-lg fa-chain'></i> Reset</button></td>"
              + "</tr>";
   }

   private String getUserData(java.sql.Connection con) {

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      String rd = "";
      boolean empty = true;
      try {
         String sql = "SELECT `username` FROM `user_details`;";

         pst = con.prepareStatement(sql);
         rs = pst.executeQuery();

         while (rs.next()) {
            String username = rs.getString("username");

            if (!username.equals("admin")) {
               empty = false;
               rd += this.createTableRow(username);
            }
         }

      } catch (Exception yh) {
         rd = "";
      } finally {
         DB.disConnectDB(con, rs, pst);
      }

      if (empty) {
         rd = "<h5 class='text-danger'><i class='fa fa-lg fa-exclamation-circle'></i> No user found</h5>";
      } else {
         rd = ""
                 + "<table style='width:100%'>"
                 + rd
                 + "</table>";
      }

      return rd;
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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

      String rd = this.getUserData(con);
      DB.closeCon(con);
      Ajaxer.sendHtmlOutput(res, rd);

   }

   private String resetThisUser(java.sql.Connection con, HttpServletRequest req) {

      String username = req.getParameter("username");
      if (username == null || username.isEmpty()) {
         return "username either null or empty!";
      }

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      String rd = "";
      try {
         con.setAutoCommit(false);

         pst = con.prepareStatement("DELETE FROM `user_details` WHERE `username` = ?");
         pst.setString(1, username);

         int delete = pst.executeUpdate();

         if (1 == delete) {
            con.commit();
            rd = Ajaxer.DONE;
         } else {
            rd = Ajaxer.showRowCountError(delete);
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

      String rd = this.resetThisUser(con, req);
      DB.closeCon(con);
      Ajaxer.sendHtmlOutput(res, rd);

   }
}
