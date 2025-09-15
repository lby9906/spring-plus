package org.example.expert.domain.todo.repository;

import static org.example.expert.domain.comment.entity.QComment.*;
import static org.example.expert.domain.manager.entity.QManager.*;
import static org.example.expert.domain.todo.entity.QTodo.*;
import static org.example.expert.domain.user.entity.QUser.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoQuerydslRepository{

	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public List<TodoSearchResponse> findAllByTodo(
		String title, String nickname,
		long page, long size, LocalDate startDate, LocalDate endDate) {

		return jpaQueryFactory.select(
			Projections.constructor(TodoSearchResponse.class,
				todo.title.as("title"),
				manager.id.countDistinct().as("managerCount"),
				comment.id.countDistinct().as("totalComments")))
			.from(todo)
			.leftJoin(todo.managers, manager)
			.leftJoin(manager.user, user)
			.leftJoin(todo.comments, comment)
			.where(likeTitle(title),
				likeNickname(nickname),
				createdDateBetween(startDate, endDate))
			.groupBy(todo.id)
			.orderBy(todo.createdAt.desc())
			.offset(Math.max(page, 0) * size)
			.limit(size)
			.fetch();
	}

	@Override
	public Long countByTodos() {
		return jpaQueryFactory
			.select(todo.countDistinct())
			.from(todo)
			.fetchOne();
	}

	private BooleanExpression likeTitle(String title) {
		return StringUtils.hasText(title) ? todo.title.contains(title) : null;
	}

	private BooleanExpression likeNickname(String nickname) {
		return StringUtils.hasText(nickname) ? user.nickname.contains(nickname) : null;
	}

	private BooleanExpression createdDateBetween(LocalDate startDate, LocalDate endDate) {
		BooleanExpression startCondition = startDate != null ? todo.createdAt.goe(startDate.atStartOfDay()) : null;
		BooleanExpression endCondition = endDate != null ? todo.createdAt.loe(endDate.atTime(LocalTime.MAX)) : null;

		return Expressions.allOf(startCondition, endCondition);
	}
}
