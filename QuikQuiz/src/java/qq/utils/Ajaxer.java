package qq.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SKR
 */
public class Ajaxer {

   public static final String CONECTION_FAILED = "Unable to connect with Database";
   public static final String DONE = "DONE";
   public static final String INITIAL_STATE = "starting line of ajax";
   public static final String INVALID_PROTOCOL = "Invalid Protocol: [221]";
   public static final String INVALID_METHODE = "Invalid methode: [x3ee4FF]";
   public static final String UN_AUTHORIZED_ACCESS = "Invalid Server";
   public static final String EXCEPTION = "Exception";

   public static void ajaxDelay(long mili) {
      try {
         Thread.sleep(mili);
      } catch (InterruptedException e) {
      }
   }

   public static void ajaxDelay() {
      Ajaxer.ajaxDelay(1000);
   }

   //--------
   public static String messageOps(String msg) {
      return "<h3 class='margin-no text-center'>Ooppss!</h3><hr><h4 class='margin-no'>" + msg + "</h4>";
   }

   public static String messageYeah(String msg) {
      return "<h3 class='margin-no text-center'>Yeah!</h3><hr><h4 class='margin-no'>" + msg + "</h4>";
   }
   //--------

   public static void sendJsonOutput(HttpServletResponse res, com.google.gson.JsonObject jsonObject) throws IOException {
      res.setContentType("application/json");
      res.setCharacterEncoding("UTF-8");
      java.io.PrintWriter out = res.getWriter();
      out.write(new com.google.gson.Gson().toJson(jsonObject));
      out.flush();
      out.close();
   }

   public static void sendJsonOutput(HttpServletResponse res, com.google.gson.JsonArray jsonArray) throws IOException {
      res.setContentType("application/json");
      res.setCharacterEncoding("UTF-8");
      java.io.PrintWriter out = res.getWriter();
      out.write(new com.google.gson.Gson().toJson(jsonArray));
      out.flush();
      out.close();
   }

   public static void sendJsonOutput(HttpServletResponse res, java.util.Map map) throws IOException {
      res.setContentType("application/json");
      res.setCharacterEncoding("UTF-8");
      java.io.PrintWriter out = res.getWriter();
      out.write(new com.google.gson.Gson().toJson(map));
      out.flush();
      out.close();
   }

   public static void sendHtmlOutput(HttpServletResponse res, String rDATA) throws IOException {
      res.setContentType("text/html;charset=UTF-8");
      java.io.PrintWriter out = res.getWriter();
      out.print(rDATA);
      out.flush();
      out.close();
   }

   public static void sendXLSOutput(HttpServletResponse res, String fileName, String rDATA) throws IOException {
      res.setContentType("application/vnd.ms-excel");
      res.setHeader("Content-Disposition", "inline; filename=" + fileName + ".xls");

      java.io.PrintWriter out = res.getWriter();
      out.print(rDATA);
      out.flush();
      out.close();
   }

   public static void sendXLSxOutput(HttpServletResponse res, String fileName, String rDATA) throws IOException {
      //res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
      res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
      //res.setContentType("application/octet-stream;charset=UTF-8");
      res.setHeader("Content-Disposition", "inline; filename=" + fileName + ".xlsx");

      java.io.PrintWriter out = res.getWriter();
      out.print(rDATA);
      out.flush();
      out.close();
   }

   public static boolean isValidParameters(String... params) {
      for (String param : params) {
         if (!isValidParameter(param)) {
            return false;
         }
      }
      return true;
   }

   public static boolean isValidParameter(String param) {
      return (param != null && param.trim().length() > 0);
   }

   //----------------------------------------------------------------------------------------------------------------------
   public static String showException(Exception ex) {
      String msg = ex.getMessage();
      msg = (msg == null) ? "" : " [" + msg + "]";
      return "Exception Occurred" + msg;
   }

   public static String showSQLException(java.sql.SQLException sqlEx) {
      String state = sqlEx.getSQLState();
      state = (state == null) ? "" : " [" + state + "]";
      return "SQL Error" + state;
   }

   public static String showRowCountError(int rowCount) {
      return "executeUpdate() return: " + rowCount;
   }
}
