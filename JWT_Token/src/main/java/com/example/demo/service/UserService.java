package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MyUser;
import com.example.demo.repository.MyUserRepository;

@Service
public class UserService {

	@Autowired
	private MyUserRepository repository;
	
	public MyUser createUser(MyUser user) {
	return repository.save(user);
	}
	
	public List<MyUser> getAll(){
		return repository.findAll();
	}
}
