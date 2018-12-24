package sensing.cmmn.model;

public class ResponseModel {
	private String success;
	private String trCode;
	private String trMsg;
	private Object data;
	private final static String AJAXTAG = "ajaxtags";
	
	public ResponseModel() {
		
	}
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getTrCode() {
		return trCode;
	}
	public void setTrCode(String trCode) {
		this.trCode = trCode;
	}
	public String getTrMsg() {
		return trMsg;
	}
	public void setTrMsg(String trMsg) {
		this.trMsg = trMsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static String getAjaxtag() {
		return AJAXTAG;
	}

}
