package com.wallet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public User save(User u) {
		return userRepo.save(u);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepo.findByEmailEquals(email);
	}

}
