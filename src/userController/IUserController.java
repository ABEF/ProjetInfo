package userController;

/**
 * @author jcheng
 * @version 04/2016
 */



public interface IUserController{ 
	String getUserName(String Login);

	String getUserClass(String UserLogin, String Password);

	int getStudentGroup(String arg0);

	boolean addAdmin(String AdminLogin, String NewAdminLogin, int NewIDadmin, String NewAdminFirstname,
			String NewAdminLastname, String NewAdminPassword);

	boolean addTeacher(String AdminLogin, String NewTeacherLogin, int NewIDteacher, String NewTeacherFirstname,
			String NewTeacherLastname, String NewTeacherPassword);

	boolean addStudent(String AdminLogin, String NewStudentLogin, int NewIDstudent, String NewStudentFirstname,
			String NewStudentLastname, String NewStudentPassword);

	boolean removeUser(String AdminLogin, String StudentUser);

	boolean addGroup(String AdminLogin, int NewIDgroup);

	boolean removeGroup(String AdminLogin, int IDgroup);

	boolean associateStudToGroup(String AdminLogin, String StudentUser, int IDgroup);

	String[] usersToString();

	String[] usersLoginToString();

	String[] studentsLoginToString();

	String[] groupsIdToString();

	String[] groupsToString();

	boolean loadDB();

	boolean saveDB();
}