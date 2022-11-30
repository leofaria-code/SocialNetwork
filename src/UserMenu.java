public class UserMenu {
    String option;
    String function;
    
    final static String USER_MENU_OPTIONS_0 = "#";
    final static String USER_MENU_OPTIONS_1 = "P";
    final static String USER_MENU_OPTIONS_2 = "T";
    final static String USER_MENU_OPTIONS_3 = "F";
    final static String USER_MENU_OPTIONS_4 = "X";
    final static String USER_MENU_OPTIONS_5 = "";
    final static String USER_MENU_OPTIONS_6 = " ";
    final static String[] USER_MENU_OPTIONS = {USER_MENU_OPTIONS_0, USER_MENU_OPTIONS_1, USER_MENU_OPTIONS_2, USER_MENU_OPTIONS_3, USER_MENU_OPTIONS_4};
    
    final static String USER_MENU_FUNCTIONS_0 = "### RESERVADO ###";
    final static String USER_MENU_FUNCTIONS_1 = "POSTAR alguma coisa";
    final static String USER_MENU_FUNCTIONS_2 = "Exibir minha TIMELINE";
    final static String USER_MENU_FUNCTIONS_3 = "Exibir FEED de todos os usuários";
    final static String USER_MENU_FUNCTIONS_4 = "LOGOUT";
    final static String[] USER_MENU_FUNCTIONS = {USER_MENU_FUNCTIONS_0, USER_MENU_FUNCTIONS_1, USER_MENU_FUNCTIONS_2, USER_MENU_FUNCTIONS_3, USER_MENU_FUNCTIONS_4};
    public UserMenu(String option, String function) {
        this.option = option;
        this.function = function;
    }
    public static void populateUserMenu() {
        final UserMenu OPTION_1 = new UserMenu(USER_MENU_OPTIONS[1], USER_MENU_FUNCTIONS[1]);
        final UserMenu OPTION_2 = new UserMenu(USER_MENU_OPTIONS[2], USER_MENU_FUNCTIONS[2]);
        final UserMenu OPTION_3 = new UserMenu(USER_MENU_OPTIONS[3], USER_MENU_FUNCTIONS[3]);
        final UserMenu OPTION_4 = new UserMenu(USER_MENU_OPTIONS[4], USER_MENU_FUNCTIONS[4]);
        User.userMenuOptions.add(OPTION_1);
        User.userMenuOptions.add(OPTION_2);
        User.userMenuOptions.add(OPTION_3);
        User.userMenuOptions.add(OPTION_4);
    }
}