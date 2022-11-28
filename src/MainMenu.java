public class MainMenu {
    String option;
    String function;
    public MainMenu (String option, String function) {
        this.option = option;
        this.function = function;
    }
    public static void populateMainMenu() {
        Main.mainMenu.add(Main.OPTION_1);
        Main.mainMenu.add(Main.OPTION_2);
        Main.mainMenu.add(Main.OPTION_3);
        Main.mainMenu.add(Main.OPTION_4);
    }
}