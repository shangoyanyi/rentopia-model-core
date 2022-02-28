package com.rentopia.model.core.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentopia.model.core.entity.User;
import com.rentopia.model.core.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	//@RequestMapping(value="/user/add", method=RequestMethod.POST)
	@RequestMapping(value="/user/add")
	public String postUser() {
		User newbee = User.builder().name("newbee").gender("M").build();
							
		
		// @NOTE: In most cases, we'll be using the save() method.
		userRepository.save(newbee);
		
		// @NOTE: Use this method when our business logic needs to read the saved changes at a later point during the same transaction but before the commit.
		// userRepository.saveAndFlush(newbee);
		
		
		return "add newbee done: " + newbee.toString();
	}
	
	
	@RequestMapping("/user/findAll")
	public List<User> findAllUsers() {
		
		List<User> allUsers = userRepository.findAll();
		
		log.info("find all newbees done, there are " + allUsers.size() + " newbees");
		
		for(User user:allUsers) {
			log.info(user.toString());
		}
		
		return allUsers;
	}

}
