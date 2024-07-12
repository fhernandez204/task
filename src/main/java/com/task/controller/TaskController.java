package com.task.controller;

import com.google.gson.Gson;
import com.task.entity.Task;
import com.task.services.ITaskService;
import com.task.services.TaskServiceImpl;
import com.task.utils.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.task.utils.Constans.*;


@RestController
@RequestMapping("/api")
public class TaskController {
	
	private static final Logger logger = LogManager.getLogger();


    @Autowired
    private ITaskService iTaskService;

     @GetMapping("/tasks")
      public ResponseEntity<List<Task>> getAllTask(@RequestParam(required = false) String title) {
        try {

          List<Task> tasks = new ArrayList<Task>();

          logger.info("title: "+title);
          tasks =  iTaskService.getTask();

          logger.info("tasks: "+tasks);

          if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("e.getMessage(): "+e.getMessage());
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }

      @GetMapping("/tasks/{id}")
      public ResponseEntity<Task> getUserById(@PathVariable("id") long id) {
        Optional<Task> taskData = iTaskService.findById(id);
          logger.info("taskData: "+taskData);
          return taskData.map(task -> new ResponseEntity<>(task, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
      }

      @PostMapping("/tasks")
      public String createTask(@RequestBody Task task) {
          Message message;
          Gson gson = new Gson();
            try {
                logger.info("task: "+task);
                Task taskIn = new Task(task.getTitle(), task.getDescription(), task.getCompleted());

                Task _task = iTaskService.createTask(taskIn);

              logger.info("_task: "+_task);

              ResponseEntity<Task> responseUser = new ResponseEntity<>(_task, HttpStatus.CREATED);

                if(responseUser.getStatusCodeValue()==HttpStatus.CREATED.value()) {
                    //com.apiRest.model.UserReturn userReturn = new com.apiRest.model.UserReturn(_task.getId(),_user.getDateCreate(),_user.getDateModified(),_user.getDateLastLogin(),jwt,_user.getActive());
                    return gson.toJson(_task);
                }
                else {
                    message = new Message(ERROR_INSERT_STATUS+responseUser.getStatusCode());
                    return gson.toJson(message);
                }
            } catch (Exception e) {
                logger.info("Exception e: "+e);
                message = new Message(ERROR_EXCEPTION_THROWN+" "+e.getMessage());
                return gson.toJson(message);
            }
      }

  @PutMapping("/tasks/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
        logger.info("task: "+task);
        Task updateTask = iTaskService.updateTask(id,task);
        if(updateTask!=null)
            return new ResponseEntity<>(updateTask, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
    try {
        logger.info("id: "+id);
        iTaskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}