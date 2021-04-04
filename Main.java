package part6;
import java.util.Arrays;

import static java.util.Arrays.copyOfRange;


class GooQuery {
    private String language;
    private String text;



    public String getLanguage() {
        return language;
    }

    public String getText() {
        return text;
    }

    public GooQuery(String language, String text) {
        this.language = language;
        this.text = text;

    }

    @Override
    public String toString() {
        return "Searching " +"["+getText()+"]," + " using language: " + getLanguage();
    }
}
class GooQueryTest {
    public static void main(String[] args) {
        GooQuery query = new GooQuery("en", "capital");

        //en
        System.out.println(query.getLanguage());

        //capital
        System.out.println(query.getText());

        //Searching [capital], using language: en
        System.out.println(query);
    }
}
class GooWordStatTest {
    public static void main(String[] args) {
        //Word is [human], search freq is LOW
        System.out.println(new GooWordStat("human", 10));

        //Word is [mars], search freq is MEDIUM
        System.out.println(new GooWordStat("mars", 10000));

        //Word is [space], search freq is HIGH
        System.out.println(new GooWordStat("space", 9965499));

        //Word is [life], search freq is EXTRA HIGH
        System.out.println(new GooWordStat("life", 564785654));
    }
}
class GooWordStat {
    private String word;
    private int freq;
    public GooWordStat(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }
    public String getWord() {
        return word;
    }
    public int getFreq() {
        return freq;
    }
    @Override
    public String toString() {
        return "Word is ["+ getWord() +"], " + "search freq is " + freqReturn();
    }
    private String freqReturn() {
        if (freq<1000){
            return "LOW";
        }
        if (freq >= 1000 && freq < 100000) {
            return "MEDIUM";
        }
        if (freq >= 100000 && freq < 10000000) {
            return "HIGH";
        }
        if (freq >= 10000000) {
            return "EXTRA HIGH";
        }
        return "";
    }
}
class GooSearchResultTest {
    public static void main(String[] args) {
        //test.com
        System.out.println(new GooSearchResult("https://test.com").parseDomain());

        //apple.in.mars
        System.out.println(new GooSearchResult("http://apple.in.mars").parseDomain());
    }
}

class GooSearchResult {
    private String url;

    public GooSearchResult(String url) {
        this.url = url;
    }

    public String getUrl() {

        return url;
    }

    public String parseDomain() {

        if (getUrl().startsWith("http://")) {
            return getUrl().replaceAll("http://", "");
        }
        if (getUrl().startsWith("https://")) {
            return getUrl().replaceAll("https://", "");
        }
        return String.valueOf(getUrl().split("/"));}


}


class PhraseTest {
    public static void main(String[] args) {
        String[] words = {"alpha", "beta", "gamma"};

        Phrase phrase = new Phrase(words);

        //alpha beta gamma
        System.out.println(phrase);

        words[0] = "zero";

        //alpha beta gamma
        System.out.println(phrase);
    }
}
class Phrase {
    private final String[] words;

   protected  Phrase(String[] words) {
       this.words = Arrays.copyOf(words, words.length);
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String i : words){
        result.append(i).append(" ");
        }
        return result.toString();


    }
}
class WordSplitterTest {
    public static void main(String[] args) {
        WordSplitter wordSplitter = new WordSplitter();

        String[] words = wordSplitter.split("Hello      world");

        //[hello, world]
        System.out.println(Arrays.toString(words));
    }
}
class WordSplitter {
    public String[] split(String phrase) {
        return phrase.replaceAll(" +"," ").toLowerCase().split(" ");
    }
}
class PunctuationMarkCounterTest {
    public static void main(String[] args) {
        PunctuationMarkCounter counter = new PunctuationMarkCounter();

        //2
        System.out.println(counter.count("Hello, world!"));

        //1
        System.out.println(counter.count("This is Sparta!"));

        //1
        System.out.println(counter.count("End."));
    }
}
class PunctuationMarkCounter {
    public int count(String phrase) {
        int before = phrase.length();
        int after = phrase.replaceAll("[.,!?;:]", "").length();
        int result = before - after;
        return result;
    }
}
class DoubleSpaceCleanerTest {
    public static void main(String[] args) {
        DoubleSpaceCleaner cleaner = new DoubleSpaceCleaner();

        //Hello World
        System.out.println(cleaner.clean("Hello World"));

        //Hello World
        System.out.println(cleaner.clean("       Hello              World          "));
    }
}
class DoubleSpaceCleaner {
    public String clean(String phrase) {
        return phrase.replaceAll("( +)"," ").trim();
    }
    }
