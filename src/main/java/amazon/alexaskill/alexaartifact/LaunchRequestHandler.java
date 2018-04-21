package amazon.alexaskill.alexaartifact;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Map;
import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Welcome to Break the Code game. Are you ready to start with? ";
        Map<String,Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);
        sessionAttributes.put(Attributes.COUNTER_KEY, 0);
        sessionAttributes.put(Attributes.GAME_LEVEL_KEY,3);
        sessionAttributes.put(Attributes.GAME_SCORE_KEY,0);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Welcome", speechText)
                .withReprompt(speechText)
                .withShouldEndSession(false)
                .build();
    }
}