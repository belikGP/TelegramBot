import java.util.*;

public class Diction {
    public Map<String, String> biology;
    public Map<String, String> chemistry;
    public Map<String, String> physics;
    public Map<String, String> music;
    public static Map<String, String> mapForRand;
    public static String lastWord;
    public static String preLastWord;

    {
        biology = Map.of("Genetics", "генетика", "Biotechnology", "биотехнология", "Molecular biology", "молекулярная биология", "Microbiology", "микробиология", "Evolution", "эволюция", "Immunology", "иммунология", "Neurobiology", "нейробиология", "Developmental biology", "развивающая биология", "Ecology", "экология", "Systems biology", "системная биология");
        biology = Map.of("Synthetic biology", "синтетическая биология", "Phylogenetics", "филогенетика", "Bioinformatics", "биоинформатика", "Biochemistry", "биохимия", "Physiology", "физиология", "Zoology", "зоология", "Botany", "ботаника", "Marine biology", "морская биология", "Ornithology", "орнитология", "Cell membrane", "клеточная мембрана");
        chemistry = Map.of("Atom", "Атом", "Molecule", "Молекула", "Ionic bond", "Ионная связь", "Covalent bond", "Ковалентная связь", "Acid", "Кислота", "Base", "Основание", "Reaction", "Реакция", "Element", "Элемент", "Compound", "Соединение", "Oxidation", "Окисление");
        chemistry = Map.of("Reduction", "Восстановление", "Catalyst", "Катализатор", "Organic chemistry", "Органическая химия", "Inorganic chemistry", "Неорганическая химия", "Biochemistry", "Биохимия", "Nucleotide", "Нуклеотид", "Amino acid", "Аминокислота", "Polymer", "Полимер", "Enzyme", "Энзим", "Nanochemistry", "Нанохимия");
        physics = Map.of("Atom", "Атом", "Molecule", "Молекула", "Energy", "Энергия", "Force", "Сила", "Wave", "Волна", "Light", "Свет", "Particle", "Частица", "Radiation", "Радиация", "Electron", "Электрон", "Nucleus", "Ядро");
        physics = Map.of("quantum mechanics", "квантовая механика", "electromagnetism", "электромагнетизм", "thermodynamics", "термодинамика", "optics", "оптика", "relativity", "относительность", "atomic physics", "атомная физика", "particle physics", "физика частиц", "nuclear physics", "ядерная физика", "condensed matter physics", "физика интенсивных состояний", "astrophysics", "астрофизика");
        music = Map.of("Piano", "фортепиано", "Guitar", "гитара", "Drums", "ударные", "Bass", "бас", "Trumpet", "труба", "Violin", "скрипка", "Flute", "флейта", "Clarinet", "кларнет", "Saxophone", "саксофон", "Harp", "арфа");
        music = Map.of("Melody", "мелодия", "Harmony", "гармония", "Rhythm", "ритм", "Beat", "бит", "Chord", "аккорд", "Arpeggio", "арпеджио", "Orchestra", "оркестр", "Composition", "композиция", "Symphony", "симфония", "Opera", "опера");
    }

    public static String getWord(LearnProgress learnProgress) {
        String randomKey = "";

        List<String> keys = new ArrayList<>(mapForRand.keySet());
        Random random = new Random();
        randomKey = keys.get(random.nextInt(keys.size()));
        preLastWord = lastWord;
        if (learnProgress.progressOfCategory.get(preLastWord) != null && learnProgress.progressOfCategory.get(Diction.preLastWord) > 0){
            System.out.println(Diction.preLastWord +" " +learnProgress.progressOfCategory.get(Diction.preLastWord));
            learnProgress.progressOfCategory.remove(Diction.preLastWord);
            if (learnProgress.progressOfCategory.isEmpty()) randomKey = "Вы усвоили категорию";
        }

        lastWord = randomKey;
        return randomKey;
    }


    public static String getTranslate() {
        return mapForRand.get(lastWord);
    }


}
