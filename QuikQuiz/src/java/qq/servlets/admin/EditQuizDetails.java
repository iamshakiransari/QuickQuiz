package qq.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qq.doas.Question;
import qq.utils.Ajaxer;
import qq.utils.DB;
import qq.utils.Tools;

/**
 *
 * @author SKR
 */
@WebServlet(name = "EditQuizDetails", urlPatterns = {"/admin/EditQuizDetails"})
public class EditQuizDetails extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;
   private final String landingPage = "editQuizDetails.jsp";
   private final String quizDetailObject = "reqQuizDetailObj";
   private final String questionList = "reqQuestionsList";

   private final String MSG_Q = "questionErrorMSG";
   private final String MSG_D = "detailErrorMSG";

   private void getQuestions(java.sql.Connection con, HttpServletRequest req, int quizNo) {

      boolean empty = true;

      try {

         java.util.ArrayList<Question> list = new java.util.ArrayList<>();

         java.sql.ResultSet rs = con.prepareStatement("SELECT * FROM `questions` WHERE `quiz_no` = " + quizNo).executeQuery();

         while (rs.next()) {
            empty = false;

            Question q = new Question();
            q.setAnwser(1);
            q.setQuestion(rs.getString("question"));
            q.setOpt1(rs.getString("opt1"));
            q.setOpt2(rs.getString("opt2"));
            q.setOpt3(rs.getString("opt3"));
            q.setOpt4(rs.getString("opt4"));
            //q.randOptions();
            q.setQuestionID(rs.getInt("question_no"));

            list.add(q);
         }

         req.setAttribute(this.questionList, list);

      } catch (Exception yh) {
         req.setAttribute(this.MSG_Q, yh.toString());
      }

      if (empty) {
         req.setAttribute(this.MSG_Q, "No question found for the given quiz.");
      }
   }

   private void getQuizDetail(java.sql.Connection con, HttpServletRequest req, int quizNo) {

      boolean empty = true;
      try {

         String sql = "SELECT * FROM `quiz_list` WHERE `deleted` = 0 AND `quiz_no` = " + quizNo;
         java.sql.ResultSet rs = con.prepareStatement(sql).executeQuery();

         if (rs.next()) {
            empty = false;

            qq.doas.Quiz quiz = null;
            quiz = new qq.doas.Quiz(rs.getString("title"), rs.getInt("quiz_no"), rs.getInt("level"), rs.getInt("attempts"), rs.getDate("last_date"));

            req.setAttribute(this.quizDetailObject, quiz);
         }

      } catch (Exception yh) {
         req.setAttribute(this.MSG_D, yh.toString());
      }

      if (empty) {
         req.setAttribute(this.MSG_D, "Requested quiz not found!");
      }

   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

      int quizNo = 0;
      try {
         quizNo = Integer.parseInt(req.getParameter("quizNo"));
      } catch (Exception e) {
         quizNo = 0;
      }

      if (quizNo == 0) {
         req.setAttribute("reqMSG", "Requested quiz result not found!");
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      java.sql.Connection con = DB.getDataBaseConnection();
      if (con == null) {
         req.setAttribute("reqMSG", Ajaxer.CONECTION_FAILED);
         req.getRequestDispatcher(this.landingPage).forward(req, res);
         return;
      }

      //-----
      this.getQuestions(con, req, quizNo);
      this.getQuizDetail(con, req, quizNo);

      req.getRequestDispatcher(this.landingPage).forward(req, res);
   }
}
