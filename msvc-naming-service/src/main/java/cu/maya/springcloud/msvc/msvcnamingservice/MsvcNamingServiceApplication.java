package cu.maya.springcloud.msvc.msvcnamingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsvcNamingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcNamingServiceApplication.class, args);
    }

}
