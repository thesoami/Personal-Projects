package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
//import com.company.exception.NoTasksFoundInDb;
//import com.company.exception.NotFoundCategory;
//import com.company.exception.NotFoundException;
//import com.company.exception.NotFoundId;
import com.trilogyed.tasker.exception.NotFoundException;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.util.feign.AdserverServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class TaskerServiceLayer {
    private TaskerDao dao;
    private AdserverServiceClient client;

    @Autowired
    public TaskerServiceLayer(TaskerDao dao, AdserverServiceClient client){
        this.dao=dao;
        this.client=client;
    }

    public TaskViewModel getTask(int id) {

        Task task = dao.getTask(id);
        TaskViewModel tvm = new TaskViewModel();
        if(task!=null)
            return buildTaskViewModel(task);

            // TODO - get ad from Adserver and put in tvm
        else
            throw new NotFoundException(id);

    }

    public List<TaskViewModel> getAllTasks() {
        List<TaskViewModel> tvmList = new ArrayList<>();
        List<Task>tList = dao.getAllTasks();
        if(dao.getAllTasks().size()==0)
            throw new NotFoundException();
        tList.stream()
                .forEach(task -> {
                    TaskViewModel tvm = buildTaskViewModel(task);
                    tvm.setAdvertisement(client.getAd());
                    tvmList.add(tvm);
                });

        return tvmList;
    }

    public List<TaskViewModel> getTasksByCategory(String category) {
        List<TaskViewModel> tvmListCategory = new ArrayList<>();
        if(dao.getTasksByCategory(category).size()==0)
            throw new NotFoundException(category);

        List<Task> taskByCat = dao.getTasksByCategory(category);
        taskByCat.stream()
                .forEach(task -> {
                    TaskViewModel tvmCat = buildTaskViewModel(task);
                    tvmCat.setAdvertisement(client.getAd());
                    tvmListCategory.add(tvmCat);
                });
        return tvmListCategory;

    }

    @Transactional
    public TaskViewModel createTask(TaskViewModel taskViewModel) {

        Task task = new Task();
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        task = dao.createTask(task);
//        taskViewModel.setAdvertisement(client.getAd());

//        taskViewModel.setId(task.getId());

        // TODO - get ad from Adserver and put in taskViewModel
        return buildTaskViewModel(task);
    }

    public void deleteTask(int id) {
        if(dao.getTask(id)!=null)
            dao.deleteTask(id);
        else
            throw new NotFoundException(+id);
    }



    @Transactional
    public void updateTask(TaskViewModel tvm) {
        Task task = new Task();
        int id = tvm.getId();
        if(dao.getTask(id)==null)
            throw new NotFoundException(tvm.getId());

        task.setDescription(tvm.getDescription());
        task.setCreateDate(tvm.getCreateDate());
        task.setDueDate(tvm.getDueDate());
        task.setCategory(tvm.getCategory());
//
//        tvm.setAdvertisement("");
//        tvm.setAdvertisement(client.getAd());
        dao.updateTask(task);
    }

    private TaskViewModel buildTaskViewModel(Task task){
        if(task==null)
            return null;
        TaskViewModel tvm = new TaskViewModel();
        tvm.setId(task.getId());
        tvm.setDescription(task.getDescription());
        tvm.setCreateDate(task.getCreateDate());
        tvm.setDueDate(task.getDueDate());
        tvm.setCategory(task.getCategory());
        tvm.setAdvertisement(client.getAd());
        return tvm;
    }
}
