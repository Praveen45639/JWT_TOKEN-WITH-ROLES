package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.MyUser;
import com.example.demo.repository.MyUserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	  @Autowired
	   private MyUserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<MyUser> user = repository.findByUsername(username);
	        if (user.isPresent()) {
	            var userObj = user.get();
	            return org.springframework.security.core.userdetails.User.builder()
	                    .username(userObj.getUsername())
	                    .password(userObj.getPassword())
	                    .roles(getRoles(userObj))
	                    .build();
	        } else {
	            throw new UsernameNotFoundException(username);
	        }
	}

	private String[] getRoles(MyUser user) {
		 if (user.getRole() == null) {
	            return new String[]{"USER"};
	        }
	        return user.getRole().split(",");
	    }
	}


