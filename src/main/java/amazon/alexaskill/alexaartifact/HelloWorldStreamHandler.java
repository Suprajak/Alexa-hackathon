package amazon.alexaskill.alexaartifact;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import amazon.alexaskill.alexaartifact.CancelandStopIntentHandler;
import amazon.alexaskill.alexaartifact.HelloWorldIntentHandler;
import amazon.alexaskill.alexaartifact.HelpIntentHandler;
import amazon.alexaskill.alexaartifact.SessionEndedRequestHandler;
import amazon.alexaskill.alexaartifact.LaunchRequestHandler;
import amazon.alexaskill.alexaartifact.DecodedWordsIntentHandler;
 
 public class HelloWorldStreamHandler extends SkillStreamHandler {
 
     private static Skill getSkill() {
         return Skills.standard()
                 .addRequestHandlers(new CancelandStopIntentHandler(), new HelloWorldIntentHandler(), new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler(), new DecodedWordsIntentHandler())
                 .build();
     }
 
     public HelloWorldStreamHandler() {
         super(getSkill());
     }
 
 }
