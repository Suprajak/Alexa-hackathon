package amazon.alexaskill.alexaartifact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
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
   	 for(int i=0;i<3;i++) {
   		 int randomNumber = random.nextInt((4-0)+1)+0;
   		 encodedWords.put(randomNumber, words.get(randomNumber));
   		 mappedWords+=randomNumber+" is "+words.get(randomNumber);
   		 System.out.println("#### "+mappedWords);
   	 }
    }
}
