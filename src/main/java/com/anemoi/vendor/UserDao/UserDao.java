package com.anemoi.vendor.UserDao;

import java.sql.SQLException;
import java.util.List;

import com.anemoi.vendor.UserModel.User;

public interface UserDao {

	User createNewUser(User user, String dataBaseName) throws SQLException, UserDaoException;

	User getUserById(String id, String dataBaseName) throws SQLException, UserDaoException;

	List<User> getAllUsers(String dataBaseName) throws UserDaoException;

	User editUserById(User user, String id, String dataBaseName) throws UserDaoException, SQLException;

	void deleteUserById(String id, String dataBaseName) throws SQLException, UserDaoException;

}
