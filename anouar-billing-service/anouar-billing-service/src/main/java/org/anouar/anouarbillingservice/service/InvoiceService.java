package org.anouar.anouarbillingservice.service;

import org.anouar.anouarbillingservice.dtos.InvoiceRequestDto;
import org.anouar.anouarbillingservice.dtos.InvoiceResponseDto;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDto save(InvoiceRequestDto invoiceRequestDto);
    InvoiceResponseDto getInvoice(String invoiceId);
    List<InvoiceResponseDto> invoicesByCustomerId(String customerId);
    List<InvoiceResponseDto> allInvoices();
}
