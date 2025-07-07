package id.putra.wms.component;

import lombok.Data;

@Data
public class MessageBox {
    private final String icon;
    private final String text;
    private final Number count;
    private final String status;
}
