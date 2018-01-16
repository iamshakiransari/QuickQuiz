package qq.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qq.doas.AllResults;
import qq.utils.Ajaxer;
import qq.utils.DB;

/**
 *
 * @author SKR
 */
@WebServlet(name = "EditQuizSelector", urlPatterns = {"/admin/EditQuizSelector"})
public class EditQuizSelector extends javax.servlet.http.HttpServlet {

   private static final long serialVersionUID = 1L;
   private final String landingPage = "editQuizSelector.jsp";

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

      req.setAttribute("reqQuizDetails", qq.utils.FetchQuizDetailsInList.getQuizDetails());
      req.getRequestDispatcher(this.landingPage).forward(req, res);
   }
}
