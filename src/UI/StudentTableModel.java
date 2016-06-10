package UI;
import userModel.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;



			
public class StudentTableModel extends AbstractTableModel {

	private static final int Login_COL = 0;
	private static final int StudentId_COL = 1;
	private static final int Firstname_COL = 2;
	private static final int Lastname_COL = 3;
	private static final int Password_COL = 4;

	private String[] columnNames = { "Login", "StudentId", "FirstName",
			"Lastname", "Password" };
	private List<Student> students;

	public StudentTableModel(List<Student> aStudents) {
		students = aStudents;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Student tableStudent = students.get(row);
		switch (col) {
		case Login_COL:
			return tableStudent.getLogin();
		case StudentId_COL:
			return tableStudent.getIDstudent();
		case Firstname_COL:
			return tableStudent.getFirstname();
		case Lastname_COL:
			return tableStudent.getLastname();
		case Password_COL:
			return tableStudent.getPassword();
		default:
			return tableStudent.getLogin();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
