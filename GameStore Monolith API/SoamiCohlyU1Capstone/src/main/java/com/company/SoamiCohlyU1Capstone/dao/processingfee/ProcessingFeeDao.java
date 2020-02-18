package com.company.SoamiCohlyU1Capstone.dao.processingfee;

import com.company.SoamiCohlyU1Capstone.model.ProcessingFee;

import java.math.BigDecimal;
import java.util.List;

public interface ProcessingFeeDao {


//    ProcessingFee getFee(String itemType);

    BigDecimal getFee(String itemType);

//    ProcessingFee addFee(ProcessingFee processingFee);

    void deleteFee(String itemType);

    List<ProcessingFee> getAllFees();
//    BigDecimal getFee(String itemType);
}
