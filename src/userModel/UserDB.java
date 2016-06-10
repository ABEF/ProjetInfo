/*******************************************************************************
 /*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import userModel.Admin;
import userModel.Student;
import userModel.Teacher;
import userModel.Group;
import userModel.User;

/**
 * Description of UserDB.
 * 
 * @author jcheng
 */
public final class UserDB{
	private Hashtable UserTable = null;
	private Hashtable GroupTable = null;
	private String XML;
	
 public UserDB(String file) {
		this.UserTable = new Hashtable();
		this.GroupTable = new Hashtable();
		this.XML = file;
		this.UserTable.put("su", new Admin("su", 1, "su", "su", "pwd")); //
		this.loadDB();      
	}
 public String getFile() {
		return XML;
	}
 
/**
 * there is method saveDB()
 * to save the data in the file XML
 */
 public boolean saveDB() {
		Enumeration userEnumeration = (this.UserTable).keys();
		Enumeration groupEnumeration = (this.GroupTable).keys();
		SAXBuilder sax =  new SAXBuilder();
		Document DocUserDB = null;
		
		Element UserDB = new Element("UsersDB");
		Element groupsRoof = new Element("Groups");
		Element studentsRoof = new Element("Students");
		Element teachersRoof = new Element("Teachers");
		Element adminsRoof = new Element("Administrators");
		
		UserDB.addContent((Content)groupsRoof);
		UserDB.addContent((Content)studentsRoof);
		UserDB.addContent((Content)teachersRoof);
		UserDB.addContent((Content)adminsRoof);
		
/**
 * try to open the file XML
 */
		try { 
			DocUserDB = sax.build(new File(this.XML));
		} catch (Exception v0) {}

		if(DocUserDB != null) { 
			while(groupEnumeration.hasMoreElements()) {
				String key = String.valueOf(groupEnumeration.nextElement());
				
				Element group = new Element("Group");
				Element groupID = new Element("groupId");
				
				groupID.setText(key);
				group.addContent((Content)groupID);
				groupsRoof.addContent((Content)group);
			}

/**
 * for all users
 */
			while(userEnumeration.hasMoreElements()) {
				String key = (String)userEnumeration.nextElement();
			   
/**
 * if the user is a student
 */
				if(this.UserTable.get(key) instanceof Student) {
					Student student = (Student)this.UserTable.get(key);
					Element Student = new Element("Student");
					Element login = new Element("login");
					Element firstname = new Element("firstname");
					Element lastname = new Element("lastname");
					Element pwd = new Element("pwd");
					Element studentId = new Element("studentId");
					Element groupID = new Element("groupId");
					
					login.setText(key);
					firstname.setText(student.getFirstname());
					lastname.setText(student.getLastname());
					pwd.setText(student.getPassword());
					studentId.setText(Integer.toString(student.getIDstudent()));
					groupID.setText(Integer.toString(student.getIDgroup()));
					
					Student.addContent(login);
					Student.addContent(firstname);
					Student.addContent(lastname);
					Student.addContent(pwd);
					Student.addContent(studentId);
					Student.addContent(groupID);
					studentsRoof.addContent(Student);
				}
			   
/**
 * if the user is a teacher
 */
				if(this.UserTable.get(key) instanceof Teacher) {
					Teacher teacher = (Teacher)this.UserTable.get(key);
					
					Element Teacher = new Element("Teacher");
					Element login = new Element("login");
					Element firstname = new Element("firstname");
					Element lastname = new Element("lastname");
					Element pwd = new Element("pwd");
					Element teacherID = new Element("teacherId");
					
					login.setText(key);
					firstname.setText(teacher.getFirstname());
					lastname.setText(teacher.getLastname());
					pwd.setText(teacher.getPassword());
					teacherID.setText(Integer.toString(teacher.getIDteacher()));
					
					Teacher.addContent(login);
					Teacher.addContent(firstname);
					Teacher.addContent(lastname);
					Teacher.addContent(pwd);
					Teacher.addContent(teacherID);
					teachersRoof.addContent(Teacher);
				}
/**
 * if the user is a admin
 */
				if(this.UserTable.get(key) instanceof Admin) {
					Admin admin = (Admin)this.UserTable.get(key);
					
					Element Admin = new Element("Administrator");
					Element login = new Element("login");
					Element firstname = new Element("firstname");
					Element lastname = new Element("lastname");
					Element pwd = new Element("pwd");
					Element adminId = new Element("adminId");
					
					login.setText(key);					
					firstname.setText(admin.getFirstname());
					lastname.setText(admin.getLastname());
					pwd.setText(admin.getPassword());
					adminId.setText(Integer.toString(admin.IDadmin()));
					
					Admin.addContent(login);
					Admin.addContent(firstname);
					Admin.addContent(lastname);
					Admin.addContent(pwd);
					Admin.addContent(adminId);
					adminsRoof.addContent(Admin);
				}
			   
			} 
			  
			DocUserDB.setRootElement(UserDB);
			  
			try {
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				outputter.output(DocUserDB, (OutputStream)new FileOutputStream(this.XML));
			}
			catch (IOException v0) {
				return false;
			}
			return true;
		} 
		return true;	 
	} 
 /**
  * Here is method loadDB()
  * to load the data saved in the file XML
  */
 public boolean loadDB() {

		SAXBuilder builder;
		builder = new SAXBuilder();
		Document document;
		document = null;
		Element roof;
		String Login,Firstname, Lastname,pwd;
		int ID, groupID;
			        
		try {
			document = builder.build(new File(this.XML));
		} catch (Exception v0) {}
			  
		if(document != null) {
		    roof = document.getRootElement();
			
		    Element roofStudent = roof.getChild("Students");   
		    Element roofAdmin = roof.getChild("Administrators");
		    Element roofTeacher = roof.getChild("Teachers");
		    Element roofGroup = roof.getChild("Groups");
			
			List<Element> studentList = roofStudent.getChildren("Student");
			List<Element> adminList = roofAdmin.getChildren("Administrator");
			List<Element> teacherList = roofTeacher.getChildren("Teacher");
			List<Element> groupList = roofGroup.getChildren("Group");

			for(int i = 0 ; i < studentList.size() ; i++) {
				List<Element> student = studentList.get(i).getChildren();
				
				Login = student.get(0).getText();
				Firstname = student.get(1).getText();
				Lastname = student.get(2).getText();
				pwd = student.get(3).getText();
				ID = Integer.parseInt(student.get(4).getText());
				groupID = Integer.parseInt(student.get(5).getText());
				
				this.UserTable.put(Login, new Student(Login,ID, Firstname, Lastname, pwd, groupID));
			}	
			
			for(int i = 0 ; i < adminList.size() ; i++) {
				List<Element> admin = adminList.get(i).getChildren();

				Login = admin.get(0).getText();
				Firstname = admin.get(1).getText();
				Lastname = admin.get(2).getText();
				pwd = admin.get(3).getText();
				ID = Integer.parseInt(admin.get(4).getText());
				
				this.UserTable.put(Login, new Admin(Login,ID,Firstname, Lastname, pwd));
			}
				
			for(int i = 0 ; i < teacherList.size() ; i++) {
				List<Element> teacher = teacherList.get(i).getChildren();

				Login = teacher.get(0).getText();
				Firstname = teacher.get(1).getText();
				Lastname = teacher.get(2).getText();
				pwd = teacher.get(3).getText();
				ID = Integer.parseInt(teacher.get(4).getText());

				this.UserTable.put(Login, new Teacher(Login,ID, Firstname, Lastname, pwd));
			}
		
			for(int i = 0; i < groupList.size() ; i++) {
				List<Element> group = groupList.get(i).getChildren();
			    
				ID = Integer.parseInt(group.get(0).getText());
				Group newGroup = new Group(ID);
			    this.GroupTable.put(ID, newGroup);
			    //((Group)this.groupTable.get(ID)).addStudentToGroup()     ; METTRE ICI DANS 
			    
			}
			return true;
		}
		return false;
	}

	
/**
 * an User uses the password to login and return the type of the User
 * @param UserLogin
 * @param Password
 */
	public final String UserLogin(String UserLogin, String Password) {
		String Type = "";
		boolean flag = false;
		User UserObj;
		//UserTable include the user login and password,if correct, it is done login.
		if ((UserObj = (User) this.UserTable.get(UserLogin)) != null
				&& UserObj.getPassword().compareTo(Password) == 0) {
			System.out.println("User recognized !!");
			flag = true;
		}
/**
 * succeeded login
 * check the type of user is an admin or a student or a teacher
 */
		if (flag) {
			if (UserObj instanceof Student) {
				Type = "Student";
			} else if (UserObj instanceof Teacher) {
				Type = "Teacher";
			} else if (UserObj instanceof Admin) {
				Type = "Administrator";
			}
		}

		return Type;
	}
/**
 * return User table 
 * @param Login
 */
	public final User ReturnUser(String Login) {
		return (User) this.UserTable.get(Login);
	}
//Return the student from a group
	public final int ReturnStudentFromGroup(String StudentLogin) {
		return ((Student) this.UserTable.get(StudentLogin)).getIDgroup(); 
	}

