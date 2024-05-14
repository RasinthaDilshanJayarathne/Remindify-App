package com.app.remindifyapp;

import com.app.remindifyapp.entities.User;
import com.app.remindifyapp.repository.UserRepo;
import com.app.remindifyapp.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RemindifyappApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(RemindifyappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepo.findByRole(Role.admin);
		if (null == adminAccount) {
			User user = new User();

			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setSecondName("admin");
			user.setRole(Role.admin);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));

			userRepo.save(user);
		}
	}
}
