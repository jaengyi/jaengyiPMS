package com.example.projectservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    // POST /projects: Create a new project
    @PostMapping
    public ResponseEntity<String> createProject() {
        // ... service logic to create a project
        return ResponseEntity.ok("Project created");
    }

    // GET /projects/{projectId}: Get project details
    @GetMapping("/{projectId}")
    public ResponseEntity<String> getProjectDetails(@PathVariable String projectId) {
        // ... service logic to get project details including WBS
        return ResponseEntity.ok("Project details for " + projectId);
    }

    // POST /projects/{projectId}/tasks: Add a new task to a project
    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<String> addTaskToProject(@PathVariable String projectId) {
        // ... service logic to add a task
        return ResponseEntity.ok("Task added to project " + projectId);
    }

    // POST /projects/{projectId}/members: Add a member to a project
    @PostMapping("/{projectId}/members")
    public ResponseEntity<String> addMemberToProject(@PathVariable String projectId) {
        // ... service logic to add a member
        return ResponseEntity.ok("Member added to project " + projectId);
    }
}

@RestController
@RequestMapping("/tasks")
class TaskController {
    // PUT /tasks/{taskId}: Update task details
    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable String taskId) {
        // ... service logic to update a task
        return ResponseEntity.ok("Task " + taskId + " updated");
    }
}