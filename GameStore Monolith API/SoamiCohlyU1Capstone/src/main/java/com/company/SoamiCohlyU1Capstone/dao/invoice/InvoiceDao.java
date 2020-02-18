package com.company.SoamiCohlyU1Capstone.dao.invoice;

import com.company.SoamiCohlyU1Capstone.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    List<Invoice> getAllInvoices();

    void updateInvoice(Invoice invoice);

    void deleteInvoice(int id);

}
