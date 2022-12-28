package com.web.backend;

import com.web.backend.user.AuthorityEntity;
import com.web.backend.user.UserEntity;
import com.web.backend.user.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebProjectApplication {
	@GetMapping("/message")
	public String getMessage(){
		return "for docker file test";
	}
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}

	@PostConstruct // 초기화 후 수행되는 매서드
	protected void init() {
		List<AuthorityEntity> authorityEntityList = new ArrayList<>();

		authorityEntityList.add(createAuthority("USER", "User role"));
		authorityEntityList.add(createAuthority("ADMIN", "Admin role"));

		UserEntity user = new UserEntity();
		user.setUserName("admin");
		user.setFirstName("test");
		user.setLastName("H");

		user.setPassword(passwordEncoder.encode("adminadmin"));
		user.setEnabled(true);
		user.setAuthorities(authorityEntityList);

		userDetailsRepository.save(user);

		List<AuthorityEntity> dummyAuthorityEntityList = new ArrayList<>();
		dummyAuthorityEntityList.add(createAuthority("USER", "User role"));

		UserEntity dummyUser = new UserEntity();
		dummyUser.setUserName("user");
		dummyUser.setFirstName("user");
		dummyUser.setLastName("H");

		dummyUser.setPassword(passwordEncoder.encode("useruser"));
		dummyUser.setEnabled(true);
		dummyUser.setAuthorities(dummyAuthorityEntityList);

		userDetailsRepository.save(dummyUser);

		UserEntity dummyUser2 = new UserEntity();

		List<AuthorityEntity> dummyAuthorityEntityList2 = new ArrayList<>();
		dummyAuthorityEntityList2.add(createAuthority("USER", "User role"));

		dummyUser2.setUserName("user2");
		dummyUser2.setFirstName("user2");
		dummyUser2.setLastName("H");

		dummyUser2.setPassword(passwordEncoder.encode("useruser"));
		dummyUser2.setEnabled(true);
		dummyUser2.setAuthorities(dummyAuthorityEntityList2);

		userDetailsRepository.save(dummyUser2);
	}

	private AuthorityEntity createAuthority(String roleCode, String roleDescription) {
		AuthorityEntity authority = new AuthorityEntity();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;

	}
}
