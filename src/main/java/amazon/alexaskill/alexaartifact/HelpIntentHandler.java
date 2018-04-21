package amazon.alexaskill.alexaartifact;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class HelpIntentHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.HelpIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        String speechText = "You will be given with a mapping of number to word. For example 1  maps to Awesome and 2 maps to Alexa. Then there will be a combination give to you " +
                "like 2,1. So you have to correctly say in order like 'answer is Alexa Awesome'. There will be different combination of words. For each level" +
                "there will be an increase in complexity with number of words and the combination." ;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Listen to Alexa's instructions. ", speechText)
                .withReprompt(speechText)
                .build();
    }
}