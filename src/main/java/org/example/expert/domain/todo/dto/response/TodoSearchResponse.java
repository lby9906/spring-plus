package org.example.expert.domain.todo.dto.response;


import lombok.Getter;

@Getter
public class TodoSearchResponse {
	private final String title;
	private final Long managerCount;
	private final Long totalComments;

	public TodoSearchResponse(String title, Long managerCount, Long totalComments) {
		this.title = title;
		this.managerCount = managerCount;
		this.totalComments = totalComments;
	}

	public static TodoSearchResponse of(String title, Long managerCount, Long totalComments) {
		return new TodoSearchResponse(title, managerCount, totalComments);
	}
}
