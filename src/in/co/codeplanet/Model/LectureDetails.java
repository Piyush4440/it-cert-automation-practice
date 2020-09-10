package in.co.codeplanet.Model;

public class LectureDetails {
	
	private String User_Id;
	private String Course_Id;
	private String Lecture_Id;
	private String Lecture_Name;
	private String Lecture_Description;
	private String Is_Active;
	private String Created_By;
	
	public String getLecture_Id() {
		return Lecture_Id;
	}
	public void setLecture_Id(String lecture_Id) {
		Lecture_Id = lecture_Id;
	}
	public String getLecture_Name() {
		return Lecture_Name;
	}
	public void setLecture_Name(String lecture_Name) {
		Lecture_Name = lecture_Name;
	}
	public String getLecture_Description() {
		return Lecture_Description;
	}
	public void setLecture_Description(String lecture_Description) {
		Lecture_Description = lecture_Description;
	}
	public String getUser_Id() {
		return User_Id;
	}
	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}
	public String getCourse_Id() {
		return Course_Id;
	}
	public void setCourse_Id(String course_Id) {
		Course_Id = course_Id;
	}
	public String getIs_Active() {
		return Is_Active;
	}
	public void setIs_Active(String is_Active) {
		Is_Active = is_Active;
	}
	public String getCreated_By() {
		return Created_By;
	}
	public void setCreated_By(String created_By) {
		Created_By = created_By;
	}
	
	

}
