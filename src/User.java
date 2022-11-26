import java.util.ArrayList;
import java.util.Scanner;

public class User {
    static int id;
    static String name;
    static String username;
    static String password;
    static ArrayList<Post> posts = new ArrayList<>();
    static ArrayList<UserMenu> userMenuOptions = new ArrayList<>();
    public User (int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    static Scanner input = new Scanner(System.in);
    static String optionAtUserMenu;
    public static void populateUserMenu() {
        userMenuOptions.add(new UserMenu(0, "P", "POSTAR alguma coisa"));
        userMenuOptions.add(new UserMenu(1, "T", "Exibir minha TIMELINE"));
        userMenuOptions.add(new UserMenu(2, "X", "LOGOUT"));
    }
    public static void openUserMenu(int id) {
        System.out.println();
        printUserMenu();
        optionAtUserMenu = input.nextLine().toUpperCase();
        switch (optionAtUserMenu) {
            case "P":
                makeNewPost(id);
                break;
            case "T":
                showMyPosts(id);
                break;
            case "X":
                Main.openMainMenu();
                break;
            default:
                System.out.printf(" Opção '%S' inválida! Tente novamente.", optionAtUserMenu);
        }
        openUserMenu(id);
    }
    private static void printUserMenu() {
        Main.printHead('#');
        String title = "MENU DO USUÁRIO: o que você deseja fazer?";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        Main.printHead('#');
        System.out.printf("\n%-7s %-88s |", "| OPÇÃO |", "FUNÇÃO");
        Main.printLine('-');
        for (UserMenu userMenuOption : userMenuOptions) {
            System.out.printf("\n|   %s   | %-88s |", userMenuOption.option, userMenuOption.function);
        }
        Main.printFoot('=');
        System.out.print("\n Digite o caractere correspondente à opção escolhida: ");
    }
    static void makeNewPost(int id) {
        System.out.print("\n## Novo Post do usuário ##\n");
        System.out.print("Digite o conteúdo: ");
        String content = input.nextLine();
        posts.add(new Post(content));
    }
    static void showMyPosts(int id) {
        System.out.println("Nome: " + Main.users.get(id).name);
        System.out.println("Login: " + username);
        for (Post post : posts) {
            post.printPost();
        }
    }
    public static void printUser(int uid) {
//        System.out.printf("\n| %04d | %-89s |", id, name);
        System.out.printf("\n| %04d | %-89s |", Main.users.get(uid).id, Main.users.get(uid).name);
    }
    public static void powerPrintUser(int uid) {
        System.out.printf("\n| %04d | %-41s | %-21s | %-21s |", Main.users.get(uid).id, Main.users.get(uid).name, Main.users.get(uid).username, Main.users.get(uid).password);
    }
}