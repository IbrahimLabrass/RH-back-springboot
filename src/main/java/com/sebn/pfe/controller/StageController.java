package com.sebn.pfe.controller;

import com.sebn.pfe.exception.ResourceNotFoundException;
import com.sebn.pfe.model.Stage;
import com.sebn.pfe.model.User;
import com.sebn.pfe.repository.StageRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing stages.
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class StageController {

    @Autowired
    private StageRepository stageRepository;
    private Optional<User> user;


    @GetMapping("/stages")
    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    @GetMapping("/stage/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found for this id :: " + id));
        return ResponseEntity.ok().body(stage);
    }

    @PostMapping("/stage")
    public Stage createStage(@Valid @RequestBody Stage stage) {
        return stageRepository.save(stage);
    }

    @PutMapping("/stages/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable(value = "id") Long id,
                                             @Valid @RequestBody Stage stageDetails) throws ResourceNotFoundException {
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found for this id :: " + id));

        stage.setTitle(stageDetails.getTitle());
        stage.setStartDate(stageDetails.getStartDate());
        stage.setEndDate(stageDetails.getEndDate());
        stage.setCompany(stageDetails.getCompany());
        stage.setDescription(stageDetails.getDescription());
        stage.setDocument(stageDetails.getDocument());
        stage.setUser(stageDetails.getUser());

        final Stage updatedStage = stageRepository.save(stage);
        return ResponseEntity.ok(updatedStage);
    }

    @DeleteMapping("/stages/{id}")
    public Map<String, Boolean> deleteStage(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found for this id :: " + id));

        stageRepository.delete(stage);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
