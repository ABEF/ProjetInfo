package userController;

import userModel.UserDB;
import userController.IUserController;
/**
 * @author jcheng
 * @version 04/2016 
 */

public class UserController implements IUserController{

	/**
	 * description of UserDB
	 */
	private UserDB UserDB = null;
	
	public UserController(String Userfile) {
		UserDB UserDB = new UserDB(Userfile);
		this.UserDB = UserDB;
	}

	public UserDB getUserDB() {
		return this.UserDB;
	}

	public void setUserDB(UserDB UserDB) {
		this.UserDB = UserDB;
	}

	public String getUserName(String Login) {
		return this.UserDB.ReturnUser(Login).getFirstname() + " " + this.UserDB.ReturnUser(Login).getLastname();
	}

	public String getUserClass(String UserLogin, String Password) {
		return this.UserDB. UserLogin(UserLogin, Password);
	}

	public boolean addAdmin(String AdminLogin, String NewAdminLogin, int NewIDadmin, String NewAdminFirstname,
			String NewAdminLastname, String NewAdminPassword) {
		boolean Flag;
		return (Flag = this.UserDB.AdminAddAdmin(AdminLogin, NewAdminLogin, NewIDadmin, NewAdminFirstname, 
				NewAdminLastname, NewAdminPassword)) ? this
				.saveDB() : Flag;
	}

	public boolean addTeacher(String AdminLogin, String NewTeacherLogin, int NewIDteacher, String NewTeacherFirstname,
			String NewTeacherLastname, String NewTeacherPassword) {
		boolean Flag;
		return (Flag = this.UserDB.AdminAddTeacher(AdminLogin, NewTeacherLogin, NewIDteacher, NewTeacherFirstname, 
				NewTeacherLastname, NewTeacherPassword)) ? this
				.saveDB() : Flag;
	}

	public boolean addStudent(String AdminLogin, String NewStudentLogin, int NewIDstudent, String NewStudentFirstname,
			String NewStudentLastname, String NewStudentPassword) {
		boolean Flag;
		return (Flag = this.UserDB.AdminAddStudent(AdminLogin, NewStudentLogin, NewIDstudent, NewStudentFirstname, 
				NewStudentLastname, NewStudentPassword)) ? this
				.saveDB() : Flag;
	}

	public boolean removeUser(String AdminLogin, String StudentUser) {
		boolean Flag;
		return (Flag = this.UserDB.removeUser(AdminLogin, StudentUser)) ? this.saveDB() : Flag;
	}

	public boolean addGroup(String AdminLogin, int NewIDgroup) {
		boolean Flag;
		return (Flag = this.UserDB.AdminAddGroup(AdminLogin, NewIDgroup)) ? this.saveDB() : Flag;
	}

	public boolean removeGroup(String AdminLogin, int IDgroup) {
		boolean Flag;
		return (Flag = this.UserDB.AdminDeleteGroup(AdminLogin, IDgroup)) ? this.saveDB() : Flag;
	}

	public boolean associateStudToGroup(String AdminLogin, String StudentUser, int IDgroup) {
		boolean Flag;
		return (Flag = this.UserDB.ChangeStudentToNewGroup(AdminLogin, StudentUser, IDgroup)) ? this.saveDB() : Flag;
	}

	public int getStudentGroup(String StudentLogin) {
		return this.UserDB.ReturnStudentFromGroup(StudentLogin);
	}

	public String[] usersToString() {
		return this.UserDB.ReturnUserString();
	}

	public String[] usersLoginToString() {
		return this.UserDB.ReturnLogin();
	}

	public String[] groupsToString() {
		return this.UserDB.GroupToString();
	}

	public String[] groupsIdToString() {
		return this.UserDB.ReturnGroup();
	}

	public String[] studentsLoginToString() {
		return this.UserDB.ReturnGroupStudent();
	}

	public boolean loadDB() {
		return this.UserDB.loadDB();
	}

	@Override
	public boolean saveDB() {
		// TODO Auto-generated method stub
		return false;
	}
}