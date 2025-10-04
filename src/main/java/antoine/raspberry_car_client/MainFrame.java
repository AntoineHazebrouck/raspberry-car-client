package antoine.raspberry_car_client;

import antoine.raspberry_car_client.web.client.WebSocketConnection;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class MainFrame extends JFrame {

    public MainFrame(ApplicationContext app) {
        var socket = app.getBean(WebSocketConnection.class);

        setTitle("raspberry-car-client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        var inputs = new InputListenerPanel();
        inputs.addKeyListener(
            new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    // DO NOTHING
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    log.info("pressed");
                    if (e.getKeyChar() == 'Z') {
                        socket.send(100, true);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    log.info("released");
                    if (e.getKeyChar() == 'Z') {
                        socket.send(100, true);
                    }
                }
            }
        );
        inputs.setBackground(Color.BLUE);
        inputs.setFocusable(true);
        // inputs.u
        getContentPane().add(inputs);
    }
}
