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

import com.offretechnical.test.annotations.LogExecutionTime;
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
	 * getAllUsers
	 * 
	 * @return ResponseEntity<List<User>>
	 */
	@GetMapping("/users")
	@LogExecutionTime
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			/**
			 * La liste des users à retourner
			 */
			List<User> users = new ArrayList<>();
			/**
			 * la récupération des
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
			 * if liste pas vide , return la liste avec un code http 200
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
	 * Création d'un user
	 * 
	 * @param user
	 * 
	 * @return ResponseEntity<User>
	 */
	@PostMapping("/users")
	@LogExecutionTime
	public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
		/**
		 * Enregistrement d'user
		 */
		User userCreated = userService.save(new User(user.getBirthdate(), user.getUserName(), user.getCountry(),
				user.getPhoneNumber(), user.getGender()));

		/**
		 * Réponse HTTP 201 , user crée
		 */
		return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
	}
}
