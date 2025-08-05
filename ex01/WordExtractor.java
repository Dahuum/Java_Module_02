import java.util.*;
import java.io.*;


public class WordExtractor {

    public List<String> allWords = new ArrayList<>();
    
    public void getAllWords(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String [] allW = line.split("[^a-zA-Z]+");
                for (String word: allW) {
                    if (!word.isEmpty()) allWords.add(word.toLowerCase());
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    
    public void printAllWordsInline() {
        System.out.println(allWords);
    }

	
}