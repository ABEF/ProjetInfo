package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import DAO.*;
import userModel.*;
import userModel.*;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

public class StudentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField LoginTextField;
	private JTextField StudentIdTextField;
	private JTextField FirstnameTextField;
	private JTextField LastnameTextField;
	private JTextField PasswordTextField;
	
	private StudentDAO studentDAO;

	private StudentSearchApp studentSearchApp;

	private Student previousStudent = null;
	private boolean updateMode = false;

	public StudentDialog(StudentSearchApp aStudentSearchApp,
			StudentDAO aStudentDAO, Student thePreviousStudent, boolean theUpdateMode) {
		this();
		studentDAO = aStudentDAO;
		studentSearchApp = aStudentSearchApp;

		previousStudent = thePreviousStudent;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Student");
			
			populateGui(previousStudent);
		}
	}

	private void populateGui(Student aStudent) {

		LoginTextField.setText(aStudent.getLogin());
		FirstnameTextField.setText(aStudent.getFirstname());
		LastnameTextField.setText(aStudent.getLastname());	
		PasswordTextField.setText(aStudent.getPassword());	
	}

	public StudentDialog(StudentSearchApp aStudentSearchApp,
			StudentDAO aStudentDAO) {
		this(aStudentSearchApp, aStudentDAO, null, false);
	}

	/**
	 * Create the dialog.
	 */
	public StudentDialog() {
		setTitle("Add Student");
		setBounds(100, 100, 450, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));
		{
			
			JLabel lblLogin = new JLabel("Login");
			contentPanel.add(lblLogin, "2, 2, right, default");
		}
		{
			LoginTextField = new JTextField();
			contentPanel.add(LoginTextField, "4, 2, fill, default");
			LoginTextField.setColumns(10);
		}
		{
			JLabel lblStudentId = new JLabel("StudentId");
			contentPanel.add(lblStudentId, "2, 4, right, default");
		}
		{
			StudentIdTextField = new JTextField();
			contentPanel.add(StudentIdTextField, "4, 4, fill, default");
			StudentIdTextField.setColumns(10);
		}
		{
			JLabel lblFirstname = new JLabel("Firstname");
			contentPanel.add(lblFirstname, "2, 6, right, default");
		}
		{
			FirstnameTextField = new JTextField();
			contentPanel.add(FirstnameTextField, "4, 6, fill, default");
			FirstnameTextField.setColumns(10);
		}
		{
			JLabel lblLastname = new JLabel("Lastname ");
			contentPanel.add(lblLastname , "2, 8, right, default");
		}
		{
			LastnameTextField = new JTextField();
			contentPanel.add(LastnameTextField, "4, 8, fill, default");
			LastnameTextField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			contentPanel.add(lblPassword , "2, 8, right, default");
		}
		{
			PasswordTextField = new JTextField();
			contentPanel.add(PasswordTextField, "4, 8, fill, default");
			PasswordTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
			
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected BigDecimal convertStringToBigDecimal(String salaryStr) {

		BigDecimal result = null;

		try {
			double salaryDouble = Double.parseDouble(salaryStr);

			result = BigDecimal.valueOf(salaryDouble);
		} catch (Exception exc) {
			System.out.println("Invalid value. Defaulting to 0.0");
			result = BigDecimal.valueOf(0.0);
		}

		return result;
	}
	

	
}
