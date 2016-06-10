/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;

import java.util.ArrayList;

import userModel.User;

/**
 * Description of Admin.
 * 
 * @author jcheng
 */

public final class Admin extends User {
	/**
	 * Description of the property IDadmin.
	 */
	
	private int IDadmin;
	
/**
 * an user is a admin
 * admin has his own IDadmin, and Login ,Firstname,Lastname and Paswoord as an user.
 * @param Login
 * @param IDadmin
 * @param Firstname
 * @param Lastname
 * @param Password
 */
	public Admin(String Login,int IDadmin, String Firstname,String Lastname, String Password) {
		super(Login, Firstname,Lastname, Password,"Administractor");
		this.IDadmin=IDadmin;
	}
	
	public final int IDadmin() {
		return this.IDadmin;	
	}
/**
 * add an admin
 * add a teacher
 * add a student
 */
	public Admin addAdmin(String NewLogin,int NewIDadmin, String Firstname, String Lastname, String Password){
		return new Admin(NewLogin,NewIDadmin,Firstname,Lastname,Password);
	}
	public Teacher addTeacher(String NewLogin,int NewIDadmin, String Firstname, String Lastname, String Password){
		return new Teacher(NewLogin,NewIDadmin,Firstname,Lastname,Password);
	}
	public Student addStudent(String NewLogin,int NewIDadmin, String Firstname, String Lastname, String Password){
		return new Student(NewLogin,NewIDadmin,Firstname,Lastname,Password);
	}
	
	public final String toString() {
		return "Admin :" + super.toString() +"|" + this.IDadmin;
	}

}
	

	
