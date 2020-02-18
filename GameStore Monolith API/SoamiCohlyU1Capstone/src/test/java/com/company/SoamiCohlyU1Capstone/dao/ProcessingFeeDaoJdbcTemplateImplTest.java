package com.company.SoamiCohlyU1Capstone.dao;


import com.company.SoamiCohlyU1Capstone.dao.processingfee.ProcessingFeeDao;
import com.company.SoamiCohlyU1Capstone.model.ProcessingFee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeDaoJdbcTemplateImplTest {

    @Autowired
    protected ProcessingFeeDao processingFeeDao;

//    @Before
//    public void setUp() throws Exception{
//        List<ProcessingFee> processingFeeList = processingFeeDao.getAllFees();
//
//        processingFeeList.stream()
//                .forEach(processingFee ->processingFeeDao.deleteFee(processingFee.getProductType()) );
//    }

    @Test
    public void getProcessingFee(){
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Game");
        processingFee.setFee(new BigDecimal("1.49"));

        assertEquals(new BigDecimal("1.49"),processingFeeDao.getFee(processingFee.getProductType()));
    }

//    public void addGetDeleteProcessingFee(){
//        ProcessingFee processingFee = new ProcessingFee();
//        processingFee.setProductType("Game");
//        processingFee.setFee(new BigDecimal("1.49"));
//
//        processingFee = processingFeeDao.addFee(processingFee);
//
//        ProcessingFee processingFee1 = processingFeeDao.getAllFees();
//
//    }
}
