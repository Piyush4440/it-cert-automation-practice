package in.co.codeplanet.Dao;

import in.co.codeplanet.Model.UserDetails;
import in.co.codeplanet.Output.SignInOutput;

public interface SignInDao {

	SignInOutput userSignUp(UserDetails userDetails);

	SignInOutput userSignIn(UserDetails userDetails);

}
