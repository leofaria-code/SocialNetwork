package Menus;

import java.util.Scanner;

import Utility.VisualElements;
public class MenuMain {
    
    private static final MenuOptions[] menuMain = MenuOptions.values();
    private static final String askMenuOption = "> Digite o caractere ou o número correspondente à opção escolhida:";
    
    static Scanner input = new Scanner(System.in);
    
    public static void printMenuMain() {
        String title = "MENU PRINCIPAL: o que você deseja fazer?";
        VisualElements.printHeader(title);
        System.out.printf("\n|%-3s%-9s %-84s |", " # ", "| OPÇÃO |", "FUNÇÃO");
        VisualElements.printLine('-');
        for (MenuOptions menu : menuMain) {
            System.out.printf("\n| %d |   %s   | %-84s |", menu.getOptNum(), menu.getOptString(), menu.getDescription());
        }
        VisualElements.printLine('=');
    }
    
    public static void getUserChoice() {
        System.out.printf("\n%s ", askMenuOption);
        String optionAtMainMenu = input.nextLine().toUpperCase();
        String msg0 = "Opçao escolhida: ";
        String msg = msg0 + optionAtMainMenu;
        VisualElements.printLine('-');
        VisualElements.printMessage(msg);
        VisualElements.printLine('-');
    }
    
}