class WordFreqCounterTest {
    public static void main(String[] args) {
        WordFreqCounter freqCounter = new WordFreqCounter();

        //0.5
        System.out.println(freqCounter.countFreq("Hello Java", "java"));

        //0
        System.out.println(freqCounter.countFreq("Hello World", "java"));
    }
}
class WordFreqCounter {
    public float countFreq(String phrase, String word) {
        String[] phrasLong = phrase.toLowerCase().split(" +");
        float phrases = phrasLong.length;
        float count = 0.0f;

        for (int i = 0; i < phrasLong.length - 1; i++) {
            String fras = phrasLong[i];
            if (phrasLong[i].equals(word.toLowerCase())) {
                count++;
            }

        }
        return count / phrases;
    }
}
class StringByteWorkerTest {
    public static void main(String[] args) {
        byte[] startBytes = {74, 97, 86, 97};

        //java
        System.out.println(new StringByteWorker().process(startBytes));
    }
}
class StringByteWorker {
    public String process(byte[] bytes) {
        String str = new String(bytes);
        return str.toLowerCase();
    }
}
class ShortWordCounterTest {
    public static void main(String[] args) {
        //1
        System.out.println(new ShortWordCounter().count("Java is great language", 2));

        //2
        System.out.println(new ShortWordCounter().count("Java is great language", 4));

        //3
        System.out.println(new ShortWordCounter().count("Java is great language", 5));

        //4
        System.out.println(new ShortWordCounter().count("Java is great language", 100));
    }
}
class ShortWordCounter {
    public int count(String phrase, int minLength) {
        int cnt = 0;
        String[] words = phrase.split(" ");
        for (String s : words) {
            if (s.length() <= minLength) {
                cnt++;
            }


        }
        return cnt;
    }
}
class PalindromeCounterTest {
    public static void main(String[] args) {
        PalindromeCounter counter = new PalindromeCounter();

        //1
        System.out.println(counter.count("Level done!"));

        //0
        System.out.println(counter.count("No palindromes"));
    }
}
class PalindromeCounter {
    public int count(String phrase) {
        int count = 0;
        String[] words = phrase.split(" ");
        for (int j = 0; j < words.length; j++) {
           String s = words[j];
        }
        StringBuilder reverse;
        for (String s : words) {
            StringBuilder result = new StringBuilder(s).reverse();
            String fuck = result.toString();

            if (s.equalsIgnoreCase(fuck)) {
                count++;
            }

            }

        return count;
    }
    }
