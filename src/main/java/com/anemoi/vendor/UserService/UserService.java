package com.anemoi.vendor.UserService;

import java.sql.SQLException;
import java.util.List;

import com.anemoi.vendor.UserModel.User;

public interface UserService {

	 

	User createNewUser(User user) throws SQLException, UserServiceException;

	User getUserById(String id) throws SQLException, UserServiceException;

	List<User> getAllUsers() throws UserServiceException;

	User updateUser(User user, String id) throws UserServiceException ;

	String deleteUser(String id) throws UserServiceException;
		
	
	

}
