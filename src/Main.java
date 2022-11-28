import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static final int PRINT_width = 100;                                                            // delimita a largura de impressão => WIDTH = 100
    static final String askMenuOption = "> Digite o caractere correspondente à opção escolhida:";      // msg a ser exibida nos MENUS
    final static User ADMIN = new User(0,"ADM", "admin", "admin"); // super usuário, privilégios de ADMINISTRADOR
    final static String opt1 = "E";
    final static MainMenu OPTION_1 = new MainMenu(opt1, "ENTRAR    com seus dados de login");
    final static String opt2 = "C";
    final static MainMenu OPTION_2 = new MainMenu(opt2, "CADASTRAR novo usuário");
    final static String opt3 = "L";
    final static MainMenu OPTION_3 = new MainMenu(opt3, "LISTAR    usuários cadastrados");
    final static String opt4 = "X";
    final static MainMenu OPTION_4 = new MainMenu(opt4, "FECHAR    a aplicação sem salvar os dados!");
    static ArrayList<MainMenu> mainMenu = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static int id;
    static Scanner input = new Scanner(System.in);
    static String optionAtMainMenu;
    static String usernameInput;
    static int idUser = -1;    // inicia contador pra verificar username
    static int idVerified = -1;// inicia contador pra verificar username
    public static void main(String[] args) {
        users.add(ADMIN);              // insere o ADMIN na posição '0' da ArrayList
        MainMenu.populateMainMenu();   // popula o MENU PRINCIPAL com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        UserMenu.populateUserMenu();       // popula o MENU DO USUÁRIO com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        openMainMenu();
    }
    public static void openMainMenu() {
        cleanConsole();
        welcomeStrange();
        printMainMenu();
        optionAtMainMenu = input.nextLine().toUpperCase();
        switch (optionAtMainMenu) {
            case "1":
            case opt1:
                signIn();
                break;
            case "2":
            case opt2:
                id++;
                createNewUser();
                break;
            case "3":
            case opt3:
                showAllUsers();
                break;
            case "4":
            case opt4:
                close();
                break;
            case "":
            case " ":
                System.out.printf("| Opções '%S' ou '%S' são inválidas! ", "espaço", "vazio");
                followUp("tentar novamente");
                break;
            case "ADM":
                powerShowAllUsers();   // método SECRETO que imprime ID, NOME, USERNAME e PASSWORD dos usuários cadastrados
                break;
            default:
                System.out.printf(" Opção '%S' inválida! Tente novamente.", optionAtMainMenu);
                followUp("tentar novamente");
                break;
        } openMainMenu();
    }
    public static void welcomeStrange(){
        printLine('*');
        String msg = "Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA";
        System.out.printf("\n* %-75s %10s *", msg, TimeStamp.getDateTime()); // imprime SAUDAÇÃO com DATA e HORA atuais
        printLine('*');
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
        printLine('#');
        String title = " CADASTRO de novo usuário ";
        System.out.printf("\n%s %-94s %3s", "|", title, "|");
        printLine('=');
        int newId;
        newId = id;
        System.out.print("\n> Digite seu nome: ");
        String newName = input.nextLine();
        System.out.print("> Digite seu username: ");
        String newUsername = input.nextLine();         // verifyUsername
        System.out.print("> Digite sua senha: ");
        String newPassword = input.nextLine();         // verifyPassword
        users.add(new User(newId, newName, newUsername, newPassword));
        printLine('#');
        System.out.printf("\n| %-4s | %-41s | %-21s | %-21s |", " ID ", "NOME", "USERNAME", "PASSWORD");
        printLine('-');
        System.out.printf("\n| %04d | %-41s | %-21s | %-21s |", newId, newName, newUsername, newPassword);
        printLine('#');
        System.out.print("\n Cadastrado com sucesso!\n");
        System.out.print("\n Tecle ENTER para retornar ao MENU PRINCIPAL ");
        input.nextLine();
    }
    static void signIn() {
        printLine('#');
        String title = "VERIFICAÇÃO de cadastro";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printLine('=');
        User.openUserMenu(verifyPassword(verifyUsername()));
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
            followUp("fazer login nessa conta");
        } else {
            System.out.print("| Usuário não cadastrado! ");
            verifyUsername();
        }
        return (idVerified);
    }
    static int verifyPassword(int p) {
        int idSelected = p;
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
        int idPassword = -1;
        if (!passwordInput.equals(users.get(p).password)) {
            printLine('!');
            System.out.print("\n Acesso NEGADO!!! Tente novamente! \n");
            printLine('!');
        } else {
            validPassword = true;
            idPassword = idSelected;
        }
        if (validPassword) {
            printLine('+');
            String okPass0 = "Senha do usuário <<< ";
            String okPass1 = " >>> correta!";
            String concatPass = okPass0 + users.get(idPassword).username + okPass1;
            System.out.printf("\n| %-96s |", concatPass);
            followUp("abrir o MENU DO USUÁRIO");
        } else {
            verifyPassword(idSelected);
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
        followUp("retornar ao MENU PRINCIPAL");
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
        System.out.print(" Digite a senha de ADMINISTRADOR: ");
        admPassTry = input.nextLine();
        if (!admPassTry.equals(admPassword)) {
            System.out.print("\n Senha inválida! \n !!! ACESSO NEGADO!!! ");
            followUp("retornar ao MENU PRINCIPAL");
            openMainMenu();
        } else {
            System.out.println(" Senha CORRETA!!! ");
        }
        followUp("exibir os resultados");
    }
    static void followUp (String msg){
        System.out.printf("\n| Tecle ENTER para %s ", msg);
        input.nextLine();
    }
    static void printLine(char c){
        System.out.println();
        for (int i = 0; i < PRINT_width; i++) {
            System.out.printf("%c", c);
        }
    }
    public static void cleanConsole(){
        for (int i = 0; i < 20; ++i)
            System.out.println();
    }
}