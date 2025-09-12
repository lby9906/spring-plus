package org.example.expert.domain.comment.repository;

import java.util.List;

import org.example.expert.domain.comment.entity.Comment;

public interface CommentQuerydslRepository {
	List<Comment> findByTodoIdWithUser(Long todoId);
}
