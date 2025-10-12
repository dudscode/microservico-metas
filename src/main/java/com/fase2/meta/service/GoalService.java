package com.fase2.meta.service;

import com.fase2.meta.model.Goal;
import com.fase2.meta.model.StatusGoal;
import com.fase2.meta.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserService userService;

    public Optional<Goal> save(String IdUser, Goal goal) {
        if (userService.findById(IdUser).isEmpty()) {
            return Optional.empty();
        }
        goal.setStatus(StatusGoal.PENDING);
        goal.setUser(userService.findById(IdUser).get());
        return Optional.of(goalRepository.save(goal));
    }

    public Optional<List<Goal>> allFindByIdUser(String IdUser) {
        if (userService.findById(IdUser).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(goalRepository.findByUserId(IdUser));
    }
}
