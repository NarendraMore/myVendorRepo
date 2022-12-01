package com.anemoi.vendor.UserService;

import java.lang.System.Logger;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.anemoi.vendor.UserConstants.UserQueryConstant;
import com.anemoi.vendor.UserDao.UserDao;
import com.anemoi.vendor.UserModel.User;
import com.anemoi.vendor.configuration.DatabaseName;


@Singleton
public class UserServiceImple implements UserService {

	private static final Object STATUS = "status";
	private static final Object SUCCESS = "success";
	private static final Object MSG = "msg";
	private static String DATABASENAME = "databasename";
	@Inject
	private UserDao userDao;

	private static String dataBaseName() {
		List<String> tenentList = DatabaseName.getAllTenant();
		for (String tenent : tenentList) {
			DATABASENAME = DatabaseName.dataBaseName(tenent);
		}
		return DATABASENAME;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User createNewUser(User user) throws SQLException, UserServiceException {
		try {

			String dataBaseName = UserServiceImple.dataBaseName();
			

//			aplyValidation(user);

			           User createNewUser = this.userDao.createNewUser(user, dataBaseName);
			           return createNewUser;
			           
		
			
		} catch (Exception e) {
			throw new UserServiceException("unable to create user " + e.getMessage());
		}
//		JSONObject reposneJSON = new JSONObject();
//		reposneJSON.put(STATUS, SUCCESS);
//		reposneJSON.put(MSG, "enter the valid name");
//		return reposneJSON.toString();
	
		
	}

	private void aplyValidation(User user) throws Exception {

		Pattern pattern;
		if(user.getManager()==null || user.getManager().isEmpty())
		{
			 throw new Exception("manager name cannot be null or empty");
		}
		
		if(user.getPartner()==null || user.getPartner().isEmpty())
		{
			 throw new Exception("partner name cannot be null or empty");
		}
		 if(user.getFirstName()==null || user.getFirstName().isEmpty())
		 {
			 throw new Exception("firstname cannot be null or empty");
		 }
		 pattern = Pattern.compile("[A-Z]{1}[a-z]{2,14}");
		boolean result = pattern.matcher(user.getFirstName()).matches();
		if (!result) {
			throw new Exception("invalid first name");
		}
		
		if(user.getLastName()==null || user.getLastName().isEmpty())
		 {
			 throw new Exception("lastname cannot be null or empty");
		 }
	
		 pattern = Pattern.compile("[A-Z]{1}[a-z]{2,14}");
		boolean result1 = pattern.matcher(user.getLastName()).matches();
		if (!result1) {
			throw new Exception("invalid last name");
		}
		
		 if(user.getMobileNumber()==null || user.getMobileNumber().isEmpty())
		 {
			 throw new Exception("mobileNumber cannot be null or empty");
		 }
		 pattern = Pattern.compile("[7-9]{1}[0-9]{9}");
		boolean result2 = pattern.matcher(user.getMobileNumber()).matches();
		if (!result2) {
			throw new Exception("invalid phone number formate");
		}
		
		if(user.getEmail()==null || user.getEmail().isEmpty())
		 {
			 throw new Exception("mobileNumber cannot be null or empty");
		 }
		String emailRegex = "([a-zA-Z0-9]+)([\\.{1}])?([a-zA-Z0-9]+)\\@(?:gmail|GMAIL)([\\.])(?:com|COM)";
		 pattern = Pattern.compile(emailRegex);
		boolean result3 = pattern.matcher(user.getEmail()).matches();
		if (!result3) {
			throw new Exception(user.getEmail() + " is invalid email formate");
		}
		
		
	}

	@Override
	public User getUserById(String id) throws SQLException, UserServiceException {

		try {
			String dataBaseName = UserServiceImple.dataBaseName();
			if (id == null || id.isEmpty()) {
				throw new UserServiceException("User id must not be null or empty");
			}
			User user = this.userDao.getUserById(id, dataBaseName);
			return user;

		} catch (Exception e) {
			throw new UserServiceException("unable to get user by id " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() throws UserServiceException {
		try {
				String dataBaseName = UserServiceImple.dataBaseName();
				List<User> users = this.userDao.getAllUsers(dataBaseName);
				JSONObject object = new JSONObject();
				JSONArray userJsonData = getJSONFromUserList(users);
				object.put(users, userJsonData);
				return users;
			
		} catch (Exception e) {
			throw new UserServiceException("unble to get users " + e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	private JSONArray getJSONFromUserList(List<User> users) {
		JSONArray array = new JSONArray();
		for (User user : users) {
			JSONObject object = buildJsonFromUser(user);
			array.add(object);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromUser(User users) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(UserQueryConstant.USERID, users.getId());
		jsonObject.put(UserQueryConstant.FIRST_NAME, users.getFirstName());
		jsonObject.put(UserQueryConstant.LAST_NAME, users.getLastName());
		jsonObject.put(UserQueryConstant.EMAIL, users.getEmail());
		jsonObject.put(UserQueryConstant.MOBILE, users.getMobileNumber());
		jsonObject.put(UserQueryConstant.USERNAME, users.getRoleName());
		jsonObject.put(UserQueryConstant.MANAGER, users.getManager());
		jsonObject.put(UserQueryConstant.PARTNER, users.getPartner());
		jsonObject.put(UserQueryConstant.STATUS, users.getUserStatus());
		jsonObject.put(UserQueryConstant.CREATEDON, users.getCreatedOn());
		jsonObject.put(UserQueryConstant.USERNAME, users.getRoleName());
		return jsonObject;

	}

	@SuppressWarnings("unchecked")
	@Override
	public User updateUser(User user, String id) throws UserServiceException {
		try {
			String dataBaseName = UserServiceImple.dataBaseName();
				if (id == null || id.isEmpty()) {
					throw new UserServiceException("user id can't be null or empty ");
				}
//				User user1 = this.userDao.getUserById(id, dataBaseName);
				User updatedUser = this.userDao.editUserById(user, id, dataBaseName);
				JSONObject object = new JSONObject();
				JSONObject jsonFromUser = buildJsonFromUpdatedUser(updatedUser);
				object.put(updatedUser, jsonFromUser);
				return updatedUser;

			
		} catch (Exception e) {
			throw new UserServiceException("Unable to update user " + e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromUpdatedUser(User users) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(UserQueryConstant.FIRST_NAME, users.getFirstName());
		jsonObject.put(UserQueryConstant.LAST_NAME, users.getLastName());
		jsonObject.put(UserQueryConstant.EMAIL, users.getEmail());
		jsonObject.put(UserQueryConstant.MOBILE, users.getMobileNumber());
		jsonObject.put(UserQueryConstant.USERNAME, users.getRoleName());
		jsonObject.put(UserQueryConstant.MANAGER, users.getManager());
		jsonObject.put(UserQueryConstant.PARTNER, users.getPartner());
		jsonObject.put(UserQueryConstant.CREATEDON, users.getCreatedOn());
		jsonObject.put(UserQueryConstant.STATUS, users.getUserStatus());
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String deleteUser(String id) throws UserServiceException {
		try {
			String dataBaseName = UserServiceImple.dataBaseName();
				if (id == null || id.isEmpty()) {
					throw new UserServiceException("userId cannot be null or empty ");
				}
				User user = userDao.getUserById(id, dataBaseName);
				if (user == null) {
					throw new UserServiceException("user not found");
				}
				this.userDao.deleteUserById(id, dataBaseName);
				JSONObject reposneJSON = new JSONObject();
				reposneJSON.put(STATUS, SUCCESS);
				reposneJSON.put(MSG, "User deleted suucessfully");
				return reposneJSON.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



}
