package in.appikonda.gifs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
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
		if (message != null && message.hasText()) {
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

					/*
					 * // Create a keyboard row KeyboardRow row = new
					 * KeyboardRow(); // Set each button, you can also use
					 * KeyboardButton objects if you need something else than
					 * text row.add("Row 1 Button 1");
					 * row.add("Row 1 Button 2"); row.add("Row 1 Button 3"); //
					 * Add the first row to the keyboard keyboard.add(row); //
					 * Create another keyboard row row = new KeyboardRow(); //
					 * Set each button for the second line
					 * row.add("Row 2 Button 1"); row.add("Row 2 Button 2");
					 * row.add("Row 2 Button 3"); // Add the second row to the
					 * keyboard keyboard.add(row);
					 */
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

}
