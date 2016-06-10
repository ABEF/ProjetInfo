/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;

import userModel.User;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Student.
 * 
 * @author jcheng
 */
public final class Student extends User {
	/**
	 * Description of the property IDstudent.
	 */
	private int IDstudent = 0;
    private int IDgroup = 0;
	// Start of user code (user defined attributes for Student)

	// End of user code

	/**
	 * The constructor.
	 */
	public Student(String Login,int IDstudent, String Firstname, String Lastname, String Password) {
		// Start of user code constructor for Student)
		super(Login,Firstname,Lastname,Password,"Student");
	    this.IDstudent = IDstudent;
	    this.IDgroup = -1;
		// End of user code
	}

	// Start of user code (user defined methods for Student)
	public Student(String Login,int IDstudent, String Firstname, String Lastname, String Password,int IDgroup) {
		// Start of user code constructor for Student)
		super(Login,Firstname,Lastname,Password,"Student");
	    this.IDstudent = IDstudent;
	    this.IDgroup = IDgroup;
	}
	/**
	 * Returns IDstudent.
	 * @return IDstudent 
	 */
	public final int getIDstudent() {
		return this.IDstudent;
	}
	public void setIDstudent(int newIDStudent) {
		this.IDstudent = newIDStudent;
	}

	/**
	 * Returns groupID.
	 * @return groupID 
	 */
	
	public final int getIDgroup(){
		return this.IDgroup;
	}
	
	public final void setIDgroup(int newIDgroup){
		this.IDgroup = newIDgroup;
	}
	
	public final String toString(){
		return "Student:" + super.toString() + "-" + this.IDstudent + "-" + this.IDgroup;
	}
	

}
