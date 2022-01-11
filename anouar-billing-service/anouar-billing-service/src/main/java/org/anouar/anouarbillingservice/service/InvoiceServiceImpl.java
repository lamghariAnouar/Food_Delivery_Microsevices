package org.anouar.anouarbillingservice.service;

import org.anouar.anouarbillingservice.dtos.InvoiceRequestDto;
import org.anouar.anouarbillingservice.dtos.InvoiceResponseDto;
import org.anouar.anouarbillingservice.entities.Customer;
import org.anouar.anouarbillingservice.entities.Invoice;
import org.anouar.anouarbillingservice.exception.CustomerNotFoundException;
import org.anouar.anouarbillingservice.mappers.InvoiceMapper;
import org.anouar.anouarbillingservice.openfeign.CustomerRestClient;
import org.anouar.anouarbillingservice.repositories.InvoiceRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private  InvoiceRepositories invoiceRepositories;
    private  InvoiceMapper invoiceMapper;
    private  CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepositories invoiceRepositories, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepositories = invoiceRepositories;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }


    @Override
    public InvoiceResponseDto save(InvoiceRequestDto invoiceRequestDto) {
        /* verification de l'integrité referentielle*/
        Customer customer;
        try {
             customer=customerRestClient.getCustomer(invoiceRequestDto.getCustomerId());
        }
        catch (Exception e){
            throw new CustomerNotFoundException("customer not found");
        }
        Invoice invoice=invoiceMapper.fromInvoiceRequestDto(invoiceRequestDto);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice savedInvoice=invoiceRepositories.save(invoice);
        savedInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceResponseDto getInvoice(String invoiceId) {
        Invoice invoice=invoiceRepositories.findById(invoiceId).get();
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDto> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices=invoiceRepositories.findByCustomerId(customerId);
        for(Invoice invoice:invoices){
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        return invoices.stream().map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDto> allInvoices() {
        List<Invoice> invoices=invoiceRepositories.findAll();

        invoices.forEach(invoice -> {
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        });


    /* for(Invoice invoice:invoices){
         Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
         invoice.setCustomer(customer);
     }*/

        return invoices.stream().map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
    }
}
