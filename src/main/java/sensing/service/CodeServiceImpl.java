package sensing.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import sensing.model.Code;


@Service
public class CodeServiceImpl implements CodeService{

	public List<Code> getAllCodes() {
		List<Code> codes = new LinkedList<Code> ();
		codes.add(new Code("k1","code1"));
		codes.add(new Code("k2","code2"));
		codes.add(new Code("k3","code3"));
		codes.add(new Code("k4","code4"));
		return codes;
	}

}
