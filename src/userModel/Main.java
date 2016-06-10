package userModel;

import java.util.Hashtable;
import userController.UserController;
/** 
 * 
 * @author jcheng
 * @version 04/2016 
 * 
 */

public class Main {
	/**
	 * Fonction principale 
	 * 
	 * @param UserCon
	 * 
	 */
	public static void main(String[] UserController) {
		UserController UserCon;
		(UserCon = new UserController("userDB.xml")).addAdmin("su", "KF", 1,
				"Kevin", "Flynn", "@tron");
		UserCon.addAdmin("su", "KR", 2, "Keanu", "Reeves", "redpill");
		UserCon.addTeacher("su", "GS", 1001, "Grand", "Schtroumpf",
				"salsepareille");
		UserCon.addTeacher("su", "MF", 1002, "Morgan", "Freeman", "iknowall");
		UserCon.addStudent("su", "BS", 2001, "Buffy", "Summers", "stake");
		UserCon.addStudent("su", "NL", 2002, "Nicolas", "Lepetit", "prout");
		UserCon.addGroup("su", 1);
		UserCon.associateStudToGroup("su", "BS", 1);
		UserCon.saveDB();
		Hashtable UserTab = UserCon.getUserDB().ReturnUsertable();
		System.out.println(UserTab.get("KF"));
		System.out.println(UserTab.get("KR"));
		System.out.println(UserTab.get("GS"));
		System.out.println(UserTab.get("MF"));
		System.out.println(UserTab.get("BS"));
		System.out.println(UserTab.get("NL"));
	}
}
