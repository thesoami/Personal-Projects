package com.company.SoamiCohlyU1Capstone.dao;


import com.company.SoamiCohlyU1Capstone.dao.tshirt.TShirtDao;
import com.company.SoamiCohlyU1Capstone.model.TShirt;
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
public class TShirtDaoJdbcTemplateImplTest {

    @Autowired
    protected TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception{
        List<TShirt> tShirtList = tShirtDao.getAllTshirts();

        tShirtList.stream()
                .forEach(tShirt -> tShirtDao.deleteTshirt(tShirt.gettShirtId()));
    }
    @After
    public void tearDown() throws Exception {
        List<TShirt> tShirtList = tShirtDao.getAllTshirts();

        tShirtList.stream()
                .forEach(tShirt -> tShirtDao.deleteTshirt(tShirt.gettShirtId()));
    }

    @Test
    public void addGetDeleteTshirt(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Blue");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(4);
        tShirt = tShirtDao.addTshirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTshirt(tShirt.gettShirtId());
        assertEquals(tShirt1,tShirt);

        tShirtDao.deleteTshirt(tShirt.gettShirtId());
        tShirt1 = tShirtDao.getTshirt(tShirt.gettShirtId());
        assertNull(tShirt1);
    }

    @Test
    public void updateTshirt(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Blue");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(4);
        tShirt = tShirtDao.addTshirt(tShirt);

        tShirt.setDescription("UPDATED");
        tShirt.setQuantity(2);
        tShirtDao.updateTshirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTshirt(tShirt.gettShirtId());
        assertEquals(tShirt1,tShirt);
    }

    @Test
    public void getAllTshirts(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Blue");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(4);
        tShirtDao.addTshirt(tShirt);

        tShirt.setSize("M");
        tShirt.setColor("Grey");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(9);
        tShirtDao.addTshirt(tShirt);

        tShirt.setSize("S");
        tShirt.setColor("Red");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(1);
        tShirtDao.addTshirt(tShirt);

        List<TShirt>tShirtList = tShirtDao.getAllTshirts();
        assertEquals(3,tShirtList.size());

    }

    @Test
    public void getTshirtBySize(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Blue");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(4);
        tShirtDao.addTshirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Orange");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("40.99"));
        tShirt.setQuantity(12);
        tShirtDao.addTshirt(tShirt);

        tShirt.setSize("M");
        tShirt.setColor("Grey");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(9);
        tShirtDao.addTshirt(tShirt);

        tShirt.setSize("S");
        tShirt.setColor("Red");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(1);
        tShirtDao.addTshirt(tShirt);

        List<TShirt>largeTshirtList = tShirtDao.getTshirtBySize("L");
        assertEquals(2,largeTshirtList.size());

    }

    @Test
    public void getTshirtByColor(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Blue");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(4);
        tShirtDao.addTshirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Orange");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("40.99"));
        tShirt.setQuantity(12);
        tShirtDao.addTshirt(tShirt);

        tShirt.setSize("M");
        tShirt.setColor("Grey");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("29.99"));
        tShirt.setQuantity(9);
        tShirtDao.addTshirt(tShirt);

        tShirt.setSize("S");
        tShirt.setColor("Orange");
        tShirt.setDescription("Patterend dark blue shirt with collar and game logo");
        tShirt.setPrice(new BigDecimal("49.99"));
        tShirt.setQuantity(1);
        tShirtDao.addTshirt(tShirt);

        List<TShirt>colorList = tShirtDao.getTshirtByColor("Orange");
        assertEquals(2,colorList.size());
    }

}
