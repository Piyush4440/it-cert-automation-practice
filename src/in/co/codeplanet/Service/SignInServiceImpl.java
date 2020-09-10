package in.co.codeplanet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.codeplanet.Dao.SignInDao;
import in.co.codeplanet.Model.UserDetails;
import in.co.codeplanet.Output.SignInOutput;

@Service
public class SignInServiceImpl implements SignInService {
	
	@Autowired
	SignInDao signInDao;

	@Override
	public SignInOutput userSignUp(UserDetails userDetails) {
		return signInDao.userSignUp(userDetails);
	}

	@Override
	public SignInOutput userSignIn(UserDetails userDetails) {
		return signInDao.userSignIn(userDetails);
	}

}
