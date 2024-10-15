package com.example.InvoiceSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.InvoiceSystem.model.Invoice;
import com.example.InvoiceSystem.model.Invoice.Status;
import com.example.InvoiceSystem.service.InvoiceSystemService;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceSystemServiceTest {

    private InvoiceSystemService invoiceService;

    @BeforeEach
    public void setUp() {
    	invoiceService = new InvoiceSystemService();
    }

    @Test
    public void testCreateInvoice() {
        Invoice invoice = invoiceService.createInvoice(199.99, LocalDate.now().plusDays(10));
        assertNotNull(invoice);
        assertEquals("1234", invoice.getId());
        assertEquals(Status.PENDING, invoice.getStatus());
    }

    @Test
    public void testGetInvoices() {
        invoiceService.createInvoice(199.99, LocalDate.now().plusDays(10));
        invoiceService.createInvoice(299.99, LocalDate.now().plusDays(20));
        
        List<Invoice> invoices = invoiceService.getInvoices();
        assertEquals(2, invoices.size());
    }
    
    @Test
    public void testPayInvoice() {
        Invoice invoice = invoiceService.createInvoice(199.99, LocalDate.now().plusDays(10));
        invoiceService.payInvoice(invoice.getId(), 100.00);

        assertEquals(Status.PENDING, invoice.getStatus()); // Still pending

        invoiceService.payInvoice(invoice.getId(), 100.00);
        assertEquals(Status.PAID, invoice.getStatus()); // Now paid
    }
    @Test
    public void testProcessOverDue_PartiallyPaid() {
        Invoice invoice = invoiceService.createInvoice(199.99, LocalDate.now().minusDays(5));
        invoiceService.payInvoice(invoice.getId(), 100.00); // Partially paid

        List<Invoice> updatedInvoices = invoiceService.processOverDue(10.5, 10);

        assertEquals(2, updatedInvoices.size()); // Original invoice + new invoice
        assertEquals(Status.PAID, updatedInvoices.get(0).getStatus()); // Original invoice marked as paid
    }	
    @Test
    public void testProcessOverDue_notpaid() {
        Invoice invoice = invoiceService.createInvoice(199.99, LocalDate.now().minusDays(5));

        List<Invoice> updatedInvoices = invoiceService.processOverDue(10.5, 10);

        assertEquals(2, updatedInvoices.size()); // Original invoice marked as void
        assertEquals(Status.VOID, updatedInvoices.get(0).getStatus());
    }

}


