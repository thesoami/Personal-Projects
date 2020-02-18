package com.company.SoamiCohlyU1Capstone.controller;

import com.company.SoamiCohlyU1Capstone.exception.NotFoundException;
import com.company.SoamiCohlyU1Capstone.service.InvoiceGameStoreInventoryService;
import com.company.SoamiCohlyU1Capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class TShirtController {

    @Autowired
    InvoiceGameStoreInventoryService serviceLayer;

    //=============================================================================================






//    @RequestMapping(value = "/tshirt", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public TShirtViewModel addShirt(@RequestBody @Valid TShirtViewModel tsvm) {
//        return serviceLayer.saveTShirt(tsvm);
//    }
//
//    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public TShirtViewModel getShirt(@PathVariable("id") int id) {
//        TShirtViewModel tsvm = serviceLayer.findTShirtById(id);
//        if (tsvm == null)
//            throw new NotFoundException("Cannot find T-shirt with given id:" + id);
//        return tsvm;
//    }
//
//    @RequestMapping(value = "/tshirts", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<TShirtViewModel> getAllShirts() {
//        return serviceLayer.findAllTShirts();
//    }
//
//    //=========================================================================================================
//    //Need to Fix get by Manufactureer as it does not work, lots of sorting out to do patiently
//    @RequestMapping(value = "/tshirt/size/{size}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<TShirtViewModel> getTShirtBySize(@PathVariable("size") String size) {
//        return serviceLayer.getTShirtsBySize(size);
//    }
//
//    //=========================================================================================================
//    //Need to Fix get by Manufactureer as it does not work
//    @RequestMapping(value = "/tshirt/color/{color}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<TShirtViewModel> getTShirtByColor(@PathVariable("color") String color) {
//        return serviceLayer.getTShirtsByColor(color);
//    }
//
//    //=========================================================================================================
//    //Need to Fix get by Manufactureer as it does not work
//    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//
//    public String updateShirt(@PathVariable("id") int id, @RequestBody @Valid TShirtViewModel tsvm) {
//        serviceLayer.updateTShirt(tsvm);
//        return "T-Shirt was updated";
//    }
//
//    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public String removeShirt(@PathVariable("id") int id) {
//        serviceLayer.removeTShirt(id);
//        return "T-Shirt successfully removed.";
//    }
//
//
    //=============================================with security



    @RequestMapping(value = "/tshirt", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TShirtViewModel addShirt(Principal principal, @RequestBody @Valid TShirtViewModel tsvm) {
        return serviceLayer.saveTShirt(tsvm);
    }

    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TShirtViewModel getShirt(@PathVariable("id") int id) {
        TShirtViewModel tsvm = serviceLayer.findTShirtById(id);
        if (tsvm == null)
            throw new NotFoundException("Cannot find T-shirt with given id:" + id);
        return tsvm;
    }

    @RequestMapping(value = "/tshirts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllShirts() {
        return serviceLayer.findAllTShirts();
    }

    //=========================================================================================================
    //Need to Fix get by Manufactureer as it does not work, lots of sorting out to do patiently
    @RequestMapping(value = "/tshirt/size/{size}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtBySize(@PathVariable("size") String size) {
        return serviceLayer.getTShirtsBySize(size);
    }

    //=========================================================================================================
    //Need to Fix get by Manufactureer as it does not work
    @RequestMapping(value = "/tshirt/color/{color}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtByColor(@PathVariable("color") String color) {
        return serviceLayer.getTShirtsByColor(color);
    }

    //=========================================================================================================
    //Need to Fix get by Manufactureer as it does not work
    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public String updateShirt(Principal principal,@PathVariable("id") int id, @RequestBody @Valid TShirtViewModel tsvm) {
        serviceLayer.updateTShirt(tsvm);
        return "T-Shirt was updated";
    }

    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String removeShirt(Principal principal,@PathVariable("id") int id) {
        serviceLayer.removeTShirt(id);
        return "T-Shirt successfully removed.";
    }
}

