package com.company.SoamiCohlyU1Capstone.dao.salestax;

import com.company.SoamiCohlyU1Capstone.model.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SalesTaxRateDaoJdbcTemplateImpl implements SalesTaxRateDao {
    private JdbcTemplate jdbcTemplate;

    //Prepared Statements

//    private static final String INSERT_SALESTAXRATE_SQL =
//            "INSERT INTO sales_tax_rate (state, rate) values(?,?)";

    private static final String SELECT_SALESTAXRATE_SQL =
            "SELECT * FROM sales_tax_rate WHERE state like ?";

//    private static final String SELECT_SALESTAXRATE_SQL =
//            "SELECT rate FROM sales_tax_rate WHERE state = ?";

    private static final String SELECT_ALL_SALESTAXRATE_SQL =
            "SELECT * FROM sales_tax_rate";

//    private static final String UPDATE_SALESTAXRATE_SQL =
//            "UPDATE sales_tax_rate set rate = ? where state = ?";
//
//    private static final String DELETE_SALESTAXRATE_SQL =
//            "DELETE FROM sales_tax_rate where state = ?";



    @Autowired
    public SalesTaxRateDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}
//I removed the U from CRUD mainly cause I feel the responsibility for updating new states/tax rates is
// the job of the DB admin...my job is to get the data for user interaction...and end user would not be CHANGING values
// within the database...imo...also specs didnt ask for CCRUD for TaxRate or Processing FEE ;)


//    @Transactional
//    @Override
//    public SalesTaxRate addSalesTaxRate(SalesTaxRate salesTaxRate) {
//        jdbcTemplate.update(
//                INSERT_SALESTAXRATE_SQL,
//                salesTaxRate.getState(),
//                salesTaxRate.getRate());
//        return salesTaxRate;
//    }


    @Override
    public BigDecimal getSalesTaxRate(String state) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SALESTAXRATE_SQL, this::mapRowToSalesTaxRate,"%"+state+"%");
        }catch (EmptyResultDataAccessException e){

            return null;
        }
    }

    @Override
    public SalesTaxRate getSalesTaxRateObj(String state) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SALESTAXRATE_SQL, this::mapRowToSalesTaxRateObj,state);
        }catch (EmptyResultDataAccessException e){

            return null;
        }
    }

    @Override
    public List<SalesTaxRate> getAllSalesTaxRates() {
        return jdbcTemplate.query(SELECT_ALL_SALESTAXRATE_SQL,this::mapRowToSalesTaxRateObj);
    }

//    @Override
//    public void updateSalesTaxRate(SalesTaxRate salesTaxRate) {
//        jdbcTemplate.update(
//                UPDATE_SALESTAXRATE_SQL,
//                salesTaxRate.getRate(),
//                salesTaxRate.getState());
//
//    }
//
//    @Override
//    public void deleteSalesTaxRate(String state) {
//        jdbcTemplate.update(DELETE_SALESTAXRATE_SQL,state);
//
//    }

    //RowMapper
    private SalesTaxRate mapRowToSalesTaxRateObj(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));

        return salesTaxRate;
    }

    private BigDecimal mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));

        return salesTaxRate.getRate();
    }
}

