package org.example.expert.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setContentType("application/json; charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		PrintWriter writer = response.getWriter();
		writer.write("{\"status\": 403, \"message\": \"권한 없음.\"}");
		writer.flush();
	}
}
