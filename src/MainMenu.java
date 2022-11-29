public class MainMenu {
    String option;
    String function;
    
    final static String MAIN_MENU_OPTIONS_0 = "#";
    final static String MAIN_MENU_OPTIONS_1 = "E";
    final static String MAIN_MENU_OPTIONS_2 = "C";
    final static String MAIN_MENU_OPTIONS_3 = "L";
    final static String MAIN_MENU_OPTIONS_4 = "X";
    final static String MAIN_MENU_OPTIONS_5 = "";
    final static String MAIN_MENU_OPTIONS_6 = " ";
    final static String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTIONS_0, MAIN_MENU_OPTIONS_1, MAIN_MENU_OPTIONS_2, MAIN_MENU_OPTIONS_3, MAIN_MENU_OPTIONS_4};

    final static String MAIN_MENU_FUNCTIONS_0 = "### RESERVADO ###";
    final static String MAIN_MENU_FUNCTIONS_1 = "ENTRAR    com username e senha";
    final static String MAIN_MENU_FUNCTIONS_2 = "CADASTRAR novo usuário";
    final static String MAIN_MENU_FUNCTIONS_3 = "LISTAR    usuários cadastrados";
    final static String MAIN_MENU_FUNCTIONS_4 = "FECHAR    a aplicação sem salvar os dados!";
    final static String[] MAIN_MENU_FUNCTIONS = {MAIN_MENU_FUNCTIONS_0, MAIN_MENU_FUNCTIONS_1, MAIN_MENU_FUNCTIONS_2, MAIN_MENU_FUNCTIONS_3,MAIN_MENU_FUNCTIONS_4};
    
    public MainMenu (String option, String function) {
        this.option = option;
        this.function = function;
    }
    public static void populateMainMenu() {
        final MainMenu OPTION_1 = new MainMenu(MAIN_MENU_OPTIONS[1], MAIN_MENU_FUNCTIONS[1]);
        final MainMenu OPTION_2 = new MainMenu(MAIN_MENU_OPTIONS[2], MAIN_MENU_FUNCTIONS[2]);
        final MainMenu OPTION_3 = new MainMenu(MAIN_MENU_OPTIONS[3], MAIN_MENU_FUNCTIONS[3]);
        final MainMenu OPTION_4 = new MainMenu(MAIN_MENU_OPTIONS[4], MAIN_MENU_FUNCTIONS[4]);
        Main.mainMenu.add(OPTION_1);
        Main.mainMenu.add(OPTION_2);
        Main.mainMenu.add(OPTION_3);
        Main.mainMenu.add(OPTION_4);
    }
}