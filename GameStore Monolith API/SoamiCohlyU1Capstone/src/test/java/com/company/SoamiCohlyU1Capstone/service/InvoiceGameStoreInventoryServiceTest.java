package com.company.SoamiCohlyU1Capstone.service;


import com.company.SoamiCohlyU1Capstone.dao.console.ConsoleDaoJdbcTemplateImpl;
import com.company.SoamiCohlyU1Capstone.dao.console.ConsolesDao;
import com.company.SoamiCohlyU1Capstone.dao.games.GamesDao;
import com.company.SoamiCohlyU1Capstone.dao.games.GamesDaoJdbcTemplateImpl;
import com.company.SoamiCohlyU1Capstone.dao.invoice.InvoiceDao;
import com.company.SoamiCohlyU1Capstone.dao.invoice.InvoiceDaoJdbcTemplateImpl;
import com.company.SoamiCohlyU1Capstone.dao.processingfee.ProcessingFeeDao;
import com.company.SoamiCohlyU1Capstone.dao.processingfee.ProcessingFeeDaoJdbcTemplateImpl;
import com.company.SoamiCohlyU1Capstone.dao.salestax.SalesTaxRateDao;
import com.company.SoamiCohlyU1Capstone.dao.salestax.SalesTaxRateDaoJdbcTemplateImpl;
import com.company.SoamiCohlyU1Capstone.dao.tshirt.TShirtDao;
import com.company.SoamiCohlyU1Capstone.dao.tshirt.TShirtDaoJdbcTemplateImpl;
import com.company.SoamiCohlyU1Capstone.model.*;
import com.company.SoamiCohlyU1Capstone.viewmodel.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class InvoiceGameStoreInventoryServiceTest {
    ConsolesDao consolesDao;
    GamesDao gamesDao;
    TShirtDao tShirtDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;
    InvoiceDao invoiceDao;

    InvoiceGameStoreInventoryService invoiceGameStoreInventoryService;

    @Before
    public void  setUp() throws Exception{
        setUpConsolesDaoMock();
        setUpGamesDao();
        setUpTShirtDao();
        setUpProcessingFee();
        setUpSalesTaxRateDao();
        setUpInvoiceDao();

        invoiceGameStoreInventoryService = new InvoiceGameStoreInventoryService(consolesDao, gamesDao, tShirtDao, processingFeeDao,
                salesTaxRateDao,invoiceDao);
    }

    @Test
    public void saveFindFindAllConsole(){
        ConsoleViewModel cvm = new ConsoleViewModel();
//        cvm.setConsoleId(1);
        cvm.setModel("Xbox1");
        cvm.setManufacturer("Microsoft");
        cvm.setMemoryAmount("256 gb");
        cvm.setProcessor("amd 8 core");
        cvm.setPrice(new BigDecimal("299.99"));
        cvm.setQuantity(1);

        cvm = invoiceGameStoreInventoryService.saveConsole(cvm);
        ConsoleViewModel cvmFromService = invoiceGameStoreInventoryService.findConsoleById(cvm.getConsoleId());
        assertEquals(cvm,cvmFromService);

//        ConsoleViewModel cvm2 = new ConsoleViewModel();
//        cvm2.setConsoleId(2);
//        cvm2.setModel("Xbox360");
//        cvm2.setManufacturer("Microsoft");
//        cvm2.setMemoryAmount("256 gb");
//        cvm2.setProcessor("amd 4 core");
//        cvm2.setPrice(new BigDecimal("199.99"));
//        cvm2.setQuantity(4);
//
//        cvm2=invoiceGameStoreInventoryService.saveConsole(cvm2);

        ConsoleViewModel cvm3 = new ConsoleViewModel();
//        cvm3.setConsoleId(5);
        cvm3.setModel("Ps4");
        cvm3.setManufacturer("Sony");
        cvm3.setMemoryAmount("1 tb");
        cvm3.setProcessor("amd 8 core");
        cvm3.setPrice(new BigDecimal("399.99"));
        cvm3.setQuantity(4);
        cvm3=invoiceGameStoreInventoryService.saveConsole(cvm3);

//        ConsoleViewModel cvm4 = new ConsoleViewModel();
//        cvm4.setConsoleId(6);
//        cvm4.setModel("Ps3");
//        cvm4.setManufacturer("Sony");
//        cvm4.setMemoryAmount("500 gb");
//        cvm4.setProcessor("amd 4 core");
//        cvm4.setPrice(new BigDecimal("199.99"));
//        cvm4.setQuantity(2);
//        cvm4 = invoiceGameStoreInventoryService.saveConsole(cvm4);

        List<ConsoleViewModel> cvmlist = invoiceGameStoreInventoryService.findAllConsoles();
        assertEquals(2,cvmlist.size());
        assertEquals(cvm,cvmlist.get(0));
//        assertEquals(cvm2,cvmlist.get(1));
        assertEquals(cvm3,cvmlist.get(1));
//        assertEquals(cvm4,cvmlist.get(3));
    }

    @Test
    public void saveConsole(){
        ConsoleViewModel cvm5 = new ConsoleViewModel();
//        cvm5.setConsoleId(1);
        cvm5.setModel("Xbox1");
        cvm5.setManufacturer("Microsoft");
        cvm5.setManufacturer("Microsoft");
        cvm5.setMemoryAmount("256 gb");
        cvm5.setProcessor("amd 8 core");
        cvm5.setPrice(new BigDecimal("299.99"));
        cvm5.setQuantity(1);

        cvm5 = invoiceGameStoreInventoryService.saveConsole(cvm5);
        ConsoleViewModel cvm = invoiceGameStoreInventoryService.findConsoleById(cvm5.getConsoleId());
        assertEquals(cvm5,cvm);
    }

    @Test
    public void findConsole(){
        ConsoleViewModel cvm2 = new ConsoleViewModel();
        cvm2.setConsoleId(1);
        cvm2.setModel("Xbox1");
        cvm2.setManufacturer("Microsoft");
        cvm2.setManufacturer("Microsoft");
        cvm2.setMemoryAmount("256 gb");
        cvm2.setProcessor("amd 8 core");
        cvm2.setPrice(new BigDecimal("299.99"));
        cvm2.setQuantity(1);

        ConsoleViewModel cvmFound = invoiceGameStoreInventoryService.findConsoleById(cvm2.getConsoleId());
        assertEquals(cvm2,cvmFound);
    }

    @Test
    public void findConsoleByManuf(){
        List<ConsoleViewModel> cvmMsoft = new ArrayList<>();

        ConsoleViewModel cvm = new ConsoleViewModel();
//        cvm.setConsoleId(1);
        cvm.setModel("Xbox1");
        cvm.setManufacturer("Microsoft");
        cvm.setMemoryAmount("256 gb");
        cvm.setProcessor("amd 8 core");
        cvm.setPrice(new BigDecimal("299.99"));
        cvm.setQuantity(1);

        cvm = invoiceGameStoreInventoryService.saveConsole(cvm);
//        cvmMsoft.add(cvm);

        ConsoleViewModel cvm2 = new ConsoleViewModel();
//        cvm2.setConsoleId(2);
//        cvm2.setModel("Xbox360");
//        cvm2.setManufacturer("Microsoft");
//        cvm2.setMemoryAmount("256 gb");
//        cvm2.setProcessor("amd 4 core");
//        cvm2.setPrice(new BigDecimal("199.99"));
//        cvm2.setQuantity(4);
//        cvm2=invoiceGameStoreInventoryService.saveConsole(cvm2);

//        cvmMsoft.add(cvm2);
        cvmMsoft=invoiceGameStoreInventoryService.findConsolesByManf(cvm.getManufacturer());

        assertEquals(1,cvmMsoft.size());

    }

    @Test
    public void findAllConsoles(){
        List<ConsoleViewModel> all = invoiceGameStoreInventoryService.findAllConsoles();
        assertEquals(2,all.size());
    }

    @Test
    public void updateConsole(){
        //Create console object for update
        ConsoleViewModel cvmUpdate = new ConsoleViewModel();

//        cvmUpdate.setConsoleId(3);
        cvmUpdate.setModel("Xbox1");
        cvmUpdate.setManufacturer("Microsoft");
        cvmUpdate.setMemoryAmount("256 gb");
        cvmUpdate.setProcessor("amd 8 core");
        cvmUpdate.setPrice(new BigDecimal("299.99"));
        cvmUpdate.setQuantity(6);

        //passing updateConsole into cvmUpdate view model
        cvmUpdate = invoiceGameStoreInventoryService.saveConsole(cvmUpdate);
//        System.out.println(cvmUpdate.getConsoleId());


        cvmUpdate.setModel("Xbox1");
        cvmUpdate.setManufacturer("Microsoft");
        cvmUpdate.setMemoryAmount("256 gb");
        cvmUpdate.setProcessor("amd 8 core");
        cvmUpdate.setPrice(new BigDecimal("249.99"));
        cvmUpdate.setQuantity(6);

        invoiceGameStoreInventoryService.updateConsole(cvmUpdate);
        ConsoleViewModel cvmUpdate2 = invoiceGameStoreInventoryService.findConsoleById(cvmUpdate.getConsoleId());

        assertEquals(cvmUpdate2,cvmUpdate);
    }

    @Test
    public void removeConsole(){
//        ConsoleViewModel cvmDelete = new ConsoleViewModel();
        ConsoleViewModel cvmDelete = invoiceGameStoreInventoryService.findConsoleById(4);
        invoiceGameStoreInventoryService.removeGameById(4);
        assertNull(cvmDelete);



//        consoleDelete.setConsoleId(4);
//        cvmDelete.setModel("Xbox Scorpion");
////        cvmDelete.setManufacturer("Microsoft");
////        cvmDelete.setMemoryAmount("2 Tb");
////        cvmDelete.setProcessor("amd 16 core");
////        cvmDelete.setPrice(new BigDecimal("599.99"));
////        cvmDelete.setQuantity(1);
////
////        invoiceGameStoreInventoryService.removeConsoleById(cvmDelete.getConsoleId());
////        ConsoleViewModel cvmDeleteCheck = invoiceGameStoreInventoryService.findConsoleById(cvmDelete.getConsoleId());
////
////        assertNull(cvmDeleteCheck);

    }
    //consolesDao setUp ==================================================================================================

    private void setUpConsolesDaoMock(){
        consolesDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        //ConsoleA and B are paired, B doesnt have a consoleId, A does
        //Microsoft Mock Objects with consoleId
        Console consoleA = new Console();
        consoleA.setConsoleId(1);
        consoleA.setModel("Xbox1");
        consoleA.setManufacturer("Microsoft");
        consoleA.setMemoryAmount("256 gb");
        consoleA.setProcessor("amd 8 core");
        consoleA.setPrice(new BigDecimal("299.99"));
        consoleA.setQuantity(1);

        Console consoleB = new Console();
        consoleB.setModel("Xbox1");
        consoleB.setManufacturer("Microsoft");
        consoleB.setMemoryAmount("256 gb");
        consoleB.setProcessor("amd 8 core");
        consoleB.setPrice(new BigDecimal("299.99"));
        consoleB.setQuantity(1);

       //================================================================================================ --
        Console consoleAA = new Console();
        consoleAA.setConsoleId(111);
        consoleAA.setModel("Xbox1");
        consoleAA.setManufacturer("Microsoft");
        consoleAA.setMemoryAmount("256 gb");
        consoleAA.setProcessor("amd 8 core");
        consoleAA.setPrice(new BigDecimal("299.99"));
        consoleAA.setQuantity(10);

        Console consoleBB = new Console();
        consoleBB.setModel("Xbox1");
        consoleBB.setManufacturer("Microsoft");
        consoleBB.setMemoryAmount("256 gb");
        consoleBB.setProcessor("amd 8 core");
        consoleBB.setPrice(new BigDecimal("299.99"));
        consoleBB.setQuantity(10);

        doReturn(consoleAA).when(consolesDao).addConsole(consoleBB);
        doReturn(consoleAA).when(consolesDao).getConsole(111);
        //----------------------------------------------------------------
        //msoftList creation


        List<Console>msoftList = new ArrayList<>();

        msoftList.add(consoleA);


        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        //Console C and D are paired, C has consoleId, D does not
        Console consoleC = new Console();
        consoleC.setConsoleId(5);
        consoleC.setModel("Ps4");
        consoleC.setManufacturer("Sony");
        consoleC.setMemoryAmount("1 tb");
        consoleC.setProcessor("amd 8 core");
        consoleC.setPrice(new BigDecimal("399.99"));
        consoleC.setQuantity(4);

        Console consoleD = new Console();
        consoleD.setModel("Ps4");
        consoleD.setManufacturer("Sony");
        consoleD.setMemoryAmount("1 tb");
        consoleD.setProcessor("amd 8 core");
        consoleD.setPrice(new BigDecimal("399.99"));
        consoleD.setQuantity(4);





        //----------------------------------------------------------------

        List<Console> sonyList = new ArrayList<>();
        sonyList.add(consoleC);


//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


        doReturn(consoleA).when(consolesDao).addConsole(consoleB);


        doReturn(consoleC).when(consolesDao).addConsole(consoleD);



        doReturn(consoleA).when(consolesDao).getConsole(1);


        doReturn(consoleC).when(consolesDao).getConsole(5);


        List<Console> consoleList = new ArrayList<>();
        consoleList.add(consoleA);

        consoleList.add(consoleC);




        //returns ConsoleA (which has the consoleId, when we add consoleB



        //Mock getConsoleByManufact
        doReturn(msoftList).when(consolesDao).getConsoleByManuf("Microsoft");
        doReturn(sonyList).when(consolesDao).getConsoleByManuf("Sony");
        doReturn(consoleList).when(consolesDao).getAllConsoles();



        //Mock for Update
        //Create a not updated console with same id as updated

        Console consoleNOId = new Console();
        consoleNOId.setModel("Xbox1");
        consoleNOId.setManufacturer("Microsoft");
        consoleNOId.setMemoryAmount("256 gb");
        consoleNOId.setProcessor("amd 8 core");
        consoleNOId.setPrice(new BigDecimal("299.99"));
        consoleNOId.setQuantity(6);


        Console consoleNotUPDATED = new Console();
        consoleNotUPDATED.setConsoleId(3);
        consoleNotUPDATED.setModel("Xbox1");
        consoleNotUPDATED.setManufacturer("Microsoft");
        consoleNotUPDATED.setMemoryAmount("256 gb");
        consoleNotUPDATED.setProcessor("amd 8 core");
        consoleNotUPDATED.setPrice(new BigDecimal("299.99"));
        consoleNotUPDATED.setQuantity(6);



        Console consoleUPDATED = new Console();
        consoleUPDATED.setConsoleId(3);
        consoleUPDATED.setModel("Xbox1");
        consoleUPDATED.setManufacturer("Microsoft");
        consoleUPDATED.setMemoryAmount("256 gb");
        consoleUPDATED.setProcessor("amd 8 core");
        consoleUPDATED.setPrice(new BigDecimal("249.99"));
        consoleUPDATED.setQuantity(6);



        //added Stock and there is a 50 dollar sale
        doReturn(consoleNotUPDATED).when(consolesDao).addConsole(consoleNOId);
        doNothing().when(consolesDao).updateConsole(consoleUPDATED);
        doReturn(consoleUPDATED).when(consolesDao).getConsole(consoleUPDATED.getConsoleId());


        //Mock for Delete-------------------------------------------
        Console consoleDelete = new Console();
        consoleDelete.setConsoleId(4);
        consoleDelete.setModel("Xbox Scorpion");
        consoleDelete.setManufacturer("Microsoft");
        consoleDelete.setMemoryAmount("2 Tb");
        consoleDelete.setProcessor("amd 16 core");
        consoleDelete.setPrice(new BigDecimal("599.99"));
        consoleDelete.setQuantity(1);

        doNothing().when(consolesDao).deleteConsole(4);
        doReturn(null).when(consolesDao).getConsole(4);
    }

    //===============================END OF CONSOLE SERVICE LAYER TESTS================================


    //===============================START OF GAME SERVICE LAYER TESTS================================
    @Test
    public void saveGame(){
        GameViewModel gvm = new GameViewModel();
        gvm.setTitle("Call of Duty: Modern Warfare (2019)");
        gvm.setEsrbRating("Mature: 17+");
        gvm.setDescription("The stakes have never been higher as players take on the role of lethal Tier One " +
                "operators in a heart-racing saga that will affect the global balance" +
                " of power");
        gvm.setPrice(new BigDecimal("59.99"));
        gvm.setStudio("Infinity Ward");
        gvm.setQuantity(10);

        gvm = invoiceGameStoreInventoryService.saveGame(gvm);
        GameViewModel gvmCheck = invoiceGameStoreInventoryService.findGameById(gvm.getGameId());


        assertEquals(gvm,gvmCheck);

    }

    @Test
    public void findGame(){
        GameViewModel gvm2 = new GameViewModel();

        gvm2.setTitle("STAR WARS Jedi: Fallen Order(2019)");
        gvm2.setEsrbRating("Teen: 13+");
        gvm2.setDescription("A new 3rd person action-adventure title from Respawn Entertainment. " +
                "This narratively-driven single player game puts you in the role of a Jedi Padawan who narrowly" +
                " escaped the purge of Order 66 following the events of Episode 3: Revenge of the Sith");
        gvm2.setPrice(new BigDecimal("59.99"));
        gvm2.setStudio("Electronic Arts");
        gvm2.setQuantity(5);

        gvm2 = invoiceGameStoreInventoryService.saveGame(gvm2);

        GameViewModel gvmFound = invoiceGameStoreInventoryService.findGameById(gvm2.getGameId());
        assertEquals(gvmFound,gvm2);
    }

    @Test
    public void findAllGames(){
        List<GameViewModel>allGames = invoiceGameStoreInventoryService.findAllGames();
        assertEquals(2,allGames.size());

    }

    @Test
    public void findGamesByTitle(){
        List<GameViewModel> gvmSW = invoiceGameStoreInventoryService.findGamesByTitle("STAR WARS");
        assertEquals(1,gvmSW.size());
    }

    @Test
    public void findGamesByStudio(){
        List<GameViewModel> iwList = invoiceGameStoreInventoryService.findGamesByStudio("Infinity Ward");
        assertEquals(1,iwList.size());
    }

    @Test
    public void findGamesByRating(){
        List<GameViewModel>ratingList = invoiceGameStoreInventoryService.findGamesByRating("Mature");
        assertEquals(1,ratingList.size());

    }


    @Test
    public void updateGame(){

        GameViewModel gvmUpdate = new GameViewModel();
        gvmUpdate.setTitle("Call of Duty: Modern Warfare (2019)");
        gvmUpdate.setEsrbRating("Mature: 17+");
        gvmUpdate.setDescription("Call of Duty: Modern Warfare (2019) ON SALE! AND FOR PRE ORDER");
        gvmUpdate.setPrice(new BigDecimal("59.99"));
        gvmUpdate.setStudio("Infinity Ward");
        gvmUpdate.setQuantity(4);

        gvmUpdate = invoiceGameStoreInventoryService.saveGame(gvmUpdate);

        gvmUpdate.setTitle("Call of Duty: Modern Warfare (2019)");
        gvmUpdate.setEsrbRating("Mature: 17+");
        gvmUpdate.setDescription("Call of Duty: Modern Warfare (2019) ON SALE! AND FOR PRE ORDER");
        gvmUpdate.setPrice(new BigDecimal("49.99"));
        gvmUpdate.setStudio("Infinity Ward");
        gvmUpdate.setQuantity(4);

        invoiceGameStoreInventoryService.updateGame(gvmUpdate);
        GameViewModel gvmUp2 = invoiceGameStoreInventoryService.findGameById(gvmUpdate.getGameId());

        assertEquals(gvmUp2,gvmUpdate);

    }
    @Test
    public void removeGame(){
        GameViewModel gameDelete = invoiceGameStoreInventoryService.findGameById(4);
        invoiceGameStoreInventoryService.removeGameById(4);
        assertNull(gameDelete);
    }



    //gamesDao setUp ==================================================================================================

    private void setUpGamesDao(){
        gamesDao = mock(GamesDaoJdbcTemplateImpl.class);

        //Game A data mock
        Game gameA = new Game();
        gameA.setGameId(1);
        gameA.setTitle("Call of Duty: Modern Warfare (2019)");
        gameA.setEsrbRating("Mature: 17+");
        gameA.setDescription("The stakes have never been higher as players take on the role of lethal Tier One " +
                "operators in a heart-racing saga that will affect the global balance" +
                " of power");
        gameA.setPrice(new BigDecimal("59.99"));
        gameA.setStudio("Infinity Ward");
        gameA.setQuantity(10);

        //A1 = A w/o gameId------------------------------------------------------------------------------------------
        Game gameA1 = new Game();
        gameA1.setTitle("Call of Duty: Modern Warfare (2019)");
        gameA1.setEsrbRating("Mature: 17+");
        gameA1.setDescription("The stakes have never been higher as players take on the role of lethal Tier One " +
                "operators in a heart-racing saga that will affect the global balance" +
                " of power");
        gameA1.setPrice(new BigDecimal("59.99"));
        gameA1.setStudio("Infinity Ward");
        gameA1.setQuantity(10);
//----------
        //Game B data mock------------------------------------------------------------------------------------------
        Game gameB = new Game();
        gameB.setGameId(2);
        gameB.setTitle("STAR WARS Jedi: Fallen Order(2019)");
        gameB.setEsrbRating("Teen: 13+");
        gameB.setDescription("A new 3rd person action-adventure title from Respawn Entertainment. " +
                "This narratively-driven single player game puts you in the role of a Jedi Padawan who narrowly" +
                " escaped the purge of Order 66 following the events of Episode 3: Revenge of the Sith");
        gameB.setPrice(new BigDecimal("59.99"));
        gameB.setStudio("Electronic Arts");
        gameB.setQuantity(5);

        //B1 = B w/o gameId ------------------------------------------------------------------------------------------
        Game gameB1 = new Game();
        gameB1.setTitle("STAR WARS Jedi: Fallen Order(2019)");
        gameB1.setEsrbRating("Teen: 13+");
        gameB1.setDescription("A new 3rd person action-adventure title from Respawn Entertainment. " +
                "This narratively-driven single player game puts you in the role of a Jedi Padawan who narrowly" +
                " escaped the purge of Order 66 following the events of Episode 3: Revenge of the Sith");
        gameB1.setPrice(new BigDecimal("59.99"));
        gameB1.setStudio("Electronic Arts");
        gameB1.setQuantity(5);

        //Mocks for Adding games---------------------------------------------------------------------------------------
        doReturn(gameA).when(gamesDao).addGame(gameA1);
        doReturn(gameB).when(gamesDao).addGame(gameB1);

        //Mocks for Getting gamesById---------------------------------------------------------------------------------

        doReturn(gameA).when(gamesDao).getGame(1);
        doReturn(gameB).when(gamesDao).getGame(2);

        //Mocks for Getting gameByTitle ------------------------------------------------------------------------------
        List<Game> callDutyList = new ArrayList<>();
        callDutyList.add(gameA);
        doReturn(callDutyList).when(gamesDao).getGamesByTitle("Call of Duty");

        List<Game> starWarsList = new ArrayList<>();
        starWarsList.add(gameB);
        doReturn(starWarsList).when(gamesDao).getGamesByTitle("STAR WARS");

        //Mocks for Getting game by rating---------------------------------------------------------------------------
        List<Game>mature = new ArrayList<>();
        List<Game>teen = new ArrayList<>();

        mature.add(gameA);
        teen.add(gameB);

        doReturn(mature).when(gamesDao).getGamesByRating("Mature");
        doReturn(teen).when(gamesDao).getGamesByRating("Teen");

        //Mocks for Getting game by studio-------------------------------------------------------------------------
        List<Game>infinityWard = new ArrayList<>();
        List<Game>electronicArts = new ArrayList<>();

        infinityWard.add(gameA);
        electronicArts.add(gameB);

        doReturn(infinityWard).when(gamesDao).getGamesByStudio("Infinity Ward");
        doReturn(electronicArts).when(gamesDao).getGamesByStudio("Electronic Arts");

        //Mocks for Getting All games---------------------------------------------------------------------------------
        List<Game> allGames = new ArrayList<>();
        allGames.add(gameA);
        allGames.add(gameB);

        doReturn(allGames).when(gamesDao).getAllGames();

        //Mocks for Update-------------------------------------------------------------------------------------------
        Game gameNoId = new Game();
        gameNoId.setTitle("Call of Duty: Modern Warfare (2019)");
        gameNoId.setEsrbRating("Mature: 17+");
        gameNoId.setDescription("Call of Duty: Modern Warfare (2019) ON SALE! AND FOR PRE ORDER");
        gameNoId.setPrice(new BigDecimal("59.99"));
        gameNoId.setStudio("Infinity Ward");
        gameNoId.setQuantity(4);

        Game gameNotUpdate = new Game();
        gameNotUpdate.setGameId(3);
        gameNotUpdate.setTitle("Call of Duty: Modern Warfare (2019)");
        gameNotUpdate.setEsrbRating("Mature: 17+");
        gameNotUpdate.setDescription("Call of Duty: Modern Warfare (2019) ON SALE! AND FOR PRE ORDER");
        gameNotUpdate.setPrice(new BigDecimal("59.99"));
        gameNotUpdate.setStudio("Infinity Ward");
        gameNotUpdate.setQuantity(4);

        Game gameUpdated = new Game();
        gameUpdated.setGameId(3);
        gameUpdated.setTitle("Call of Duty: Modern Warfare (2019)");
        gameUpdated.setEsrbRating("Mature: 17+");
        gameUpdated.setDescription("Call of Duty: Modern Warfare (2019) ON SALE! AND FOR PRE ORDER");
        gameUpdated.setPrice(new BigDecimal("49.99"));
        gameUpdated.setStudio("Infinity Ward");
        gameUpdated.setQuantity(4);

/*
        doReturn(consoleNotUPDATED).when(consolesDao).addConsole(consoleNOId);
        doNothing().when(consolesDao).updateConsole(consoleUPDATED);
        doReturn(consoleUPDATED).when(consolesDao).getConsole(consoleUPDATED.getConsoleId());
 */
        doReturn(gameNotUpdate).when(gamesDao).addGame(gameNoId);
        doNothing().when(gamesDao).updateGame(gameNotUpdate);
        doReturn(gameUpdated).when(gamesDao).getGame(3);

        //Mock for Delete------------------------------------------------------------------------------------------
        Game gameDelete = new Game();
        gameDelete.setGameId(4);
        gameDelete.setTitle("Secret GTA (2019)");
        gameDelete.setEsrbRating("Mature: 17+");
        gameDelete.setDescription("Un official GTA title (2019) ON SALE! AND FOR PRE ORDER");
        gameDelete.setPrice(new BigDecimal("89.99"));
        gameDelete.setStudio("RockStar Games");
        gameDelete.setQuantity(3);

        doNothing().when(gamesDao).deleteGame(4);
        doReturn(null).when(gamesDao).getGame(4);
    }


    @Test
    public void findFindAllSaveTshirt(){
        TShirtViewModel tvmOriginal = new TShirtViewModel();
        tvmOriginal.setSize("Large");
        tvmOriginal.setColor("Navy Blue");
        tvmOriginal.setDescription("Patterned navy blue shirt with a fade and the call of duty logo");
        tvmOriginal.setPrice(new BigDecimal("19.99"));
        tvmOriginal.setQuantity(20);
        tvmOriginal = invoiceGameStoreInventoryService.saveTShirt(tvmOriginal);

        TShirtViewModel foundTshirt = invoiceGameStoreInventoryService.findTShirtById(tvmOriginal.gettShirtId());
        assertEquals(foundTshirt,tvmOriginal);

        List<TShirtViewModel> tvmList = invoiceGameStoreInventoryService.findAllTShirts();
        assertEquals(2,tvmList.size());
    }

    @Test
    public void findTshirtBySize(){
        List<TShirtViewModel> sizeList = invoiceGameStoreInventoryService.getTShirtsBySize("Large");
        assertEquals(1,sizeList.size());
    }

    @Test
    public void findTshirtByColor(){
        List<TShirtViewModel>colorList = invoiceGameStoreInventoryService.getTShirtsByColor("Blue");
        assertEquals(1,colorList.size());
    }

    @Test
    public void updateTshirt(){
        /*

         */
        TShirtViewModel tvmUpdate = new TShirtViewModel();
        TShirtViewModel toUpdate = new TShirtViewModel();



        toUpdate.settShirtId(3);
        toUpdate.setSize("UPDATED");
        toUpdate.setColor("Black");
        toUpdate.setDescription("Patterned Black shirt with a fade and the Star Wars logo in red");
        toUpdate.setPrice(new BigDecimal("30.99"));
        toUpdate.setQuantity(12);

        tvmUpdate.settShirtId(toUpdate.gettShirtId());
        tvmUpdate.setSize(toUpdate.getSize());
        tvmUpdate.setColor(toUpdate.getColor());
        tvmUpdate.setDescription(toUpdate.getDescription());
        tvmUpdate.setPrice(toUpdate.getPrice());
        tvmUpdate.setQuantity(toUpdate.getQuantity());

        invoiceGameStoreInventoryService.updateTShirt(tvmUpdate);

//        tvm = invoiceGameStoreInventoryService.saveTShirt(tvm);
        TShirtViewModel tsvmFound = invoiceGameStoreInventoryService.findTShirtById(3);

        TShirtViewModel postUpdate = new TShirtViewModel();

        postUpdate.settShirtId(tsvmFound.gettShirtId());
        postUpdate.setSize(tsvmFound.getSize());
        postUpdate.setColor(tsvmFound.getColor());
        postUpdate.setDescription(tsvmFound.getDescription());
        postUpdate.setPrice(tsvmFound.getPrice());
        postUpdate.setQuantity(tsvmFound.getQuantity());

//        tvm.setPrice(new BigDecimal("20.99"));

        System.out.println(postUpdate.getSize());
        System.out.println(toUpdate.getSize());

//        invoiceGameStoreInventoryService.updateTShirt(tvm);
//        TShirtViewModel tvm2 = invoiceGameStoreInventoryService.findTShirtById(tvm.gettShirtId());
        assertEquals(postUpdate,toUpdate);
    }

    @Test
    public void removeTShirt(){
        TShirtViewModel tshirtDelete = invoiceGameStoreInventoryService.findTShirtById(4);
        invoiceGameStoreInventoryService.removeTShirt(4);
        assertNull(tshirtDelete);
    }

    @Test
    public void saveInvoice(){
        OrderViewModel ovm = new OrderViewModel();
        ovm.setName("Neo");
        ovm.setStreet("233 Broadway");
        ovm.setCity("NYC");
        ovm.setState("NY");
        ovm.setZipCode("10019");
        ovm.setItemType("Console");
        ovm.setItemId(111);
        ovm.setQuantity(1);

//        ReceiptViewModel receipt = invoiceGameStoreInventoryService.saveInvoiceReturnReceipt(ovm);


        InvoiceViewModel receipt = invoiceGameStoreInventoryService.saveInvoice(ovm);

        Console console = new Console();
        console = consolesDao.getConsole(ovm.getItemId());

        InvoiceViewModel check = invoiceGameStoreInventoryService.saveInvoice(ovm);

        System.out.println(receipt);
        assertEquals(receipt,check);    //set break point here and above

    }
/*
   ReceiptViewModel check = new ReceiptViewModel();
        check.setName("Neo");
        check.setStreet("233 Broadway");
        check.setCity("NYC");
        check.setState("NY");
        check.setZipCode("10019");
        check.setItemType("Console");
        check.setUnitPrice(new BigDecimal("299.99"));
        check.setQuantity(1);
        check.setSubTotal(new BigDecimal("299.99"));
        check.setTax(new BigDecimal("29.99"));
        check.setProcessingFee(new BigDecimal("14.99"));
        check.setTotal(new BigDecimal("329.97"));

        assertEquals(check,receipt);
 */




    //Tshirt DAO SetUp ==================================================================================================

    private void setUpTShirtDao(){
        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);
        //Tshirt A mock data------------------------------------------------------------------------------------------

        TShirt tShirtA = new TShirt();
        tShirtA.settShirtId(1);
        tShirtA.setSize("Large");
        tShirtA.setColor("Navy Blue");
        tShirtA.setDescription("Patterned navy blue shirt with a fade and the call of duty logo");
        tShirtA.setPrice(new BigDecimal("19.99"));
        tShirtA.setQuantity(20);

        TShirt tShirtA1 = new TShirt();
        tShirtA1.setSize("Large");
        tShirtA1.setColor("Navy Blue");
        tShirtA1.setDescription("Patterned navy blue shirt with a fade and the call of duty logo");
        tShirtA1.setPrice(new BigDecimal("19.99"));
        tShirtA1.setQuantity(20);

        //Tshirt B mock data------------------------------------------------------------------------------------------
        TShirt tShirtB = new TShirt();
        tShirtB.settShirtId(2);
        tShirtB.setSize("Medium");
        tShirtB.setColor("Dark Grey");
        tShirtB.setDescription("Patterned Dark Grey shirt with a fade and the Star Wars logo");
        tShirtB.setPrice(new BigDecimal("19.99"));
        tShirtB.setQuantity(25);

        TShirt tShirtB1 = new TShirt();
        tShirtB1.setSize("Medium");
        tShirtB1.setColor("Dark Grey");
        tShirtB1.setDescription("Patterned Dark Grey shirt with a fade and the Star Wars logo");
        tShirtB1.setPrice(new BigDecimal("19.99"));
        tShirtB1.setQuantity(25);

        //Mocks for Adding TShirt
        doReturn(tShirtA).when(tShirtDao).addTshirt(tShirtA1);
        doReturn(tShirtB).when(tShirtDao).addTshirt(tShirtB1);

        //mocks for getting by id
        doReturn(tShirtA).when(tShirtDao).getTshirt(1);
        doReturn(tShirtB).when(tShirtDao).getTshirt(2);

        //Mocks for getting Tshirts (all) ----------------------------------------------------------------------------
        List<TShirt> allShirts = new ArrayList<>();
        allShirts.add(tShirtA);
        allShirts.add(tShirtB);

        doReturn(allShirts).when(tShirtDao).getAllTshirts();

        //Mocks for getting shirt by size -----------------------------------------------------------------------------
        List<TShirt> largeShirt = new ArrayList<>();
        largeShirt.add(tShirtA);

        List<TShirt> smallShirt = new ArrayList<>();
        smallShirt.add(tShirtB);

        doReturn(largeShirt).when(tShirtDao).getTshirtBySize("Large");
        doReturn(smallShirt).when(tShirtDao).getTshirtBySize("Small");

        //Mocks for getting shirt by color -----------------------------------------------------------------------------
        List<TShirt> blueShirt = new ArrayList<>();
        blueShirt.add(tShirtA);

        List<TShirt> greyShirt = new ArrayList<>();
        greyShirt.add(tShirtB);

        doReturn(blueShirt).when(tShirtDao).getTshirtByColor("Blue");
        doReturn(greyShirt).when(tShirtDao).getTshirtByColor("Grey");

        //Mocks for Update-------------------------------------------------------------------------------------------

        TShirt tShirtUpdated = new TShirt();
        tShirtUpdated.settShirtId(3);
        tShirtUpdated.setSize("UPDATED");
        tShirtUpdated.setColor("Black");
        tShirtUpdated.setDescription("Patterned Black shirt with a fade and the Star Wars logo in red");
        tShirtUpdated.setPrice(new BigDecimal("30.99"));
        tShirtUpdated.setQuantity(12);

        doNothing().when(tShirtDao).updateTshirt(tShirtUpdated);
        doReturn(tShirtUpdated).when(tShirtDao).getTshirt(3);

        //Mock for Delete------------------------------------------------------------------------------------------
        TShirt tShirtDel = new TShirt();
        tShirtDel.settShirtId(4);
        tShirtDel.setSize("XL");
        tShirtDel.setColor("Green");
        tShirtDel.setDescription("Patterned Green shirt with a fade and the Star Wars logo in red");
        tShirtDel.setPrice(new BigDecimal("30.99"));
        tShirtDel.setQuantity(2);

        doNothing().when(tShirtDao).deleteTshirt(4);
        doReturn(null).when(tShirtDao).getTshirt(4);
    }


