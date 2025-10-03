package antoine.raspberry_car_client;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputListenerPanel extends JPanel {

    public InputListenerPanel() {
        getInputMap().put(KeyStroke.getKeyStroke("Z"), "moveForward");

        getActionMap()
            .put(
                "moveForward",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        log.info("Z");
                    }
                }
            );
    }
}