	public final Hashtable ReturnUsertable() {
		return this.UserTable;
	}
/**
 * Admin log in and add new admin to the usertable. 
 * @param AdminLogin
 * @param NewAdminLogin
 * @param NewIDadmin
 * @param NewAdminFirstname
 * @param NewAdminLastname
 * @param NewAdminPassword
 */
	public final boolean AdminAddAdmin(String AdminLogin, String NewAdminLogin, int NewIDadmin, String NewAdminFirstname,
			String NewAdminLastname, String NewAdminPassword) {
		boolean flag = false;
		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.get(NewAdminLogin) == null) {
			this.UserTable.get(AdminLogin);
			Admin AdminObj = new Admin(NewAdminLogin, NewIDadmin, NewAdminFirstname, NewAdminLastname, NewAdminPassword); //
			this.UserTable.put(NewAdminLogin, AdminObj);
			flag = true;
		}

		return flag;
	}
/**
 * Admin log in and add new teacher to the user table. 
 * @param AdminLogin
 * @param NewTeacherLogin
 * @param NewIDteacher
 * @param NewTeacherFirstname
 * @param NewTeacherLastname
 * @param NewTeacherPassword
 */
	public final boolean AdminAddTeacher(String AdminLogin, String NewTeacherLogin, int NewIDteacher, String NewTeacherFirstname,
			String NewTeacherLastname, String NewTeacherPassword) {
		boolean flag = false;
		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.get(NewTeacherLogin) == null) {
			this.UserTable.get(AdminLogin);
			Teacher TeacherObj = new Teacher(NewTeacherLogin, NewIDteacher, NewTeacherFirstname, NewTeacherLastname, NewTeacherPassword);
			this.UserTable.put(NewTeacherLogin, TeacherObj);
			flag = true;
		}

		return flag;
	}
