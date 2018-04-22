package amazon.alexaskill.alexaartifact;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import java.util.*;

public class UtilityClass {

    public static String getCombination(HandlerInput input){
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Attributes.STATE_KEY, Attributes.PLAY_STATE);
        sessionAttributes.put(Attributes.RESPONSE_KEY, "Success");
        int counter = (Integer) sessionAttributes.get(Attributes.COUNTER_KEY);
        int level = (Integer) sessionAttributes.get(Attributes.GAME_LEVEL_KEY);
        if(level >= 15) {// Start again.
            level = 3;
            counter = 0;
        }
        Map<Integer,String> encodedWords = new HashMap<Integer, String>();
        //List of words
        List<String> words = new ArrayList<String>();
        words.add("Amazon");
        words.add("India");
        words.add("Best");
        words.add("Day1");
        words.add("Customer");
        words.add("Trust");
        words.add("Awesome");
        words.add("World");
        words.add("Land");
        words.add("Android");
        words.add("Notes");
        words.add("Pizza");
        words.add("Bag");
        words.add("Wallet");
        words.add("Cricket");
        words.add("Computer");
        words.add("Mirror");
        words.add("Card");
        words.add("Keyboard");
        words.add("Building");
        words.add("Box");
        words.add("Calender");
        words.add("Fire");
        words.add("Exit");
        words.add("Bottle");
        words.add("Chennai");
        words.add("Sample");
        words.add("Pen");
        words.add("Laptop");
        words.add("Sky");

        //Pick random numbers
        Random random = new Random();
        String mappedWords = new String();
        //Make sure random numbers are unique.
        for(int i=0;i<level;i++) {
            int randomNumber = random.nextInt((words.size()-1)+1)+0;
            if(encodedWords.get(randomNumber) == null) {
                encodedWords.put(randomNumber, words.get(randomNumber));
                mappedWords+=randomNumber+" is "+words.get(randomNumber)+",  ";
            } else {
                i--;
            }
        }
        if(counter >=3) {
            counter = 0;
            level++;
        } else {
            counter++;
        }

        sessionAttributes.put(Attributes.GAME_MAPPED_WORDS, encodedWords);
        sessionAttributes.put(Attributes.GAME_LEVEL_KEY, level);
        sessionAttributes.put(Attributes.COUNTER_KEY, counter);
        //Pick a combination of numbers
        Set<Integer> randomNumbers = encodedWords.keySet();

        String speechText = "The mapped words are, "+mappedWords+" , Your combination is, "+randomNumbers+", Your time starts now. . . "; //Add code for mapping words to numbers and say to the player.


        Long time = System.currentTimeMillis();
        sessionAttributes.put(Attributes.GAME_START_TIME, time);

        return speechText;
    }

    public static Optional<Response> verifyCombination(HandlerInput input) {
        String responseToUser = new String();
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        Map<Integer, String> encodedWords = (Map<Integer, String>) sessionAttributes.get(Attributes.GAME_MAPPED_WORDS);
        IntentRequest intentRequest = (IntentRequest)input.getRequestEnvelope().getRequest();
        Map<String,Slot> slots = intentRequest.getIntent().getSlots();
        String[] userInput = slots.get(Attributes.INPUT_INTENT).getValue().split(" ");
        boolean isSuccess = true;
        int i = 0;
        Long startTime = (Long) sessionAttributes.get(Attributes.GAME_START_TIME);
        Long stopTime = System.currentTimeMillis();
        Long timeTaken = stopTime - startTime;
        if(timeTaken < 35000) {
            for (Map.Entry<Integer, String> map : encodedWords.entrySet()) {
                if (i < userInput.length && !map.getValue().equalsIgnoreCase(userInput[i])) {
                    isSuccess = false;
                }
                i++;
            }
            if (isSuccess) {
                int score = (Integer) sessionAttributes.get(Attributes.GAME_SCORE_KEY);
                score += (Integer) sessionAttributes.get(Attributes.GAME_LEVEL_KEY);
                sessionAttributes.put(Attributes.GAME_SCORE_KEY, score);
                responseToUser = "Awesome!!! You cracked it!! Your score is:   " + score +",   Here's your next combination!. " ;
            } else {
                responseToUser = "Sorry! That's not right! KeepTrying.   Here's your next combination!.  ";
            }
        } else {
            responseToUser = "Sorry! That was a good try. But need to be fast.   You exceeded the time limit.   Here's your next combination!. ";
        }
        String nextQuestion = getCombination(input);
        return input.getResponseBuilder()
                .withSpeech(responseToUser + nextQuestion)
                .withSimpleCard("","Good Job!!")
                .withShouldEndSession(false)
                .build();
    }

}
