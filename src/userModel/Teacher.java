/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;
import userModel.User;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Teacher.
 * 
 * @author jcheng
 */
public final class Teacher extends User {
	/**
	 * Description of the property IDteacher.
	 */
	private int IDteacher;
	
	// Start of user code (user defined attributes for Teacher)
	
	// End of user code
	
	/**
	 * The constructor.
	 */
	public Teacher(String Login,int IDteacher, String Firstname, String Lastname, String Password) {
		// Start of user code constructor for Teacher)
		super(Login,Firstname,Lastname,Password,"Student");
		this.IDteacher = IDteacher;
		// End of user code
	}
	
	// Start of user code (user defined methods for Teacher)
	
	// End of user code
	/**
	 * Returns IDteacher.
	 * @return IDteacher 
	 */
	public final int getIDteacher(){
		return this.IDteacher;
	}
	/**
	 * Sets a value to attribute IDteacher. 
	 * @param newIDteacher 
	 */
	public void seIDtTeacher(int newIDTeacher) {
		this.IDteacher = newIDTeacher;
	}
	public void set(Contrainte contrainte) {   /////
		// TODO Auto-generated method stub     ////
		
	}
	public final String toString(){
		return "Teacher:" + super.toString() + "|" + this.IDteacher;
	}
}
