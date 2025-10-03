package antoine.raspberry_car_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WindowOpener {

    public void open(ApplicationContext app) {
        var mainFrame = new MainFrame(app);
    }
}
