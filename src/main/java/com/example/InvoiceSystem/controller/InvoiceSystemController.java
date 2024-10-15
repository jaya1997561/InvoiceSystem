package com.example.InvoiceSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InvoiceSystem.dto.CreateInvoiceRequest;
import com.example.InvoiceSystem.dto.CreateInvoiceResponse;
import com.example.InvoiceSystem.dto.CreatePaymentRequest;
import com.example.InvoiceSystem.dto.ProcessOverDueRequest;
import com.example.InvoiceSystem.model.Invoice;
import com.example.InvoiceSystem.service.InvoiceSystemService;

@RestController
@RequestMapping("/invoices")
public class InvoiceSystemController {
	
	private final InvoiceSystemService invoiceSystemService = new InvoiceSystemService();
	
	// To create invoices
	@PostMapping
	public ResponseEntity<CreateInvoiceResponse> createInvoice(@RequestBody CreateInvoiceRequest request) {
	    Invoice invoiceId = invoiceSystemService.createInvoice(request.getAmount(), request.getDue_date());
	    CreateInvoiceResponse response = new CreateInvoiceResponse(invoiceId.getId());
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	//get the invoices
	@GetMapping
	public ResponseEntity<List<Invoice>> getInvoices(){
		return new ResponseEntity<>(invoiceSystemService.getInvoices(), HttpStatus.OK);
	}
	
	//To make payment
	@PostMapping("/{id}/payments")
	public ResponseEntity<Void> addPayment(@PathVariable String id, @RequestBody CreatePaymentRequest request){
		invoiceSystemService.payInvoice(id, request.getAmount());
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	//to check overdue payment
	@PostMapping("/process-overdue")
	public ResponseEntity<Void> processOverDue(@RequestBody ProcessOverDueRequest dueRequest){
		invoiceSystemService.processOverDue(dueRequest.getLate_fee(),dueRequest.getOverdue_days());
		return ResponseEntity.status(HttpStatus.OK).build();	
	}
	
}