//garbage test
    /*
            TShirt tShirtNoUdateNoId = new TShirt();
        tShirtNoUdateNoId.setSize("Large");
        tShirtNoUdateNoId.setColor("Black");
        tShirtNoUdateNoId.setDescription("Patterned Black shirt with a fade and the Star Wars logo in red");
        tShirtNoUdateNoId.setPrice(new BigDecimal("30.99"));
        tShirtNoUdateNoId.setQuantity(12);

        TShirt tShirtNotUpdate = new TShirt();
        tShirtNotUpdate.settShirtId(3);
        tShirtNotUpdate.setSize("Large");
        tShirtNotUpdate.setColor("Black");
        tShirtNotUpdate.setDescription("Patterned Black shirt with a fade and the Star Wars logo in red");
        tShirtNotUpdate.setPrice(new BigDecimal("30.99"));
        tShirtNotUpdate.setQuantity(12);

        TShirt tShirtUpdated = new TShirt();
        tShirtNotUpdate.settShirtId(3);
        tShirtNotUpdate.setSize("Large");
        tShirtNotUpdate.setColor("Black");
        tShirtNotUpdate.setDescription("Patterned Black shirt with a fade and the Star Wars logo in red");
        tShirtNotUpdate.setPrice(new BigDecimal("20.99"));
        tShirtNotUpdate.setQuantity(12);

        //tShirtUdateNoId
        doReturn(tShirtNotUpdate).when(tShirtDao).addTshirt(tShirtNoUdateNoId);
        doNothing().when(tShirtDao).updateTshirt(tShirtNotUpdate);
        doReturn(tShirtUpdated).when(tShirtDao).getTshirt(3);
     */

