package qq.utils;

import qq.doas.Quiz;

/**
 *
 * @author SKR
 */
public class FetchQuizDetailsInList {

   public static java.util.ArrayList<Quiz> getQuizDetails() {

      java.sql.Connection con = DB.getDataBaseConnection();
      if (con == null) {
         return null;
      }

      boolean empty = true;
      java.util.ArrayList<Quiz> list = new java.util.ArrayList<>();

      try {
         con.setAutoCommit(false);

         java.sql.ResultSet rs = con.prepareStatement("SELECT * FROM `quiz_list` WHERE `deleted` = 0").executeQuery();

         while (rs.next()) {
            empty = false;
            list.add(new Quiz(rs.getString("title"), rs.getInt("quiz_no"), rs.getInt("level"), rs.getInt("attempts"), rs.getDate("last_date")));
         }

         if (empty) {
            list = null;
         }

      } catch (Exception yh) {
         list = null;
      } finally {
         DB.disConnectDB(con);
      }
      return list;
   }

}
