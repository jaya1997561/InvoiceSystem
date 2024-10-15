package com.example.InvoiceSystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.InvoiceSystem.model.Invoice;
import com.example.InvoiceSystem.model.Invoice.Status;

@Service
public class InvoiceSystemService {

	private final Map<String, Invoice> invoices = new HashMap<>();
	private int idCounter = 1234;

	//To create invoices
	public Invoice createInvoice(double amount, LocalDate dueDate) {
		String id = String.valueOf(idCounter++);
		Invoice invoice = new Invoice(id, amount, 0, dueDate, Status.PENDING);
		invoices.put(id,invoice);
		return invoice;
	}
	
	//To get all invoices
	public List<Invoice> getInvoices() {
		return new ArrayList<Invoice>(invoices.values());
	}

	//To process payment
	public Invoice payInvoice(String id, double amount) {
		for (Invoice invoice : invoices.values()) {
			if (invoice.getId().equals(id)) {
				if (invoice != null) {
					invoice.setPaid_amount(invoice.getPaid_amount() + amount);
					if (invoice.getPaid_amount() >= invoice.getAmount()) {
						invoice.setStatus(Status.PAID);
					}
				}
			}
		}
		return null;
	}

	//To process overdue
	public List<Invoice> processOverDue(double late_fee, int overdue_days) {
		LocalDate today = LocalDate.now();
		   List<Invoice> updatedInvoices = new ArrayList<>();
	        for (Invoice invoice : new ArrayList<>(invoices.values())) {
	            if (Status.PENDING.equals(invoice.getStatus()) && invoice.getDue_date().isBefore(today)) {
	            	if ((invoice.getPaid_amount() < invoice.getAmount()) && invoice.getPaid_amount() != 0.0) {
	                    // Partially paid
	                    double remainingAmount = invoice.getAmount() - invoice.getPaid_amount();
	                    Invoice newInvoice = createInvoice(remainingAmount + late_fee, today.plusDays(overdue_days));
	                    newInvoice.setStatus(Status.PAID);
	                    updatedInvoices.add(newInvoice);
	                } else {
	                    // Not paid at all
	                    Invoice notPaid = createInvoice(invoice.getAmount() + late_fee, today.plusDays(overdue_days));
	                    notPaid.setStatus(Status.VOID);
	                    updatedInvoices.add(notPaid);
	                }
	                updatedInvoices.add(invoice); // Add modified invoice
	            }
	        }
	        return updatedInvoices; // Return the list of updated invoices
	    }
}
