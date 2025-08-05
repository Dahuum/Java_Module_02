public class Program {

    public static void main (String [] args) {
        Menu mainMenu = new Menu();
        
        if (args.length == 1) {
            String [] param = args[0].split("=", 2);
            if (!param[0].equals("--current-folder")) {
                System.err.println("Program: missing operand");
                System.err.println("Usage: java Program --current-folder=/path/to/folder");
                System.exit(-1);
            }
            mainMenu.setDirectory(param[1]);
        } else {
            System.err.println("Program: missing operand");
            System.exit(-1);
        }
        mainMenu.startMenu();
    }
	
}