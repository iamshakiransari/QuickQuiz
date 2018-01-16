package qq.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author SKR
 */
public class DB {

   private DB() {
   }

   public static Connection getDataBaseConnection() {
      Connection con = getServerHost();
      if (con != null) {
         System.err.println("------------ Connected with server");
         return con;
      }
      System.err.println("------------ Connected with localhost");
      return getLocalHost();
   }

   /**
    * Database: skr User: adminEb1qudX Password: TVeuErl-gpFV IP: 127.7.12.130
    *
    * @return
    */
   private static Connection getServerHost() {
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://127.7.12.130:3306/qq";

      //String user = "adminEb1qudX";
      //String password = "TVeuErl-gpFV";
      String user = "root";
      String password = "";

      try {
         Class.forName(driver);
         return DriverManager.getConnection(url, user, password);
      } catch (Exception e) {
         //System.err.println(e);
         return null;
      }
   }

   private static Connection getLocalHost() {
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://localhost/skr";
      String user = "root";
      String password = "";

      try {
         Class.forName(driver);
         return DriverManager.getConnection(url, user, password);
      } catch (Exception e) {
         //System.err.println(e);
         return null;
      }
   }

   public static void closeCon(Connection con) {
      try {
         con.close();
      } catch (Exception e) {
      }
   }

   public static void closeRS(ResultSet rs) {
      try {
         rs.close();
      } catch (Exception e) {
      }
   }

   public static void closeST(Statement st) {
      try {
         st.close();
      } catch (Exception e) {
      }
   }

   public static void disConnectDB(Connection con) {
      closeCon(con);
   }

   public static void disConnectDB(Connection con, ResultSet rs) {
      closeCon(con);
      closeRS(rs);
   }

   public static void disConnectDB(Connection con, Statement st) {
      closeCon(con);
      closeST(st);
   }

   public static void disConnectDB(ResultSet rs, Statement st) {
      closeRS(rs);
      closeST(st);
   }

   public static void disConnectDB(ResultSet rs, PreparedStatement pst) {
      closeRS(rs);
      closeST(pst);
   }

   public static void disConnectDB(Connection con, ResultSet rs, Statement st) {
      closeCon(con);
      closeRS(rs);
      closeST(st);
   }

   public static void disConnectDB(Connection con, ResultSet rs, PreparedStatement pst) {
      closeCon(con);
      closeRS(rs);
      closeST(pst);
   }
}

//----------------------------------------------------------------------------------------------------------------------
class DbHandMain {

   static public void main(String xSKR[]) {
      Connection con = DB.getDataBaseConnection();
      if (con != null) {
         System.out.println("Connected: " + con);
         DB.disConnectDB(con);
      } else {
         System.out.println("con == null");
      }
   }
}
