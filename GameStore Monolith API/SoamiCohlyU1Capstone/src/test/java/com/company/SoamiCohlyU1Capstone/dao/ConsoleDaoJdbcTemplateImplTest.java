package com.company.SoamiCohlyU1Capstone.dao;

import com.company.SoamiCohlyU1Capstone.dao.console.ConsolesDao;
import com.company.SoamiCohlyU1Capstone.model.Console;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleDaoJdbcTemplateImplTest {

    @Autowired
    protected ConsolesDao consolesDao;

    @Before
    public void setUp() throws Exception{

        List<Console> deleteList = consolesDao.getAllConsoles();
        deleteList.stream().forEach(console ->consolesDao.deleteConsole(console.getConsoleId()) );

//        List<Console> consoleList = consolesDao.getAllConsoles();
//        consoleList.stream()
//                .forEach(console -> consolesDao.deleteConsole(console.getConsoleId()));
    }

    @After
    public void tearDown() throws Exception {
        List<Console> deleteList = consolesDao.getAllConsoles();
        deleteList.stream().forEach(console ->consolesDao.deleteConsole(console.getConsoleId()) );

    }


    @Test
    public void getConsoleByManufacturer(){
        Console console = new Console();
        console.setModel("Xbox1");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("256 gb");
        console.setProcessor("AMD octo core");
        console.setPrice(new BigDecimal("299.99").setScale(2));
        console.setQuantity(3);

        consolesDao.addConsole(console);

        console = new Console();
        console.setModel("Xbox360");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("256 gb");
        console.setProcessor("AMD quad core");
        console.setPrice(new BigDecimal("199.99").setScale(2));
        console.setQuantity(2);

        consolesDao.addConsole(console);


        console = new Console();
        console.setModel("Xbox Original");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("128 gb");
        console.setProcessor("AMD dual core");
        console.setPrice(new BigDecimal("99.99").setScale(2));
        console.setQuantity(5);

        consolesDao.addConsole(console);

        console = new Console();
        console.setModel("PS4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("256 gb");
        console.setProcessor("AMD octo core");
        console.setPrice(new BigDecimal("299.99").setScale(2));
        console.setQuantity(2);
        consolesDao.addConsole(console);

        List<Console>msoftList=consolesDao.getConsoleByManuf("Microsoft");
        assertEquals(3,msoftList.size());
    }

    @Test
    public void getAllConsoles(){
        Console console = new Console();
        console.setModel("Xbox1");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("256 gb");
        console.setProcessor("AMD octo core");
        console.setPrice(new BigDecimal("299.99").setScale(2));
        console.setQuantity(3);

        //console =
        consolesDao.addConsole(console);

        console.setModel("PS4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("256 gb");
        console.setProcessor("AMD octo core");
        console.setPrice(new BigDecimal("299.99").setScale(2));
        console.setQuantity(2);
        consolesDao.addConsole(console);

        List<Console>consoleList = consolesDao.getAllConsoles();
        assertEquals(2,consoleList.size());

    }
    @Test
    public void updateConsole(){

        Console console = new Console();
        console.setModel("Xbox1");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("256 gb");
        console.setProcessor("AMD octo core");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(3);

        console = consolesDao.addConsole(console);

        console.setModel("PS4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("256 gb");
        console.setProcessor("AMD octo core");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(2);
        consolesDao.updateConsole(console);

        Console console1 = consolesDao.getConsole(console.getConsoleId());
        assertEquals(console,console1);

    }

    @Test
    public void addGetDeleteConsole(){
        Console console = new Console();
        console.setModel("Xbox1");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("256 gb");
        console.setProcessor("AMD octo core");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(3);

        console = consolesDao.addConsole(console);

        Console console1 = consolesDao.getConsole(console.getConsoleId());
        assertEquals(console1,console);

        consolesDao.deleteConsole(console.getConsoleId());
        console1 = consolesDao.getConsole(console.getConsoleId());
        assertNull(console1);
    }


}
