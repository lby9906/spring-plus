package org.example.expert.domain.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.example.expert.domain.user.repository.UserJdbcRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceTest {

	@Autowired
	private UserJdbcRepository userJdbcRepository;

	@Test
	@DisplayName("유저 데이터 500만건 생성")
	public void createUsersSignup() {
		int batchSize = 300;
		List<User> users = new ArrayList<>();

		for (int i = 5005001; i<=5010000; i++) {
			String nickname = UUID.randomUUID().toString().substring(0, 10) + i;
			users.add(User.of("test" + i + "@test.com", "pw", UserRole.USER, nickname));
			if (i % batchSize == 0) {
				userJdbcRepository.saveAll(users);
				users.clear();
			}
		}
	}

	@Test
	@DisplayName("유저 중복 닉네임 넣기")
	public void duplicateNicknames() {
		int batchSize = 1000;
		List<Long> ids = new ArrayList<>();

		for (int i = 4000000; i<5000000; i++) {
			ids.add((long)i);
		}

		for (int i = 0; i < ids.size(); i += batchSize) {
			int end = Math.min(i + batchSize, ids.size());
			List<Long> subList = ids.subList(i, end);
			userJdbcRepository.updateNicknames(subList, "test");
		}
	}
}