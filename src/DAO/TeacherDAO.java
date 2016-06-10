package DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import userModel.*;

public class TeacherDAO  {

	private Connection myConn;
	
	public TeacherDAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("userDB.xml"));
		
		String user = props.getProperty("user");  // root 
		String password = props.getProperty("password"); // root 
		String dburl = props.getProperty("dburl"); // root 
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("UserDAO - DB connection successful to: " + dburl);
	}
	
	private Teacher convertRowToTeacher(ResultSet myRs) throws SQLException {
	
		String Login = myRs.getString("login");
		int IDteacher = myRs.getInt("teacherid");
		String Firstname = myRs.getString("firstname");
		String Lastname = myRs.getString("lastname");
		String Password = myRs.getString("password");
		boolean admin = myRs.getBoolean("is_admin");
		
		Teacher tableTeacher = new Teacher(Login, IDteacher, Firstname ,Lastname,Password);
		
		return tableTeacher;
	}
	
	public List<Teacher> getTeachers(boolean admin, int TeacherId) throws Exception {
		List<Teacher> list = new ArrayList<Teacher>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			
			String sql = null;
			
			if (admin) {
				// get all users
				sql = "select * from teacher order by last_name";
			}
			else {
				// only the current user
				sql = "select * from teacher where id=" + TeacherId + " order by last_name";
			}
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				Teacher tableTeacher = convertRowToTeacher(myRs);
				list.add(tableTeacher);
			}

			return list;		
		}
		finally {
			UtilsDAO.close(myStmt, myRs);
		}
	}	
	
	public void addTeacher(Teacher aTeacher) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into teacher"
					+ " (login, teacherid, firstname, lastname, password)"
					+ " values (?, ?, ?, ?, ?)");
			
			// set params
			myStmt.setString(1, aTeacher.getLogin());
			myStmt.setInt(2, aTeacher.getIDteacher());
			myStmt.setString(3, aTeacher.getFirstname());
			myStmt.setString(4, aTeacher.getLastname());
			myStmt.setString(5, aTeacher.getPassword());		
			
			// execute SQL
			myStmt.executeUpdate();				
		}
		finally {
			UtilsDAO.close(myStmt);
		}
		
	}
		
	
	public void updateTeacher(Teacher aTeacher) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update teacher"
					+ " set login =?, teacherid =?, firstname =?, lastname =?, password =?,"
					+ " where id=?");
			
			// set params
			myStmt.setString(1, aTeacher.getLogin());
			myStmt.setInt(2, aTeacher.getIDteacher());
			myStmt.setString(3, aTeacher.getFirstname());
			myStmt.setString(4, aTeacher.getLastname());
			myStmt.setString(5, aTeacher.getPassword());
			
			// execute SQL
			myStmt.executeUpdate();

		}
		finally {
			UtilsDAO.close(myStmt);
		}		
	}
	
	/**
	 * Return true if user's password is authenticated.
	 * 
	 * @param theUser
	 * @return
	 */
	public boolean authenticate(Teacher aTeacher) throws Exception {
		boolean result = false;
		
		String plainTextPassword = aTeacher.getPassword();
		
		
		return result;
	}

	
			

		
}
