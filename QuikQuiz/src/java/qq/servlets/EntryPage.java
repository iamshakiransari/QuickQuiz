package qq.servlets;

import qq.utils.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SKR
 */
@WebServlet(name = "EntryPage", urlPatterns = {"/EntryPage"})
public class EntryPage extends javax.servlet.http.HttpServlet {

   private boolean isUserAvailable(String username) {

      java.sql.Connection con = DB.getDataBaseConnection();
      if (con == null) {
         //-- means available
         return true;
      }

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      boolean available = true;

      try {

         String sql = "SELECT `user_id` FROM `user_details` WHERE `username` = ?";

         pst = con.prepareStatement(sql);
         pst.setString(1, username);

         rs = pst.executeQuery();

         if (rs.next()) {
            available = true;
         } else {
            available = false;
         }

         //con.commit();
      } catch (java.sql.SQLException sqlException) {
         available = true;
      } finally {
         DB.disConnectDB(con, rs, pst);
      }

      return available;
   }

   private String doLogin(java.sql.Connection con, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

      String rd = Ajaxer.INITIAL_STATE;

      String username = req.getParameter("username");
      String password = req.getParameter("password");

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      try {
         String sql = "SELECT * FROM `user_details` WHERE `username` = ? AND `password` = ?";

         pst = con.prepareStatement(sql);
         pst.setString(1, username);
         pst.setString(2, password);

         rs = pst.executeQuery();

         if (rs.next()) {

            HttpSession ses = req.getSession(true);
            ses.setAttribute("username", username);
            ses.setAttribute("password", password);
            ses.setMaxInactiveInterval(0); // zero for never invalidation

            rd = Ajaxer.DONE;
         } else {
            rd = "Username or password or both incorrect !!!";
         }

      } catch (Exception yh) {
         rd = Ajaxer.EXCEPTION + ": " + yh.getMessage();
      } finally {
         DB.disConnectDB(con, rs, pst);
      }
      return rd;
   }

   private String doRegistration(java.sql.Connection con, HttpServletRequest req) throws ServletException, IOException {

      String username = req.getParameter("username");
      String password = req.getParameter("password");

      if (this.isUserAvailable(username)) {
         return "Username \""+username+"\" already registered.";
      }

      String rd = Ajaxer.INITIAL_STATE;

      java.sql.PreparedStatement pst = null;

      try {
         con.setAutoCommit(false);

         String sql = "INSERT INTO `user_details` (`username`, `password`) VALUES (?,?)";
         pst = con.prepareStatement(sql);

         pst.setString(1, username);
         pst.setString(2, password);

         int insert = pst.executeUpdate();
         if (1 == insert) {
            rd = Ajaxer.DONE;
            con.commit();
         } else {
            rd = Ajaxer.showRowCountError(insert);
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

      String rd = Ajaxer.INITIAL_STATE, entryType = req.getParameter("entryType");

      if (entryType.equalsIgnoreCase("registration")) {
         rd = this.doRegistration(con, req);
      } else if (entryType.equalsIgnoreCase("login")) {
         rd = this.doLogin(con, req, res);
      } else {
         rd = "Invalid method: " + entryType;
      }

      DB.closeCon(con);

      Ajaxer.sendHtmlOutput(res, rd);
   }
}
