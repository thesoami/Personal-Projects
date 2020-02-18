package com.company.SoamiCohlyU1Capstone.dao.salestax;

import com.company.SoamiCohlyU1Capstone.model.SalesTaxRate;

import java.math.BigDecimal;
import java.util.List;

public interface SalesTaxRateDao {


//    BigDecimal getTaxRate(String state);

//    SalesTaxRate addSalesTaxRate(SalesTaxRate salesTaxRate);

    BigDecimal getSalesTaxRate(String state);

    SalesTaxRate getSalesTaxRateObj(String state);

    List<SalesTaxRate> getAllSalesTaxRates();

//    List<BigDecimal> getAllSalesTaxRates();

//    void updateSalesTaxRate(SalesTaxRate salesTaxRate);
//
//    void deleteSalesTaxRate(String state);


}
