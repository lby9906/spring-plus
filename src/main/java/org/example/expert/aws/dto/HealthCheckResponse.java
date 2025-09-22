package org.example.expert.aws.dto;

import lombok.Getter;

@Getter
public class HealthCheckResponse {

	private String status;

	public HealthCheckResponse(String status) {
		this.status = status;
	}

	public static HealthCheckResponse of(String status) {
		return new HealthCheckResponse(status);
	}
}
