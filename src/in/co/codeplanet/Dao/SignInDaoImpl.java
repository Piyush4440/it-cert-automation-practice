package in.co.codeplanet.Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.co.codeplanet.Model.UserDetails;
import in.co.codeplanet.Output.SignInOutput;

@Repository
public class SignInDaoImpl implements SignInDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public SignInOutput userSignUp(UserDetails userDetails) {
		SignInOutput signUpStatus = new SignInOutput();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		int userId = -1;
		
		final String procedureCall = "{call User_Sign_In(?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "user_sign_up");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, userDetails.getFirst_Name());
			callableSt.setString(5, userDetails.getLast_Name());
			callableSt.setString(6, userDetails.getEmail_ID());
			callableSt.setString(7, userDetails.getMobile_Number());
			callableSt.setString(8, userDetails.getPassword());
			callableSt.setString(9, userDetails.getProfile_Pic_Url());
			callableSt.setString(10, null);
			
			boolean insertFlag = callableSt.execute();
			connection.commit();
			
			if (insertFlag == true) 
			{
				ResultSet rs = callableSt.getResultSet();
				if (rs.next())
				{
					if(rs.getInt("status") == 0) {
						errors.put("Message", "Sign-Up Unsuccessful... Mobile Number Already exists...");
						dataList.add(errors);
						signUpStatus.setError(dataList);
						signUpStatus.setStatus(false);						
					} else if(rs.getInt("status") == -1) {
						errors.put("Message", "Sign-Up Unsuccessful... Email_ID Already exists...");
						dataList.add(errors);
						signUpStatus.setError(dataList);
						signUpStatus.setStatus(false);						
					} else {
						
						userId = rs.getInt("User_Id");
						
						data.put("Message", "Sign-Up Successful...");
						dataList.add(data);
						signUpStatus.setError(dataList);
						signUpStatus.setStatus(true);
					}
				}
				
			} else {
				errors.put("Message", "Sign-Up Unsuccessful... Error in Adding User...");
				dataList.add(errors);
				signUpStatus.setError(dataList);
				signUpStatus.setStatus(false);
			}
			
			if(userId != -1) {
				try {
					connection = jdbcTemplate.getDataSource().getConnection();
					connection.setAutoCommit(false);
					CallableStatement callableSt1 = connection.prepareCall(procedureCall);
					callableSt1.setString(1, "add_user_details");
					callableSt1.setInt(2, userId);
					callableSt1.setInt(3, Integer.parseInt(userDetails.getRole_Id()));
					callableSt1.setString(4, null);
					callableSt1.setString(5, null);
					callableSt1.setString(6, null);
					callableSt1.setString(7, null);
					callableSt1.setString(8, null);
					callableSt1.setString(9, null);
					callableSt1.setString(10, null);
					boolean flag = callableSt1.execute();
					connection.commit();
				} catch(Exception e1) {
					errors.put("Message", "Error in Updating User Role");
					dataList.add(errors);
					signUpStatus.setError(dataList);
					signUpStatus.setStatus(false);
					
					e1.printStackTrace();			
				}
			}
			
		}
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			signUpStatus.setError(dataList);
			signUpStatus.setStatus(false);
			
			e.printStackTrace();
		}
		return signUpStatus;
	}

	@Override
	public SignInOutput userSignIn(UserDetails userDetails) {
		SignInOutput userData = new SignInOutput();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		final String procedureCall = "{call User_Sign_In(?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "user_sign_in");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setString(6, userDetails.getEmail_ID());
			callableSt.setString(7, null);
			callableSt.setString(8, userDetails.getPassword());
			callableSt.setString(9, null);
			callableSt.setString(10, null);
			
			ResultSet rs  = callableSt.executeQuery();
			
			if(rs.next())
			{
				int status = rs.getInt("Status");
				
				if(status == -1) {
					errors.put("Message", "Login Unsuccessful... Password Incorrect...");
					dataList.add(errors);
					userData.setError(dataList);
					userData.setStatus(false);
				} else if(status == 0) {
					errors.put("Message", "Invalid Credentials... User does not exist...");
					dataList.add(errors);
					userData.setError(dataList);
					userData.setStatus(false);					
				} else {
					userDetails.setUser_Id(String.valueOf(rs.getInt("User_Id")));
					userDetails.setRole_Id(String.valueOf(rs.getInt("User_Role_Id")));
					userDetails.setUser_Role(rs.getString("Role_Title"));
					userDetails.setFirst_Name(rs.getString("First_Name"));
					userDetails.setLast_Name(rs.getString("Last_Name"));
					userDetails.setEmail_ID(rs.getString("Email_Id"));
					userDetails.setPassword(null);
					userDetails.setMobile_Number(rs.getString("Mobile_No"));
					userDetails.setIs_Google_Login(String.valueOf(rs.getInt("Is_Google_Login")));
					userDetails.setIs_Verified(rs.getString("Is_Verified"));
					
					
					data.put("Message", "Login Successful...");
					dataList.add(data);
					dataList.add(userDetails);
					userData.setData(dataList);
					userData.setStatus(true);					
				}
			}
		}
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			userData.setError(dataList);
			userData.setStatus(false);
			
			e.printStackTrace();
		}
		return userData;
	}

}
