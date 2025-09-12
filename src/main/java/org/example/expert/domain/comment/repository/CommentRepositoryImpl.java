package org.example.expert.domain.comment.repository;

import static org.example.expert.domain.comment.entity.QComment.*;
import static org.example.expert.domain.user.entity.QUser.*;

import java.util.List;

import org.example.expert.domain.comment.entity.Comment;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentQuerydslRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Comment> findByTodoIdWithUser(Long todoId) {
		return jpaQueryFactory.selectFrom(comment)
			.join(comment.user, user).fetchJoin()
			.where(eqTodoId(todoId))
			.fetch();
	}

	private BooleanExpression eqTodoId(Long todoId) {
		return todoId != null ? comment.todo.id.eq(todoId) : null;
	}
}
