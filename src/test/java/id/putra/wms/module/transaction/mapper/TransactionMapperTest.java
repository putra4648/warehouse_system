package id.putra.wms.module.transaction.mapper;

import id.putra.wms.test.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestContainersConfig.class)
public class TransactionMapperTest {

    @Autowired
    private TransactionMapper transactionMapper;

    @Test
    void mapperLoads() {
        assertThat(transactionMapper).isNotNull();
    }

}
