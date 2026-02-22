package org.zenoda.ztrader;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "org.zenoda.ztrader")
@MapperScan(basePackages = "org.zenoda.ztrader.**.mapper")
@EnableConfigurationProperties
public class ZtraderServer {

    public static void main(String[] args) {
        SpringApplication.run(ZtraderServer.class, args);
    }

}
