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
         String speechText = "Welcome to Break the Code game. Are you ready to play the game break the code? ";
         Map<String,Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
         sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);
         return input.getResponseBuilder()
                 .withSpeech(speechText)
                 .withSimpleCard("Welcome", speechText)
                 .withReprompt(speechText)
                 .withShouldEndSession(false)
                 .build();
     }
 
}
