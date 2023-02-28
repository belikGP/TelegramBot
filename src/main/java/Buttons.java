import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
    private static final InlineKeyboardButton Button = new InlineKeyboardButton("Помню");
    private static final InlineKeyboardButton Button1 = new InlineKeyboardButton("Забыл");
    private static final InlineKeyboardButton Button2 = new InlineKeyboardButton("Физика");
    private static final InlineKeyboardButton Button3 = new InlineKeyboardButton("Химия");
    private static final InlineKeyboardButton Button4 = new InlineKeyboardButton("Биология");
    private static final InlineKeyboardButton Button5 = new InlineKeyboardButton("Музыка");

    public static InlineKeyboardMarkup getRememberMarkup(){
        Button.setCallbackData("Помню");
        Button1.setCallbackData("Забыл");
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();

        firstRow.add(Button);
        firstRow.add(Button1);

        buttons.add(firstRow);

        InlineKeyboardMarkup rememberMarkUp = new InlineKeyboardMarkup();
        rememberMarkUp.setKeyboard(buttons);
        return rememberMarkUp;
    }
    public static InlineKeyboardMarkup getCategoriesMarkUp(){
        Button2.setCallbackData("Физика");
        Button3.setCallbackData("Химия");
        Button4.setCallbackData("Биология");
        Button5.setCallbackData("Музыка");
        List<List<InlineKeyboardButton>> buttonsCategories = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();

        firstRow.add(Button2);
        firstRow.add(Button3);
        secondRow.add(Button4);
        secondRow.add(Button5);

        buttonsCategories.add(firstRow);
        buttonsCategories.add(secondRow);

        InlineKeyboardMarkup categoriesMarkUp = new InlineKeyboardMarkup();
        categoriesMarkUp.setKeyboard(buttonsCategories);
        return categoriesMarkUp;
    }

}
