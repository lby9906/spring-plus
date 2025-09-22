package org.example.expert.domain.user.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.example.expert.domain.user.entity.User;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserJdbcRepository {

	private final JdbcTemplate jdbcTemplate;

	@Transactional
	public void saveAll(List<User> users) {
		jdbcTemplate.batchUpdate("INSERT INTO users (email, password, nickname, user_role, created_at, modified_at) " +
				"VALUES (?, ?, ?, ?, ?, ?)",
			new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					User user = users.get(i);
					ps.setString(1, user.getEmail());
					ps.setString(2, user.getPassword());
					ps.setString(3, user.getNickname());
					ps.setString(4, user.getUserRole().name());

					Timestamp now = Timestamp.valueOf(LocalDateTime.now());
					ps.setTimestamp(5, now);
					ps.setTimestamp(6, now);
				}

				@Override
				public int getBatchSize() {
					return users.size();
				}
			});
	}

	@Transactional
	public void updateNicknames(List<Long> userIds, String nickname) {
		jdbcTemplate.batchUpdate(
			"UPDATE users SET nickname = ?, modified_at = ? WHERE id = ?",
			new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, nickname);
					ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
					ps.setLong(3, userIds.get(i));
				}

				@Override
				public int getBatchSize() {
					return userIds.size();
				}
			}
		);
	}

}
