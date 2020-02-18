package com.company.SoamiCohlyU1Capstone.controller;

import com.company.SoamiCohlyU1Capstone.exception.NotFoundException;
import com.company.SoamiCohlyU1Capstone.service.InvoiceGameStoreInventoryService;
import com.company.SoamiCohlyU1Capstone.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class GameController {
    @Autowired
    InvoiceGameStoreInventoryService serviceLayer;



    //=================================================NO SECURITY
//@RequestMapping(value = "/game")



//    @RequestMapping(value ="/game", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public GameViewModel addGame(@RequestBody @Valid GameViewModel gvm) {
//        return serviceLayer.saveGame(gvm);
//    }
//
//    @RequestMapping(value ="game/{id}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public GameViewModel getGame(@PathVariable("id")int id) {
//        GameViewModel gvm = serviceLayer.findGameById(id);
//        if (gvm==null)
//            throw new NotFoundException("Game cant be found in db with id:"+id);
//        return gvm;
//    }
//
//    //GetAllGames
//    @RequestMapping(value ="/games", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<GameViewModel> getAllGames(){return serviceLayer.findAllGames();}
//
//    //=========================================================================================================
//    //Need to Fix get by Manufactureer as it does not work
//    @RequestMapping(value ="games/title/{title}",method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title){
//        return serviceLayer.findGamesByTitle(title);
//    }
//
//    //=========================================================================================================
//    //Need to Fix get by Manufactureer as it does not work
//    @RequestMapping(value ="games/studio/{studio}",method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<GameViewModel> getAllGamesByStudio(@PathVariable("studio") String studio) {
//        return serviceLayer.findGamesByStudio(studio);
//    }
//
//    //=========================================================================================================
//    //Need to Fix get by Manufactureer as it does not work
//    @RequestMapping(value ="games/rating/{rating}",method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<GameViewModel> getAllGamesByRating(@PathVariable("rating") String rating) {
////        return serviceLayer.findGamesByRating(rating.toUpperCase());
//        List<GameViewModel>ratingList  = serviceLayer.findGamesByRating(rating);
//        if (ratingList.size() == 0) {
//            throw new NotFoundException("Cant find any games by given rating: " + rating);
//        }
//        return ratingList;
//    }
//
//    @RequestMapping(value ="game/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public String updateGame(@PathVariable("id") int id, @RequestBody @Valid GameViewModel gvm) {
//        serviceLayer.updateGame(gvm);
//        return "Game updated.";
//    }
//
//    @RequestMapping(value ="game/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public String removeGame(@PathVariable("id") int id) {
//        serviceLayer.removeGameById(id);
//        return "Game  deleted.";
//    }

    @RequestMapping(value ="/game", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel addGame(Principal principal,@RequestBody @Valid GameViewModel gvm) {
        return serviceLayer.saveGame(gvm);
    }

    @RequestMapping(value ="game/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGame(@PathVariable("id")int id) {
        GameViewModel gvm = serviceLayer.findGameById(id);
        if (gvm==null)
            throw new NotFoundException("Game cant be found in db with id:"+id);
        return gvm;
    }

    //GetAllGames
    @RequestMapping(value ="/games", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames(){return serviceLayer.findAllGames();}

    //=========================================================================================================
    //Need to Fix get by Manufactureer as it does not work
    @RequestMapping(value ="games/title/{title}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title){
        return serviceLayer.findGamesByTitle(title);
    }

    //=========================================================================================================
    //Need to Fix get by Manufactureer as it does not work
    @RequestMapping(value ="games/studio/{studio}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGamesByStudio(@PathVariable("studio") String studio) {
        return serviceLayer.findGamesByStudio(studio);
    }

    //=========================================================================================================
    //Need to Fix get by Manufactureer as it does not work
    @RequestMapping(value ="games/rating/{rating}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGamesByRating(@PathVariable("rating") String rating) {
//        return serviceLayer.findGamesByRating(rating.toUpperCase());
        List<GameViewModel>ratingList  = serviceLayer.findGamesByRating(rating);
        if (ratingList.size() == 0) {
            throw new NotFoundException("Cant find any games by given rating: " + rating);
        }
        return ratingList;
    }

    @RequestMapping(value ="game/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateGame(Principal principal,@PathVariable("id") int id, @RequestBody @Valid GameViewModel gvm) {
        serviceLayer.updateGame(gvm);
        return "Game updated.";
    }

    @RequestMapping(value ="game/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String removeGame(Principal principal,@PathVariable("id") int id) {
        serviceLayer.removeGameById(id);
        return "Game  deleted.";
    }

}
