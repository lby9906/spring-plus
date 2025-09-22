package org.example.expert.domain.user.repository;

import static org.example.expert.domain.user.entity.QUser.*;

import java.util.List;

import org.example.expert.domain.user.dto.response.UserSearchResponse;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserQuerydslRepository {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<UserSearchResponse> findAllByUser(String nickname) {
		return jpaQueryFactory.select(
				Projections.constructor(UserSearchResponse.class,
					user.id.as("id"),
					user.email.as("email"),
					user.nickname.as("nickname"))
			).from(user)
			.where(eqNickname(nickname))
			.fetch();
	}

	private BooleanExpression eqNickname(String nickname) {
		return StringUtils.hasText(nickname) ? user.nickname.eq(nickname) : null;
	}

}
