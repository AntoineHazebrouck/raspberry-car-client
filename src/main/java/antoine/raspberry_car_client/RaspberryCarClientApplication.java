package antoine.raspberry_car_client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RaspberryCarClientApplication {

    public static void main(String[] args) {
        var app = new SpringApplicationBuilder(
            RaspberryCarClientApplication.class
        )
            .web(WebApplicationType.NONE)
            .run(args);

        app.getBean(Main.class).run();
    }
}
