package userModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  
  public void readDataBase() throws Exception {
  
	  try {
    	
      Class.forName("com.mysql.jdbc.Driver");  
      // 
     /** String url = "jdbc:mysql://localhost:3306/";-
      *  Statement myStmt =Myconn createStatement(); 
      *  String sq1 = "insert into administrators" 
      *  			+ " ("Login, IDadmin, Firstname, Lastname, Password"
      *  			+ " values ('Brown', '15421', 'David', 'BD');
      * 
      *  myStmt.exectureUpdate(sq1);
      *  
      *     connection myConn = DriverManager.getConnection(url, "root","root"); user and password are root by default on mysql **/
     
      connect = DriverManager
    	  .getConnection("jdbc:mysql://localhost/feedback?"
              + "user=sqluser&password=sqluserpw");  // Setup the connection with the DB     
      statement = connect.createStatement(); // Statements allow to issue SQL queries to the database
      resultSet = statement.executeQuery("select * from UserDB"); // Result set get the result of the SQL query
      writeResultSet(resultSet);

      preparedStatement = connect // PreparedStatements can use variables and are more efficient
          .prepareStatement("insert into  UserDB values (default, ?, ?, ?, ? , ?, ?)");
   
         
      preparedStatement.setString(1, "TestAdmin"); // Parameters start with 1
      preparedStatement.setString(2, "TestGroup");
      preparedStatement.setString(3, "TestStudent");
      preparedStatement.setString(5, "TestTeacher");
      preparedStatement.setString(6, "TestUser");
      preparedStatement.executeUpdate();

      preparedStatement = connect.prepareStatement("SELECT administrators, groups, students, teachers, users");
      resultSet = preparedStatement.executeQuery();
      
      writeResultSet(resultSet);

      // Remove again the insert comment
      preparedStatement = connect.prepareStatement("delete from UserDB where myuser= ? ; ");
      preparedStatement.setString(1, "Test");
      preparedStatement.executeUpdate();
      
      resultSet = statement
        .executeQuery("select * from UserDB");
      
      writeMetaData(resultSet);
      
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
	  
    // ResultSet is initially before the first data set
	  
    while (resultSet.next()) {
    	
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // * resultSet.getSTring(2);
    	
      String administrator = resultSet.getString("Administrators");
      String group = resultSet.getString("Groups");
      String student = resultSet.getString("Students");
      String teacher = resultSet.getString("Teachers");
      String user = resultSet.getString("Users");
      
  
      System.out.println("Les administrateurs: " + user);
      System.out.println("Les groupes: " + group);
      System.out.println("Les etudiants: " + student);
      System.out.println("Les professeurs: " + teacher);
      System.out.println("Les utilisateurs: " + user);
    }
  } 
 
  

  //  need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

} 