package antoine.raspberry_car_client;

import javax.swing.SwingUtilities;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RaspberryCarClientApplication {

    public static void main(String[] args) {
        var app = new SpringApplicationBuilder(
            RaspberryCarClientApplication.class
        )
            .headless(false)
            .run(args);

        var mainWindow = app.getBean(WindowOpener.class);

        SwingUtilities.invokeLater(() -> mainWindow.open());
    }
}
