package com.company.SoamiCohlyU1Capstone.dao;


import com.company.SoamiCohlyU1Capstone.dao.games.GamesDao;
import com.company.SoamiCohlyU1Capstone.model.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoJdbcTemplateImplTest {

    @Autowired
    protected GamesDao gamesDao;

    @Before
    public void setUp() throws Exception {
        List<Game> game = gamesDao.getAllGames();
        for (Game g : game) {
            gamesDao.deleteGame(g.getGameId());
        }
    }

    @After
    public void tearDown() throws Exception {
        List<Game> game = gamesDao.getAllGames();
        for (Game g : game) {
            gamesDao.deleteGame(g.getGameId());
        }
    }

    @Test
    public void addGetDeleteGame(){
        Game game = new Game();
        game.setTitle("GTA V");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Los Santos");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(5);
        game = gamesDao.addGame(game);

        Game game1 = gamesDao.getGame(game.getGameId());
        assertEquals(game1,game);

        gamesDao.deleteGame(game.getGameId());
        game1 = gamesDao.getGame(game.getGameId());
        assertNull(game1);

    }

    @Test
    public void updateGame(){
        Game game = new Game();
        game.setTitle("GTA V");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Los Santos");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(5);
        game = gamesDao.addGame(game);

        game.setTitle("Mario Kart");
        game.setEsrbRating("E");
        game.setDescription("Race with classic and new nintendo characters");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Nintendo");
        game.setQuantity(10);
        gamesDao.updateGame(game);

        Game game1 = gamesDao.getGame(game.getGameId());
        assertEquals(game1,game);
    }

    @Test
    public void getAllGames(){
        Game game = new Game();
        game.setTitle("GTA V");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Los Santos");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(5);
        game = gamesDao.addGame(game);

        game.setTitle("GTA VI");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Liberty Tokyo");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(2);
        game = gamesDao.addGame(game);

        game.setTitle("Mario Kart");
        game.setEsrbRating("E");
        game.setDescription("Race with classic and new nintendo characters");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Nintendo");
        game.setQuantity(10);
        gamesDao.addGame(game);

        game.setTitle("Last Jedi: Fallen Order");
        game.setEsrbRating("M");
        game.setDescription("You're a jedi in training, the last of them, combat the Empire");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Lucasarts");
        game.setQuantity(1);
        gamesDao.addGame(game);

        game.setTitle("The Force Unleashed 2");
        game.setEsrbRating("T");
        game.setDescription("You're a jedi in training or a Sith, play to findout");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Lucasarts");
        game.setQuantity(5);
        gamesDao.addGame(game);

//        game.setTitle("Catch em all");
//        game.setEsrbRating("E");
//        game.setDescription("Catch the pokemon");
//        game.setPrice(new BigDecimal("39.99"));
//        game.setStudio("Nintendo");
//        game.setQuantity(20);
//        gamesDao.addGame(game);

        List<Game> gameList = gamesDao.getAllGames();
        assertEquals(5,gameList.size());
    }

    @Test
    public void getGamesByStudio(){
        Game game = new Game();
        game.setTitle("GTA V");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Los Santos");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(5);
        game = gamesDao.addGame(game);

        game.setTitle("GTA VI");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Liberty Tokyo");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(2);
        game = gamesDao.addGame(game);

        game.setTitle("Mario Kart");
        game.setEsrbRating("E");
        game.setDescription("Race with classic and new nintendo characters");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Nintendo");
        game.setQuantity(10);
        gamesDao.addGame(game);

        game.setTitle("Last Jedi: Fallen Order");
        game.setEsrbRating("M");
        game.setDescription("You're a jedi in training, the last of them, combat the Empire");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Lucasarts");
        game.setQuantity(1);
        gamesDao.addGame(game);

        game.setTitle("The Force Unleashed 2");
        game.setEsrbRating("T");
        game.setDescription("You're a jedi in training or a Sith, play to findout");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Lucasarts");
        game.setQuantity(5);
        gamesDao.addGame(game);

//        game.setTitle("Catch em all");
//        game.setEsrbRating("E");
//        game.setDescription("Catch the pokemon");
//        game.setPrice(new BigDecimal("39.99"));
//        game.setStudio("Nintendo");
//        game.setQuantity(20);
//        gamesDao.addGame(game);

        List<Game> rockStarList = gamesDao.getGamesByStudio("Rockstar Games");
        assertEquals(2,rockStarList.size());

        List<Game> lucasList = gamesDao.getGamesByStudio("Lucasarts");
        assertEquals(2,lucasList.size());

        List<Game> nintendo = gamesDao.getGamesByStudio("Nintendo");
        assertEquals(1,nintendo.size());

        List<Game> activision = gamesDao.getGamesByStudio("Activision");
        assertEquals(0,activision.size());
    }

    @Test
    public void getGamesByRating(){
        Game game = new Game();
        game.setTitle("GTA V");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Los Santos");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(5);
        game = gamesDao.addGame(game);

        game.setTitle("GTA VI");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Liberty Tokyo");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(2);
        game = gamesDao.addGame(game);

        game.setTitle("Mario Kart");
        game.setEsrbRating("E");
        game.setDescription("Race with classic and new nintendo characters");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Nintendo");
        game.setQuantity(10);
        gamesDao.addGame(game);

        game.setTitle("Last Jedi: Fallen Order");
        game.setEsrbRating("M");
        game.setDescription("You're a jedi in training, the last of them, combat the Empire");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Lucasarts");
        game.setQuantity(1);
        gamesDao.addGame(game);

        game.setTitle("The Force Unleashed 2");
        game.setEsrbRating("T");
        game.setDescription("You're a jedi in training or a Sith, play to findout");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Lucasarts");
        game.setQuantity(5);
        gamesDao.addGame(game);

        List<Game> mList = gamesDao.getGamesByRating("M");
        assertEquals(3,mList.size());

        List<Game> tList = gamesDao.getGamesByRating("T");
        assertEquals(1,tList.size());

        List<Game> eList = gamesDao.getGamesByRating("E");
        assertEquals(1,eList.size());

        List<Game> aList = gamesDao.getGamesByRating("A");
        assertEquals(0,aList.size());
    }

    @Test
    public void getGameByTitle(){
        Game game = new Game();
        game.setTitle("GTA V");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Los Santos");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(5);
        game = gamesDao.addGame(game);

        game.setTitle("GTA VI");
        game.setEsrbRating("M");
        game.setDescription("Run the streets of Liberty Tokyo");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(2);
        game = gamesDao.addGame(game);

        game.setTitle("Mario Kart");
        game.setEsrbRating("E");
        game.setDescription("Race with classic and new nintendo characters");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Nintendo");
        game.setQuantity(10);
        gamesDao.addGame(game);

        game.setTitle("Last Jedi: Fallen Order");
        game.setEsrbRating("M");
        game.setDescription("You're a jedi in training, the last of them, combat the Empire");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Lucasarts");
        game.setQuantity(1);
        gamesDao.addGame(game);

        game.setTitle("The Force Unleashed 2");
        game.setEsrbRating("T");
        game.setDescription("You're a jedi in training or a Sith, play to findout");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Lucasarts");
        game.setQuantity(5);
        gamesDao.addGame(game);

        List<Game> gameList = gamesDao.getGamesByTitle("Last Jedi: Fallen Order");
        assertEquals(1,gameList.size());
    }


}
