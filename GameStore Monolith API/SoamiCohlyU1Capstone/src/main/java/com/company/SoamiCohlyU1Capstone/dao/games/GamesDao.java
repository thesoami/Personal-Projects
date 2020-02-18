package com.company.SoamiCohlyU1Capstone.dao.games;

import com.company.SoamiCohlyU1Capstone.model.Game;

import java.util.List;

public interface GamesDao {

    List<Game>getAllGames();

    Game addGame(Game game);

    Game getGame(int id);

    List<Game> getGamesByStudio(String studio);

    List<Game> getGamesByRating(String esrbRating);

    List<Game> getGamesByTitle(String title);

//    Game getGameByTitle(String title);

    void updateGame(Game game);

    void deleteGame(int id);


}
