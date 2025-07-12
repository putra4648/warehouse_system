package id.putra.wms.component;

import id.putra.wms.enums.MessageBoxDataType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MessageBoxV2 extends MessageBox {
    private final String progressDesc;
    private final MessageBoxDataType dataType;

    public MessageBoxV2(String icon, String text, Number count, MessageBoxDataType dataType, String status,
            String progressDesc) {
        super(icon, text, count, status);
        this.progressDesc = progressDesc;
        this.dataType = dataType;
    }

}
