package org.anouar.anouarbillingservice.web;

import org.anouar.anouarbillingservice.dtos.InvoiceRequestDto;
import org.anouar.anouarbillingservice.dtos.InvoiceResponseDto;
import org.anouar.anouarbillingservice.entities.Invoice;
import org.anouar.anouarbillingservice.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestController {
    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoice/{id}")
    public InvoiceResponseDto getInvoice(@PathVariable(name = "id") String invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }

    @GetMapping(path = "/invoices/{customerId}")
    public List<InvoiceResponseDto> getInvoiceByCustomer(@PathVariable String customerId){
        return invoiceService.invoicesByCustomerId(customerId);
    }
    @PostMapping(path = "/invoice")
    public InvoiceResponseDto save(@RequestBody InvoiceRequestDto invoiceRequestDto){
        return invoiceService.save(invoiceRequestDto);
    }

    @GetMapping(path = "/allInvoices")
    public List<InvoiceResponseDto> allInvoices() {
       return invoiceService.allInvoices();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
