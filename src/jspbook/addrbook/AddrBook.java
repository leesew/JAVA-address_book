package jspbook.addrbook;

public class AddrBook {
	// 멤버 변수 선언
	private int abId;
	private String abName;
	private String abEmail;
	private String abTel;
	private String abBirth;
	private String abCompany;
	private String abMemo;
	
	public int getAbId() {
		return abId;
	}
	public void setAbId(int abId) {
		this.abId = abId;
	}
	public String getAbName() {
		return abName;
	}
	public void setAbName(String abName) {
		this.abName = abName;
	}
	public String getAbEmail() {
		return abEmail;
	}
	public void setAbEmail(String abEmail) {
		this.abEmail = abEmail;
	}
	public String getAbTel() {
		return abTel;
	}
	public void setAbTel(String abTel) {
		this.abTel = abTel;
	}
	public String getAbBirth() {
		return abBirth;
	}
	public void setAbBirth(String abBirth) {
		this.abBirth = abBirth;
	}
	public String getAbCompany() {
		return abCompany;
	}
	public void setAbCompany(String abCompany) {
		this.abCompany = abCompany;
	}
	public String getAbMemo() {
		return abMemo;
	}
	public void setAbMemo(String abMemo) {
		this.abMemo = abMemo;
	}
	@Override
	public String toString() {
		return "AddrBook [abId=" + abId + ", abName=" + abName + ", abEmail=" + abEmail + ", abTel=" + abTel
				+ ", abBirth=" + abBirth + ", abCompany=" + abCompany + ", abMemo=" + abMemo + "]";
	}
}

	