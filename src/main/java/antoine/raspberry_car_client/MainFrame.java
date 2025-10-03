package antoine.raspberry_car_client;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("raspberry-car-client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        getContentPane().add(new InputListenerPanel());
    }
}
