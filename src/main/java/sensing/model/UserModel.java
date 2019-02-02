package sensing.model;

import org.springframework.format.annotation.NumberFormat;

public class UserModel {
	private int id;
	private String name;
	private String password;
	
	@NumberFormat(pattern="###,##0")
	private int point;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
