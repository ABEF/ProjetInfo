/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;
import java.util.ArrayList;
import java.util.Hashtable;

import userModel.Student;
// Start of user code (user defined imports)


public final class Group {

	public Hashtable GroupTable;
	private int IDgroup;
	private int StudentNumber;

	
	public Group(int IDgroup) {
		// Start of user code constructor for Groupe)
		this.IDgroup = IDgroup;
		this.StudentNumber = 0;
		this.GroupTable = new Hashtable();
		// End of user code
	}
	public final int getGroupTable(){
		return this.IDgroup;
	}
	public int getNbStudents() {
		return this.StudentNumber;
	}
	public Hashtable getStudentsFromGroup() {
		return this.GroupTable;
	}
	
	public final void Add(Student IDstudent) {
		if (this.GroupTable.get(IDstudent.getIDstudent()) == null) {
			this.GroupTable.put(IDstudent.getIDstudent(), IDstudent);
			IDstudent.setIDgroup(this.IDgroup);
			++this.StudentNumber;
		}

	}

	public final void Delete(Student IDstudent) {
		if (this.GroupTable.get(IDstudent.getIDstudent()) != null) {
			IDstudent.setIDgroup(-1);
			this.GroupTable.remove(IDstudent.getIDstudent());
			--this.StudentNumber;
		}

	}


	public ArrayList<Group> addGroup(int iD, ArrayList<Group> ListGroup){ // 
	ListGroup.add(new Group(iD));
	return ListGroup;
	}

	
	
	public final String toString() {
		return "Group " + this.IDgroup + " - StudentNumber: " + this.StudentNumber;
	}
}
