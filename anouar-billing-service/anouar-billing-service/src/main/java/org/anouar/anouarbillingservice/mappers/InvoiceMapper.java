package org.anouar.anouarbillingservice.mappers;

import org.anouar.anouarbillingservice.dtos.InvoiceRequestDto;
import org.anouar.anouarbillingservice.dtos.InvoiceResponseDto;
import org.anouar.anouarbillingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDto(InvoiceRequestDto invoiceRequestDto);
    InvoiceResponseDto fromInvoice(Invoice invoice);
}
