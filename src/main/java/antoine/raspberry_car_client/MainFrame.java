package antoine.raspberry_car_client;

import java.awt.Color;
import javax.swing.JFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class MainFrame extends JFrame {

    public MainFrame(ApplicationContext app) {
        setTitle("raspberry-car-client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        var inputs = new InputListenerPanel();
        inputs.addKeyListener(new InputListener(app));
        inputs.setBackground(Color.BLUE);
        inputs.setFocusable(true);
        getContentPane().add(inputs);
    }
}
