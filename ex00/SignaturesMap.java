import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class SignaturesMap {
    private  Map<String, String> signatureMap = new HashMap<>();
    
    public void loadFromFile(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String [] splited = line.split(",");
                if (splited.length != 2) continue;
                signatureMap.put(splited[1].trim(), splited[0].trim());
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public String getFileType(String signature) {
        return signatureMap.get(signature);
    }
    
    public Set<String> getAllSignatures() {
        return signatureMap.keySet();
    }
    
    public void printMap() {
        signatureMap.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}