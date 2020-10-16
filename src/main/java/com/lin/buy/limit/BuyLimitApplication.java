package com.lin.buy.limit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class BuyLimitApplication implements CommandLineRunner, DisposableBean {

    private final Logger logger = LoggerFactory.getLogger(BuyLimitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BuyLimitApplication.class, args);
    }

    @Override
    public void destroy() throws Exception {
        logger.info("buy-limit stop-------------------------------------------------------------");
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("buy-limit start-------------------------------------------------------------");
    }
}
