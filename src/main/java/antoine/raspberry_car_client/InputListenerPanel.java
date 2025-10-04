package antoine.raspberry_car_client;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputListenerPanel extends JPanel {

    // public InputListenerPanel(ApplicationContext app) {

    //     getInputMap().put(KeyStroke.getKeyStroke("Z"), "moveForward");

    //     getActionMap()
    //         .put(
    //             "moveForward",
    //             new AbstractAction() {
    //                 @Override
    //                 public void actionPerformed(ActionEvent e) {
    //                     log.info("Z");
    //                     socket.send(100, true);
    //                 }
    //             }
    //         );
    // }

    // public void registerInput(
    //     String name,
    //     KeyStroke key,
    //     Consumer<ActionEvent> action
    // ) {
    //     getInputMap().put(key, name);

    //     getActionMap()
    //         .put(
    //             name,
    //             new AbstractAction() {
    //                 @Override
    //                 public void actionPerformed(ActionEvent e) {
    //                     action.accept(e);
    //                 }
    //             }
    //         );
    // }
}
