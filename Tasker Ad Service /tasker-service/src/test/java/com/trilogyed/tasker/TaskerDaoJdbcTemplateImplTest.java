package com.trilogyed.tasker;


import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskerDaoJdbcTemplateImplTest {

    @Autowired
    protected TaskerDao dao;

    @Before
    public void setUp() throws Exception{

        List<Task> tList = dao.getAllTasks();

        tList.stream()
                .forEach(task -> dao.deleteTask(task.getId()));
    }
    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void createGetDeleteTask(){
        Task task = new Task();
        task.setDescription("Invite friends");
        task.setCreateDate(LocalDate.of(2019,8,3));
        task.setDueDate(LocalDate.of(2019,9,20));
        task.setCategory("Party Invites");

        task = dao.createTask(task);

        Task t2 = dao.getTask(task.getId());
        assertEquals(task,t2);

        dao.deleteTask(task.getId());

        t2 = dao.getTask(task.getId());

        assertNull(t2);
    }

    @Test
    public void getAllTasks(){
        Task task = new Task();
        task.setDescription("Invite friends");
        task.setCreateDate(LocalDate.of(2019,8,3));
        task.setDueDate(LocalDate.of(2019,9,20));
        task.setCategory("Party Invites");

        task = dao.createTask(task);

        task = new Task();
        task.setDescription("Invite Family");
        task.setCreateDate(LocalDate.of(2019,8,3));
        task.setDueDate(LocalDate.of(2019,9,21));
        task.setCategory("Party Invites");

        task = dao.createTask(task);

        List<Task> tasks = dao.getAllTasks();

        assertEquals(tasks.size(),2);
    }

    @Test
    public void getTasksByCategory(){
        Task task = new Task();
        task.setDescription("Invite friends");
        task.setCreateDate(LocalDate.of(2019,8,3));
        task.setDueDate(LocalDate.of(2019,9,20));
        task.setCategory("Party Invites");

        task = dao.createTask(task);

        task = new Task();
        task.setDescription("Invite Family");
        task.setCreateDate(LocalDate.of(2019,8,3));
        task.setDueDate(LocalDate.of(2019,9,21));
        task.setCategory("Party Invites");

        task = dao.createTask(task);

        task = new Task();
        task.setDescription("Invite Colleagues");
        task.setCreateDate(LocalDate.of(2019,8,3));
        task.setDueDate(LocalDate.of(2019,9,21));
        task.setCategory("Work Meeting Invites");

        task = dao.createTask(task);

        List<Task> partyTasks = dao.getTasksByCategory("Party Invites");
        assertEquals(2,partyTasks.size());

        List<Task>workTasks = dao.getTasksByCategory("Work Meeting Invites");
        assertEquals(1,workTasks.size());
    }

    @Test
    public void updateTask(){
        Task task = new Task();
        task.setDescription("Invite Family");
        task.setCreateDate(LocalDate.of(2019,8,3));
        task.setDueDate(LocalDate.of(2019,9,21));
        task.setCategory("Party Invites");

        task = dao.createTask(task);

        task.setDescription("Invite Family");
        task.setCreateDate(LocalDate.of(2019,8,3));
        task.setDueDate(LocalDate.of(2019,9,21));
        task.setCategory("Dinner Invites");

        dao.updateTask(task);

        Task task2 = dao.getTask(task.getId());

        assertEquals(task2,task);
    }

}
