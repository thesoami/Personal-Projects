package com.trilogyed.tasker.controller;

import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import com.trilogyed.tasker.util.feign.AdserverServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping(produces = "application/json")
public class TaskerController {

    @Autowired
    TaskerServiceLayer service;
//    @Autowired
//    AdserverServiceClient client;
//    public TaskerController(TaskerServiceLayer service) {
//        this.service = service;
//    }

    @RequestMapping(value = "/tasks",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public TaskViewModel createTask(@RequestBody @Valid TaskViewModel tvm){
        return service.createTask(tvm);
    }

    @RequestMapping(value = "/tasks/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public TaskViewModel geTask(@PathVariable int id) { return service.getTask(id);
    }

    @RequestMapping(value = "/tasks",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel> getAllTasks(){
        return service.getAllTasks();
    }

    @RequestMapping(value = "/tasks/category/{category}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel>getTasksByCategory(@PathVariable("category")String s){
        return service.getTasksByCategory(s);
    }


    @RequestMapping(value = "/tasks{id}",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateTask(@PathVariable ("id") int id, @RequestBody @Valid TaskViewModel tvm){
        if (id != tvm.getId()){
            throw new IllegalArgumentException(("Task ID on path must match the ID of the task in the database."));
        }
        service.updateTask(tvm);
    }

    @RequestMapping(value = "/tasks{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable ("id") int id) {
        service.deleteTask(id);
    }
}
