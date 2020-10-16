package com.lin.buy.limit.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("com.lin.buy.limit.db.dao")
public class BuyLimitConfig {

}
