import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Menu {

    private Path currentPath;
    private Scanner scanner;

    public void setDirectory(String path) {
        currentPath = Paths.get(path);
        if (!Files.exists(currentPath) || !Files.isDirectory(currentPath)) {
            System.err.println("Program: " + path + ": No such directory");
            System.exit(1);
        }
    }

    public void handleCD(String targetFolder) {
        Path newPath = currentPath.resolve(targetFolder).normalize();
        
        if (Files.exists(newPath) && Files.isDirectory(newPath)) currentPath = newPath;
        else System.err.println("cd " + newPath + ":  No such file or directory");
    }
    
    public void handleMV(String source, String destination) {
        try {
            Path src = currentPath.resolve(source).normalize();
            Path des = currentPath.resolve(destination).normalize();
            
            if (!Files.exists(src)) {
                System.err.println("Source file not found: " + source);
                return;
            }
            
            if (Files.exists(des) && Files.isDirectory(des)) {
                des = des.resolve(src.getFileName());

                if (Files.exists(des)) {
                    System.err.println("Destination already exists: " + des.getFileName());
                    return;
                }
            }
            
            
            Files.move(src, des);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void handleLS() {
        try {
            Files.list(currentPath).forEach(file -> {
                try {
                    String name = file.getFileName().toString();
                    long sizeKB = Files.size(file) / 1024;
                   System.out.println(name + " " + sizeKB + " KB"); 
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
     
    public void startMenu() {
        scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println(currentPath);
            
            System.out.print("--> ");
            String cmd = scanner.nextLine();
            
            if (cmd.equals("exit")) break;
            
            else if (cmd.equals("ls")) handleLS();
            
            else if (cmd.startsWith("cd")) {
                String [] cmds = cmd.split(" ");
                if (cmds.length != 2) {
                    System.out.println("Error: Wrong arguments\n");
                    continue ;
                }
                handleCD(cmds[1]);
            }
            
            else if (cmd.startsWith("mv")) {
                String [] cmds = cmd.split(" ");
                if (cmds.length != 3) {
                    System.out.println("Error: Wrong arguments\n");
                    continue ;
                }
                handleMV(cmds[1], cmds[2]);
            }
            
            else {
                System.out.println("Error: Wrong command\n");
                continue;
            }
        }
    }
}