package com.wallet.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class UserServiceTest {

	@MockBean
	UserRepository userRepo;

	@Autowired
	UserService service;

	@BeforeEach
	public void setUp() {
		given(userRepo.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new User()));
	}

	@Test
	public void testFindByEmail() {

		Optional<User> user = service.findByEmail("email@teste.com");

		assertTrue(user.isPresent());

	}

}
