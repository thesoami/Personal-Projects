
package com.company.SoamiCohlyU1Capstone.controller;


import com.company.SoamiCohlyU1Capstone.exception.NotFoundException;
import com.company.SoamiCohlyU1Capstone.service.InvoiceGameStoreInventoryService;
import com.company.SoamiCohlyU1Capstone.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class ConsoleController {

    //for sake of simplicity opted to use the simplified annotations for various mappings
    @Autowired
    InvoiceGameStoreInventoryService serviceLayer;


    //----------------------------------------------------no security

//    @RequestMapping(value ="/console", method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public ConsoleViewModel addConsole(@RequestBody @Valid ConsoleViewModel cvm){
//        return serviceLayer.saveConsole(cvm);
//    }
//
//    @RequestMapping(value ="console/{id}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public ConsoleViewModel getConsole(@PathVariable("id") int id) {
//        ConsoleViewModel consoleViewModel = serviceLayer.findConsoleById(id);
//        if (consoleViewModel == null)
//            throw new NotFoundException("NO console found with given id:" + id);
//        return consoleViewModel;
//    }
//
//    //GetAll
//    @RequestMapping(value ="/consoles", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<ConsoleViewModel> getAllConsoles(){
//        return serviceLayer.findAllConsoles();}
//
//        //=========================================================================================================
//        //Need to Fix get by Manufactureer as it does not work
//    @RequestMapping(value ="/consoles/console/{manufacturer}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<ConsoleViewModel> getConsolesByManufacturer(@PathVariable("manufacturer") String manufacturer) {
//        List<ConsoleViewModel> cvmList = serviceLayer.findConsolesByManf(manufacturer);
//        if (cvmList.size() == 0) {
//            throw new NotFoundException("Cant find any consoles by given manufacturer: " + manufacturer);
//        }
//        return cvmList;
//    }
//
//
//        @RequestMapping(value ="console/{id}", method = RequestMethod.PUT)
//        @ResponseStatus(HttpStatus.NO_CONTENT)
//        public String updateConsole(@RequestBody @Valid ConsoleViewModel cvm,@PathVariable("id") int id) {
//            serviceLayer.updateConsole(cvm);
//            return "Console updated.";
//        }
//
//    @RequestMapping(value ="console/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public String removeConsole(@PathVariable("id") int id){
//        serviceLayer.removeConsoleById(id);
//        return "Console removed.";
//    }


    @RequestMapping(value ="/console", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ConsoleViewModel addConsole(Principal principal, @RequestBody @Valid ConsoleViewModel cvm){
        return serviceLayer.saveConsole(cvm);
    }

    @RequestMapping(value ="console/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable("id") int id) {
        ConsoleViewModel consoleViewModel = serviceLayer.findConsoleById(id);
        if (consoleViewModel == null)
            throw new NotFoundException("NO console found with given id:" + id);
        return consoleViewModel;
    }

    //GetAll
    @RequestMapping(value ="/consoles", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles(){
        return serviceLayer.findAllConsoles();}

    //=========================================================================================================
    //Need to Fix get by Manufactureer as it does not work
    @RequestMapping(value ="/consoles/console/{manufacturer}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsolesByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        List<ConsoleViewModel> cvmList = serviceLayer.findConsolesByManf(manufacturer);
        if (cvmList.size() == 0) {
            throw new NotFoundException("Cant find any consoles by given manufacturer: " + manufacturer);
        }
        return cvmList;
    }


    @RequestMapping(value ="console/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateConsole(Principal principal,@RequestBody @Valid ConsoleViewModel cvm,@PathVariable("id") int id) {
        serviceLayer.updateConsole(cvm);
        return "Console updated.";
    }

    @RequestMapping(value ="console/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String removeConsole(Principal principal,@PathVariable("id") int id){
        serviceLayer.removeConsoleById(id);
        return "Console removed.";
    }


    //Principal principal,

}
