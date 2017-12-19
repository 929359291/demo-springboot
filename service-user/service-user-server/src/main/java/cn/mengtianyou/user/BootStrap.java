package cn.mengtianyou.user;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

}
