
package in.appikonda.gifs;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.json.JSONException;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultGif;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotForGifs extends TelegramLongPollingBot {
	AnswerInlineQuery answer;
	JsonParser jp;
	private int size;
	public String getBotUsername() {
		return "DesiGIFs_bot";
	}

	public void onUpdateReceived(Update update) {
		if (update!=null && update.getInlineQuery()!=null)
		{
		Message message = update.getMessage();
		     String inlineQuery =  update.getInlineQuery().getQuery();
		     String queryId = update.getInlineQuery().getId();
		if (message != null && message.hasText() ) {
			if (message.getText().contains("/gif")) {
				JsonParser jp = new JsonParser();
				String searchStg = new String("Sherya");
				try {
					List<String> allGifs = jp.getItAll(searchStg);

					// Create ReplyKeyboardMarkup object
					ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
					// Create the keyboard (list of keyboard rows)\
					// Create a keyboard row
					KeyboardRow row = new KeyboardRow();
					List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
					for (int i = 0; i < allGifs.size(); i++) {
						row.add(allGifs.get(i));
						keyboard.add(row);
					}

					
					// Set the keyboard to the markup
					keyboardMarkup.setKeyboard(keyboard);
					// Add it to the message

					// InlineKeyboardButton
					InlineKeyboardButton inline = new InlineKeyboardButton();
					for (int i = 0; i < allGifs.size(); i++) {
						inline.setUrl(allGifs.get(i));
					}
					SendDocument msg = new SendDocument().setChatId(message.getChatId()).setDocument(allGifs.get(1));

					// sendMessageRequest.setChatId();
					try {
						sendDocument(msg); // Sending our message object to user
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		
		else if (inlineQuery != null && !inlineQuery.isEmpty()) {
			answer = new AnswerInlineQuery();
			jp = new JsonParser();		
			try {
				List<String> allGifs = jp.getItAll(inlineQuery);
				System.out.println("GIFFSSSSIZE:" + allGifs.size());
				if (allGifs.size()>10)
				{
					size = 9 ;
				}
				else
				{
					size = allGifs.size();
				}
					
				if (!allGifs.isEmpty() ){
					List<InlineQueryResult> results = new ArrayList<InlineQueryResult>();
						for (int i = 0; i < size; i++) {
							InlineQueryResultGif inlineGif = new InlineQueryResultGif();
							String s=   shuffle("s4324abcdefghgghfijklmnopqr");
							inlineGif.setGifHeight(600);
							inlineGif.setGifWidth(600);
							inlineGif.setId(s);// Need a random string 
							inlineGif.setThumbUrl(allGifs.get(i));
							inlineGif.setGifUrl(allGifs.get(i));
							results.add(inlineGif);
						}
						answer.setInlineQueryId(queryId);
						answer.setCacheTime(10000);
						System.out.println("QUERYIDDDDDDDDDDDDDDDDDDDDDDDDDDD" + queryId);
						answer.setResults(results);
						answer.isPersonal();
						this.execute(answer);
						answer = null;
				}
			} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TelegramApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "505659769:AAHEXFN6HfteZliFkTZYkAzBuEkUlq1wAd0";
	}

	private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
		System.out.println("\n ----------------------------");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out
		.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
		System.out.println("Bot answer: \n Text - " + bot_answer);
	}
	
	 public String shuffle(String input){
	        List<Character> characters = new ArrayList<Character>();
	        for(char c:input.toCharArray()){
	            characters.add(c);
	        }
	        StringBuilder output = new StringBuilder(input.length());
	        while(characters.size()!=0){
	            int randPicker = (int)(Math.random()*characters.size());
	            output.append(characters.remove(randPicker));
	        }
	        System.out.println(output.toString());
	        return output.toString();
	    }

}

package in.appikonda.gifs;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.json.JSONException;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultGif;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotForGifs extends TelegramLongPollingBot {

	public String getBotUsername() {
		return "DesiGIFs_bot";
	}

	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		     String inlineQuery =  update.getInlineQuery().getQuery();
		     String queryId = update.getInlineQuery().getId();
		if (message != null && message.hasText() ) {
			if (message.getText().contains("/gif")) {
				JsonParser jp = new JsonParser();
				String searchStg = new String("Sherya");
				try {
					List<String> allGifs = jp.getItAll(searchStg);

					// Create ReplyKeyboardMarkup object
					ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
					// Create the keyboard (list of keyboard rows)\
					// Create a keyboard row
					KeyboardRow row = new KeyboardRow();
					List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
					for (int i = 0; i < allGifs.size(); i++) {
						row.add(allGifs.get(i));
						keyboard.add(row);
					}

					
					// Set the keyboard to the markup
					keyboardMarkup.setKeyboard(keyboard);
					// Add it to the message

					// InlineKeyboardButton
					InlineKeyboardButton inline = new InlineKeyboardButton();
					for (int i = 0; i < allGifs.size(); i++) {
						inline.setUrl(allGifs.get(i));
					}
					SendDocument msg = new SendDocument().setChatId(message.getChatId()).setDocument(allGifs.get(1));

					// sendMessageRequest.setChatId();
					try {
						sendDocument(msg); // Sending our message object to user
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		
		else if (inlineQuery != null && !inlineQuery.isEmpty()) {
			AnswerInlineQuery answer = new AnswerInlineQuery();
			JsonParser jp = new JsonParser();		
			try {
				List<String> allGifs = jp.getItAll(inlineQuery);
				if (!allGifs.isEmpty() ){
					List<InlineQueryResult> results = new ArrayList<InlineQueryResult>();
						for (int i = 0; i < allGifs.size(); i++) {
							InlineQueryResultGif inlineGif = new InlineQueryResultGif();
							String s=   shuffle("abcdefghijklmnopqr");
							inlineGif.setGifHeight(600);
							inlineGif.setGifWidth(600);
							inlineGif.setId(s);// Need a random string 
							inlineGif.setThumbUrl(allGifs.get(i));
							inlineGif.setGifUrl(allGifs.get(i));
							results.add(inlineGif);
						}
						answer.setInlineQueryId(queryId);
						answer.setResults(results);
						answer.isPersonal();
						execute(answer);
				}
			} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TelegramApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "505659769:AAHEXFN6HfteZliFkTZYkAzBuEkUlq1wAd0";
	}

	private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
		System.out.println("\n ----------------------------");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out
		.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
		System.out.println("Bot answer: \n Text - " + bot_answer);
	}
	
	 public String shuffle(String input){
	        List<Character> characters = new ArrayList<Character>();
	        for(char c:input.toCharArray()){
	            characters.add(c);
	        }
	        StringBuilder output = new StringBuilder(input.length());
	        while(characters.size()!=0){
	            int randPicker = (int)(Math.random()*characters.size());
	            output.append(characters.remove(randPicker));
	        }
	        System.out.println(output.toString());
	        return output.toString();
	    }

}