//When testing the mock for invoice, we need to dummy up data for an orderViewModel that would
    // be passed in to generate said INVOICE




    //processingFeeDao setUp ==================================================================================================

    private void setUpProcessingFee(){
        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);

        ProcessingFee consoleFee = new ProcessingFee();
        consoleFee.setProductType("Consoles");
        consoleFee.setFee(new BigDecimal("14.99"));

        ProcessingFee gameFee = new ProcessingFee();
        gameFee.setProductType("Games");
        gameFee.setFee(new BigDecimal("1.49"));

        ProcessingFee tshirtFee = new ProcessingFee();
        tshirtFee.setProductType("T-Shirts");
        tshirtFee.setFee(new BigDecimal("1.98"));

        doReturn(consoleFee.getFee()).when(processingFeeDao).getFee(consoleFee.getProductType());
        doReturn(gameFee.getFee()).when(processingFeeDao).getFee(gameFee.getProductType());
        doReturn(tshirtFee.getFee()).when(processingFeeDao).getFee(tshirtFee.getProductType());

    }


    //salesTaxFeeDao setUp ==================================================================================================
    private void setUpSalesTaxRateDao(){
        salesTaxRateDao = mock(SalesTaxRateDaoJdbcTemplateImpl.class);

        SalesTaxRate nyTax = new SalesTaxRate();
        nyTax.setState("NY");
        nyTax.setRate(new BigDecimal("0.06"));

//        SalesTaxRate neTax = new SalesTaxRate();
//        nyTax.setState("NE");
//        nyTax.setRate(new BigDecimal("0.04"));
//
//        SalesTaxRate caTax = new SalesTaxRate();
//        caTax.setState("CA");
//        caTax.setRate(new BigDecimal("0.06"));

        doReturn(new BigDecimal("0.06")).when(salesTaxRateDao).getSalesTaxRate(nyTax.getState());

//        doReturn(neTax.getRate()).when(salesTaxRateDao).getSalesTaxRate(neTax.getState());
//        doReturn(caTax.getRate()).when(salesTaxRateDao).getSalesTaxRate(caTax.getState());

        List<SalesTaxRate> taxrates = new ArrayList<>();

//        taxrates.add(neTax);
//        taxrates.add(caTax);
        taxrates.add(nyTax);

        doReturn(taxrates).when(salesTaxRateDao).getAllSalesTaxRates();
    }

    private void setUpInvoiceDao(){
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice inv = new Invoice();
        //We include invoice id when a VALID order is sent
        //<<<<<<<<<<<<<<<<<<<<<<OrderViewModel starts
        inv.setInvoiceId(1);
        inv.setName("Neo");
        inv.setStreet("233 Broadway");
        inv.setCity("NYC");
        inv.setState("NY");
        inv.setZipCode("10019");
        inv.setItemType("Console");
        inv.setItemId(111); //-----------xbox1

        //<<<<<<<<<<<<<<<<<<<<<<OrderViewModelEnds
        inv.setUnitPrice(new BigDecimal("299.99"));
        inv.setQuantity(1);
        inv.setSubTotal(new BigDecimal("299.99"));
        inv.setTax(new BigDecimal("18.00"));
        inv.setProcessingFee(new BigDecimal("14.99"));
        inv.setTotal(new BigDecimal("332.98"));

        Invoice inv2 = new Invoice();
        inv2.setName("Neo");
        inv2.setStreet("233 Broadway");
        inv2.setCity("NYC");
        inv2.setState("NY");
        inv2.setZipCode("10019");
        inv2.setItemType("Console");

        inv2.setItemId(111);
        inv2.setUnitPrice(new BigDecimal("299.99"));
        inv2.setQuantity(1);
        inv2.setSubTotal(new BigDecimal("299.99"));
        inv2.setTax(new BigDecimal("18.00"));
        inv2.setProcessingFee(new BigDecimal("14.99"));
        inv2.setTotal(new BigDecimal("332.98"));
        //-------------------------------------------------------------------

        //Mocks for Adding-----------------------------------------------------------------------------
        doReturn(inv).when(invoiceDao).addInvoice(inv2);
        doReturn(inv).when(invoiceDao).getInvoice(1);

//        List<Invoice> invList = new ArrayList<>();
//        invList.add(inv2);
//        doReturn(invList).when(invoiceDao).getAllInvoices();

    }
}


