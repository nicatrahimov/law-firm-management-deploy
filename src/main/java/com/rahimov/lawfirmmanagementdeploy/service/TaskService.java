package com.rahimov.lawfirmmanagementdeploy.service;


import com.rahimov.lawfirmmanagementdeploy.dto.TaskDto;
import com.rahimov.lawfirmmanagementdeploy.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface TaskService {

    List<TaskDto>getAllTasks();
    TaskDto getTaskById(Long id);

    String saveTask(TaskDto taskDto);

    String deleteTask(Long taskId);

    String editTask(TaskDto task);
}
