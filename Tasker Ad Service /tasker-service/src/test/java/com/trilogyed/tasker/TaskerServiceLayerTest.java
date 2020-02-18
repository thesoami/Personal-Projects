package com.trilogyed.tasker;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.dao.TaskerDaoJdbcTemplateImpl;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import com.trilogyed.tasker.util.feign.AdserverServiceClient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class TaskerServiceLayerTest {
    TaskerDao dao;
    TaskerServiceLayer service;
    AdserverServiceClient client;

    @Before
    public void setUp() throws Exception{
        setUpTaskerDao();
        setUpAdServiceClientMock();
        service = new TaskerServiceLayer(dao,client);
    }

    @Test
    public void createGetGetAllTasks(){
        TaskViewModel tvm = new TaskViewModel();
        tvm.setDescription("Invitations");
        tvm.setCreateDate(LocalDate.of(2019,9,12));
        tvm.setDueDate(LocalDate.of(2019,9,20));
        tvm.setCategory("Party");
        tvm.setAdvertisement(client.getAd());

        tvm = service.createTask(tvm);

        TaskViewModel tvmFromService = service.getTask(tvm.getId());
        assertEquals(tvm,tvmFromService);

        TaskViewModel tvm2 = new TaskViewModel();
        tvm2.setDescription("Invitations");
        tvm2.setCreateDate(LocalDate.of(2019,9,13));
        tvm2.setDueDate(LocalDate.of(2019,9,21));
        tvm2.setCategory("Dinner");
        tvm2.setAdvertisement(client.getAd());

        tvm2 = service.createTask(tvm2);

        List<TaskViewModel> tvmList = service.getAllTasks();
        assertEquals(2,tvmList.size());
    }

    @Test
    public void getTasksByCategory(){
        List<TaskViewModel> tvmParty = new ArrayList<>();
        TaskViewModel tvm = new TaskViewModel();
        tvm.setDescription("Invitations");
        tvm.setCreateDate(LocalDate.of(2019,9,12));
        tvm.setDueDate(LocalDate.of(2019,9,20));
        tvm.setCategory("Party");
        tvm.setAdvertisement(client.getAd());

        tvm = service.createTask(tvm);

        tvmParty = service.getTasksByCategory(tvm.getCategory());

        assertEquals(1,tvmParty.size());
    }

    @Test
    public void updateTask(){
        TaskViewModel tvmUpdate = new TaskViewModel();
        tvmUpdate.setId(3);
        tvmUpdate.setDescription("Updated task");
        tvmUpdate.setCreateDate(LocalDate.of(2019,9,29));
        tvmUpdate.setDueDate(LocalDate.of(2019,9,30));
        tvmUpdate.setCategory("yes");
        tvmUpdate.setAdvertisement(client.getAd());

//        tvmUpdate = service.createTask(tvmUpdate);
        tvmUpdate.setDescription("Updated task");
        tvmUpdate.setCategory("yes");
        service.updateTask(tvmUpdate);

        TaskViewModel uvm = service.getTask(tvmUpdate.getId());
        assertEquals(uvm,tvmUpdate);
    }

    @Test
    public void deleteTask(){
        TaskViewModel delete = service.getTask(99);
//        delete.setAdvertisement(client.getAd());
        service.deleteTask(99);
        assertNull(delete);
    }

    private void setUpAdServiceClientMock(){
        client = mock(AdserverServiceClient.class);

        String advert = "This is an Advertisement Test";
        doReturn(advert).when(client).getAd();
    }

    private void setUpTaskerDao(){
        dao = mock(TaskerDaoJdbcTemplateImpl.class);

        Task taskA = new Task();
        taskA.setDescription("Invitations");
        taskA.setCreateDate(LocalDate.of(2019,9,12));
        taskA.setDueDate(LocalDate.of(2019,9,20));
        taskA.setCategory("Party");

        Task taskB = new Task();
        taskB.setId(1);
        taskB.setDescription("Invitations");
        taskB.setCreateDate(LocalDate.of(2019,9,12));
        taskB.setDueDate(LocalDate.of(2019,9,20));
        taskB.setCategory("Party");

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>ADD
        doReturn(taskB).when(dao).createTask(taskA);
        doReturn(taskB).when(dao).getTask(1);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>UPDATE

        Task update = new Task();
        update.setId(3);
        update.setDescription("Updated task");
        update.setCreateDate(LocalDate.of(2019,9,29));
        update.setDueDate(LocalDate.of(2019,9,30));
        update.setCategory("yes");

        doNothing().when(dao).updateTask(update);
        doReturn(update).when(dao).getTask(3);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>DELETE
        Task delete = new Task();
        delete.setId(99);
        delete.setDescription("task to be deleted");
        delete.setCreateDate(LocalDate.of(2019,9,22));
        delete.setDueDate(LocalDate.of(2019,9,23));
        delete.setCategory("deleted");

        doNothing().when(dao).deleteTask(99);
        doReturn(null).when(dao).getTask(99);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>OBJECT2

        Task tC = new Task();
        tC.setDescription("Invitations");
        tC.setCreateDate(LocalDate.of(2019,9,13));
        tC.setDueDate(LocalDate.of(2019,9,21));
        tC.setCategory("Dinner");

        Task tD = new Task();
        tD.setId(2);
        tD.setDescription("Invitations");
        tD.setCreateDate(LocalDate.of(2019,9,13));
        tD.setDueDate(LocalDate.of(2019,9,21));
        tD.setCategory("Dinner");

        doReturn(tD).when(dao).createTask(tC);
        doReturn(tD).when(dao).getTask(2);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>GETALL
        List<Task> taskList = new ArrayList<>();
        taskList.add(taskB);
        taskList.add(tD);

        doReturn(taskList).when(dao).getAllTasks();

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>GETTASKSBYCATEGORY

        List<Task> party = new ArrayList<>();
        party.add(taskB);

        doReturn(party).when(dao).getTasksByCategory("Party");

        List<Task> dinner = new ArrayList<>();
        dinner.add(tD);
        doReturn(dinner).when(dao).getTasksByCategory("Dinner");
    }

}
