package com.example.InvoiceSystem.model;

import java.time.LocalDate;

public class Invoice {
	
	private String id;
	private double amount;
	private double paid_amount;
	private LocalDate due_date;
	private Status status;
	
	public Invoice(String id, double amount, double paid_amount, LocalDate due_date, Status status) {
		super();
		this.id = id;
		this.amount = amount;
		this.paid_amount = paid_amount;
		this.due_date = due_date;
		this.status = status;
	}

	public Invoice(String id, double amount, LocalDate due_date) {
        this.id = id;
        this.amount = amount;
        this.due_date = due_date;
    }
	
	
	public Invoice() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}
	
	public LocalDate getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public enum Status {
	    PENDING, PAID, VOID
	}
	
}

