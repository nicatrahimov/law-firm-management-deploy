package com.rahimov.lawfirmmanagementdeploy.controller;

import com.rahimov.lawfirmmanagementdeploy.dto.TaskDto;
import com.rahimov.lawfirmmanagementdeploy.model.Task;
import com.rahimov.lawfirmmanagementdeploy.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    @GetMapping()
    public ResponseEntity<List<TaskDto>> getAllTasks(){
return new
        ResponseEntity<>(taskService.getAllTasks(),
        HttpStatusCode.valueOf(200));
    }
@GetMapping("/{id}")
public ResponseEntity<TaskDto>getTaskById(@PathVariable Long id){
    return new
            ResponseEntity<>(taskService.getTaskById(id),HttpStatusCode.valueOf(200));
}
@PostMapping()
    public ResponseEntity<String> addTask (@RequestBody TaskDto task){
    return new
            ResponseEntity<>(taskService.saveTask(task), HttpStatus.CREATED);
}



@DeleteMapping("/{id}")
    public ResponseEntity<String>deleteTask(@PathVariable Long id){
    return new
            ResponseEntity<>(taskService.deleteTask(id),HttpStatus.OK);
}


@PutMapping()
    public ResponseEntity<String> editTask(@RequestBody TaskDto taskDto){

    return new
            ResponseEntity<>(taskService.editTask(taskDto),HttpStatus.OK);
}
}
