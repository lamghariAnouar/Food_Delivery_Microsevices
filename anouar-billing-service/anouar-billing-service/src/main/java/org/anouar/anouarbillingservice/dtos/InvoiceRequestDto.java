package org.anouar.anouarbillingservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequestDto {
    private BigDecimal amount;
    private String customerId;


}
