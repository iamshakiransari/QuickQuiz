package qq.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SKR
 */
@WebServlet(name = "QuizSelector", urlPatterns = {"/QuizSelector"})
public class QuizSelector extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;
   private final String landingPage = "QuizSelector.jsp";

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      req.setAttribute("reqQuizDetails", qq.utils.FetchQuizDetailsInList.getQuizDetails());
      req.getRequestDispatcher(this.landingPage).forward(req, res);
   }
}
