package antoine.raspberry_car_client;

import antoine.raspberry_car_client.web.client.WebSocketConnection;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class Main {

    private static final long TARGET_TIME_NANOS = 1_000_000_000L / 60;

    private final WebSocketConnection socket;

    private boolean running = true;

    public void run() {
        val gamepad = gamepad();
        long lastTime = System.nanoTime();

        while (running) {
            long startTime = System.nanoTime();
            long elapsedTime = startTime - lastTime;
            lastTime = startTime;

            handleInputs(gamepad);

            long timeSpent = System.nanoTime() - startTime;
            long timeToSleep = TARGET_TIME_NANOS - timeSpent;

            if (timeToSleep > 0) {
                try {
                    // Sleep for the remainder of the 16.67ms
                    long sleepMillis = timeToSleep / 1_000_000L;
                    int sleepNanos = (int) (timeToSleep % 1_000_000L);
                    Thread.sleep(sleepMillis, sleepNanos);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return; // Exit loop on interruption
                }
            }
        }

        while (running) {
            handleInputs(gamepad());
        }
    }

    private void handleInputs(Controller gamepad) {
        // Event event = new Event();

        if (!gamepad.poll()) log.error("controller disconnected or is invalid");

        Component[] components = gamepad.getComponents();

        for (Component component : components) {
            var data = component.getPollData();

            if (component.getIdentifier() == Component.Identifier.Axis.Y) {
                double power = data * -100;
                // forward
                if (power >= 0) socket.send(power, true);
            }

            if (
                component.getIdentifier() == Component.Identifier.Button._7 &&
                data == 1
            ) {
                log.info("shutting down the client application");
                socket.send(0, true);
                running = false;
            }
        }
    }

    private Controller gamepad() {
        var controllers = Stream.of(
            ControllerEnvironment.getDefaultEnvironment().getControllers()
        )
            .filter(controller -> controller.getType().equals(Type.GAMEPAD))
            .toList();

        if (controllers.size() != 1) {
            log.error("found {} controllers, should be 1", controllers.size());
            log.info("shutting down the client application");
            running = false;
        }

        return controllers.get(0);
    }
}
