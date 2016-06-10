/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of User.
 * 
 * @author jcheng
 */
public abstract class User {
	
	private String Login = "";
	private String Firstname = "";
	private String Lastname = "";
	private String Password = "";
	private String Type = "";
	// Start of user code (user defined attributes for User)

	// End of user code

	/**
	 * The constructor.
	 */
	public User(String Login, String Firstname,String Lastname, String Password,String Type) {
		// Start of user code constructor for User)
		this.Login = Login;
		this.Firstname = Firstname;
		this.Lastname = Lastname;
		this.Password = Password;
		this.Type = Type;
		// End of user code
	}
	public final String getLogin() {
		return this.Login;
	}

	public final String getFirstname() {
		return this.Firstname;
	}

	public final String getLastname() {
		return this.Lastname;
	}

	public final String getPassword() {
		return this.Password;
	}
	public final String getType() {
		return this.Type;
	}

	public void ViewTimeTable() {
		// Start of user code for method ViewTimeTable
		// End of user code
	}
	public String toString(){
		return this.Login + "-" + this.Firstname + "-" + this.Lastname + "-" + this.Password;
	}


}
