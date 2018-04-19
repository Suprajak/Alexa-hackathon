package amazon.alexaskill.alexaartifact;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import static com.amazon.ask.request.Predicates.sessionAttribute;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
 
public class HelloWorldIntentHandler implements RequestHandler {
 
     public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("BreakTheCode").and(sessionAttribute(Attributes.STATE_KEY, Attributes.PLAY_STATE).negate()));
     }
 
     public Optional<Response> handle(HandlerInput input) {
    	 Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
     sessionAttributes.put(Attributes.STATE_KEY, Attributes.PLAY_STATE);
     sessionAttributes.put(Attributes.RESPONSE_KEY, "Success");
    	 Map<Integer,String> encodedWords = new HashMap<Integer, String>();
    	 //List of words
    	 List<String> words = new ArrayList<String>();
    	 words.add("Amazon");
    	 words.add("India");
    	 words.add("Best");
    	 words.add("Day1");
    	 words.add("Customer");
    	 words.add("Trust");
    	 
    	 //Pick random numbers
    	 Random random = new Random();
    	 String mappedWords = new String();
    	 //Make sure random numbers are unique. TODO
    	 for(int i=0;i<3;i++) {
    		 int randomNumber = random.nextInt((4-0)+1)+0;
    		 encodedWords.put(randomNumber, words.get(randomNumber));
    		 mappedWords+=randomNumber+" is "+words.get(randomNumber)+",  ";
    	 }
    	 
    	 //Pick a combination of numbers
     Set<Integer> randomNumbers = encodedWords.keySet();

     String speechText = "The encoded words are, "+mappedWords+" , Your combination is, "+randomNumbers+", your timer starts now"; //Add code for mapping words to numbers and say to the player.
     IntentRequest intentRequest = (IntentRequest)input.getRequestEnvelope().getRequest();  
     return input.getResponseBuilder()
                 .withSpeech(speechText)
                 .withReprompt(speechText)
                 .withSimpleCard("Break the code", speechText)
                 .withShouldEndSession(false)
                 .build();
     }
 
}