package antoine.raspberry_car_client;

import antoine.raspberry_car_client.web.client.WebSocketConnection;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class InputListenerPanel extends JPanel {

    public InputListenerPanel(ApplicationContext app) {
        var socket = app.getBean(WebSocketConnection.class);

        getInputMap().put(KeyStroke.getKeyStroke("Z"), "moveForward");

        getActionMap()
            .put(
                "moveForward",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        log.info("Z");
                        socket.send(100, true);
                    }
                }
            );
    }
}
