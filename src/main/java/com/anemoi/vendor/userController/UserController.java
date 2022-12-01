package com.anemoi.vendor.userController;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.anemoi.vendor.UserModel.User;
import com.anemoi.vendor.UserService.UserService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

@Controller("/vendor/user")
public class UserController {

	@Inject
	private UserService userService;

	@Get("/{id}")
	public HttpResponse<User> getSingleUser(@PathVariable("id") String id)
			throws SQLException, UserControllerException {
		try {
			User user = this.userService.getUserById(id);
			return HttpResponse.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserControllerException("Unable to get user by user id " + e.getMessage());
		}

	}

	@Post("/")
	public User createUser(@Body User user) throws SQLException, UserControllerException {
		System.out.println(user);
		try {
			User newUser = this.userService.createNewUser(user);
			return newUser;
		} catch (Exception e) {
			throw new UserControllerException("Unable to create user " + e.getMessage());
		}
	}

	@Get("/list")
	public List<User> getAllUsers() throws UserControllerException {
		try {
			List<User> allUsers = this.userService.getAllUsers();
			return allUsers;
		} catch (Exception e) {
			throw new UserControllerException("unable to get users " + e.getMessage());
		}
	}

	@Patch("/{id}")
	public User updateUser(@Body User user, @PathVariable("id") String id)
			throws UserControllerException {
		try {
//			System.out.println("success");
			User updatedUser = this.userService.updateUser(user, id);
			return updatedUser;
		} catch (Exception e) {
			throw new UserControllerException("unable to update user " + e.getMessage());
		}
	}

	@Delete("/delete/{id}")
	public HttpResponse<String> deleteUser(@PathVariable("id") String id) throws UserControllerException {
		try {
			String response = this.userService.deleteUser(id);
			return HttpResponse.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserControllerException("user with " + id + " not found");
		}

	}

}
