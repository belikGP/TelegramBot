import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {

    public static Map<String, Map> categories = new HashMap<>();

    static{
        Diction diction = new Diction();
        categories.put("Биология", diction.biology);
        categories.put("Музыка", diction.music);
        categories.put("Физика", diction.physics);
        categories.put("Химия", diction.chemistry);
    }
    public static Map<String, String> getCategory(String category){
        return categories.get(category);
    }
    public static SendMessage showCategoriesMarkUp(SendMessage outMess){
        outMess.setReplyMarkup(Buttons.getCategoriesMarkUp());
        return outMess;
    }
    /*public class Subcategory{
        private Map<String, Map> subBiology;
        private List<String> subChemistry;
        private List<String> subPhysics;
        private List<String> subMusic;

        {
            subBiology = Map.of("Анатомия","Ботаника", "Цитология");
            subChemistry = List.of("Органическая", "Неорганическая", "Биохимия");
            subPhysics = List.of("Механика", "Термодинамика", "Электродинамика");
            subMusic = List.of("Поп", "Классическая", "Рок");
        }
    }*/
}
