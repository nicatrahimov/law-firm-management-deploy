package com.rahimov.lawfirmmanagementdeploy.service.Impl;



import com.rahimov.lawfirmmanagementdeploy.dto.TaskDto;
import com.rahimov.lawfirmmanagementdeploy.exception.CaseNotFoundException;
import com.rahimov.lawfirmmanagementdeploy.exception.TaskNotFound;
import com.rahimov.lawfirmmanagementdeploy.model.Case;
import com.rahimov.lawfirmmanagementdeploy.model.Task;
import com.rahimov.lawfirmmanagementdeploy.repository.CaseRepository;
import com.rahimov.lawfirmmanagementdeploy.repository.TaskRepository;
import com.rahimov.lawfirmmanagementdeploy.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CaseRepository caseRepository;

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto>taskDtos = new ArrayList<>();
        for(Task t : tasks){
            TaskDto dto = TaskDto.builder()
                    .id(t.getId())
                    .caseId(t.getACase().getId())
                    .remindDate(t.getRemindDate())
                    .priority(t.getPriority())
                    .name(t.getName())
                    .description(t.getDescription())
                    .build();

            taskDtos.add(dto);
        }

return taskDtos;
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task t = taskRepository.findById(id).orElseThrow(()->new TaskNotFound("not found id with: "+id));

        return TaskDto.builder()
                .id(t.getId())
                .caseId(t.getACase().getId())
                .remindDate(t.getRemindDate())
                .priority(t.getPriority())
                .name(t.getName())
                .description(t.getDescription())
                .build();
    }

    @Override
    public String saveTask(TaskDto taskDto) {

        Case aCase = caseRepository.findById(taskDto.getCaseId()).orElseThrow(CaseNotFoundException::new);
        Task task = Task.builder()
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .priority(taskDto.getPriority())
                .remindDate(taskDto.getRemindDate())
                .aCase(aCase)
                .build();
        taskRepository.save(task);
        return "Saved successfully";
    }

    @Override
    public String deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFound("Task not found with this id:" + taskId));
        taskRepository.delete(task);
        return "Deleted successfully";
    }

    @Override
    public String editTask(TaskDto task) {
        Task task1 = taskRepository.findById(task.getId()).orElseThrow(() -> new TaskNotFound("Task not found"));
Case aCase = caseRepository.findById(task1.getACase().getId()).orElseThrow(CaseNotFoundException::new);

        if (task.getName() != null) {
            task1.setName(task.getName());
        }
        if (task.getDescription() != null) {
            task1.setDescription(task.getDescription());
        }
        if (task.getPriority() != null) {
            task1.setPriority(task.getPriority());
        }
        if (task.getCaseId() != null) {
            task1.setACase(aCase);
        }
        taskRepository.save(task1);

        return "Edited successfully";

    }
}
