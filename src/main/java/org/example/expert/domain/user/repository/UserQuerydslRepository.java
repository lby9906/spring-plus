package org.example.expert.domain.user.repository;

import java.util.List;

import org.example.expert.domain.user.dto.response.UserSearchResponse;

public interface UserQuerydslRepository {
	List<UserSearchResponse> findAllByUser(String nickname);
}
