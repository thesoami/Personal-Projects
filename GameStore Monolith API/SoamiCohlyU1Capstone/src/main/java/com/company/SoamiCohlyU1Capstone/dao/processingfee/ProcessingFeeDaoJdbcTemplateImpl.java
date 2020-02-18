package com.company.SoamiCohlyU1Capstone.dao.processingfee;

import com.company.SoamiCohlyU1Capstone.model.ProcessingFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProcessingFeeDaoJdbcTemplateImpl implements ProcessingFeeDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_PROCESSINGFEE_SQL =
            "SELECT * FROM processing_fee WHERE product_type like ? ";

    @Autowired
    public ProcessingFeeDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public BigDecimal getFee(String itemType) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PROCESSINGFEE_SQL, this::mapRowToProcessingFee,"%"+itemType+"%");
        }catch (EmptyResultDataAccessException e){

            return null;
        }
    }

    @Override
    public void deleteFee(String itemType) {

    }

    @Override
    public List<ProcessingFee> getAllFees() {
        return null;
    }

    //RowMapper
    private BigDecimal mapRowToProcessingFee(ResultSet rs, int rowNum) throws SQLException{
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType(rs.getString("product_type"));
        processingFee.setFee(rs.getBigDecimal("fee"));

        return processingFee.getFee();

        //changed private ProcessingFee to private BigDecimal, changed return to get fee
    }
}
