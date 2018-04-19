package amazon.alexaskill.alexaartifact;

import static com.amazon.ask.request.Predicates.sessionAttribute;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;

public class DecodedWordsIntentHandler implements RequestHandler {
	public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("DecodedWords").and(sessionAttribute(Attributes.STATE_KEY, Attributes.PLAY_STATE)));
    }

    public Optional<Response> handle(HandlerInput input) {
   	 Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
   	 IntentRequest intentRequest = (IntentRequest)input.getRequestEnvelope().getRequest();
   	 Map<String,Slot> slots = intentRequest.getIntent().getSlots();
   	 String response = new String();
   	for (Slot slot : slots.values()) {
        if (slot.getValue() != null ) {
        	   response +=" "+slot.getValue().toLowerCase();
        }
    }
        return input.getResponseBuilder()
                .withSpeech("Thanks for answering")
                .withReprompt(response)
                .withSimpleCard(response,"Good job")
                .withShouldEndSession(false)
                .build();
    }
}