/**
 * Admin log in and add new student to the user table.
 * @param AdminLogin
 * @param NewStudentLogin
 * @param NewIDstudent
 * @param NewStudentFirstname
 * @param NewStudentLastname
 * @param NewStudentPassword
 */
	public final boolean AdminAddStudent(String AdminLogin, String NewStudentLogin, int NewIDstudent, String NewStudentFirstname,
			String NewStudentLastname, String NewStudentPassword) {
		boolean flag = false;
		
		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.get(NewStudentLogin) == null) {
			this.UserTable.get(AdminLogin);
			Student StudentObj = new Student(NewStudentLogin, NewIDstudent, NewStudentFirstname, NewStudentLastname, NewStudentPassword); 
			this.UserTable.put(NewStudentLogin, StudentObj);
			flag = true;
		}

		return flag;
	}
/**
 * Admin login and delete users
 * @param adminLogin
 * @param userLogin
 * @return
 */
	public boolean removeUser(String adminLogin, String userLogin) {
		boolean isUserRemoved = false;
		User userToRemove;
		if (this.UserTable.get(adminLogin) instanceof Admin && this.UserTable.containsKey(userLogin)) {
			userToRemove = (User)this.UserTable.get(userLogin);
			if (userToRemove instanceof Student && ((Student)userToRemove).getIDgroup() != -1) {
				((Group)this.GroupTable.get(((Student)userToRemove).getIDgroup())).Delete((Student)userToRemove);
			}
			this.UserTable.remove(userLogin);
			isUserRemoved = true;
			saveDB();
		}
		return isUserRemoved;
	}
/**
 * admin deletes student from an user table
 * @param AdminLogin
 * @param StudentUser
 * @return
 */
	public final boolean AdminDeleteStudent(String AdminLogin, String StudentUser) {
		boolean flag = false;
		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.containsKey(StudentUser)) {
			User UserObj;
			Student StudentOjb;
			int IDgroup;
            /**
             * check the student user is a type student and this id of student group is not -1
             */
			if ((UserObj = (User) this.UserTable.get(StudentUser)) instanceof Student
					&& (IDgroup = (StudentOjb = (Student) UserObj).getIDgroup()) != -1) { 
				((Group) this.GroupTable.get(Integer.valueOf(IDgroup))).Delete(StudentOjb);
			}
			this.UserTable.remove(StudentUser);
			flag = true;
		}

		return flag;
	}
/**
 * admin login and add a new group in the group table 
 * @param AdminLogin
 * @param NewIDgroup
 * @return
 */
	public final boolean AdminAddGroup(String AdminLogin, int NewIDgroup) {
		boolean flag = false;
		if (this.UserTable.get(AdminLogin) instanceof Admin
				&& this.GroupTable.get(Integer.valueOf(NewIDgroup)) == null) {
			this.UserTable.get(AdminLogin);
			Group IDgroup = new Group(NewIDgroup);
			this.GroupTable.put(Integer.valueOf(NewIDgroup), IDgroup);
			flag = true;
		}

		return flag;
	}
/**
 * Admin login and delete group
 * @param AdminLogin
 * @param IDgroup
 * @return
 */
	public final boolean AdminDeleteGroup(String AdminLogin, int IDgroup) {
		boolean flag = false;
		//Group table includes the id of group 
		if (this.UserTable.get(AdminLogin) instanceof Admin
				&& this.GroupTable.containsKey(Integer.valueOf(IDgroup))) {
			/**
			 * this id group have a series of student , it is a list of students.
			 */
			Iterator Grouplist = ((Group) this.GroupTable.get(Integer.valueOf(IDgroup))).GroupTable.values()
					.iterator();
/**
 * search the group list which have students£¬ delete the id of these student from the group list, and set -1
 */
			while (Grouplist.hasNext()) {
				((Student) Grouplist.next()).setIDstudent(-1);
			}
/*
 * delete this id group from the group table
 */
			this.GroupTable.remove(Integer.valueOf(IDgroup));
			flag = true;
		}

		return flag;
	}

	public final boolean ChangeStudentToNewGroup(String AdminLogin, String StudentUser, int IDgroup) {
		boolean flag = false;
		/**
		 * check Student User is a student who already has a group
		 */
		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.get(StudentUser) != null
				&& this.GroupTable.get(Integer.valueOf(IDgroup)) != null
				&& this.UserTable.get(StudentUser) instanceof Student) {
			/**
			 * if this student user already have group , delete him from this group
			 */
			if (((Student) this.UserTable.get(StudentUser)).getIDgroup() != -1) {
				((Group) this.GroupTable.get(Integer.valueOf(((Student) this.UserTable.get(StudentUser)).getIDgroup())))
						.Delete((Student) this.UserTable.get(StudentUser));
			}
/**
 * add this student user to new group
 */
			Student IDstudent = (Student) this.UserTable.get(StudentUser);
			Group GroupOjb = (Group) this.GroupTable.get(Integer.valueOf(IDgroup));
			this.UserTable.get(AdminLogin);
			Student StudentObj;
			(StudentObj = IDstudent).setIDstudent(GroupOjb.getGroupTable()); 
			GroupOjb.Add(StudentObj);
			flag = true;
		}

		return flag;
	}
	
