import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static int numWords() throws FileNotFoundException {
        File happyFile = new File("happy.txt");
        Set<String> setWords = new HashSet<String>();
        try (Scanner happyScanner = new Scanner(happyFile)) {
            while (happyScanner.hasNext()) {
                String currWord = happyScanner.next();
                System.out.println(currWord);
                setWords.add(currWord);
            }
        }
        return setWords.size();
    }

    static Map<String, Integer> numTimes() throws FileNotFoundException {
        File happyFile = new File("happy.txt");
        Map<String, Integer> repeatedTimes = new HashMap<String, Integer>();

        try (Scanner happyScanner = new Scanner(happyFile)) {
            while (happyScanner.hasNext()) {
                String currWord = happyScanner.next();

                for (int i = 0; i < currWord.length(); ++i) {
                    if (!isAlphabetic(currWord.charAt(i))) {
                        StringBuffer currBuffer = new StringBuffer(currWord);
                        currBuffer.deleteCharAt(i);
                        currWord = currBuffer.toString();
                    }
                }

                if (repeatedTimes.containsKey(currWord)) {
                    //Repeated words
                    repeatedTimes.replace(currWord, repeatedTimes.get(currWord), repeatedTimes.get(currWord) + 1);
                }
                else {
                    repeatedTimes.put(currWord, 1);
                }
            }
        }
        return repeatedTimes;
    }

    private static boolean isAlphabetic(char charAt) {
        if (charAt == ')' || charAt == '(' || charAt == ',') {
            return false;
        }
        else {
            return true;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        int numberWords = numWords();
//Happy counter
        Map<String, Integer> numberRepeated = numTimes();

        System.out.println(numberWords);

        if (numberRepeated.containsKey("Happy")) {
            System.out.println(numberRepeated.get("Happy"));
        }
        else {
            System.out.println(0);
        }
    }
}
