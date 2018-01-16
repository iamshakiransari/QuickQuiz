package qq.utils;

/**
 *
 * @author SKR
 */
public class Tools {

   public static boolean isUsernameExist(String userLogin) {

      java.sql.ResultSet rs = null;
      java.sql.Connection con = DB.getDataBaseConnection();
      java.sql.PreparedStatement pst = null;
      boolean rd = false;

      try {
         pst = con.prepareCall("SELECT `username` from `students` where `username` = ?");
         pst.setString(1, userLogin);

         rs = pst.executeQuery();
         rd = rs.next();
      } catch (Exception yh) {
         rd = false;
      } finally {
         DB.disConnectDB(con, rs, pst);
      }
      return rd;
   }

   public static boolean isEmailExist(String email) {

      java.sql.ResultSet rs = null;
      java.sql.Connection con = DB.getDataBaseConnection();
      java.sql.PreparedStatement pst = null;
      boolean rd = false;

      try {
         pst = con.prepareCall("SELECT `email` from `students` where `email` = ?");
         pst.setString(1, email);

         rs = pst.executeQuery();
         rd = rs.next();
      } catch (Exception yh) {
         rd = false;
      } finally {
         DB.disConnectDB(con, rs, pst);
      }
      return rd;
   }

   public static boolean paramNotOkay(String param) {
      return param == null || param.isEmpty();
   }

   public static boolean isValidRequest(javax.servlet.http.HttpServletRequest request) throws java.io.IOException {

      //String referer = request.getHeader("referer").toLowerCase();
      //System.out.println("```````````````referer: " + referer);
      return true;
   }

   public static String getUUID() {
      return java.util.UUID.randomUUID().toString();
   }

   public static String getSmallUUID() {
      return getUUID().substring(0, 5);
   }

   public static String sqlDateForJqueryDatePicker(java.sql.Date sqlDate) {
      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM dd, yyyy");
      return sdf.format(sqlDate);
   }

   public static String intoHtml(String str) {

      if (str == null || str.isEmpty()) {
         return str;
      }

      str = str.trim();
      StringBuilder sb = new StringBuilder("");

      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (i != str.length() && ch == ' ' && str.charAt(i + 1) == ' ') {
            sb.append("&nbsp;&nbsp;");
         } else if (ch == '\t') {
            sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
         } else if (ch == '\n') {
            sb.append("<br/>");
         } else {
            sb.append("" + ch);
         }
      }
      return sb.toString();
   }

}
