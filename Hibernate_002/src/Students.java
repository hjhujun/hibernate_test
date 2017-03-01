import java.sql.Blob;
import java.util.Date;

public class Students {

	private int stid;
	private String sname;
	private String gender;
	private Date birthday;
	private String address;
	private Blob picture;

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob image) {
		this.picture = image;
	}

	public Students() {

	}

	public Students(int stid, String sname, String gender, Date birthday, String address) {
		// super();
		this.stid = stid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Students [stid=" + stid + ", sname=" + sname + ", gender=" + gender + ", birthday=" + birthday
				+ ", address=" + address +  "]";
	}

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
