package org.zenoda.ztrader;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.zenoda.ztrader")
@MapperScan(basePackages = "org.zenoda.ztrader.**.mapper")
public class ZtraderServer {

    public static void main(String[] args) {
        SpringApplication.run(ZtraderServer.class, args);
    }

}
