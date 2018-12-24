package sensing.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class Remote {
	@Autowired
	RestTemplate template;
	
	public String returnString() {
		String result="";
		return result;
	}
}
