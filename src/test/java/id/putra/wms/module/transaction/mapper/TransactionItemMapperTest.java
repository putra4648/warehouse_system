package id.putra.wms.module.transaction.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionItemMapperTest {

    private TransactionItemMapper transactionItemMapper = Mappers.getMapper(TransactionItemMapper.class);

    @Test
    void mapperLoads() {
        assertThat(transactionItemMapper).isNotNull();
    }

}
