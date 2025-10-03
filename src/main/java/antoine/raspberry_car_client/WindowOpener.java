package antoine.raspberry_car_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WindowOpener {

    public void open() {
        var mainFrame = new MainFrame();
    }
}
