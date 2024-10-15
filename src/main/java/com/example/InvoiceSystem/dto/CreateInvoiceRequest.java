package com.example.InvoiceSystem.dto;

import java.time.LocalDate;

public class CreateInvoiceRequest {
	  private double amount;
	    private LocalDate due_date;

	    // Getters and Setters
	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

		public LocalDate getDue_date() {
			return due_date;
		}

		public void setDue_date(LocalDate due_date) {
			this.due_date = due_date;
		}

	  
}
