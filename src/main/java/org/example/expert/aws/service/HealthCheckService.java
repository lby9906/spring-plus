package org.example.expert.aws.service;

import org.example.expert.aws.dto.HealthCheckResponse;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthCheckService {

	public HealthCheckResponse checkApplicationHealth() {
		return HealthCheckResponse.of("UP");
	}
}