/**
 * return the details of all the user in the user table
 * @return
 */
	public final String[] ReturnUserString() {
		String[] UserLogin = new String[this.UserTable.size()];
		int GroupList = 0;

		for (Iterator IDgroup = this.UserTable.values().iterator(); IDgroup.hasNext(); ++GroupList) {
			User UserObj = (User) IDgroup.next();
			UserLogin[GroupList] = UserObj.toString();
		}

		return UserLogin;
	}
/**
 * return all login in user table
 * @return
 */
	public final String[] ReturnLogin() {
		String[] UserLogin = new String[this.UserTable.size()];
		int GroupList = 0;

		for (Iterator IDgroup = this.UserTable.values().iterator(); IDgroup.hasNext(); ++GroupList) {
			User UserObj = (User) IDgroup.next();
			UserLogin[GroupList] = UserObj.getLogin();
		}

		return UserLogin;
	}
	
	public final String[] GroupToString() {
		String[] tabGroups = null;
		if (this.GroupTable != null) {
			tabGroups = new String[this.GroupTable.size()];
			int GroupList = 0;

			for (Iterator IDgroup = this.GroupTable.values().iterator(); IDgroup.hasNext(); ++GroupList) {
				Group GroupObj = (Group) IDgroup.next();
				tabGroups[GroupList] = GroupObj.toString();
			}
		}

		return tabGroups;
	}
/**
 * Return all the IDgroup from the group table 
 * @return
 */
	public final String[] ReturnGroup() {
		String[] tabGroups = new String[this.GroupTable.size()];
		int GroupList = 0;

		for (Iterator IDgroup = this.GroupTable.values().iterator(); IDgroup.hasNext(); ++GroupList) {
			Group GroupObj = (Group) IDgroup.next();
			tabGroups[GroupList] = "" + GroupObj.getGroupTable();
		}

		return tabGroups;
	}
