package org.example.expert.domain.todo.repository;

import java.time.LocalDate;
import java.util.List;

import org.example.expert.domain.todo.dto.response.TodoSearchResponse;

public interface TodoQuerydslRepository {
	List<TodoSearchResponse> findAllByTodo(String title, String nickname, long page, long size, LocalDate startDate, LocalDate endDate);

	Long countByTodos();
}
