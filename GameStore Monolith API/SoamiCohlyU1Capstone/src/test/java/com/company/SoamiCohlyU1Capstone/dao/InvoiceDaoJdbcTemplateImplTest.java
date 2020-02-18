package com.company.SoamiCohlyU1Capstone.dao;


import com.company.SoamiCohlyU1Capstone.dao.invoice.InvoiceDao;
import com.company.SoamiCohlyU1Capstone.model.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    protected InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception{
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        invoiceList.stream()
                .forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));
    }
    @After
    public void tearDown() throws Exception {
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        invoiceList.stream()
                .forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));
    }

    @Test
    public void addGetDeleteInvoice(){
        Invoice invoice = new Invoice();
        invoice.setName("BobBaba");
        invoice.setStreet("25 Broadway");
        invoice.setCity("NYC");
        invoice.setState("NY");
        invoice.setZipCode("10015");
        invoice.setItemType("Game");
        invoice.setItemId(2);
        invoice.setUnitPrice(new BigDecimal("29.99"));
        invoice.setQuantity(2);
        invoice.setSubTotal(new BigDecimal("60.00"));
        invoice.setTax(new BigDecimal("12.48"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("82.99"));

        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1,invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertNull(invoice1);

    }

    @Test
    public void getAllInvoices(){
        Invoice invoice = new Invoice();
        invoice.setName("BobBaba");
        invoice.setStreet("25 Broadway");
        invoice.setCity("NYC");
        invoice.setState("NY");
        invoice.setZipCode("10015");
        invoice.setItemType("Game");
        invoice.setItemId(2);
        invoice.setUnitPrice(new BigDecimal("29.99"));
        invoice.setQuantity(2);
        invoice.setSubTotal(new BigDecimal("60.00"));
        invoice.setTax(new BigDecimal("12.48"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("82.99"));

        invoiceDao.addInvoice(invoice);

        List<Invoice> iList = invoiceDao.getAllInvoices();
        assertEquals(1,iList.size());
    }

    @Test
    public void updateInvoice(){
        Invoice invoice = new Invoice();
        invoice.setName("BobBaba");
        invoice.setStreet("25 Broadway");
        invoice.setCity("NYC");
        invoice.setState("NY");
        invoice.setZipCode("10015");
        invoice.setItemType("Game");
        invoice.setItemId(2);
        invoice.setUnitPrice(new BigDecimal("29.99"));
        invoice.setQuantity(2);
        invoice.setSubTotal(new BigDecimal("60.00"));
        invoice.setTax(new BigDecimal("12.48"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("82.99"));

        invoice = invoiceDao.addInvoice(invoice);

        invoice.setState("UPDATED");
        invoice.setItemId(1);
        invoice.setQuantity(3);
        invoiceDao.updateInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1,invoice);
    }

}
