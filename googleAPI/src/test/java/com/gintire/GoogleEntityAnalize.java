package com.gintire;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1.AnalyzeEntitySentimentRequest;
import com.google.cloud.language.v1.AnalyzeEntitySentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;

public class GoogleEntityAnalize {

	@Test
	public void main() {
		String text = "It is famous and the pink fact is good, and it is good, and this time home shopping. We watched broadcasting and bought it again. I opened the fact to receive, but what is this puff with industrial smell? I was surprised to see. Who wrote it for three years? It's so dirty.";
		try {
			func(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void func(String text) throws IOException {
		// Instantiate the Language client com.google.cloud.language.v1.LanguageServiceClient
//		try (LanguageServiceClient language = LanguageServiceClient.create()) {
//		  Document doc = Document.newBuilder()
//		      .setContent(text)
//		      .setType(Type.PLAIN_TEXT)
//		      .build();
//		  AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder()
//		      .setDocument(doc)
//		      .setEncodingType(EncodingType.UTF16)
//		      .build();
//
//		  AnalyzeEntitiesResponse response = language.analyzeEntities(request);
//
//		  // Print the response
//		  for (Entity entity : response.getEntitiesList()) {
//		    System.out.printf("Entity: %s", entity.getName());
//		    System.out.printf("Salience: %.3f\n", entity.getSalience());
//		    System.out.println("Metadata: ");
//		    for (Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()) {
//		      System.out.printf("%s : %s", entry.getKey(), entry.getValue());
//		    }
//		    for (EntityMention mention : entity.getMentionsList()) {
//		      System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
//		      System.out.printf("Content: %s\n", mention.getText().getContent());
//		      System.out.printf("Type: %s\n\n", mention.getType());
//		    }
//		  }
//		}
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			  Document doc = Document.newBuilder()
			      .setContent(text).setType(Type.PLAIN_TEXT).build();
			  AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.newBuilder()
			      .setDocument(doc)
			      .setEncodingType(EncodingType.UTF16).build();
			  // detect entity sentiments in the given string
			  AnalyzeEntitySentimentResponse response = language.analyzeEntitySentiment(request);
			  // Print the response
			  for (Entity entity : response.getEntitiesList()) {
			    System.out.printf("Entity: %s\n", entity.getName());
			    System.out.printf("Salience: %.3f\n", entity.getSalience());
			    System.out.printf("Sentiment : %s\n", entity.getSentiment());
			    for (EntityMention mention : entity.getMentionsList()) {
			      System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
			      System.out.printf("Content: %s\n", mention.getText().getContent());
			      System.out.printf("Magnitude: %.3f\n", mention.getSentiment().getMagnitude());
			      System.out.printf("Sentiment score : %.3f\n", mention.getSentiment().getScore());
			      System.out.printf("Type: %s\n\n", mention.getType());
			    }
			  }
			}
	}

}
