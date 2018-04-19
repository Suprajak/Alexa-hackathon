package amazon.alexaskill.alexaartifact;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;

import com.amazon.ask.request.Predicates;

public class SessionEndedRequestHandler implements RequestHandler {
    private static Logger LOG = getLogger(SessionEndedRequestHandler.class);

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(SessionEndedRequest.class));
    }

    public Optional<Response> handle(HandlerInput input) {
    	SessionEndedRequest sessionEndedRequest = (SessionEndedRequest) input.getRequestEnvelope().getRequest();
        LOG.debug("Session ended with reason: " + sessionEndedRequest.getReason().toString());
        return Optional.empty();
    }
}
