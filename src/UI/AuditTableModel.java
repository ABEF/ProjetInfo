package UI;
import java.util.List;

import userModel.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import userModel.AuditHistory;

public class AuditTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	public static final int DATE_TIME_COL = 0;
	private static final int ACTION_COL = 1;
	private static final int AdminId_COL = 2;
	private static final int Login_COL = 3;

	private String[] columnNames = { "Date/Time", "Action", "adminId",
			"Login" };
	
	private List<AuditHistory> auditHistoryList;

	public AuditTableModel(List<AuditHistory> aAuditTableModel) {
		auditHistoryList = aAuditTableModel;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return auditHistoryList.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		AuditHistory tempAuditHistory = auditHistoryList.get(row);

		switch (col) {
		case DATE_TIME_COL:			
			return tempAuditHistory.getActionDateTime();
		case ACTION_COL:
			return tempAuditHistory.getAction();
		case AdminId_COL:
			return tempAuditHistory.getAdminid();
		case Login_COL:
			return tempAuditHistory.getLogin();
		case OBJECT_COL:
			return tempAuditHistory;
		default:
			return tempAuditHistory.getLogin();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
