package antoine.raspberry_car_client.web.client;

import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Slf4j
@Service
public class WebSocketConnection {

    private StompSession session;

    public WebSocketConnection() {
        var socket = new WebSocketStompClient(new StandardWebSocketClient());
        socket.setMessageConverter(new MappingJackson2MessageConverter());

        socket
            .connectAsync(
                "ws://raspberrypi:8080/",
                new StompSessionHandlerAdapter() {
                    @Override
                    public void afterConnected(
                        @NonNull StompSession session,
                        @NonNull StompHeaders connectedHeaders
                    ) {
                        log.info(
                            "New STOMP session established: " +
                            session.getSessionId()
                        );
                    }

                    @Override
                    public void handleException(
                        @NonNull StompSession session,
                        @Nullable StompCommand command,
                        @NonNull StompHeaders headers,
                        @NonNull byte[] payload,
                        @NonNull Throwable exception
                    ) {
                        log.error(
                            "Error in STOMP session: " + exception.getMessage()
                        );
                    }

                    @Override
                    public void handleTransportError(
                        @NonNull StompSession session,
                        @NonNull Throwable exception
                    ) {
                        log.error(
                            "STOMP transport error: " + exception.getMessage()
                        );
                    }

                    @Override
                    public @NonNull Type getPayloadType(
                        @NonNull StompHeaders headers
                    ) {
                        return MotorControls.class;
                    }
                }
            )
            .thenAccept(session -> {
                log.info("Registering the StompSession");
                this.session = session;
            });
    }

    public void send(double power, boolean forward) {
        if (session != null) {
            session.send("/motor-controls", new MotorControls(power, forward));
        } else {
            log.info("socket session was null");
        }
    }
}