/**
 * Return the students in the group.
 * @return
 */
	public final String[] ReturnGroupStudent() {
		Hashtable StudentGroup = new Hashtable();
		Iterator StudentLogin = this.UserTable.values().iterator();

		while (StudentLogin.hasNext()) {
			User UserObj;
			if ((UserObj = (User) StudentLogin.next()) instanceof Student) {
				StudentGroup.put(Integer.valueOf(StudentGroup.size()), UserObj.getLogin());
			}
		}

		String[] Student = null;
		if (StudentGroup.size() > 0) {
			Student = new String[StudentGroup.size()];

			for (int GroupList = 0; GroupList < StudentGroup.size(); ++GroupList) {
				Student[GroupList] = (String) StudentGroup.get(Integer.valueOf(GroupList));
			}
		}

		return Student;
	}
	/**
	 * admin can remove user
	 * @param userLogin
	 * @param ListUsers
	 * @param ListGroup
	 * @return
	 */
		public ArrayList<User> removeUser(Student userLogin,ArrayList<User> ListUsers,ArrayList<Group> ListGroup) {
			for(int i=0;i<ListUsers.size();i++){
				if(ListUsers.get(i).getLogin().equals(userLogin)){
					if(ListUsers.get(i).getType().equals("Student")){
						for(int j=0;j<ListGroup.size();j++){
							if(ListGroup.get(j).getGroupTable()==((Student) ListUsers.get(i)).getIDgroup())
								ListGroup.get(j).Delete(userLogin);
						}
					}
						
					ListUsers.remove(i);
					return ListUsers;
				}
			}
			return ListUsers;
		}
	/**
	 * admin can add a group
	 * @param iD
	 * @param ListGroup
	 * @return
	 */
		public ArrayList<Group> addGroup(int iD, ArrayList<Group> ListGroup){
			ListGroup.add(new Group(iD));
			return ListGroup;
		}
		
	/**
	 * admin can associate a student to a group
	 * @param studentLogin
	 * @param groupId
	 * @param ListGroup
	 * @param ListUsers
	 * @return
	 */
		public boolean associateStudToGroup(String studentLogin, int groupId,ArrayList<Group> ListGroup,ArrayList<User> ListUsers){
			for(int j=0;j<ListGroup.size();j++){
				if(ListGroup.get(j).getGroupTable()==groupId){
					for(int i=0;i<ListUsers.size();i++){	
						if(ListUsers.get(i).getLogin().equals(studentLogin)){
							((Student) ListUsers.get(i)).setIDgroup(groupId);
							ListGroup.get(j).Add(((Student) ListUsers.get(i)));
							return true;
						}
					}
				}
			}
			return false;
		}
	/**
	 * admin can remove a group
	 * @param groupId
	 * @param ListGroup
	 * @param ListUsers
	 * @return
	 */
		public boolean removeGroup(int groupId,ArrayList<Group> ListGroup,ArrayList<User> ListUsers){
			for(int j=0;j<ListGroup.size();j++){
				if(ListGroup.get(j).getGroupTable()==groupId){
					for(int i=0;i<ListUsers.size();i++){	
						if(ListUsers.get(i).getType().equals("Student")){
							if(((Student) ListUsers.get(i)).getIDgroup()==groupId)
								((Student) ListUsers.get(i)).setIDgroup(-1);
						}
					}
					ListGroup.remove(j);
					return true;
				}
			}
			return false;
		}
}
///*******************************************************************************
// /*******************************************************************************
// * 2016, All rights reserved.
// *******************************************************************************/
//package userModel;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.Hashtable;
//import java.util.Iterator;
//import java.util.List;
//
//import org.jdom2.Content;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.input.SAXBuilder;
//import org.jdom2.output.Format;
//import org.jdom2.output.XMLOutputter;
//
//import userModel.Admin;
//import userModel.Student;
//import userModel.Teacher;
//import userModel.Group;
//import userModel.User;
//
//public final class UserDB{
//	private Hashtable UserTable = null;
//	private Hashtable GroupTable = null;
//	private String XML;
//	
// public UserDB(String file) {
//		this.UserTable = new Hashtable();
//		this.GroupTable = new Hashtable();
//		this.XML = file;
//		this.UserTable.put("su", new Admin("su", 0, "su", "su", "superUser")); 
//		this.loadDB();      
//	}
// public String getFile() {
//		return XML;
//	}
// 
////there is method saveDB()
// public boolean saveDB() {
//		Enumeration userEnumeration = (this.UserTable).keys();
//		Enumeration groupEnumeration = (this.GroupTable).keys();
//		SAXBuilder sax =  new SAXBuilder();
//		Document DocUserDB = null;
//	
//		Element UserDB = new Element("UsersDB");
//		Element groupsRoof = new Element("Groups");
//		Element studentsRoof = new Element("Students");
//		Element teachersRoof = new Element("Teachers");
//		Element adminsRoof = new Element("Administrators");
//	
//		UserDB.addContent((Content)groupsRoof);
//		UserDB.addContent((Content)studentsRoof);
//		UserDB.addContent((Content)teachersRoof);
//		UserDB.addContent((Content)adminsRoof);
//			        
//		try { //try to open the file XML
//			DocUserDB = sax.build(new File(this.XML));
//		} catch (Exception v0) {}
//			        
//		if(DocUserDB != null) { //si on arrive ? ouvrir le fichier
//			         
//			         
//			//for all groups
//			while(groupEnumeration.hasMoreElements()) {
//				String key = String.valueOf(groupEnumeration.nextElement());
//				Element group = new Element("Group");
//				Element groupID = new Element("groupId");
//				groupID.setText(key);
//				group.addContent((Content)groupID);
//				groupsRoof.addContent((Content)group);
//			}
//
//			// for all users
//			while(userEnumeration.hasMoreElements()) {
//				String key = (String)userEnumeration.nextElement();
//			   
//				//if student
//				if(this.UserTable.get(key) instanceof Student) {
//					Student student = (Student)this.UserTable.get(key);
//					Element Student = new Element("Student");
//					Element login = new Element("login");
//					Element firstname = new Element("firstname");
//					Element lastname = new Element("lastname");
//					Element pwd = new Element("pwd");
//					Element studentId = new Element("studentId");
//					Element groupID = new Element("groupId");
//					
//					login.setText(key);
//					firstname.setText(student.getFirstname());
//					lastname.setText(student.getLastname());
//					pwd.setText(student.getPassword());
//					studentId.setText(Integer.toString(student.getIDstudent()));
//					groupID.setText(Integer.toString(student.getIDgroup()));
//					
//					Student.addContent(login);
//					Student.addContent(firstname);
//					Student.addContent(lastname);
//					Student.addContent(pwd);
//					Student.addContent(studentId);
//					Student.addContent(groupID);
//					studentsRoof.addContent(Student);
//				}
//			   
//				//if teacher
//				if(this.UserTable.get(key) instanceof Teacher) {
//					Teacher teacher = (Teacher)this.UserTable.get(key);
//					Element Teacher = new Element("Teacher");
//					Element login = new Element("login");
//					Element firstname = new Element("firstname");
//					Element lastname = new Element("lastname");
//					Element pwd = new Element("pwd");
//					Element teacherID = new Element("teacherId");
//					
//					login.setText(key);
//					firstname.setText(teacher.getFirstname());
//					lastname.setText(teacher.getLastname());
//					pwd.setText(teacher.getPassword());
//					teacherID.setText(Integer.toString(teacher.getIDteacher()));
//				
//					Teacher.addContent(login);
//					Teacher.addContent(firstname);
//					Teacher.addContent(lastname);
//					Teacher.addContent(pwd);
//					Teacher.addContent(teacherID);
//					teachersRoof.addContent(Teacher);
//				}
//			   
//				//if Admin
//				if(this.UserTable.get(key) instanceof Admin) {
//					Admin admin = (Admin)this.UserTable.get(key);
//					Element Admin = new Element("Administrator");
//					Element login = new Element("login");
//					Element firstname = new Element("firstname");
//					Element lastname = new Element("lastname");
//					Element pwd = new Element("pwd");
//					Element adminId = new Element("adminId");
//				
//					login.setText(key);
//					firstname.setText(admin.getFirstname());
//					lastname.setText(admin.getLastname());
//					pwd.setText(admin.getPassword());
//					adminId.setText(Integer.toString(admin.IDadmin()));
//					
//					Admin.addContent(login);
//					Admin.addContent(firstname);
//					Admin.addContent(lastname);
//					Admin.addContent(pwd);
//					Admin.addContent(adminId);
//					adminsRoof.addContent(Admin);
//				}
//			   
//			} 
//			  
//			DocUserDB.setRootElement(UserDB);
//			  
//			try {
//				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
//				outputter.output(DocUserDB, (OutputStream)new FileOutputStream(this.XML));
//			}
//			catch (IOException v0) {
//				return false;
//			}
//			return true;
//		} 
//		return true;	 
//	} 
// 
// //Here is method loadDB()
// public boolean loadDB() {
//
//		SAXBuilder builder;
//		builder = new SAXBuilder();
//		Document document;
//		document = null;
//		Element roof;
//		String Login,Firstname, Lastname,pwd;
//		int ID, groupID;
//			        
//		try {
//			document = builder.build(new File(this.XML));
//		} catch (Exception v0) {}
//			  
//		if(document != null) {
//		    roof = document.getRootElement();
//			
//		    Element roofStudent = roof.getChild("Students");   
//		    Element roofAdmin = roof.getChild("Administrators");
//		    Element roofTeacher = roof.getChild("Teachers");
//		    Element roofGroup = roof.getChild("Groups");
//			
//			List<Element> studentList = roofStudent.getChildren("Student");
//			List<Element> adminList = roofAdmin.getChildren("Administrator");
//			List<Element> teacherList = roofTeacher.getChildren("Teacher");
//			List<Element> groupList = roofGroup.getChildren("Group");
//
//			for(int i = 0 ; i < studentList.size() ; i++) {
//				List<Element> student = studentList.get(i).getChildren();
//				
//				Login = student.get(0).getText();
//				Firstname = student.get(1).getText();
//				Lastname = student.get(2).getText();
//				pwd = student.get(3).getText();
//				ID = Integer.parseInt(student.get(4).getText());
//				groupID = Integer.parseInt(student.get(5).getText());
//				
//				this.UserTable.put(Login, new Student(Login,ID, Firstname, Lastname, pwd, groupID));
//			}	
//			
//			for(int i = 0 ; i < adminList.size() ; i++) {
//				List<Element> admin = adminList.get(i).getChildren();
//
//				Login = admin.get(0).getText();
//				Firstname = admin.get(1).getText();
//				Lastname = admin.get(2).getText();
//				pwd = admin.get(3).getText();
//				ID = Integer.parseInt(admin.get(4).getText());
//				
//				this.UserTable.put(Login, new Admin(Login,ID,Firstname, Lastname, pwd));
//			}
//				
//			for(int i = 0 ; i < teacherList.size() ; i++) {
//				List<Element> teacher = teacherList.get(i).getChildren();
//
//				Login = teacher.get(0).getText();
//				Firstname = teacher.get(1).getText();
//				Lastname = teacher.get(2).getText();
//				pwd = teacher.get(3).getText();
//				ID = Integer.parseInt(teacher.get(4).getText());
//
//				this.UserTable.put(Login, new Teacher(Login,ID, Firstname, Lastname, pwd));
//			}
//		
//			for(int i = 0; i < groupList.size() ; i++) {
//				List<Element> group = groupList.get(i).getChildren();
//			    
//				ID = Integer.parseInt(group.get(0).getText());
//				Group newGroup = new Group(ID);
//			    this.GroupTable.put(ID, newGroup);
//			    //((Group)this.groupTable.get(ID)).addStudentToGroup()     ;
//			    
//			}
//			return true;
//		}
//		return false;
//	}
//
//	
////User login use the password to login，and return the type of the User
//	public final String UserLogin(String UserLogin, String Password) {
//		String Type = "";
//		boolean flag = false;
//		User UserObj;
//		//UserTable include the user login and password,if correct, it is done login.
//		if ((UserObj = (User) this.UserTable.get(UserLogin)) != null
//				&& UserObj.getPassword().compareTo(Password) == 0) {
//			System.out.println("User recognized !!");
//			flag = true;
//		}
////succeeded login , check the type of user is admin or student or teacher
//		if (flag) {
//			if (UserObj instanceof Student) {
//				Type = "Student";
//			} else if (UserObj instanceof Teacher) {
//				Type = "Teacher";
//			} else if (UserObj instanceof Admin) {
//				Type = "Administrator";
//			}
//		}
//
//		return Type;
//	}
//	
////return the User 
//	public final User ReturnUser(String Login) {
//		return (User) this.UserTable.get(Login);
//	}
////Return the id of group where the student is
//	public final int ReturnGroupID(String StudentLogin) {
//		return ((Student) this.UserTable.get(StudentLogin)).getIDgroup(); 
//	}
//
//	public final Hashtable ReturnUsertable() {
//		return this.UserTable;
//	}
////Admin login and add new admin in the usertable. 
//	//the admin have Admin(String Login,int IDadmin, String Firstname,String Lastname, String Password)
//	public final boolean AdminAddAdmin(String AdminLogin, String NewAdminLogin, int NewIDadmin, String NewAdminFirstname,
//			String NewAdminLastname, String NewAdminPassword) {
//		boolean flag = false;
//		//there is no new admin in the usertable
//		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.get(NewAdminLogin) == null) {
//			this.UserTable.get(AdminLogin);
//			Admin AdminObj = new Admin(NewAdminLogin, NewIDadmin, NewAdminFirstname, NewAdminLastname, NewAdminPassword); //
//			this.UserTable.put(NewAdminLogin, AdminObj);
//			flag = true;
//		}
//
//		return flag;
//	}
////Admin login and add new teacher in the usertable. 
//	public final boolean AdminAddTeacher(String AdminLogin, String NewTeacherLogin, int NewIDteacher, String NewTeacherFirstname,
//			String NewTeacherLastname, String NewTeacherPassword) {
//		boolean flag = false;
//		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.get(NewTeacherLogin) == null) {
//			this.UserTable.get(AdminLogin);
//			Teacher TeacherObj = new Teacher(NewTeacherLogin, NewIDteacher, NewTeacherFirstname, NewTeacherLastname, NewTeacherPassword);
//			this.UserTable.put(NewTeacherLogin, TeacherObj);
//			flag = true;
//		}
//
//		return flag;
//	}
////the same, add a new student to usertable
//	public final boolean AdminAddStudent(String AdminLogin, String NewStudentLogin, int NewIDstudent, String NewStudentFirstname,
//			String NewStudentLastname, String NewStudentPassword) {
//		boolean flag = false;
//		
//		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.get(NewStudentLogin) == null) {
//			this.UserTable.get(AdminLogin);
//			Student StudentObj = new Student(NewStudentLogin, NewIDstudent, NewStudentFirstname, NewStudentLastname, NewStudentPassword); 
//			this.UserTable.put(NewStudentLogin, StudentObj);
//			flag = true;
//		}
//
//		return flag;
//	}
////Admin login and delete the student from the usertable and student group 
//	public final boolean AdminDeleteStudent(String AdminLogin, String StudentUser) {
//		boolean flag = false;
//		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.containsKey(StudentUser)) {
//			User UserObj;
//			Student StudentOjb;
//			int IDgroup;
//			//check the student user is a type student and this id of student group is not -1
//			//delete this student user from student group
//			if ((UserObj = (User) this.UserTable.get(StudentUser)) instanceof Student
//					&& (IDgroup = (StudentOjb = (Student) UserObj).getIDgroup()) != -1) { 
//				((Group) this.GroupTable.get(Integer.valueOf(IDgroup))).Delete(StudentOjb);
//			}
////then delete this student user from the usertable
//			this.UserTable.remove(StudentUser);
//			flag = true;
//		}
//
//		return flag;
//	}
////admin login and add a new in group in the group table 
//	public final boolean AdminAddGroup(String AdminLogin, int NewIDgroup) {
//		boolean flag = false;
//		if (this.UserTable.get(AdminLogin) instanceof Admin
//				&& this.GroupTable.get(Integer.valueOf(NewIDgroup)) == null) {
//			this.UserTable.get(AdminLogin);
//			Group IDgroup = new Group(NewIDgroup);
//			this.GroupTable.put(Integer.valueOf(NewIDgroup), IDgroup);
//			flag = true;
//		}
//
//		return flag;
//	}
////Admin login and delete group
//	public final boolean AdminDeleteGroup(String AdminLogin, int IDgroup) {
//		boolean flag = false;
//		//Group table includes the id of group 
//		if (this.UserTable.get(AdminLogin) instanceof Admin
//				&& this.GroupTable.containsKey(Integer.valueOf(IDgroup))) {
//			//this id group have a series of student , it is a list arg3
//			Iterator Grouplist = ((Group) this.GroupTable.get(Integer.valueOf(IDgroup))).GroupTable.values()
//					.iterator();
//search the grouplist which have students delete the id of these student from the group list, and set -1
//			while (Grouplist.hasNext()) {
//				((Student) Grouplist.next()).setIDstudent(-1);
//			}
////And then delete this id group from the group table
//			this.GroupTable.remove(Integer.valueOf(IDgroup));
//			flag = true;
//		}
//
//		return flag;
//	}
//
//	public final boolean ChangeStudentToNewGroup(String AdminLogin, String StudentUser, int IDgroup) {
//		boolean flag = false;
//		//check Student User is a student who already has a group
//		if (this.UserTable.get(AdminLogin) instanceof Admin && this.UserTable.get(StudentUser) != null
//				&& this.GroupTable.get(Integer.valueOf(IDgroup)) != null
//				&& this.UserTable.get(StudentUser) instanceof Student) {
//			//if this student user already have group , delete him from this group
//			if (((Student) this.UserTable.get(StudentUser)).getIDgroup() != -1) {
//				((Group) this.GroupTable.get(Integer.valueOf(((Student) this.UserTable.get(StudentUser)).getIDgroup())))
//						.Delete((Student) this.UserTable.get(StudentUser));
//			}
////add this student user to new group
//			Student IDstudent = (Student) this.UserTable.get(StudentUser);
//			Group GroupOjb = (Group) this.GroupTable.get(Integer.valueOf(IDgroup));
//			this.UserTable.get(AdminLogin);
//			Student StudentObj;
//			(StudentObj = IDstudent).setIDstudent(GroupOjb.getGroupTable()); 
//			GroupOjb.Add(StudentObj);
//			flag = true;
//		}
//
//		return flag;
//	}
//	
////return the details of all the user in the user table
//	public final String[] ReturnUserString() {
//		String[] UserLogin = new String[this.UserTable.size()];
//		int GroupList = 0;
//
//		for (Iterator IDgroup = this.UserTable.values().iterator(); IDgroup.hasNext(); ++GroupList) {
//			User UserObj = (User) IDgroup.next();
//			UserLogin[GroupList] = UserObj.toString();
//		}
//
//		return UserLogin;
//	}
////return all login in user table
//	public final String[] ReturnLogin() {
//		String[] UserLogin = new String[this.UserTable.size()];
//		int GroupList = 0;
//
//		for (Iterator IDgroup = this.UserTable.values().iterator(); IDgroup.hasNext(); ++GroupList) {
//			User UserObj = (User) IDgroup.next();
//			UserLogin[GroupList] = UserObj.getLogin();
//		}
//
//		return UserLogin;
//	}
//	
//	public final String[] GroupToString() {
//		String[] tabGroups = null;
//		if (this.GroupTable != null) {
//			tabGroups = new String[this.GroupTable.size()];
//			int GroupList = 0;
//
//			for (Iterator IDgroup = this.GroupTable.values().iterator(); IDgroup.hasNext(); ++GroupList) {
//				Group GroupObj = (Group) IDgroup.next();
//				tabGroups[GroupList] = GroupObj.toString();
//			}
//		}
//
//		return tabGroups;
//	}
////Return all the IDgroup from the group table
//	public final String[] ReturnGroup() {
//		String[] tabGroups = new String[this.GroupTable.size()];
//		int GroupList = 0;
//
//		for (Iterator IDgroup = this.GroupTable.values().iterator(); IDgroup.hasNext(); ++GroupList) {
//			Group GroupObj = (Group) IDgroup.next();
//			tabGroups[GroupList] = "" + GroupObj.getGroupTable();
//		}
//
//		return tabGroups;
//	}
////Return the students in the group  
//	public final String[] ReturnGroupStudent() {
//		Hashtable StudentGroup = new Hashtable();
//		Iterator StudentLogin = this.UserTable.values().iterator();
//
//		while (StudentLogin.hasNext()) {
//			User UserObj;
//			if ((UserObj = (User) StudentLogin.next()) instanceof Student) {
//				StudentGroup.put(Integer.valueOf(StudentGroup.size()), UserObj.getLogin());
//			}
//		}
//
//		String[] Student = null;
//		if (StudentGroup.size() > 0) {
//			Student = new String[StudentGroup.size()];
//
//			for (int GroupList = 0; GroupList < StudentGroup.size(); ++GroupList) {
//				Student[GroupList] = (String) StudentGroup.get(Integer.valueOf(GroupList));
//			}
//		}
//
//		return Student;
//	}
//	public boolean removeGroup(int groupId,ArrayList<Group> ListGroup,ArrayList<User> ListUsers){
//		boolean flag = false;
//		for(int j=0;j<ListGroup.size();j++){
//			if(ListGroup.get(j).getGroupTable()==groupId){
//				for(int i=0;i<ListUsers.size();i++){	
//					if(ListUsers.get(i).getType().equals("Student")){
//						if(((Student) ListUsers.get(i)).getIDgroup()==groupId)
//							((Student) ListUsers.get(i)).setIDgroup(-1);
//					}
//				}
//				ListGroup.remove(j);
//				
//			}
//			flag = true;
//		}
//		return false;
//	}	
//	
//	public boolean removeUser(Student userLogin,ArrayList<User> ListUsers,ArrayList<Group> ListGroup) {
//		boolean flag = false;
//		for(int i=0;i<ListUsers.size();i++){
//			if(ListUsers.get(i).getLogin().equals(userLogin)){
//				if(ListUsers.get(i).getType().equals("Student")){
//					for(int j=0;j<ListGroup.size();j++){
//						if(ListGroup.get(j).getGroupTable()==((Student) ListUsers.get(i)).getIDgroup())
//							ListGroup.get(j).Delete(userLogin);
//					}
//				}
//					
//				ListUsers.remove(i);
//			}
//			flag = true;
//		}
//		return false;
//	}
//	
//	public boolean StudentFromremoveUser(int groupId,ArrayList<Group> ListGroup,ArrayList<User> ListUsers){
//		for(int j=0;j<ListGroup.size();j++){
//			if(ListGroup.get(j).getGroupTable()==groupId){
//				for(int i=0;i<ListUsers.size();i++){	
//					if(ListUsers.get(i).getType().equals("Student")){
//						if(((Student) ListUsers.get(i)).getIDgroup()==groupId)
//							((Student) ListUsers.get(i)).setIDgroup(-1);
//					}
//				}
//				ListGroup.remove(j);
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public boolean associateStudToGroup(String studentLogin, int groupId,ArrayList<Group> ListGroup,ArrayList<User> ListUsers){
//		for(int j=0;j<ListGroup.size();j++){
//			if(ListGroup.get(j).getGroupTable()==groupId){
//				for(int i=0;i<ListUsers.size();i++){	
//					if(ListUsers.get(i).getLogin().equals(studentLogin)){
//						((Student) ListUsers.get(i)).setIDgroup(groupId);
//						ListGroup.get(j).Add(((Student) ListUsers.get(i)));
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}
//	
//	
//}