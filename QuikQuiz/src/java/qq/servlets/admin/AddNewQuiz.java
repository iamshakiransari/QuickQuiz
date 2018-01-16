
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
@WebServlet(name="AddNewQuiz", urlPatterns={"/AddNewQuiz"})
public class AddNewQuiz extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;

   private String addNewQuiz(java.sql.Connection con, HttpServletRequest req) {

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      String rd = "";
      try {
         con.setAutoCommit(false);

         pst = con.prepareStatement("INSERT INTO `quiz_list` (`title`, `level`, `attempts`, `last_date`) VALUES (?,?,?,?);");

         pst.setString(1, req.getParameter("quizTitle"));
         pst.setInt(2, Integer.parseInt(req.getParameter("level")));
         pst.setInt(3, Integer.parseInt(req.getParameter("attempts")));
         pst.setDate(4, new java.sql.Date(new java.util.Date(req.getParameter("lastDate")).getTime()));

         System.out.println(">> >> >> PreparedStatement: "+pst);

         int insert = pst.executeUpdate();
         if(insert == 1) {
            con.commit();
            rd = Ajaxer.DONE;
         } else {
            rd = Ajaxer.showRowCountError(insert);
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

      String rd = this.addNewQuiz(con, req);
      DB.closeCon(con);
      Ajaxer.sendHtmlOutput(res, rd);

   }
}