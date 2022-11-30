import java.util.ArrayList;
public class User {
    final static User ADMIN = new User(0,"ADM", "admin", "admin"); // super usuário, privilégios de ADMINISTRADOR
    static ArrayList<UserMenu> userMenuOptions = new ArrayList<>();
    int id;
    String name;
    String username;
    String password;
    static ArrayList<Post> userPosts = new ArrayList<>();
//    static ArrayList<String> follows = new ArrayList<>();
    static int idPost = -1;
    static int userPostsCounter;
    public User (int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    public static void openUserMenu(int iU) {
        Main.cleanConsole();
        welcomeUser(iU);
        printUserMenu();
        String optionAtUserMenu = Main.input.nextLine().toUpperCase();
        switch (optionAtUserMenu) {
            case "1":
            case UserMenu.USER_MENU_OPTIONS_1:
                idPost ++;
                userPostsCounter++;
                userMakeNewPost(iU);
                Main.printLine('.');
                Post.printFormatedPost(idPost);
                Main.printLine('.');
                Main.followUp();
                break;
            case "2":
            case "E":
            case UserMenu.USER_MENU_OPTIONS_2:
//                printThisUserPosts(iU,iP);
                Main.followUp();
                break;
            case "3":
            case UserMenu.USER_MENU_OPTIONS_3:
//                showAllPosts();
                Main.followUp();
                break;
            case "4":
            case UserMenu.USER_MENU_OPTIONS_4:
                Main.openMainMenu();
                break;
            case UserMenu.USER_MENU_OPTIONS_5:
            case UserMenu.USER_MENU_OPTIONS_6:
                String msg = "Opções 'ESPAÇO' ou 'VAZIO' são inválidas!";
                System.out.printf("\n%s %-94s %3s", "|", msg, "|");
                Main.followUp();
                break;
            default:
                String msg0 = "Opção '";
                String msg1 = "' inválida! Tente novamente";
                String concat = msg0 + optionAtUserMenu + msg1;
                System.out.printf("\n%s %-94s %3s", "|", concat, "|");
                Main.followUp();
        } openUserMenu(iU);
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
    static void userMakeNewPost(int iU) {
        int newPostId;
        newPostId = userPostsCounter;
        Main.printLine('#');
        String msg = "Criação de novo POST de ";
        String name = Main.users.get(iU).name.toUpperCase();
        String concat = msg + name;
        System.out.printf("\n%s %-94s %3s", "|",  concat, "|");
        Main.printLine('=');
        System.out.printf("\n> Post Nº %03d - usuário: %s ", newPostId, Main.users.get(iU).username);
        String timestamp = TimeStamp.getDateTime();
        System.out.print("\n> Digite o conteúdo: ");
        String content = Main.input.nextLine();
        userPosts.add(new Post(newPostId, timestamp, content, iU));
    }
    public static void printUserTimeline(int iU, int iP) {
        Main.printLine('#');
        String msg = "Usuário: ";
        String concat = msg + Main.users.get(iU).username;
        System.out.printf("\n%s %-94s %3s", "|", concat, "|");
        Main.printLine('_');
        for (int i = 0; i < userPosts.size(); i++) {
            Post.printFormatedPost(iP);
        }
//        for (int iP = 0; iP < userPosts.size(); iP++) {
//            Post.printFormatedPost(iP);
//            Main.printLine('.');
//        }
    }
    public static void printUser(int iU) {
        System.out.printf("\n| %04d | %-89s |", Main.users.get(iU).id, Main.users.get(iU).name);
        Main.printLine('.');
    }
    public static void powerPrintUser(int iU) {
        System.out.printf("\n| %04d | %-41s | %-21s | %-21s |", Main.users.get(iU).id, Main.users.get(iU).name, Main.users.get(iU).username, Main.users.get(iU).password);
        Main.printLine('.');
    }
    public static void welcomeUser(int iU){
        Main.printLine('*');
        String msg0 = "Bem vindo ";
        String id = Main.users.get(iU).name.toUpperCase();
        String concat0 = msg0 + id;
        String msg1 = "SINQUIA #dev_makers2, Let's Code by ADA - ";
        String concat1 = msg1 + TimeStamp.getDateTime();
        System.out.printf("\n* %-33s%63s *", concat0, concat1);
        Main.printLine('*');
    }
}