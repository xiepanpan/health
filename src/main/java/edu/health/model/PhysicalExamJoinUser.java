package edu.health.model;

public class PhysicalExamJoinUser extends PhysicalExam{
	
	private String stuName;
	private String stuAvatar;
	private String doctName;
	private String doctAvatar;
	private String tel;
	@Override
	public String toString() {
		return "PhysicalExamJoinUser [stuName=" + stuName + ", stuAvatar=" + stuAvatar + ", doctName=" + doctName
				+ ", doctAvatar=" + doctAvatar + ", tel=" + tel + "]";
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuAvatar() {
		return stuAvatar;
	}
	public void setStuAvatar(String stuAvatar) {
		this.stuAvatar = stuAvatar;
	}
	public String getDoctName() {
		return doctName;
	}
	public void setDoctName(String doctName) {
		this.doctName = doctName;
	}
	public String getDoctAvatar() {
		return doctAvatar;
	}
	public void setDoctAvatar(String doctAvatar) {
		this.doctAvatar = doctAvatar;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	

}
