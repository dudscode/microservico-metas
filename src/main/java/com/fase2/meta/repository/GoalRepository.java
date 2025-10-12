package com.fase2.meta.repository;


import com.fase2.meta.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, String> {
    List<Goal> findByUserId(String userId);
}
