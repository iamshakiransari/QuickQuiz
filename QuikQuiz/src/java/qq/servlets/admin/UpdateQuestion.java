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
@WebServlet(name = "UpdateQuestion", urlPatterns = {"/admin/UpdateQuestion"})
public class UpdateQuestion extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;

   private String saveQuestion(java.sql.Connection con, HttpServletRequest req) {

      String rd = "";
      java.sql.ResultSet rs = null;
      java.sql.PreparedStatement pst = null;

      try {
         con.setAutoCommit(false);

         String sql = "UPDATE `questions` SET `question`=?, `opt1`=?, `opt2`=?, `opt3`=?, `opt4`=? WHERE `question_no` = ?";
         pst = con.prepareStatement(sql);

         pst.setString(1, req.getParameter("question"));
         pst.setString(2, req.getParameter("opt1"));
         pst.setString(3, req.getParameter("opt2"));
         pst.setString(4, req.getParameter("opt3"));
         pst.setString(5, req.getParameter("opt4"));
         pst.setInt(6, Integer.parseInt(req.getParameter("questionID")));

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

      String rd = this.saveQuestion(con, req);
      //String rd = "Hello from UpdateQuestion servlet..";
      DB.closeCon(con);
      Ajaxer.sendHtmlOutput(res, rd);
   }
}
