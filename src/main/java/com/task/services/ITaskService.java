package com.task.services;

import com.task.entity.Task;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/**
 * * Author: Francisco Hernandez
 **/
public interface ITaskService {
    List<Task> getTask();
    Task createTask(Task task);
    Optional<Task> findById(long id);
    Task updateTask(long id, Task task);
    void deleteById(long id);
}
