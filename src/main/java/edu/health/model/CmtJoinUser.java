package edu.health.model;

public class CmtJoinUser extends CmtWithBLOBs {

	private String stuName;
	private String doctName;
	@Override
	public String toString() {
		return "CmtJoinUser [stuName=" + stuName + ", doctName=" + doctName + "]";
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getDoctName() {
		return doctName;
	}
	public void setDoctName(String doctName) {
		this.doctName = doctName;
	}
	
	
	
}
