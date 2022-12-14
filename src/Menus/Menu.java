package Menus;

public class Menu {
    static void printMainMenu() {
        String title = "MENU PRINCIPAL: o que você deseja fazer?";
//        printHeader(title);
        System.out.printf("\n%-7s %-88s |", "| OPÇÃO |", "FUNÇÃO");
//        printLine('-');
        for (MainMenu mainMenuOption : mainMenu) {
            System.out.printf("\n|   %s   | %-88s |", mainMenuOption.option, mainMenuOption.function);
        }
//        printLine('=');
        System.out.printf("\n%s ", askMenuOption);
    }
}
