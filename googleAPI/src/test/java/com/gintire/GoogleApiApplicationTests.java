package com.gintire;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class GoogleApiApplicationTests {

	public static String text;
	@Before
	public static void prepare() {
	    // Instantiates a client
	    text = "저렴이로 잘 구매했어요.~\n" + 
	    		"색상은 그다지 나쁘지 않고 박스 포장도 잘해서 왔어요.\n" + 
	    		"부담없이 그럭저럭 잘 사용 하겠네요.";
	}
	
	@Test
	private static void analyizeString(String text) throws IOException {
		// Instantiate the Language client com.google.cloud.language.v1.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
		  Document doc = Document.newBuilder()
		      .setContent(text)
		      .setType(Type.PLAIN_TEXT)
		      .build();
		  AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
		  Sentiment sentiment = response.getDocumentSentiment();
		  if (sentiment == null) {
		    System.out.println("No sentiment found");
		  } else {
			System.out.println(text);
		    System.out.printf("Sentiment magnitude: %.3f\n", sentiment.getMagnitude());
		    System.out.printf("Sentiment score: %.3f\n", sentiment.getScore());
		  }
		  assertNotNull(sentiment);
		}
		
	}

}
