/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Contrainte.
 * 
 * @author jcheng
 */
public class Contrainte {
	
	public int IDcontrainte;
	public int IDteacher;
	public String Login;
	public String StartDate = "";
	public String EndDate = "";
	public String Comment = "";
	
	

	/**
	 * Description of the property teachers.
	 */
	public Teacher teachers = null;

	// Start of user code (user defined attributes for Contrainte)

	// End of user code

	/**
	 * The constructor.
	 */
	public Contrainte() {
		// Start of user code constructor for Contrainte)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Contrainte)

	// End of user code
	/**
	 * Returns StartDate.
	 * @return StartDate 
	 */
	public String getStartDate() {
		return this.StartDate;
	}

	/**
	 * Sets a value to attribute StartDate. 
	 * @param newStartDate 
	 */
	public void setStartDate(String newStartDate) {
		this.StartDate = newStartDate;
	}

	/**
	 * Returns comment.
	 * @return comment 
	 */
	public String getComment() {
		return this.Comment;
	}

	/**
	 * Sets a value to attribute comment. 
	 * @param newComment 
	 */
	public void setComment(String newComment) {
		this.Comment = newComment;
	}

	/**
	 * Returns IDteacher.
	 * @return IDteacher 
	 */
	public Integer getIDteacher() {
		return this.IDteacher;
	}

	/**
	 * Sets a value to attribute IDteacher. 
	 * @param newIDteacher 
	 */
	public void setIDteacher(Integer newIDteacher) {
		this.IDteacher = newIDteacher;
	}

	/**
	 * Returns EndDate.
	 * @return EndDate 
	 */
	public String getEndDate() {
		return this.EndDate;
	}

	/**
	 * Sets a value to attribute EndDate. 
	 * @param newEndDate 
	 */
	public void setEndDate(String newEndDate) {
		this.EndDate = newEndDate;
	}

	/**
	 * Returns IDcontrainte.
	 * @return IDcontrainte 
	 */
	public Integer getIDcontrainte() {
		return this.IDcontrainte;
	}

	/**
	 * Sets a value to attribute IDcontrainte. 
	 * @param newIDcontrainte 
	 */
	public void setIDcontrainte(Integer newIDcontrainte) {
		this.IDcontrainte = newIDcontrainte;
	}

	/**
	 * Returns teachers.
	 * @return teachers 
	 */
	public Teacher getTeachers() {
		return this.teachers;
	}

	/**
	 * Sets a value to attribute teachers. 
	 * @param newTeachers 
	 */
	public void setTeachers(Teacher newTeachers) {
		if (this.teachers != null) {
			this.teachers.set(null);
		}
		this.teachers.set(this);
	}

}
