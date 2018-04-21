package amazon.alexaskill.alexaartifact;

import static com.amazon.ask.request.Predicates.sessionAttribute;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class DecodedWordsIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("DecodedWords").and(sessionAttribute(Attributes.STATE_KEY, Attributes.PLAY_STATE)));
    }

    public Optional<Response> handle(HandlerInput input) {
		return  UtilityClass.verifyCombination(input);
    }
}
