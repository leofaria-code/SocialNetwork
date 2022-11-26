public class MainMenu {
    int id;
    String option;
    String function;
    public MainMenu (int id, String option, String function) {
        this.id = id;
        this.option = option;
        this.function = function;
    }
    public static void populateMainMenu() {
        Main.mainMenu.add(new MainMenu(0, "E", "ENTRAR    com seus dados de login"));
        Main.mainMenu.add(new MainMenu(1, "C", "CADASTRAR novo usuário"));
        Main.mainMenu.add(new MainMenu(2, "L", "LISTAR    usuários cadastrados"));
        Main.mainMenu.add(new MainMenu(3, "X", "FECHAR    a aplicação sem salvar os dados!"));
    }
}