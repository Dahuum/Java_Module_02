import java.io.*;
import java.util.*;

public class Program {
    private static  Set<String> all = new TreeSet<>();
    
    public static Vector<Integer> createFrequencyVector(WordExtractor L) {
        Vector<Integer> v = new Vector<>();
        int count = 0;
        
        for (String word: all) {
            for (String Lword: L.allWords) {
                if (Lword.equals(word)) count++;
            }
            v.add(count);
            count = 0;
        }
        
        return v;
    }
    
    public static double dotProduct(Vector<Integer> vectorA, Vector<Integer> vectorB) {
        double sum = 0.0;
        for (int i = 0; i < vectorA.size(); i++)
            sum += vectorA.get(i) * vectorB.get(i);
        return sum;
    }
    
    public static double magnitude(Vector<Integer> vector) {
        double sumOfSquares = 0.0;
        for (int i = 0; i < vector.size(); i++) 
            sumOfSquares += vector.get(i) * vector.get(i);
        return Math.sqrt(sumOfSquares);
    }
    
    public static double cosineSimilarity (Vector<Integer> vectorA, Vector<Integer> vectorB) {
        double dotProd = dotProduct(vectorA, vectorB);
        double magA = magnitude(vectorA);
        double magB = magnitude(vectorB);
        
        if (magA == 0.0 || magB == 0.0) return 0.0;
        
        return dotProd / (magA * magB);
    }
    
    public static void main ( String [] args ) {
        if (args.length != 2) {
            System.err.println("Usage: java Program InputA.txt InputB.txt"); System.exit(-1);
        }
        
        WordExtractor A = new WordExtractor(), B = new WordExtractor();
        A.getAllWords(args[0]); B.getAllWords(args[1]);
        
        all.addAll(A.allWords); all.addAll(B.allWords);
        
        Vector<Integer> vA = createFrequencyVector(A), vB = createFrequencyVector(B);
        
        System.out.printf("Similarity = %.2f%n", cosineSimilarity(vA, vB));
        try (FileWriter writer = new FileWriter("dictionary.txt")) {
            writer.write(all.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}