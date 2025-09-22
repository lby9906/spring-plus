package org.example.expert.aws.controller;

import org.example.expert.aws.dto.HealthCheckResponse;
import org.example.expert.aws.service.HealthCheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

	private final HealthCheckService healthCheckService;

	@GetMapping("/health")
	public ResponseEntity<HealthCheckResponse> healthCheck() {
		return ResponseEntity.ok(healthCheckService.checkApplicationHealth());
	}
}
