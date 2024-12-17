package com.atividades.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atividades.DTOs.AuthenticationDTO;
import com.atividades.models.User;
import com.atividades.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> Get() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> GetById(@PathVariable(value = "id") long id) {
		Optional<User> user = userRepository.findById(id);
		if  (user.isPresent()) {
			return new ResponseEntity<User> (user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User Post(@RequestBody User user) {
		return userRepository.save (user);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> Put(@PathVariable(value = "id") long id, @RequestBody User newUser) {
		Optional<User> oldUser = userRepository.findById(id);
		if (oldUser.isPresent()) {
			User user = oldUser.get();
			user.setUsername(newUser.getUsername());
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			user.setRole(newUser.getRole());
			user.setImage(newUser.getImage());
			
			userRepository.save(user);
			return new ResponseEntity<User> (user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
	{
	    Optional<User> user = userRepository.findById(id);
	    if  (user.isPresent()) {
	    	userRepository.delete (user.get());
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@RequestMapping(value = "/user/updateUsername/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<User> updateUsername(@PathVariable(value = "id") long id, @RequestBody Map<String, String> body) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Obtem o novo username e senha do corpo da requisição
            String newUsername = body.get("username");
            String password = body.get("password");

            if (password == null || password.trim().isEmpty() || !new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            if (newUsername == null || newUsername.trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            user.setUsername(newUsername);
            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@RequestMapping(value = "/user/updateEmail/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<User> updateEmail(@PathVariable(value = "id") long id, @RequestBody Map<String, String> body) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Obtem o novo username e senha do corpo da requisição
            String newEmail = body.get("email");
            String password = body.get("password");

            if (password == null || password.trim().isEmpty() || !new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            if (newEmail == null || newEmail.trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            user.setEmail(newEmail);
            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@RequestMapping(value = "/user/updatePassword/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<User> updatePassword(@PathVariable(value = "id") long id, @RequestBody Map<String, String> body) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Obtem o novo username e senha do corpo da requisição
            String newPassword = body.get("newPassword");
            String password = body.get("password");

            if (password == null || password.trim().isEmpty() || !new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            if (newPassword == null || newPassword.trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String encryptedPassword = new BCryptPasswordEncoder().encode(newPassword);
            user.setPassword(encryptedPassword);
            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
