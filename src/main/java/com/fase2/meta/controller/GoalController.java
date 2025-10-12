package com.fase2.meta.controller;

import com.fase2.meta.model.Goal;
import com.fase2.meta.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping("/create/{idUser}")
    public ResponseEntity<CollectionModel<EntityModel<Goal>>> createGoal(@PathVariable String idUser, @RequestBody Goal goal) {
        Optional<Goal> goalSave = goalService.save(idUser, goal);
        if (goalSave.isPresent()) {
            Goal saved = goalSave.get();
            List<EntityModel<Goal>> entities = List.of(EntityModel.of(saved));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaTypes.HAL_JSON)
                    .body(CollectionModel.of(entities,
                            linkTo(methodOn(GoalController.class).getGoal(idUser)).withRel("metas").withType("GET")
                    ));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping("/{idUser}")
    public ResponseEntity<CollectionModel<EntityModel<Goal>>> getGoal(@PathVariable String idUser) {
        Optional<List<Goal>> goals = goalService.allFindByIdUser(idUser);
        if (goals.isPresent()) {
            List<Goal> list = goals.get();
            List<EntityModel<Goal>> entities = list.stream().map(EntityModel::of).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaTypes.HAL_JSON)
                    .body(CollectionModel.of(entities,
                            linkTo(methodOn(GoalController.class).createGoal(idUser, null))
                                    .withRel("create")
                                    .withType("POST")
                    ));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
