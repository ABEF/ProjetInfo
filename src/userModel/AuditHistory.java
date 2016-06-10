package userModel;
import java.util.Date;

public class AuditHistory {

	private int login;
	private int adminid;
	private String action;
	private Date actionDateTime;
	
	private String adminFirstname;
	private String adminLastname;
	
	
	public AuditHistory(int adminid, int login, String action,
			Date actionDateTime, String adminFirstname, String adminLastname) {
		super();
		this.adminid = adminid;
		this.login = login;
		this.action = action;
		this.actionDateTime = actionDateTime;
		this.adminFirstname = adminFirstname;
		this.adminLastname = adminLastname;
	}
	
	public int getAdminid() {
		return adminid;
	}
	public void setAdminId(int adminid) {
		this.adminid = adminid;
	}
	public int getLogin() {
		return login;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	public String getAdminFirstname() {
		return adminFirstname;
	}
	public void setAdminFirstname(String adminFirstname) {
		this.adminFirstname = adminFirstname;
	}
	public String getAdminLastname() {
		return adminLastname;
	}
	public void setAdminLastname(String adminLastname) {
		this.adminLastname = adminLastname;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getActionDateTime() {
		return actionDateTime;
	}
	public void setActionDateTime(Date actionDateTime) {
		this.actionDateTime = actionDateTime;
	}

	@Override
	public String toString() {
		return String
				.format("AuditHistory [Adminid=%s, login=%s, action=%s, actionDateTime=%s, login=%s, AdminLastName=%s]",
						adminid, login, action, actionDateTime,
						adminFirstname, adminLastname);
	}
	
	
	
}
