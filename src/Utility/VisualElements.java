package Utility;

public class VisualElements {
    static final int PRINT_width = 100;                    // delimita a largura de impressão => WIDTH = 100
    
    static void printTitle(String msg){
        printLine('=');
        printMessage(msg);
        printLine('=');
    }
    public static void printHeader(String msg){
        printLine('#');
        printMessage(msg);
        printLine('#');
    }
    public static void printLine(char c){
        System.out.println();
        for (int i = 0; i < PRINT_width; i++) {
            System.out.printf("%c", c);
        }
    }
    public static void printMessage(String msg){
        System.out.printf("\n%s %-94s %3s", "|", msg, "|");
    }
    public static void welcomeStrange(){
        String msg = "Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA - V2.00";
        printHeader(msg);
    }
    public static void clearConsole(){
        for (int i = 0; i < 13; ++i)
            System.out.println();
    }
}
