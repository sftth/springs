package sensing.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sensing.cmmn.model.LoginModel;
import sensing.login.dao.UserDao;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	UserDao userDao;
	
	@Override
	public void insertUser(LoginModel loginModel) {
		userDao.insertUser(loginModel);
	}
}
