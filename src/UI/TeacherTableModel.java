package UI;
import userModel.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;



			
public class TeacherTableModel extends AbstractTableModel {

	private static final int Login_COL = 0;
	private static final int TeacherId_COL = 1;
	private static final int Firstname_COL = 2;
	private static final int Lastname_COL = 3;
	private static final int Password_COL = 4;

	private String[] columnNames = { "Login", "TeacherId", "FirstName",
			"Lastname", "Password" };
	private List<Teacher> teachers;

	public TeacherTableModel(List<Teacher> aTeachers) {
		teachers = aTeachers;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return teachers.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Teacher tableTeacher = teachers.get(row);
		switch (col) {
		case Login_COL:
			return tableTeacher.getLogin();
		case TeacherId_COL:
			return tableTeacher.getIDteacher();
		case Firstname_COL:
			return tableTeacher.getFirstname();
		case Lastname_COL:
			return tableTeacher.getLastname();
		case Password_COL:
			return tableTeacher.getPassword();
		default:
			return tableTeacher.getLogin();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
