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
@WebServlet(name = "Questions", urlPatterns = {"/admin/Questions"})
public class Questions extends javax.servlet.http.HttpServlet {

   private String createTableRow(int srNo, int questionNo, String question, String opt1, String opt2, String opt3, String opt4, String contextPath) {
      return "\n\n"
              + "<div class='card mb-1' id='idCardQuestion" + questionNo + "'>"
              + "<div class='card-header tog'><h5 class='m-0 p-0'><span class='text-danger'><i class='fa fa-question-circle'></i></span> Question " + srNo + "</h5></div>"
              + "<div class='card-body' style='display: none'>"
              + "<div class='row'>"
              + "<div class='col-12 mb-2'>"
              + "<input type='hidden' id='urlForQuestionQuiz" + questionNo + "' value='" + contextPath + "/QuestionQuiz'>"
              + "<label class='m-0 text-muted'>Question</label><textarea name='question' id='question" + questionNo + "' class='form-control'>" + question + "</textarea>"
              + "</div>"
              + "<div class='col-12 mb-2'>"
              + "<div class='row'>"
              + "<div class='col-6'><label class='m-0 text-muted'>Option 1 (Answer)</label>"
              + "<textarea name='opt1' id='opt1" + questionNo + "' class='form-control'>" + opt1 + "</textarea>"
              + "</div>"
              + "<div class='col-6'><label class='m-0 text-muted'>Option 2</label>"
              + "<textarea name='opt2' id='opt2" + questionNo + "' class='form-control'>" + opt2 + "</textarea>"
              + "</div></div></div>"
              + "<div class='col-12 mb-4'>"
              + "<div class='row'>"
              + "<div class='col-6'><label class='m-0 text-muted'>Option 3</label>"
              + "<textarea name='opt3' id='opt3" + questionNo + "' class='form-control'>" + opt3 + "</textarea>"
              + "</div>"
              + "<div class='col-6'><label class='m-0 text-muted'>Option 4</label>"
              + "<textarea name='opt4' id='opt4" + questionNo + "' class='form-control'>" + opt4 + "</textarea>"
              + "</div></div></div>"
              + "<div class='col-12 text-right'>"
              + "<button type='button' onclick='saveThisQuestion(this, " + questionNo + ")' class='btn btn-dark mr-2'><i class='fa fa-lg fa-folder'></i> Save to database</button>"
              + "<button type='button' onclick='deleteThisQuestion(this, " + questionNo + ")' class='btn btn-danger'><i class='fa fa-lg fa-trash'></i> Delete</button>"
              + "</div>"
              + "</div>"
              + "</div>"
              + "</div>";
   }

   private String getQuizQuestions(java.sql.Connection con, HttpServletRequest req) {

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      String rd = "";
      boolean empty = true;
      try {
         pst = con.prepareStatement("SELECT * FROM `questions` WHERE `quiz_no` = ?");
         pst.setInt(1, Integer.parseInt(req.getParameter("quizNo")));
         rs = pst.executeQuery();

         int srNo = 1;
         while (rs.next()) {
            empty = false;
            rd += this.createTableRow(srNo, rs.getInt("question_no"), rs.getString("question"), rs.getString("opt1"), rs.getString("opt2"), rs.getString("opt3"), rs.getString("opt4"), req.getContextPath());
            srNo++;
         }

      } catch (Exception yh) {
         rd = "";
      } finally {
         DB.disConnectDB(con, rs, pst);
      }

      if (empty) {
         rd = "<h5 class='p-3 bg-white rounded text-danger text-center'><i class='fa fa-exclamation-triangle'></i> No question found.</h5>";
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

      String rd = this.getQuizQuestions(con, req);
      DB.closeCon(con);
      Ajaxer.sendHtmlOutput(res, rd);

   }

   //-----------------------------------------------------------------------------------------------------------------------------

   private String saveThisQuestion(java.sql.Connection con, HttpServletRequest req) {

      java.sql.PreparedStatement pst = null;
      java.sql.ResultSet rs = null;

      String rd = "";
      try {
         con.setAutoCommit(false);

         pst = con.prepareStatement("UPDATE `questions` SET `question`=?, `opt1`=?, `opt2`=?,`opt3`=?, `opt4`=? WHERE `question_no` = ?");
         pst.setString(1, req.getParameter("question"));
         pst.setString(2, req.getParameter("opt1"));
         pst.setString(3, req.getParameter("opt2"));
         pst.setString(4, req.getParameter("opt3"));
         pst.setString(5, req.getParameter("opt4"));
         pst.setInt(6, Integer.parseInt(req.getParameter("questionNo")));

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

      String rd = this.saveThisQuestion(con, req);
      DB.closeCon(con);
      Ajaxer.sendHtmlOutput(res, rd);

   }

}
