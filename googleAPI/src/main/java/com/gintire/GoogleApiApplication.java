package com.gintire;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.language.v1.Document.Type;

@SpringBootApplication
public class GoogleApiApplication {

	public static void main(String... args)throws Exception {
		try (LanguageServiceClient language = LanguageServiceClient.create()) {

		      // The text to analyze
		      String text = "Hello, world!";
		      Document doc = Document.newBuilder()
		          .setContent(text).setType(Type.PLAIN_TEXT).build();

		      // Detects the sentiment of the text
		      Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

		      System.out.printf("Text: %s%n", text);
		      System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());
		    }
		SpringApplication.run(GoogleApiApplication.class, args);
	}
	
	public static Sentiment analyizeString(String text) throws IOException {
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
		    System.out.printf("Sentiment magnitude: %.3f\n", sentiment.getMagnitude());
		    System.out.printf("Sentiment score: %.3f\n", sentiment.getScore());
		  }
		  return sentiment;
		}
	}
}