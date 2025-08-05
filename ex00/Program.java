import java.io.*;
import java.util.Scanner;

public class Program {
    
    public static void main (String [] args) {
        SignaturesMap signature = new SignaturesMap();
        Scanner scanner = new Scanner(System.in);
        
        signature.loadFromFile("signatures.txt");
        // signature.printMap();
        
        while (true) {
            System.out.print("--> ");
            String line = scanner.nextLine();
            
            if (line.equals("42")) break;
            try (FileInputStream fis = new FileInputStream(line)) {
                byte [] buffer = new byte[8];
                int bytesRead = fis.read(buffer);
                
                StringBuilder hexString = new StringBuilder();
                for (int i = 0; i < bytesRead; i++) {
                    String hex = String.format("%02X", buffer[i]);
                    hexString.append(hex).append(" ");
                }
                
                String sig = hexString.toString().trim();
                boolean found = false;
                for (String knownHex: signature.getAllSignatures()) {
                    if (sig.startsWith(knownHex)) {
                        String fileType = signature.getFileType(knownHex);
                        if (fileType != null) {
                            System.out.println("PROCESSED");
                            try (FileWriter writer = new FileWriter("result.txt", true)) {
                                writer.write(fileType + '\n');
                            }
                            found = true;
                            break;
                        } 
                            
                    } 
                }
                if (!found) System.out.println("UNDEFINED");                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("result.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        scanner.close();
    }
    
}
/* https://codegym.cc/groups/posts/bufferedreader-and-bufferedwriter */