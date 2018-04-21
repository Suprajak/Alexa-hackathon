package amazon.alexaskill.alexaartifact;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import static com.amazon.ask.request.Predicates.sessionAttribute;

import java.util.Optional;

public class HelloWorldIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("BreakTheCode").and(sessionAttribute(Attributes.STATE_KEY, Attributes.PLAY_STATE).negate()));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText = UtilityClass.getCombination(input);
		return input.getResponseBuilder()
				.withSpeech(speechText)
				.withSimpleCard("Break the code", speechText)
				.withShouldEndSession(false)
				.build();
	}

}