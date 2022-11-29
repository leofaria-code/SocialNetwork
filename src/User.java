import java.util.ArrayList;
import java.util.Scanner;
public class User {
    int id;
    String name;
    String username;
    String password;
    Post userPosts;
    
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
    public static void openUserMenu(int idUser) {
        Main.cleanConsole();
        welcomeUser(idUser);
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
                Main.openMainMenu();
                break;
            case "":
            case " ":
                System.out.printf("| Opções '%S' ou '%S' são inválidas! ", "espaço", "vazio");
                Main.followUp("tentar novamente");
                break;
            default:
                System.out.printf(" Opção '%S' inválida! Tente novamente.", optionAtUserMenu);
                break;
        } openUserMenu(idUser);
    }
    public static void welcomeUser(int userID){
        Main.printLine('*');
        String msg0 = "Bem vindo ";
        String id = Main.users.get(userID).name.toUpperCase();
        String concat0 = msg0 + id;
        String msg1 = "SINQUIA #dev_makers2, Let's Code by ADA - ";
        String concat1 = msg1 + TimeStamp.getDateTime();
        System.out.printf("\n* %-33s%63s *", concat0, concat1);
        Main.printLine('*');
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
        System.out.printf("\n%s ", Main.askMenuOption);
    }
    static void makeNewPost(int idUser) {
        idPost ++;
        Main.printLine('#');
        String title = "Criação de novo POST";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        Main.printLine('=');
        System.out.printf("\n> Post Nº %03d - usuário: %s ", idPost, Main.users.get(idUser).username);
        System.out.print("\n> Digite o conteúdo: ");
        String content = input.nextLine();
        String timestamp = TimeStamp.getDateTime();
        posts.add(new Post(idUser,idPost, timestamp, content));
        System.out.printf("\n| Post Nº %03d - usuário: %s - %s - %s ", posts.get(idPost).idPost, Main.users.get(idUser).username, posts.get(idPost).timestamp, posts.get(idPost).content);
        Main.followUp("publicar o post e voltar ao MENU DO USUÁRIO");
    }
    static void showMyPosts(int id) {
        Main.cleanConsole();
        Main.printLine('#');
        System.out.printf("\n NOME: %s - USERNAME: %s ", Main.users.get(id).name, Main.users.get(id).username);
        Main.printLine('=');
        for (Post post : posts) {
            post.printPosts();
            Main.printLine('-');
        }
        Main.printLine('#');
        Main.followUp("para VOLTAR ao MENU do USUÁRIO");
    }
    public static void printUser(int uid) {
        System.out.printf("\n| %04d | %-89s |", Main.users.get(uid).id, Main.users.get(uid).name);
    }
    public static void powerPrintUser(int uid) {
        System.out.printf("\n| %04d | %-41s | %-21s | %-21s |", Main.users.get(uid).id, Main.users.get(uid).name, Main.users.get(uid).username, Main.users.get(uid).password);
    }
}