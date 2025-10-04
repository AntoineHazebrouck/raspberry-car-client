package antoine.raspberry_car_client;

import antoine.raspberry_car_client.web.client.WebSocketConnection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class InputListener implements KeyListener {

    private static final char FORWARD = 'z';

    private final WebSocketConnection socket;

    public InputListener(ApplicationContext app) {
        socket = app.getBean(WebSocketConnection.class);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // DO NOTHING
    }

    @Override
    public void keyPressed(KeyEvent e) {
        log.info("pressed : {}", e.getKeyChar());
        if (e.getKeyChar() == FORWARD) {
            socket.send(100, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        log.info("released : {}", e.getKeyChar());
        if (e.getKeyChar() == FORWARD) {
            socket.send(0, true);
        }
    }
}
