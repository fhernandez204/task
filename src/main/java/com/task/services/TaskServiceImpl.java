package com.task.services;

import com.task.entity.Task;
import com.task.repository.TaskRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * * Author: Francisco Hernandez
 **/
@Service
public class TaskServiceImpl implements ITaskService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    TaskRepository repository;

    @Override
    public List<Task> getTask() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            //logger.info("title: "+title);

            // (title == null)
                repository.findAll().forEach(tasks::add);
            //else
                //repository.findByTitleContainingIgnoreCase(title).forEach(tasks::add);
            logger.info("tasks: "+tasks);

        } catch (Exception e) {
            logger.info("e.getMessage(): "+e.getMessage());
            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return tasks;
    }

    @Override
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    public Optional<Task> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Task updateTask(long id, Task task) {
        Optional<Task> taskData = repository.findById(id);
        logger.info("taskData: "+taskData);
        if (taskData.isPresent()) {
            Task _task = taskData.get();
            _task.setTitle(task.getTitle());
            _task.setDescription(task.getDescription());
            _task.setCompleted(task.getCompleted());
            logger.info("_task: "+_task);
            return repository.save(_task);
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
