package sensing.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sensing.cmmn.model.LoginModel;

@Service
public class UserDaoImpl implements UserDao{
	@Autowired
	SqlSession sqlSession ;
	
	@Override
	public void insertUser(LoginModel loginModel) {
		sqlSession.insert("login.insertUser", loginModel);
	}

    @Override
    public LoginModel selectUser(String email) {
        return sqlSession.selectOne ("login.selectUser", email);
    }
}
