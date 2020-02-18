package com.company.SoamiCohlyU1Capstone.dao;

import com.company.SoamiCohlyU1Capstone.dao.salestax.SalesTaxRateDao;
import com.company.SoamiCohlyU1Capstone.model.SalesTaxRate;
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
public class SalesTaxRateDaoJdbcTemplateImplTest {



    @Autowired
    protected SalesTaxRateDao salesTaxRateDao;

    @Before
    public void setUp() throws Exception{
        List<SalesTaxRate> taxRateList = salesTaxRateDao.getAllSalesTaxRates();
        List<SalesTaxRate> taxRate = salesTaxRateDao.getAllSalesTaxRates();
//
//        taxRateList.stream()
//                .forEach(salesTaxRate -> salesTaxRateDao.deleteSalesTaxRate(salesTaxRate.getState()));
//
//        taxRateList.stream()
//                .forEach(BigDecimal -> BigDecimal.);
    }

//    @Test
//    public void addGetSalesTaxRate(){
//        SalesTaxRate salesTaxRate = new SalesTaxRate();
//
////        salesTaxRate.setState("NN");
////        salesTaxRate.setRate(new BigDecimal("0.06"));
////
////        salesTaxRate = salesTaxRateDao.addSalesTaxRate(salesTaxRate);
////
////        SalesTaxRate newRate = salesTaxRateDao.getSalesTaxRate(salesTaxRate.getState());
////        assertEquals(newRate,salesTaxRate);
//
////        salesTaxRateDao.deleteSalesTaxRate(salesTaxRate.getState());
////        newRate = salesTaxRateDao.getSalesTaxRate(salesTaxRate.getState());
////        assertNull(newRate);
//    }

    @Test
    public void getSalesTaxRateObj(){
        assertEquals(new BigDecimal("0.06"),salesTaxRateDao.getSalesTaxRateObj("NY").getRate());
        assertEquals(new BigDecimal("0.03"),salesTaxRateDao.getSalesTaxRateObj("TX").getRate());
        assertEquals(new BigDecimal("0.60"),salesTaxRateDao.getSalesTaxRateObj("AR").getRate());
//        assertNull(salesTaxRateDao.getSalesTaxRateObj("ZN").getRate());

    }
//    @Test
//    public void getSalesTaxRate(){
//        assertEquals(new BigDecimal("0.06"),salesTaxRateDao.getSalesTaxRate("NY"));
//        assertEquals(new BigDecimal("0.03"),salesTaxRateDao.getSalesTaxRate("TX"));
//        assertEquals(new BigDecimal("0.60"),salesTaxRateDao.getSalesTaxRate("AR"));
////        assertNull(salesTaxRateDao.getSalesTaxRateObj("ZN").getRate());
//
//    }

    @Test
    public  void getAllSalesTaxRate(){

        List<SalesTaxRate>taxRateList = salesTaxRateDao.getAllSalesTaxRates();
        assertEquals(50,taxRateList.size());

    }


//    @Test
//    public void updateSalesTaxRate(){
//        SalesTaxRate salesTaxRate = new SalesTaxRate();
//
//        salesTaxRate.setState("NJ");
//        salesTaxRate.setRate(new BigDecimal("0.06"));
//        salesTaxRate = salesTaxRateDao.addSalesTaxRate(salesTaxRate);
//
//        salesTaxRate.setState("NY");
//        salesTaxRate.setRate(new BigDecimal("0.05"));
//        salesTaxRateDao.updateSalesTaxRate(salesTaxRate);
//
//        SalesTaxRate newRate = salesTaxRateDao.getSalesTaxRate(salesTaxRate.getState());
//        assertEquals(newRate,salesTaxRate);
//    }

}


