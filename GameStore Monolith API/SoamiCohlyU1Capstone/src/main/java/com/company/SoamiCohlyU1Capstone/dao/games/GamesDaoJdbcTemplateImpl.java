package com.company.SoamiCohlyU1Capstone.dao.games;


import com.company.SoamiCohlyU1Capstone.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GamesDaoJdbcTemplateImpl implements GamesDao {

    private JdbcTemplate jdbcTemplate;

    //Prepared statements
    private static final String INSERT_GAME_SQL =
            "INSERT INTO game (title, esrb_rating, description, price, studio, quantity) values(?,?,?,?,?,?)";

    private static final String SELECT_GAME_SQL =
            "SELECT * FROM game WHERE game_id = ?";

    private static final String SELECT_ALL_GAMES_SQL =
            "SELECT * FROM game";

    private static final String UPDATE_GAME_SQL =
            "UPDATE game set title = ?, esrb_rating = ?, description = ?, price = ?, studio = ?, quantity = ? WHERE game_id = ?";

    private static final String DELETE_GAME_SQL =
            "DELETE FROM game WHERE  game_id = ?";

    private static final String GET_GAMES_BY_TITLE_SQL =
            "SELECT * FROM game WHERE title like ? ";

    private static final String GET_GAMES_BY_STUDIO_SQL =
            "SELECT * FROM game WHERE studio like ? ";

    private static final String GET_GAMES_BY_RATING_SQL =
            "SELECT * FROM game WHERE esrb_rating like ? ";

    @Autowired
    public GamesDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Game> getAllGames() {
        return jdbcTemplate.query(SELECT_ALL_GAMES_SQL,this::mapRowToGame);
    }

    @Transactional
    @Override
    public Game addGame(Game game) {
        jdbcTemplate.update(
                INSERT_GAME_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        game.setGameId(id);
        return game;
    }

    @Override
    public Game getGame(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_GAME_SQL, this::mapRowToGame, id);
        }catch (EmptyResultDataAccessException e){

            return null;
        }
    }

    @Override
    public List<Game> getGamesByStudio(String studio) {
        return jdbcTemplate.query(GET_GAMES_BY_STUDIO_SQL,this::mapRowToGame,"%"+studio+"%");
    }

    @Override
    public List<Game> getGamesByRating(String esrbRating) {
        return jdbcTemplate.query(GET_GAMES_BY_RATING_SQL,this::mapRowToGame,"%"+esrbRating+"%");
    }

    @Override
    public List<Game> getGamesByTitle(String title) {
        return jdbcTemplate.query(GET_GAMES_BY_TITLE_SQL,this::mapRowToGame,"%"+title+"%");
    }

//    @Override
//    public Game getGameByTitle(String title) {
//        return null;
//    }

    @Override
    public void updateGame(Game game) {
        jdbcTemplate.update(UPDATE_GAME_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity(),
                game.getGameId());

    }

    @Override
    public void deleteGame(int id) { jdbcTemplate.update(DELETE_GAME_SQL,id); }

    private Game mapRowToGame(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();
        game.setGameId(rs.getInt("game_id"));
        game.setTitle(rs.getString("title"));
        game.setEsrbRating(rs.getString("esrb_rating"));
        game.setDescription(rs.getString("description"));
        game.setPrice(rs.getBigDecimal("price"));
        game.setStudio(rs.getString("studio"));
        game.setQuantity(rs.getInt("quantity"));

        return game;
    }
}
