import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LearnProgress {
    public Map<String, String> learnedCategory;

    public Map<String, Integer> progressOfCategory;
    public List<String> keys = new ArrayList<>();
    public List<String> rememberedWords;

    public void setProgressOfCategory() {
        learnedCategory = Category.getCategory(Bot.wordsCategory);
        progressOfCategory = new HashMap<>();
        for (Map.Entry<String, String> pair:
                learnedCategory.entrySet()) {
            progressOfCategory.put(pair.getKey(), 0);
        }
    }

    public Map<String, Integer> getProgressOfCategory(){
        return this.progressOfCategory;
    }

}