class WordDeleterTest {
    public static void main(String[] args) {
        WordDeleter wordDeleter = new WordDeleter();

        //Hello
        System.out.println(wordDeleter.remove("Hello Java", new String[] {"Java"}));

        //This Sparta
        System.out.println(wordDeleter.remove("This is Sparta", new String[] {"is"}));
    }
} class WordDeleter {
    public String remove(String phrase, String[] words) {

        boolean found = false;
        String[] phrases = phrase.split(" ");
        StringBuilder result = new StringBuilder();
        for (String phr : phrases) {
            for (String word : words) {
                if (phr.equals(word)) {
                    found = true;
                    continue;
                }
            }
            if (!found) {
                result.append(phr);
                result.append(" ");
            }
            found = false;
        }
        return result.toString().trim();
    }
}
}
class SensitiveDataSearcherTest {
    public static void main(String[] args) {
        SensitiveDataSearcher searcher = new SensitiveDataSearcher();

        //false
        System.out.println(searcher.isSensitiveDataPresent("Hello world"));

        //true
        System.out.println(searcher.isSensitiveDataPresent("Pass: swordfish"));
    }
}
class SensitiveDataSearcher {
    public boolean isSensitiveDataPresent(String phrase) {
        String[] sens = {"email", "key", "login", "pass"};
        String[] part = phrase.split(" ");
        boolean result = false;
        for (String prt : part)
            for (String sns : sens)
                if (sns.equalsIgnoreCase(prt))
                    return true;

                  return false;
    }
}
class DigitTextTest {
    public static void main(String[] args) {
        //true
        System.out.println(new DigitText().detect("23 50"));

        //false
        System.out.println(new DigitText().detect("Year 1990"));
    }
}
class DigitText{
    public boolean detect(String text){
          for (char c : text.toCharArray())
            if (c < '0' || c > '9')
                return false;
        return true;
    }
}
class SummaryCreatorTest {
    public static void main(String[] args) {
        SummaryCreator summaryCreator = new SummaryCreator();

        //Mars
        String shortPhrase = "Mars";
        System.out.println(summaryCreator.create(shortPhrase));

        //Java is very po...
        String longPhrase = "Java is very popular language";
        System.out.println(summaryCreator.create(longPhrase));
    }
}
class SummaryCreator {
    public String create(String text) {
        String points = "...";
        char [] ch = text.toCharArray();
        if (ch.length <= 15) {
            return text;
        }
        for (int i = 0; i < ch.length; i++) {
            if (ch[15]!=' ') {
                String newText = text.substring(0, 15);
                return newText + points;
            }
        }
        return text.substring(0, 15);
    }
}
class MathDetectorTest {
    public static void main(String[] args) {
        //true
        System.out.println(new MathDetector().isMath("2+2=4"));

        //false
        System.out.println(new MathDetector().isMath("1992 is great year"));

        //false
        System.out.println(new MathDetector().isMath("Venus vs Earth"));
    }
}
class MathDetector {
    public boolean isMath(String text) {
        char[] math = {'=', '+', '-', '*', '/'};
        char[] part = text.toCharArray();
        for (char mth:math){
            for (char prt:part){
                if(mth==prt){
                    return true;
                }
            }
        }
        return false;
    }
}
class AvgWordLengthTest {
    public static void main(String[] args) {
        //6
        System.out.println(new AvgWordLength().count("Launch Rocket"));

        //4.5
        System.out.println(new AvgWordLength().count("Life is strange thing"));
    }
}
class AvgWordLength {
    private float sumChar;
    public double count(String phrase) {
        String[] text = phrase.split(" ");
        for (String word : text){
            sumChar += word.length();
        }
        return sumChar/text.length;
    }
}

        

class DigitExtracterTest {
    public static void main(String[] args) {
        DigitExtracter digitExtracter = new DigitExtracter();

        int[] extracted = digitExtracter.extract("april 5, year 2000");

        //[5, 2, 0, 0, 0]
        System.out.println(Arrays.toString(extracted));
    }
}
class DigitExtracter {
    public int[] extract(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (number(text.substring(i, i + 1))) {
                count++;
            }
        }
        int[] numbers = new int[count];
        count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (number(text.substring(i, i + 1))) {
                numbers[count] = Integer.parseInt(text.substring(i, i + 1));
                count++;
            }
        }
        return numbers;
    }

    private boolean number(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
class BigOrSmallTest {
    public static void main(String[] args) {
        //Small
        System.out.println(new BigOrSmall().calculate("Java"));

        //Big
        System.out.println(new BigOrSmall().calculate("JAVA"));

        //Same
        System.out.println(new BigOrSmall().calculate("jAvA"));
    }
}
class BigOrSmall {
    public String calculate(String text) {

        int uppers=0, lowers=0;

        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)){
                ++uppers;}
             if (Character.isLowerCase(c)){
                ++lowers;}
        }
        if (uppers> lowers){
            return "Big";
        }
        if (lowers>uppers){
            return "Small";
        }
    return "Same";
    }
}
c
class WordCombineTest {
    public static void main(String[] args) {
        //true
        System.out.println(new WordCombine().canCombine("Forest", "tor"));

        //true
        System.out.println(new WordCombine().canCombine("Magic", "Mama"));

        //false
        System.out.println(new WordCombine().canCombine("War", "Piece"));
    }
}
class WordCombine{
    public boolean canCombine(String sourceWord, String targetWord){
String sW = sourceWord.toLowerCase();
String tW = targetWord.toLowerCase();
        char first []  = sW.toCharArray();
        char second [] = tW.toCharArray();
        for (char fr : first) {
            for (char sc : second) {
                if (fr == sc) {
                    return true;
                }
            }
        }
            return false;
            }
        }
