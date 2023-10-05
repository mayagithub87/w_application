package cu.maya.springcloud.msvc.msvcgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@SpringBootApplication
@LoadBalancerClient(name = "msvc-entity")
public class MsvcGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcGatewayServiceApplication.class, args);
    }

}
