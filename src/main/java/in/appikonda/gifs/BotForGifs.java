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
import org.telegram.telegrambots.api.methods.send.SendMessage;
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

	public String getBotUsername() {
		return "DesiGIFs_bot";
	}

	public void onUpdateReceived(Update update) {
		
		if (update != null && update.getInlineQuery() != null) {
			String inlineQuery = update.getInlineQuery().getQuery();
			String queryId = update.getInlineQuery().getId();
			if (inlineQuery != null && !inlineQuery.isEmpty()) {
				answer = new AnswerInlineQuery();
				jp = new JsonParser();
				try {
					List<String> allGifs = jp.getItAll(inlineQuery);
					System.out.println("GIFFSSSSIZE:" + allGifs.size());
					if (!allGifs.isEmpty()) {
						List<InlineQueryResult> results = new ArrayList<InlineQueryResult>();
						for (int i = 0; i < allGifs.size() && i < 50; i++) {
							InlineQueryResultGif inlineGif = new InlineQueryResultGif();
							String s = shuffle("s4324abcdefghgghfijklmnopqr");

							inlineGif.setId(s);// Need a random string
							inlineGif.setThumbUrl(allGifs.get(i));
							inlineGif.setGifUrl(allGifs.get(i));
							results.add(inlineGif);
						}
						answer.setInlineQueryId(queryId);
						answer.setCacheTime(10000);
						answer.setResults(results);
						answer.isPersonal();
						this.execute(answer);

					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String getBotToken() {
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

	public String shuffle(String input) {
		List<Character> characters = new ArrayList<Character>();
		for (char c : input.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(input.length());
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		return output.toString();
	}

}
