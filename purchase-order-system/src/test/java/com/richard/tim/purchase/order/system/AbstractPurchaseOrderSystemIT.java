package com.richard.tim.purchase.order.system;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractPurchaseOrderSystemIT {

    @LocalServerPort
    private int port;

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
