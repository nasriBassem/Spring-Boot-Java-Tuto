package com.offretechnical.test.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offretechnical.test.daos.UserRepository;
import com.offretechnical.test.models.User;
import com.offretechnical.test.models.dtos.UserDto;

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
	UserRepository userRepository;

	/**
	 * getAllUsers
	 * 
	 * @return ResponseEntity<List<User>>
	 */
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			/**
			 * La liste des users à retourner
			 */
			List<User> users = new ArrayList<>();
			/**
			 * la récupération des
			 */
			userRepository.findAll().forEach(users::add);

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
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Création d'un user
	 * 
	 * @param user
	 * 
	 * @return ResponseEntity<User>
	 */
	@PostMapping("/userCreate")
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDto user) {
		/**
		 * Enregistrement d'user
		 */
		User userCreated = userRepository.save(new User(user.getBirthdate(), user.getUserName(), user.getCountry(),
				user.getPhoneNumber(), user.getGender()));

		/**
		 * Réponse HTTP 201 , user crée
		 */
		return new ResponseEntity<>(userCreated, HttpStatus.OK);

	}
}