class WaterCounterTest {
    public static void main(String[] args) {
        //0.07692307692307693
        System.out.println(new WaterCounter().count("Moon invaders"));

        //0
        System.out.println(new WaterCounter().count("NoWater"));
    }
}
class WaterCounter {
    public double count(String text) {
        double spaceCount = 0;
        double length = text.length();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                spaceCount++;
                      }
           }
     double cnt =   spaceCount / length;
        return cnt;
    }
}
class EmailDetectorTest {
    public static void main(String[] args) {
        //true
        System.out.println(new EmailDetector().isPresent("This email is no-reply@goo.com"));

        //false
        System.out.println(new EmailDetector().isPresent("No email present, but @ there"));
    }
}
class EmailDetector {

    public boolean isPresent(String text) {
        int MINIMUM_LENGTH = 5;

        if (text.length() < MINIMUM_LENGTH) {
            return false;
        }
        if (!text.contains("@")) {
            return false;
        }
        int i = text.indexOf("@");
        if (i < 2 || i > text.length() - 2) {
            return false;
        }
        if (checkIfNotContainsSpace(text, i)) {
            return true;
        }
        return false;
    }

    private static boolean checkIfNotContainsSpace(String text, int i) {
        if (text.charAt(i - 1) != ' ' && text.charAt(i - 2) != ' ' &&
                text.charAt(i + 1) != ' ' && text.charAt(i + 2) != ' ') {
            return true;
        }
        return false;
    }
}

class NameCounterTest {
    public static void main(String[] args) {
        //1
        System.out.println(new NameCounter().count("Mars is great planet"));

        //2
        System.out.println(new NameCounter().count("Moon is near Earth"));

        //0
        System.out.println(new NameCounter().count("SPACE IS GREAT"));
    }
}
class NameCounter {
    public int count(String text) {
        int countName = 0;
        String[] splitted = text.split(" ");
        for (int i = 0; i < splitted.length; i++) {
            if (Character.isUpperCase(splitted[i].charAt(0)) && splitted[i].length() >= 2) {
                if (Character.isLowerCase(splitted[i].charAt(1))) {
                    countName++;
                }
            }
        }
        return countName;
    }
}
class NumberJoiner {
    public String join(int[] numbers) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            builder.append(numbers[i]);
        }
        return builder.toString();
    }
}
class NumberJoinerTest {
    public static void main(String[] args) {
        //102030
        System.out.println(new NumberJoiner().join(new int[] {10, 20, 30}));
    }
}
class PhraseMaker {
    public String join(String[] words) {
    StringBuilder builder = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                builder.append(words[i]);
            }
        StringBuilder builder1 = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() < 3) {
                word = word.toUpperCase();
            } else {
                word = word.toLowerCase();
            }

            builder1.append(word);
            if (i != words.length - 1) {
                builder1.append(" ");
            }
        }
        return builder1.toString();
    }
}
class PhraseMakerTest {
    public static void main(String[] args) {
        String[] words = {"Life", "is", "great", "thing"};

        //life IS great thing
        System.out.println(new PhraseMaker().join(words));
    }
}
class PalindromeDetector {
    public boolean isPalindrome(String word) {
    return word.equalsIgnoreCase(reverse(word));
    }

    private String reverse(String word) {

        StringBuilder stringBuilder = new StringBuilder(word);
        stringBuilder.reverse();

        return stringBuilder.toString();
    }
}
class PalindromeDetectorTest {
    public static void main(String[] args) {
        //false
        System.out.println(new PalindromeDetector().isPalindrome("Java"));

        //true
        System.out.println(new PalindromeDetector().isPalindrome("Noon"));
    }
}
/*class ObjectWorker {
    public String join(Object[] objects) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            builder.append(obj);
            if (i != objects.length - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
  }*/
class ObjectWorker {
    public String join(Object[] objects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];

            if (i != objects.length - 1) {

                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}


class ObjectWorkerTest {
    public static void main(String[] args) {
        Object[] data = {"Hello", 20L, 3.14f, true};

        //Hello 20 3.14 true
        System.out.println(new ObjectWorker().join(data));
    }
}

class NameParser {
    public String parse(String[] names) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < names.length; i++) {
            builder.append(names[i].split(" ")[0]);

            if (i != names.length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}

class NameParserTest {
    public static void main(String[] args) {
        String[] names = {"John Doe", "Bill Mask", "Nigua Joshua"};

        //John, Bill, Nigua
        System.out.println(new NameParser().parse(names));
    }

}





































