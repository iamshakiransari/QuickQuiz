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
@WebServlet(name = "AddNewQuestion", urlPatterns = {"/AddNewQuestion"})
public class AddNewQuestion extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;

   private String addNewQuestion(java.sql.Connection con, HttpServletRequest req) {

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      String rd = "";
      try {
         con.setAutoCommit(false);

         pst = con.prepareStatement("INSERT INTO `questions` (`quiz_no`, `question`, `opt1`, `opt2`, `opt3`, `opt4`) VALUES (?, ?, ?,?,?,?);");

         pst.setInt(1, Integer.parseInt(req.getParameter("quizNo")));
         pst.setString(2, req.getParameter("question"));
         pst.setString(3, req.getParameter("opt1"));
         pst.setString(4, req.getParameter("opt2"));
         pst.setString(5, req.getParameter("opt3"));
         pst.setString(6, req.getParameter("opt4"));

         int intert = pst.executeUpdate();

         if (1 == intert) {
            con.commit();
            rd = Ajaxer.DONE;
         } else {
            rd = Ajaxer.showRowCountError(intert);
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

      String rd = this.addNewQuestion(con, req);
      DB.closeCon(con);
      Ajaxer.sendHtmlOutput(res, rd);

   }
}
