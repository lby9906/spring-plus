package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoQuerydslRepository {

    @Query("SELECT t FROM Todo t "
        + "LEFT JOIN FETCH t.user u "
        + "WHERE (:weather IS NULL OR t.weather LIKE %:weather%) AND "
        + "(:startDate IS NULL OR :endDate IS NULL OR DATE(t.modifiedAt) BETWEEN :startDate AND :endDate) "
        + "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(
        Pageable pageable,
        @Param("weather") String weather,
        @Param("startDate") LocalDate startDate,
        @Param("endDate")LocalDate endDate);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);
}
