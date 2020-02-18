package com.company.SoamiCohlyU1Capstone.dao.tshirt;

import com.company.SoamiCohlyU1Capstone.model.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoJdbcTemplateImpl implements TShirtDao{

    private JdbcTemplate jdbcTemplate;

    //prepared statements

    private static final String INSERT_TSHIRT_SQL =
            "INSERT INTO t_shirt (size, color, description, price, quantity) values(?,?,?,?,?)";

    private static final String SELECT_TSHIRT_SQL =
            "SELECT * FROM t_shirt WHERE t_shirt_id = ?";

    private static final String SELECT_ALL_TSHIRTS_SQL =
            "SELECT * FROM t_shirt";

    private static final String UPDATE_TSHIRT_SQL =
            "UPDATE t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? WHERE t_shirt_id = ?";

    private static final String DELETE_TSHIRT_SQL =
            "DELETE FROM t_shirt WHERE t_shirt_id = ?";

    private static final String GET_TSHIRTS_BY_SIZE_SQL =
            "SELECT * FROM t_shirt WHERE size like ?";

    private static final String GET_TSHIRTS_BY_COLOR_SQL =
            "SELECT * FROM t_shirt WHERE color like ?";

    @Autowired
    public TShirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}


    @Transactional
    @Override
    public TShirt addTshirt(TShirt tShirt) {
        jdbcTemplate.update(INSERT_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        tShirt.settShirtId(id);

        return tShirt;
    }

    @Override
    public List<TShirt> getAllTshirts() {
        return jdbcTemplate.query(SELECT_ALL_TSHIRTS_SQL,this::mapRowToTShirt);
    }



    @Override
    public TShirt getTshirt(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TSHIRT_SQL, this::mapRowToTShirt, id);
        }catch (EmptyResultDataAccessException e){

            return null;
        }
    }

    @Override
    public List<TShirt> getTshirtBySize(String size) {
        return jdbcTemplate.query(GET_TSHIRTS_BY_SIZE_SQL, this::mapRowToTShirt,"%"+size+"%");
    }

    @Override
    public List<TShirt> getTshirtByColor(String color) {
        return jdbcTemplate.query(GET_TSHIRTS_BY_COLOR_SQL, this::mapRowToTShirt,"%"+color+"%");
    }

    @Override
    public void deleteTshirt(int id) {
        jdbcTemplate.update(DELETE_TSHIRT_SQL,id);
    }

    @Override
    public void updateTshirt(TShirt tShirt) {
        jdbcTemplate.update(UPDATE_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity(),
                tShirt.gettShirtId());
    }

    //rowmapper

    private TShirt mapRowToTShirt(ResultSet rs, int rowNum) throws SQLException {
        TShirt tShirt = new TShirt();
        tShirt.settShirtId(rs.getInt("t_shirt_id"));
        tShirt.setSize(rs.getString("size"));
        tShirt.setColor(rs.getString("color"));
        tShirt.setDescription(rs.getString("description"));
        tShirt.setPrice(rs.getBigDecimal("price"));
        tShirt.setQuantity(rs.getInt("quantity"));

        return tShirt;
    }
}
