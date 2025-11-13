package id.putra.wms.module.transaction.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionMapperTest {

    private TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @Test
    void mapperLoads() {
        assertThat(transactionMapper).isNotNull();
    }

}
