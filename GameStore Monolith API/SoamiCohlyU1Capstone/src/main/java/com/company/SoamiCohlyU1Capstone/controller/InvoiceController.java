package com.company.SoamiCohlyU1Capstone.controller;

import com.company.SoamiCohlyU1Capstone.service.InvoiceGameStoreInventoryService;
import com.company.SoamiCohlyU1Capstone.viewmodel.InvoiceViewModel;
import com.company.SoamiCohlyU1Capstone.viewmodel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//thought about using mapping as "/invoice" but we are sending an orderview soooooo
public class InvoiceController {
    @Autowired
    InvoiceGameStoreInventoryService serviceLayer;

    //we were only told to save an invoice by passing in a valid ORDER

    @RequestMapping(value ="/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid OrderViewModel ovm ){

        InvoiceViewModel receipt = serviceLayer.saveInvoice(ovm);

        return receipt; }
}
