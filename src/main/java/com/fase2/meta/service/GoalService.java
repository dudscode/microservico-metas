package com.fase2.meta.service;

import com.fase2.meta.dto.StatusDTO;
import com.fase2.meta.model.Goal;
import com.fase2.meta.model.StatusGoal;
import com.fase2.meta.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserService userService;

    @CacheEvict(cacheNames = "goals", allEntries = true)
    public Optional<Goal> save(String IdUser, Goal goal) {
        if (userService.findById(IdUser).isEmpty()) {
            return Optional.empty();
        }
        goal.setStatus(StatusGoal.PENDING);
        goal.setUser(userService.findById(IdUser).get());
        return Optional.of(goalRepository.save(goal));
    }

    @Cacheable(cacheNames = "goals", key = "#root.method.name")
    public Optional<List<Goal>> allFindByIdUser(String IdUser) {
        if (userService.findById(IdUser).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(goalRepository.findByUserId(IdUser));
    }
    @CacheEvict(cacheNames = "goals", allEntries = true)
    public Optional<Goal> updateGoalById(String idGoal, StatusDTO statusGoal) {
        Optional<Goal> goal = goalRepository.findById(idGoal);
        goal.ifPresent(value ->
                {
                    value.setStatus(statusGoal.status());
                    goalRepository.save(value);
                }

        );
        return goal;

    }
}
