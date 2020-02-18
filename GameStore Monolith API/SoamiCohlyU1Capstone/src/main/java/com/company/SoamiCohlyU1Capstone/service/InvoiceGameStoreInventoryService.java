package com.company.SoamiCohlyU1Capstone.service;

import com.company.SoamiCohlyU1Capstone.dao.console.ConsolesDao;
import com.company.SoamiCohlyU1Capstone.dao.games.GamesDao;
import com.company.SoamiCohlyU1Capstone.dao.invoice.InvoiceDao;
import com.company.SoamiCohlyU1Capstone.dao.processingfee.ProcessingFeeDao;
import com.company.SoamiCohlyU1Capstone.dao.salestax.SalesTaxRateDao;
import com.company.SoamiCohlyU1Capstone.dao.tshirt.TShirtDao;
import com.company.SoamiCohlyU1Capstone.exception.NotFoundException;
import com.company.SoamiCohlyU1Capstone.model.*;
import com.company.SoamiCohlyU1Capstone.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceGameStoreInventoryService {

    ConsolesDao consolesDao;
    GamesDao gamesDao;
    TShirtDao tShirtDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;
    InvoiceDao invoiceDao;

    @Autowired
    public InvoiceGameStoreInventoryService(ConsolesDao consolesDao, GamesDao gamesDao,
                                            TShirtDao tShirtDao,ProcessingFeeDao processingFeeDao
                                            , SalesTaxRateDao salesTaxRateDao,InvoiceDao invoiceDao){

        this.consolesDao = consolesDao;
        this.gamesDao = gamesDao;
        this.tShirtDao = tShirtDao;
        this.invoiceDao = invoiceDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxRateDao = salesTaxRateDao;
    }

    //ConsoleViewModel methods:
    public ConsoleViewModel saveConsole( ConsoleViewModel consoleViewModel){
        Console console = new Console();
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());
        console = consolesDao.addConsole(console);

        consoleViewModel.setConsoleId(console.getConsoleId());
        return consoleViewModel;
    }

    public void updateConsole(ConsoleViewModel consoleViewModel){

        Console console = new Console();
        console.setConsoleId(consoleViewModel.getConsoleId());
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        consolesDao.updateConsole(console);
    }

    public ConsoleViewModel findConsoleById(int id){
        Console console = consolesDao.getConsole(id);
        if (console==null)
            return null;
        else
            return buildConsoleViewModel(console);
    }
    public List<ConsoleViewModel>findAllConsoles(){
        List<Console>consoleList = consolesDao.getAllConsoles();
        List<ConsoleViewModel> cvmList = new ArrayList<>();
        consoleList.stream().forEach(console -> cvmList.add(buildConsoleViewModel(console)));

        return cvmList;
    }

    public List<ConsoleViewModel>findConsolesByManf(String manufacturer){
        List<Console> consoleList = consolesDao.getConsoleByManuf(manufacturer);
        List<ConsoleViewModel> cvmList = new ArrayList<>();

        consoleList.stream().forEach(console -> cvmList.add(buildConsoleViewModel(console)));

        return cvmList;
    }

    public void removeConsoleById(int id){consolesDao.deleteConsole(id);}


    //GameViewModel methods:
    public GameViewModel saveGame(GameViewModel gameViewModel){
        Game game = new Game();
//        game.setGameId(gameViewModel.getGameId());
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());
        game = gamesDao.addGame(game);

        gameViewModel.setGameId(game.getGameId());

        return gameViewModel;
    }

    public void updateGame(GameViewModel gameViewModel){
        Game game = new Game();
        game.setGameId(gameViewModel.getGameId());
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());
        gamesDao.updateGame(game);
    }

    public GameViewModel findGameById(int id){
        Game game = gamesDao.getGame(id);
        if(game == null)
            return null;
        else
            return buildGameViewModel(game);
    }

    public List<GameViewModel>findAllGames(){
        List<Game>gameList = gamesDao.getAllGames();
        List<GameViewModel> gvmList = new ArrayList<>();
        gameList.stream().forEach(game -> gvmList.add(buildGameViewModel(game)));

        return gvmList;
    }

    public List<GameViewModel> findGamesByStudio(String studio){
        List<Game> gameList = gamesDao.getGamesByStudio(studio);
        List<GameViewModel> gvmList = new ArrayList<>();

        gameList.stream().forEach(game -> gvmList.add(buildGameViewModel(game)));

        return gvmList;
    }

    public List<GameViewModel> findGamesByRating (String rating){
        List<Game> gameList = gamesDao.getGamesByRating(rating);
        List<GameViewModel> gvmList = new ArrayList<>();

        gameList.stream().forEach(game -> gvmList.add(buildGameViewModel(game)));

        return gvmList;

    }

    public List<GameViewModel> findGamesByTitle (String title){
        List<Game> gameList = gamesDao.getGamesByTitle(title);
        List<GameViewModel> gvmList = new ArrayList<>();

        gameList.stream().forEach(game -> gvmList.add(buildGameViewModel(game)));

        return gvmList;

    }

    public void removeGameById(int id){gamesDao.deleteGame(id);}

    //TShirtViewModel methods:
    public TShirtViewModel saveTShirt(TShirtViewModel tShirtViewModel){
        TShirt tShirt = new TShirt();
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());
        tShirt = tShirtDao.addTshirt(tShirt);

        tShirtViewModel.settShirtId(tShirt.gettShirtId());
        return tShirtViewModel;
    }

    public void updateTShirt(TShirtViewModel tShirtViewModel){
        TShirt tShirt = new TShirt();
        tShirt.settShirtId(tShirtViewModel.gettShirtId());
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());

        tShirtDao.updateTshirt(tShirt);
    }

    public TShirtViewModel findTShirtById(int id){
        TShirt tShirt = tShirtDao.getTshirt(id);
        if(tShirt==null)
            return null;
        else
            return buildTShirtViewModel(tShirt);
    }

    public List<TShirtViewModel>findAllTShirts(){
        List<TShirt>tShirtList = tShirtDao.getAllTshirts();
        List<TShirtViewModel> tvmList = new ArrayList<>();
        tShirtList.stream().forEach(tShirt -> tvmList.add(buildTShirtViewModel(tShirt)));

        return tvmList;
    }

    public List<TShirtViewModel> getTShirtsBySize(String size){
        List<TShirt> tShirtList = tShirtDao.getTshirtBySize(size);
        List<TShirtViewModel> tsvmList = new ArrayList<>();

        tShirtList.stream().forEach(tShirt -> tsvmList.add(buildTShirtViewModel(tShirt)));

        return tsvmList;
    }
    public List<TShirtViewModel> getTShirtsByColor(String color){
        List<TShirt> tShirtList = tShirtDao.getTshirtByColor(color);
        List<TShirtViewModel> tsvmList = new ArrayList<>();

        tShirtList.stream().forEach(tShirt -> tsvmList.add(buildTShirtViewModel(tShirt)));

        return tsvmList;
    }

    public void removeTShirt(int id){tShirtDao.deleteTshirt(id);}

    @Transactional
    public InvoiceViewModel saveInvoice(OrderViewModel orderVM){
        Invoice invoice = new Invoice();

        if(orderVM.getName().length()>80)
            throw new NotFoundException("Name field is too long");

        if(orderVM.getStreet().length()>30)
            throw new IllegalArgumentException("Name field is too long");
        if(orderVM.getCity().length()>30)
            throw new IllegalArgumentException("City field is too long");

        if(orderVM.getState().length()!=2)
            throw new NotFoundException("Enter a valid state please, two characters ");

        if(orderVM.getZipCode().length()!=5)
            throw new NotFoundException("Enter a valid zipcode please, five characters ");

        if(orderVM.getQuantity()<=0)
            throw new NotFoundException("You can't have a negative quantity or 0 sorry ");

        invoice.setName(orderVM.getName());
        invoice.setStreet(orderVM.getStreet());
        invoice.setCity(orderVM.getCity());
        invoice.setState(orderVM.getState());
        invoice.setZipCode(orderVM.getZipCode());
        invoice.setItemType(orderVM.getItemType());
        invoice.setItemId(orderVM.getItemId());
        invoice.setQuantity(orderVM.getQuantity());

        BigDecimal itemPrice;

        if (invoice.getItemType().equals("Console")) {

            Console console = consolesDao.getConsole(invoice.getItemId());

            if(console==null)
                throw new NotFoundException("There is no console in that inventory id slot");
            int itemQuantity = console.getQuantity();
            itemPrice = console.getPrice();

            if (invoice.getQuantity() > itemQuantity) {
                throw new IllegalArgumentException("Low Stock, sorry, please order at another time.");
            }
            else {
                console.setQuantity(itemQuantity - invoice.getQuantity());
                consolesDao.updateConsole(console);
            }
        }

        else if (invoice.getItemType().equals("Game")) {
            Game game = gamesDao.getGame(invoice.getItemId());
            if(game==null)
                throw new NotFoundException("There is no game in that inventory id slot");
            int itemQuantity = game.getQuantity();
            itemPrice = game.getPrice();

            if (invoice.getQuantity() > itemQuantity) {
                throw new IllegalArgumentException("Low Stock, sorry, please order at another time.");
            }
            else {
                game.setQuantity(itemQuantity - invoice.getQuantity());
                gamesDao.updateGame(game);
            }
        }

        else if (invoice.getItemType().equals("T-Shirt")) {

            TShirt tShirt = tShirtDao.getTshirt(invoice.getItemId());
            if(tShirt==null)
                throw new NotFoundException("There is no tShirt in that inventory id slot");

            int itemQuantity = tShirt.getQuantity();
            itemPrice = tShirt.getPrice();

            if (invoice.getQuantity() > itemQuantity) {
                throw new IllegalArgumentException("Low Stock, sorry, please order at another time.");
            }
            else {
                tShirt.setQuantity(itemQuantity - invoice.getQuantity());
                tShirtDao.updateTshirt(tShirt);
            }
        }

        else {
            throw new IllegalArgumentException("Incorrect Entry, please choose from a Console, Game or T-Shirt");
        }
        invoice.setUnitPrice(itemPrice.setScale(2));

//        BigDecimal taxRate = salesTaxRateDao.getSalesTaxRate(invoice.getState());

//        SalesTaxRate taxRate = salesTaxRateDao.getSalesTaxRateObj(invoice.getState());
        BigDecimal taxRate = salesTaxRateDao.getSalesTaxRate(invoice.getState());
        System.out.println(taxRate);
        //^BigDecimial inputs for both
        BigDecimal processingFee = processingFeeDao.getFee(invoice.getItemType());
        invoice.setSubTotal(BigDecimal.valueOf(invoice.getQuantity()).multiply(invoice.getUnitPrice()).setScale(2));

//        System.out.println(taxRate.getRate());
        System.out.println(taxRate);
        invoice.setTax(taxRate.multiply(invoice.getSubTotal()).setScale(2,RoundingMode.HALF_UP));

        System.out.println(invoice.getItemType());
        if(invoice.getQuantity()>10)
            invoice.setProcessingFee(new BigDecimal("15.49").add(processingFee).setScale(2));

        else{
            BigDecimal fee = processingFeeDao.getFee(invoice.getItemType()+"s");
            invoice.setProcessingFee(fee);
        }

        System.out.println(invoice.getProcessingFee());
        System.out.println(invoice.getTotal());
//set break here line 338
        invoice.setTotal((invoice.getSubTotal()).add(invoice.getTax()).add(invoice.getProcessingFee()));
        System.out.println(invoice.getTotal());
        invoice = invoiceDao.addInvoice(invoice);

        return buildInvoiceViewModel(invoice);
    }

    private ConsoleViewModel buildConsoleViewModel(Console console){
        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setConsoleId(console.getConsoleId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setMemoryAmount(console.getMemoryAmount());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setQuantity(console.getQuantity());
        return consoleViewModel;
    }

    private GameViewModel buildGameViewModel(Game game){
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGameId(game.getGameId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setQuantity(game.getQuantity());
        return gameViewModel;
    }

    private TShirtViewModel buildTShirtViewModel(TShirt tShirt){
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.settShirtId(tShirt.gettShirtId());
        tShirtViewModel.setSize(tShirt.getSize());
        tShirtViewModel.setColor(tShirt.getColor());
        tShirtViewModel.setDescription(tShirt.getDescription());
        tShirtViewModel.setPrice(tShirt.getPrice());
        tShirtViewModel.setQuantity(tShirt.getQuantity());

        return tShirtViewModel;
    }



    private ReceiptViewModel buildReceiptViewModel(Invoice invoice){
        ReceiptViewModel rvm = new ReceiptViewModel();
        rvm.setName(invoice.getName());
        rvm.setStreet(invoice.getStreet());
        rvm.setCity(invoice.getCity());
        rvm.setState(invoice.getState());
        rvm.setZipCode(invoice.getZipCode());
        rvm.setItemType(invoice.getItemType());
        rvm.setUnitPrice(invoice.getUnitPrice());
        rvm.setQuantity(invoice.getQuantity());
        rvm.setSubTotal(invoice.getSubTotal());
        rvm.setTax(invoice.getTax());
        rvm.setProcessingFee(invoice.getProcessingFee());
        rvm.setTotal(invoice.getTotal());

        return rvm;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        InvoiceViewModel ivm = new InvoiceViewModel();

        ivm.setInvoiceId(invoice.getInvoiceId());
        ivm.setName(invoice.getName());
        ivm.setStreet(invoice.getStreet());
        ivm.setCity(invoice.getCity());
        ivm.setState(invoice.getState());
        ivm.setZipCode(invoice.getZipCode());
        ivm.setItemType(invoice.getItemType());
        ivm.setItemId(invoice.getItemId());
        ivm.setUnitPrice(invoice.getUnitPrice());
        ivm.setQuantity(invoice.getQuantity());
        ivm.setSubTotal(invoice.getSubTotal());
        ivm.setTax(invoice.getTax());
        ivm.setProcessingFee(invoice.getProcessingFee());
        ivm.setTotal(invoice.getTotal());

        return ivm;
    }

    /*
    in the helpermethod we build the receiptViewModel right? BUT we need to check via a switch then if/else
    that the quanitity isnt EXCEEDING inventory then we build the model
     */

//    private OrderViewModel buildOrderViewModel(Invoice invoice){
//
//    }

}

/*
 TShirtViewModel tvm = new TShirtViewModel();
        tvm.setSize("Large");
        tvm.setColor("Black");
        tvm.setDescription("Patterned Black shirt with a fade and the Star Wars logo in red");
        tvm.setPrice(new BigDecimal("30.99"));
        tvm.setQuantity(12);

        tvm = invoiceGameStoreInventoryService.saveTShirt(tvm);

        tvm.setSize("Large");
        tvm.setColor("Black");
        tvm.setDescription("Patterned Black shirt with a fade and the Star Wars logo in red");
        tvm.setPrice(new BigDecimal("20.99"));
        tvm.setQuantity(10);
        invoiceGameStoreInventoryService.updateTShirt(tvm);
        TShirtViewModel tvm2 = invoiceGameStoreInventoryService.findTShirtById(tvm.gettShirtId());
        assertEquals(tvm2,tvm);

 */
