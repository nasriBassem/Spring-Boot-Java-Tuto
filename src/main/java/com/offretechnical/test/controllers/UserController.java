package com.offretechnical.test.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offretechnical.test.models.User;
import com.offretechnical.test.services.UserService;

/**
 * Controller of the two requested services
 * 
 * @author bn
 *
 */
@CrossOrigin()
@RestController
@RequestMapping("/api")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * List users
	 * 
	 * 
	 * @return ResponseEntity<List<User>>
	 */
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			/**
			 * The list of users to return
			 */
			List<User> users = new ArrayList<>();
			/**
			 * user recovery
			 */
			userService.listAll().forEach(users::add);

			/**
			 * If null , reponse http 204
			 */
			if (users.isEmpty()) {
				logger.info("Liste des users vide");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			/**
			 * if the list is not empty, return the list with an http 200 code
			 */
			logger.info("Le nombre des users : {}", users.size());
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			/**
			 * Return Error 500 , en k d'erreur
			 */
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	/**
	 * 
	 * Creation of a user
	 * 
	 * @param user
	 * 
	 * @return ResponseEntity<User>
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
		/**
		 * User registration
		 */
		User userCreated = userService.save(new User(user.getBirthdate(), user.getUserName(), user.getCountry(),
				user.getPhoneNumber(), user.getGender()));

		/**
		 * Réponse HTTP 201 , user Created
		 */
		return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
	}
}
