/*******************************************************************************
 /*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package DAO;
import java.util.*;
import java.sql.*;
import java.io.*;
import userModel.*;


public class StudentDAO {

	private Connection myConn;
	
	public StudentDAO() throws Exception {
		
		// get DB properties
		Properties props = new Properties();
		props.load(new FileInputStream("userDB.xml"));
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("url");
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);

		System.out.println("DB connection successful to: " + dburl);
	}
	
	
	public List<Student> getAllStudents() throws Exception {
		List<Student> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from students");
			
			while (myRs.next()) {
				Student tableStudent = convertRowToStudent(myRs);
				list.add(tableStudent);
			}
			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Student> searchStudents(String Firstname) throws Exception {
		
		List<Student> list = new ArrayList<>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			Firstname += "%";
			myStmt = myConn.prepareStatement("select * from students where lastname like xxxxxx"); // Sql request credentials 
			myStmt.setString(1, Firstname);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Student tableStudent = convertRowToStudent(myRs);
				list.add(tableStudent);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	public void addStudent(Student aStudent) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into student"
					+ " (login, studentid, firsname, lastname, password)"
					+ " values (?, ?, ?, ?,?)");
			
			// set params
			myStmt.setString(1, aStudent.getLogin());
			myStmt.setInt(2, aStudent.getIDstudent());
			myStmt.setString(3, aStudent.getFirstname());
			myStmt.setString(4, aStudent.getLastname());
			myStmt.setString(5, aStudent.getPassword());
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close (myStmt, null);
		}
		
	
	}
	public void deleteStudent(int idstudent) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from employees where id=?");
			
			// set param
			myStmt.setInt(2, idstudent);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt, null);
		}
	}
	


	private Student convertRowToStudent(ResultSet myRs) throws SQLException {
		
		String Login = myRs.getString("login");
		int studentId = myRs.getInt("studentid");
		String Firstname = myRs.getString("firstname");
		String Lastname = myRs.getString("lastname");
		String Password = myRs.getString("password");
	
		
		Student tableStudent = new Student(Login, studentId, Firstname, Lastname, Password);
		return tableStudent;
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		StudentDAO dao = new StudentDAO();
	//	System.out.println(dao.searchStudents("Nicolas")); exemple
	//	System.out.println(dao.getAllStudents());
	}


}