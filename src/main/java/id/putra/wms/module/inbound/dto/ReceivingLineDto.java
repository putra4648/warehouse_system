package id.putra.wms.module.inbound.dto;

import java.math.BigDecimal;
import java.sql.Date;

import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Data;

@Data
public class ReceivingLineDto {
    private Long id;
    private Date receivedDate;
    private String status;
    private String lotBatch;
    private Date expiryDate;
    private BigDecimal qtyReceived;
    private ProductDto product;
    private ReceivingDto receiving;
    
}
