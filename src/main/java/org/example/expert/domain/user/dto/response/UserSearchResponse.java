package org.example.expert.domain.user.dto.response;

import lombok.Getter;

@Getter
public class UserSearchResponse {
	private Long id;
	private String email;
	private String nickname;

	public UserSearchResponse(Long id, String email, String nickname) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
	}

	public static UserSearchResponse of(Long id, String email, String nickname) {
		return new UserSearchResponse(id, email, nickname);
	}
}
