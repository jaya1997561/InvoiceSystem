package com.example.InvoiceSystem.dto;

public class ProcessOverDueRequest {
	
	private double late_fee;
	private int overdue_days;
	

	public ProcessOverDueRequest(double late_fee, int overdue_days) {
		super();
		this.late_fee = late_fee;
		this.overdue_days = overdue_days;
	}
	public double getLate_fee() {
		return late_fee;
	}
	public void setLate_fee(double late_fee) {
		this.late_fee = late_fee;
	}
	public int getOverdue_days() {
		return overdue_days;
	}
	public void setOverdue_days(int overdue_days) {
		this.overdue_days = overdue_days;
	}
	
}
