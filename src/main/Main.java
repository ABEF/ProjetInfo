package main;

import timeTableController.TimeTableController;
import userController.UserController;
import view.MainFrame;
/**
 * @author jcheng
 * @version 04/2016 
 */

public class Main {
	
	private static void createAndShowUI(UserController userController,TimeTableController tTController) {
        new MyMainFrame(userController,tTController);
    }
	
	public static void main(String args[]){
		
		final String userfile="userDB.xml";
		final String tTfile="timeTableDB.xml";
	    final UserController userController=new UserController(userfile);
	    final TimeTableController tTController=new TimeTableController(tTfile);
		java.awt.EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            createAndShowUI(userController,tTController);
	         }
	      });
	}
}
