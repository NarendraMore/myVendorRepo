package com.anemoi.vendor.UserDao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anemoi.vendor.UserConstants.UserQueryConstant;
import com.anemoi.vendor.UserModel.User;
import com.anemoi.vendor.databaseConfiguration.VendorDatabaseUtill;

@Singleton
public class UserDaoImpl implements UserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public User createNewUser(User user, String dataBaseName) throws UserDaoException, SQLException {

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = VendorDatabaseUtill.getConnection();

			LOGGER.debug("inserting the data");
			pstmt = connection.prepareStatement(UserQueryConstant.INSERT_INTO_USERDETAILS
					.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));

			String userId = UUID.randomUUID().toString();
			user.setId(userId);
			String id = user.getId();
			System.out.println(id+" "+user);
			Date date = new Date();

			pstmt.setString(1, id);
			pstmt.setString(2, user.getRoleName());

			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setString(5, user.getMobileNumber());
			pstmt.setString(6, user.getEmail());
			pstmt.setString(7, user.getManager());
			pstmt.setString(8, user.getPartner());
			pstmt.setString(9, user.getUserStatus());
			pstmt.setLong(10, date.getTime());		
			pstmt.executeUpdate();
			System.out.println("ssample data"+user+" !!dataaaaa");
			System.out.println("ssample data"+pstmt.toString()+" !!dataaaaa");

			return user;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new UserDaoException("Unable to create user " + e.getMessage());
		} finally {
			LOGGER.info("closing the connections");
			VendorDatabaseUtill.close(pstmt, connection);
		}

	}

	@Override
	public User getUserById(String id, String dataBaseName) throws SQLException, UserDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(UserQueryConstant.SELECT_USER_BY_ID
					.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, id);
			result = pstmt.executeQuery();
			while (result.next()) {
				User user = buildUser(result);
				return user;
			}
		} catch (Exception e) {
			LOGGER.error("User not found" + e.getMessage());
			throw new UserDaoException("unable to get user by id" + e.getMessage());
		} finally {
			LOGGER.debug("closing the connections");
			VendorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;

	}

	private User buildUser(ResultSet result) throws SQLException {
		User user = new User();
		user.setId(result.getString(UserQueryConstant.USERID));
		user.setFirstName(result.getString(UserQueryConstant.FIRST_NAME));
		user.setLastName(result.getString(UserQueryConstant.LAST_NAME));
		user.setMobileNumber(result.getString(UserQueryConstant.MOBILE));
		user.setEmail(result.getString(UserQueryConstant.EMAIL));
		user.setManager(result.getString(UserQueryConstant.MANAGER));
		user.setPartner(result.getString(UserQueryConstant.PARTNER));
		user.setUserStatus(result.getString(UserQueryConstant.STATUS));
		user.setCreatedOn(result.getLong(UserQueryConstant.CREATEDON));
		user.setRoleName(result.getString(UserQueryConstant.USERNAME));

		return user;
	}

	@Override
	public List<User> getAllUsers(String dataBaseName) throws UserDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<User> listOfUsers = new ArrayList<>();
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					UserQueryConstant.SELECT_ALL_USERS.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				User user = buildUser(result);
				listOfUsers.add(user);
			}
			return listOfUsers;
		} catch (Exception e) {
			LOGGER.error("unble to get list of user" + e.getMessage());
			throw new UserDaoException("Unable to get list of Users " + e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			VendorDatabaseUtill.close(result, pstmt, connection);
		}

	}

	@Override
	public User editUserById(User user, String id, String dataBaseName) throws SQLException, UserDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in update user database name is ::" + dataBaseName + " userId is ::" + id + " request user is ::"
				+ user);

		try {

			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					UserQueryConstant.UPDATE_USER.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getMobileNumber());
			pstmt.setString(5, user.getRoleName());
			pstmt.setString(6, user.getManager());
			pstmt.setString(7, user.getPartner());
			pstmt.setLong(8, date.getTime());
			pstmt.setString(9, user.getUserStatus());
			pstmt.setString(10, id);

			int executeUpdate = pstmt.executeUpdate();

			System.out.println(executeUpdate);
			LOGGER.info(executeUpdate + " User updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserDaoException("unable to update user " + e.getMessage());
		} finally {
			LOGGER.debug("closing the connections");
			VendorDatabaseUtill.close(pstmt, connection);
		}
		return user;
	}

	@Override
	public void deleteUserById(String id, String dataBaseName) throws SQLException, UserDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(UserQueryConstant.DELETE_USER_BY_ID
					.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, id);
			int executeUpdate = pstmt.executeUpdate();
			LOGGER.info(executeUpdate + " user deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserDaoException("unble to delete user " + e.getMessage());
		} finally {
			LOGGER.debug("closing the connections");
			VendorDatabaseUtill.close(pstmt, connection);
		}

	}

}
