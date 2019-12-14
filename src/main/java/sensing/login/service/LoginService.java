package sensing.login.service;

import sensing.cmmn.model.LoginModel;

public interface LoginService {
	public void insertUser(LoginModel loginModel) ;

    public LoginModel getloginModel(String email);
}
