import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static final int PRINT_width = 100;                    // delimita a largura de impressão => WIDTH = 100
    static ArrayList<MainMenu> mainMenu = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
//    static ArrayList<Post> postsAll = new ArrayList<>();
    static String usernameInput;
    static String passwordInput;
    static String usernameNotTaken;
    static int usersCounter;
    static int idUser = -1;
    static int idVerified = -1;
    static int idPassword = -1;
    static final String askMenuOption = "> Digite o caractere correspondente à opção escolhida:";  // msg a ser exibida nos MENUS
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        users.add(User.ADMIN);         // insere o ADMIN na posição '0' da ArrayList 'users'
        MainMenu.populateMainMenu();   // popula o MENU PRINCIPAL com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        UserMenu.populateUserMenu();   // popula o MENU DO USUÁRIO com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        openMainMenu();
    }
    public static void openMainMenu() {
        cleanConsole();
        welcomeStrange();
        printMainMenu();
        String optionAtMainMenu = input.nextLine().toUpperCase();
        switch (optionAtMainMenu) {
            case "0":
            case "ADM":
            case MainMenu.MAIN_MENU_OPTIONS_0:
                powerShowAllUsers();   // método SECRETO que imprime ID, NOME, USERNAME e PASSWORD dos usuários cadastrados
                break;
            case "1":
            case MainMenu.MAIN_MENU_OPTIONS_1:
                signIn();
                break;
            case "2":
            case MainMenu.MAIN_MENU_OPTIONS_2:
                usersCounter++;
                createNewUser();
                break;
            case "3":
            case MainMenu.MAIN_MENU_OPTIONS_3:
                showAllUsers();
                break;
            case "4":
            case MainMenu.MAIN_MENU_OPTIONS_4:
                close();
                break;
            case MainMenu.MAIN_MENU_OPTIONS_5:
            case MainMenu.MAIN_MENU_OPTIONS_6:
                String msg = "Opções 'ESPAÇO' ou 'VAZIO' são inválidas!";
//                System.out.printf("\n%s %-94s %3s", "|", msg, "|");
                System.out.printf("%s %-94s %3s", "|", msg, "|");
                followUp();
                break;
            default:
                String msg0 = "Opção ";
                String msg1 = " inválida! Tente novamente";
                String concat = msg0 + optionAtMainMenu + msg1;
                System.out.printf("\n%s %-94s %3s", "|", concat, "|");
                followUp();
        } openMainMenu();
    }
    static void printMainMenu() {
        printLine('#');
        String title = "MENU PRINCIPAL: o que você deseja fazer?";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printLine('#');
        System.out.printf("\n%-7s %-88s |", "| OPÇÃO |", "FUNÇÃO");
        printLine('-');                              // impressão das opções 'públicas' do MENU PRINCIPAL
        for (MainMenu mainMenuOption : mainMenu) {
            System.out.printf("\n|   %s   | %-88s |", mainMenuOption.option, mainMenuOption.function);
        }
        printLine('=');
        System.out.printf("\n%s ", askMenuOption);
    }
    static void createNewUser() {
        int newId;
        newId = usersCounter;
        printLine('#');
        String title = " CADASTRO de novo usuário ";
        System.out.printf("\n%s %-94s %3s", "|", title, "|");
        printLine('=');
        System.out.print("\n> Digite seu nome: ");
        String newName = input.nextLine();
        String newUsername = askNewUsername();
        String newPassword = askNewPassword();
        users.add(new User(newId, newName, newUsername, newPassword));
        printLine('#');
        System.out.printf("\n| %-4s | %-41s | %-21s | %-21s |", " ID ", "NOME", "USERNAME", "PASSWORD");
        printLine('-');
        System.out.printf("\n| %04d | %-41s | %-21s | %-21s |", Main.users.get(newId).id, Main.users.get(newId).name, Main.users.get(newId).username, Main.users.get(newId).password);
        printLine('#');
        System.out.print("\n! Cadastrado com sucesso !\n");
        System.out.print("\n> Tecle ENTER para retornar ao MENU PRINCIPAL ");
        input.nextLine();
    }
    
    private static String askNewPassword() {
        System.out.print("> Digite sua nova senha: ");
        passwordInput = input.next();
        input.nextLine();
        System.out.print("> Digite novamente sua senha: ");
        String passwordInputCheck = input.next();
        input.nextLine();
        if (!passwordInputCheck.equals(passwordInput)) {
            String text = "As senhas digitadas não coincidem. Cadastre nova senha!";
            System.out.printf("\n%s %-94s %3s", "|",  text, "|");
            System.out.println();
            askNewPassword();
        }
        return passwordInput;
    }
    private static String askNewUsername() {
        System.out.print("> Digite um novo username: ");
        usernameInput = input.nextLine();
        if (verifyNewUsername(usernameInput)) {
            askNewUsername();
        } else {
            usernameNotTaken = usernameInput;
        }
        return usernameNotTaken;
    }
    static void signIn() {
        printLine('#');
        String title = "VERIFICAÇÃO de cadastro";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printLine('=');
        User.openUserMenu(verifyPassword(verifyUsername()));
    }
    static boolean verifyNewUsername(String usernameToVerify) {
        boolean validUser = false;
        for (idUser = 0; idUser < users.size(); idUser++) {
            if (usernameToVerify.equals(users.get(idUser).username)) {
                validUser = true;
                idVerified = idUser;
            }
        }
        if (validUser) {
            printLine('+');
            String okUser0 = "Usuário <<< ";
            String okUser1 = " >>> já cadastrado!";
            String concatUser = okUser0 + users.get(idVerified).username + okUser1;
            System.out.printf("\n| %-96s |", concatUser);
            String okID = "ID do Usuário: ";
            String concatID = okID + users.get(idVerified).id;
            System.out.printf("\n| %-96s |", concatID);
            String text = "Tente outro username";
            System.out.printf("\n%s %-94s %3s", "|",  text, "|");
        } else {
            String text = "Username disponível! ";
            System.out.printf("\n%s %-94s %3s", "|",  text, "|");
            followUp();
        }
        System.out.println();
        return (validUser);
    }
    static int verifyUsername() {
        idUser = -1;
        System.out.print("\n| Digite seu username: ");
        usernameInput = input.next();
        input.nextLine();
        String usernameToVerify  = usernameInput;
        boolean validUser = false;
        for (idUser = 0; idUser < users.size(); idUser++) {
            if (usernameToVerify.equals(users.get(idUser).username)) {
                validUser = true;
                idVerified = idUser;
            }
        }
        if (validUser) {
            printLine('+');
            String okUser0 = "Usuário <<< ";
            String okUser1 = " >>> cadastrado";
            String concatUser = okUser0 + users.get(idVerified).username + okUser1;
            System.out.printf("\n| %-96s |", concatUser);
            String okID = "ID do Usuário: ";
            String concatID = okID + users.get(idVerified).id;
            System.out.printf("\n| %-96s |", concatID);
        } else {
            System.out.print("| Usuário não cadastrado! ");
            verifyUsername();
        }
        System.out.println();
        return (idVerified);
    }
    static int verifyPassword(int p) {
        printLine('#');
        String title = "VERIFICAÇÃO de senha";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printLine('-');
        String concat = "USERNAME: " + users.get(p).username;
        System.out.printf("\n%s %-94s %3s", "|", concat, "|");
        printLine('=');
        System.out.print("\n| Digite sua senha: ");
        String passwordInput = input.next();
        input.nextLine();
        boolean validPassword = false;
        idPassword = -1;
        if (!passwordInput.equals(users.get(p).password)) {
            printLine('!');
            System.out.print("\n Acesso NEGADO!!! Tente novamente! \n");
            printLine('!');
        } else {
            validPassword = true;
            idPassword = p;
        }
        if (validPassword) {
            printLine('+');
            String okPass0 = "Senha do usuário <<< ";
            String okPass1 = " >>> correta!";
            String concatPass = okPass0 + users.get(idPassword).username + okPass1;
            System.out.printf("\n| %-96s |", concatPass);
            followUp();
        } else {
            verifyPassword(p);
        }
        return idPassword;
    }
    private static void close() {
        printLine('#');
        System.out.printf("\n| " + "%-96s" + " |", "O programa será fechado e os dados não serão salvos!!!!!!!");
        printLine('#');
        System.out.printf("\n%s " + "%s ", "| Digite SAIR para confirmar", "ou tecle ENTER para voltar ao MENU PRINCIPAL:");
        String exit = input.nextLine().toUpperCase();
        if (exit.equals("SAIR")) {
            System.exit(0);
        } else {
            openMainMenu();
        }
    }
    static void showAllUsers() {
        printLine('#');
        System.out.printf("\n| %-4s | %-89s |", " ID ", "NOME");
        printLine('-');
        for (int k = 0; k < users.size() ; k++) {
            User.printUser(k);
        }
        printLine('#');
        followUp();
    }
    private static void powerShowAllUsers() {
        admPassword();
        printLine('#');
        System.out.printf("\n| %-4s | %-41s | %-21s | %-21s |", " ID ", "NOME", "USERNAME", "PASSWORD");
        printLine('-');
        for (int k = 0; k < users.size() ; k++) {
            User.powerPrintUser(k);
        }
        printLine('#');
        followUp("retornar ao MENU PRINCIPAL");
    }
    private static void admPassword() {
        final String admPassword = users.get(0).password;
        String admPassTry;
        System.out.print("> Digite a senha de ADMINISTRADOR: ");
        admPassTry = input.nextLine();
        if (!admPassTry.equals(admPassword)) {
            System.out.print("\n Senha inválida! \n !!! ACESSO NEGADO!!! ");
            followUp("voltar a um local seguro!");
            openMainMenu();
        } else {
            printLine('+');
            System.out.println("  Senha CORRETA!!! ");
        }
        followUp();
    }
    public static void welcomeStrange(){
        printLine('*');
        String msg = "Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA";
        System.out.printf("\n* %-75s %10s *", msg, TimeStamp.getDateTime()); // imprime SAUDAÇÃO com DATA e HORA atuais
        printLine('*');
    }
    static void followUp (String msg){
        String text = "Tecle ENTER para ";
        String concat = text + msg;
        System.out.printf("\n%s %-94s %3s", "|",  concat, ">");
        input.nextLine();
    }
    static void followUp (){
        String text = "Tecle ENTER";
        System.out.printf("\n%s %-94s %3s", ">",  text, ">");
        input.nextLine();
    }
    static void printLine(char c){
        System.out.println();
        for (int i = 0; i < PRINT_width; i++) {
            System.out.printf("%c", c);
        }
    }
    public static void cleanConsole(){
        for (int i = 0; i < 13; ++i)
            System.out.println();
    }
}