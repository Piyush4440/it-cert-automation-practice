package in.co.codeplanet.Service;

import in.co.codeplanet.Model.UserDetails;
import in.co.codeplanet.Output.SignInOutput;

public interface SignInService {

	SignInOutput userSignUp(UserDetails userDetails);

	SignInOutput userSignIn(UserDetails userDetails);

}
