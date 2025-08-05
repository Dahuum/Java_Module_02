import java.io.*;
import java.util.Map;
import java.util.jar.Manifest;
import java.util.HashMap;
import java.util.Scanner;

public class Program {
    
    public static void main (String [] args) {
        SignaturesMap signature = new SignaturesMap();
        Scanner scanner = new Scanner(System.in);
        
        signature.loadFromFile("signatures.txt");
        signature.printMap();
        
        while (true) {
            System.out.print("--> ");
            String line = scanner.nextLine();
            
            if (line.equals("42")) break;
            try {
                FileInputStream fis = new FileInputStream(line);
                byte [] buffer = new byte[8];
                int bytesRead = fis.read(buffer);
                
                StringBuilder hexString = new StringBuilder();
                for (int i = 0; i < bytesRead; i++) {
                    String hex = String.format("%02X", buffer[i]);
                    hexString.append(hex).append(" ");
                }
                String sig = hexString.toString().trim();
                System.out.println("signature --> " + sig);
                System.out.println("signature found or not --> " + signature.getFileType(sig));
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
}
/* https://codegym.cc/groups/posts/bufferedreader-and-bufferedwriter */