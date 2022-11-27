
import java.util.ArrayList;
import java.util.Scanner;
public class User {
    int id;
    String name;
    String username;
    String password;
    static ArrayList<UserMenu> userMenuOptions = new ArrayList<>();
    static ArrayList<Post> posts = new ArrayList<>();
    static int idPost = -1;
//    static ArrayList<String> follows = new ArrayList<>();
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
    public static void openUserMenu(int idUser) {
        printUserMenu();
        optionAtUserMenu = input.nextLine().toUpperCase();
        switch (optionAtUserMenu) {
            case "P":
                makeNewPost(idUser);
                break;
            case "T":
                showMyPosts(idUser);
                break;
            case "X":
                Main.followUp("fazer logout e voltar ao MENU INICIAL");
                Main.openMainMenu();
                break;
            default:
                System.out.printf(" Opção '%S' inválida! Tente novamente.", optionAtUserMenu);
                break;
        } openUserMenu(idUser);
    }
    private static void printUserMenu() {
        Main.printLine('#');
        String title = "MENU DO USUÁRIO: o que você deseja fazer?";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        Main.printLine('#');
        System.out.printf("\n%-7s %-88s |", "| OPÇÃO |", "FUNÇÃO");
        Main.printLine('-');
        for (UserMenu userMenuOption : userMenuOptions) {
            System.out.printf("\n|   %s   | %-88s |", userMenuOption.option, userMenuOption.function);
        }
        Main.printLine('=');
        System.out.printf("\n %s ", Main.askOption);
    }
    static void makeNewPost(int idUser) {
        idPost ++;
        System.out.printf("\n ## Post Nº %03d - usuário: %s ##\n", idPost, Main.users.get(idUser).username);
        System.out.print(" - Digite o conteúdo: ");
        String content = input.nextLine();
        String timestamp = TimeStamp.getDateTime();
        posts.add(new Post(idPost, timestamp, content));
        System.out.printf(" Post Nº %03d - usuário: %s - %s - %s ", idPost, Main.users.get(idUser).username, timestamp, content);
        Main.followUp("publicar o post");
        input.nextLine();
    }
    static void showMyPosts(int id) {
        Main.printLine('#');
        System.out.printf("\n NOME: %s - USERNAME: %s ", Main.users.get(id).name, Main.users.get(id).username);
        for (Post post : posts) {
            post.printPosts();
        }
        Main.printLine('#');
    }
    public static void printUser(int uid) {
//        System.out.printf("\n| %04d | %-89s |", id, name);
        System.out.printf("\n| %04d | %-89s |", Main.users.get(uid).id, Main.users.get(uid).name);
    }
    public static void powerPrintUser(int uid) {
        System.out.printf("\n| %04d | %-41s | %-21s | %-21s |", Main.users.get(uid).id, Main.users.get(uid).name, Main.users.get(uid).username, Main.users.get(uid).password);
    }
}