/*

    //InvoiceB
        Invoice invB = new Invoice();
        invB.setInvoiceId(2);
        invB.setName("Morpheus");
        invB.setStreet("233 Broadway");
        invB.setCity("NYC");
        invB.setState("NY");
        invB.setZipCode("10019");
        invB.setItemType("Game");
        invB.setItemId(2);
        invB.setUnitPrice(new BigDecimal("59.99"));
        invB.setQuantity(1);
        invB.setSubTotal(new BigDecimal("59.99"));
        invB.setTax(new BigDecimal("3.59"));
        invB.setProcessingFee(new BigDecimal("1.49"));
        invB.setTotal(new BigDecimal("65.07"));

        Invoice invB1 = new Invoice();
        invB1.setName("Morpheus");
        invB1.setStreet("233 Broadway");
        invB1.setCity("NYC");
        invB1.setState("NY");
        invB1.setZipCode("10019");
        invB1.setItemType("Game");
        invB1.setItemId(2);
        invB1.setUnitPrice(new BigDecimal("59.99"));
        invB1.setQuantity(1);
        invB1.setSubTotal(new BigDecimal("59.99"));
        invB1.setTax(new BigDecimal("3.59"));
        invB1.setProcessingFee(new BigDecimal("1.49"));
        invB1.setTotal(new BigDecimal("65.07"));
        doReturn(invB).when(invoiceDao).addInvoice(invB1);

        //Mock for update -------------------------------------------------------------------------------------------
        Invoice invUpdate = new Invoice();
        invUpdate.setInvoiceId(3);
        invUpdate.setName("Trinity");
        invUpdate.setStreet("233 Wabash");
        invUpdate.setCity("Chicago");
        invUpdate.setState("IL");
        invUpdate.setZipCode("10119");
        invUpdate.setItemType("Game");
        invUpdate.setItemId(2);
        invUpdate.setUnitPrice(new BigDecimal("59.99"));
        invUpdate.setQuantity(1);
        invUpdate.setSubTotal(new BigDecimal("59.99"));
        invUpdate.setTax(new BigDecimal("3.59"));
        invUpdate.setProcessingFee(new BigDecimal("1.49"));
        invUpdate.setTotal(new BigDecimal("65.07"));

        doNothing().when(invoiceDao).updateInvoice(invUpdate);
        doReturn(invUpdate).when(invoiceDao).getInvoice(3);

        //Mock for delete--------------------------------------------------------------------------------------
        Invoice invDel = new Invoice();
        invDel.setInvoiceId(4);
        invDel.setName("Tank");
        invDel.setStreet("233 Wabash");
        invDel.setCity("Chicago");
        invDel.setState("IL");
        invDel.setZipCode("10119");
        invDel.setItemType("T-Shirt");
        invDel.setItemId(1);
        invDel.setUnitPrice(new BigDecimal("19.99"));
        invDel.setQuantity(3);
        invDel.setSubTotal(new BigDecimal("59.99"));
        invDel.setTax(new BigDecimal("3.59"));
        invDel.setProcessingFee(new BigDecimal("1.49"));
        invDel.setTotal(new BigDecimal("65.07"));

        doNothing().when(invoiceDao).deleteInvoice(4);
        doReturn(null   ).when(invoiceDao).getInvoice(4);
 */