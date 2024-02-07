package com.greatlearning.Employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.Employee.model.User;
import com.greatlearning.Employee.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repository;

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

}
