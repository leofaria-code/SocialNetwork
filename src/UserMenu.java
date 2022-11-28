public class UserMenu {
    String option;
    String function;
    public UserMenu(String option, String function) {
        this.option = option;
        this.function = function;
    }
    public static void populateUserMenu() {
        User.userMenuOptions.add(new UserMenu("P", "POSTAR alguma coisa"));
        User.userMenuOptions.add(new UserMenu("T", "Exibir minha TIMELINE"));
        User.userMenuOptions.add(new UserMenu("X", "LOGOUT"));
    }
}