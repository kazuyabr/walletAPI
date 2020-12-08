package com.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	private static String EMAIL = "teste@teste.com";

	@Before
	private void setUp() {

		User u = new User();
		u.setName("First");
		u.setPassword("first");
		u.setEmail(EMAIL);

		repository.save(u);

	}

	@After
	private void tearDown() {

		repository.deleteAll();

	}

	@Test
	public void testSave() {

		User u = new User();
		u.setName("teste");
		u.setPassword("teste");
		u.setEmail("teste@teste.com");

		User response = repository.save(u);

		assertNotNull(response);

	}

	@Test
	public void testFindUser() {
		Optional<User> response = repository.findByEmailEquals(EMAIL);

		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}

}
