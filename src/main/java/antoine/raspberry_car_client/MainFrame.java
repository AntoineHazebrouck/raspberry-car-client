package antoine.raspberry_car_client;

import javax.swing.JFrame;
import org.springframework.context.ApplicationContext;

public class MainFrame extends JFrame {

    public MainFrame(ApplicationContext app) {
        setTitle("raspberry-car-client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        getContentPane().add(new InputListenerPanel(app));
    }
}
