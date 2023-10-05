package cu.maya.springcloud.msvc.msvcentity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcEntityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcEntityApplication.class, args);
    }

}
