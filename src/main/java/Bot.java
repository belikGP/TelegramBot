import lombok.NonNull;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    final private String BOT_TOKEN = "5741906140:AAERZK_n1Jvi89mxHtx-A-Dkuk31pdfLOnk";
    final private String BOT_NAME = "@justBelikBot";
    Storage storage;
    Album album;

    public Bot(DefaultBotOptions options) {
        super(options);
    }

    Bot() {
        storage = new Storage();
        album = new Album();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
    static String wordsCategory;
    LearnProgress learnProgress = new LearnProgress();

    @Override
    @SneakyThrows
    public void onUpdateReceived(@NonNull Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            //Извлекаем из объекта сообщение пользователя
            Message inMess = update.getMessage();
            //Достаем из inMess id чата пользователя
            String chatId = inMess.getChatId().toString();

            //Создаем объект класса SendMessage - наш будущий ответ пользователю
            SendMessage outMess = new SendMessage();


            //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
            String response = parseMessage(inMess.getText(), outMess);


            //вызываем метод для отображения кнопок(помню\забыл)
            //SendWord(outMess);


            //Добавляем в наше сообщение id чата а также наш ответ
            outMess.setChatId(chatId);
            outMess.setText(response);


            //Отправка в чат
            execute(outMess);
        } else if (update.hasCallbackQuery()) {

            SendMessage outMess = new SendMessage();
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            outMess.setChatId(chatId);
            String whichButton = update.getCallbackQuery().getData();
            boolean isWordsCategory = Category.categories.containsKey(whichButton);

            if (isWordsCategory) {
                wordsCategory = whichButton;
                learnProgress.setProgressOfCategory();
                outMess.setReplyMarkup(Buttons.getRememberMarkup());
                Diction.mapForRand = Category.categories.get(wordsCategory);
                Diction.lastWord = Diction.getWord(learnProgress);
                Integer score = learnProgress.progressOfCategory.get(Diction.lastWord);
                learnProgress.progressOfCategory.put(Diction.lastWord, score);
                outMess.setText(Diction.lastWord);
            } else {
                switch (whichButton) {
                    case "Помню":
                        SendWord(outMess, learnProgress);
                        if (learnProgress.progressOfCategory.get(Diction.preLastWord) != null){
                            Integer score = learnProgress.progressOfCategory.get(Diction.preLastWord);
                            learnProgress.progressOfCategory.put(Diction.preLastWord, ++score);
                        }
                        break;
                    case "Забыл":
                        outMess.setText(Diction.getTranslate());
                        execute(outMess);
                        SendWord(outMess, learnProgress);
                        break;
                }
            }
            System.out.println(Diction.lastWord + " " + learnProgress.progressOfCategory.get(Diction.lastWord));
            System.out.println(learnProgress.progressOfCategory);
            if (outMess.getText().equals("Вы усвоили категорию")) {
                execute(outMess);
                outMess.setText("/start");
                execute(outMess);
            }else execute(outMess);


        }

    }

    public static SendMessage SendWord(SendMessage outMess, LearnProgress learnProgress) {
        outMess.setText(Diction.getWord(learnProgress));
        outMess.setReplyMarkup(Buttons.getRememberMarkup());
        return outMess;
    }

    //Обработчик сообщений от пользователя
    @SneakyThrows
    public String parseMessage(String receivedMessage, SendMessage outMess) {
        String response;
        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ

        switch (receivedMessage) {
            case "/start":
                response = "Приветствую, этот бот поможет вам в обучении\n" +
                        "Выбирайте категорию:\n";
                Category.showCategoriesMarkUp(outMess);
                break;

            default:
                response = "Используйте команды: /start";
        }
        return response;
    }

    @SneakyThrows
    public void sendPhoto(String chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(new File(album.getRandPhoto())));
        execute(sendPhoto);
    }
}
