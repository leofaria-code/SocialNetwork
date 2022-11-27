import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static final int PRINT_width = 100;                        // delimita a largura de impressão => WIDTH = 100
    static final String askOption = "| Digite o caractere correspondente à opção escolhida:";        // msg a ser exibida nos MENUS
    final static User ADMIN = new User(0,"ADM", "admin", "admin"); // super usuário, privilégios de ADMINISTRADOR
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<MainMenu> mainMenu = new ArrayList<>();
    static int id;
    static Scanner input = new Scanner(System.in);
    static String optionAtMainMenu;
    static int idUser = -1;    // inicia contador pra verificar username
    static int idVerified = -1;// inicia contador pra verificar username
    public static void main(String[] args) {
        users.add(ADMIN);              // insere o ADMIN na posição '0' da ArrayList
        MainMenu.populateMainMenu();   // popula o MENU PRINCIPAL com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        User.populateUserMenu();       // popula o MENU DO USUÁRIO com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        openMainMenu();
    }
    public static void welcome(){
        printLine('*');
        String msg = "Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA";
        System.out.printf("\n*  %s - %s     *", msg, TimeStamp.getDateTime()); // imprime SAUDAÇÃO com DATA e HORA atuais
        printLine('*');
    }
    public static void openMainMenu() {
        cleanConsole();
        welcome();
        printMainMenu();
        optionAtMainMenu = input.nextLine().toUpperCase();
        switch (optionAtMainMenu) {
            case "1":
            case "E":
                signIn();
                break;
            case "2":
            case "C":
                id++;
                createNewUser();
                break;
            case "3":
            case "L":
                showAllUsers();
                break;
            case "4":
            case "X":
                close();
                break;
            case "":
            case " ":
                System.out.printf(" Opções '%S' ou '%S' são inválidas! ", "espaço", "vazio");
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
        System.out.printf("\n%s ", askOption);
    }
    static void createNewUser() {
        printLine('#');
        String title = " CADASTRO de novo usuário ";
        System.out.printf("\n%s %-94s %3s", "|", title, "|");
        printLine('=');
        int newId;
        newId = id;
        System.out.print("\n Digite seu nome: ");
        String newName = input.nextLine();
        System.out.print(" Digite seu username: ");
        String newUsername = input.nextLine();         // verifyUsername
        System.out.print(" Digite sua senha: ");
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
        cleanConsole();
        printLine('#');
        String title = "VERIFICAÇÃO de cadastro";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printLine('=');
        User.openUserMenu(verifyPassword(verifyUsername()));
    }
    static int verifyUsername() {
        System.out.print("\n| Para verificar seu cadastro, digite seu username: ");
        String usernameInput = input.nextLine();
        boolean validUser = false;
        for (idUser = 0; idUser < users.size(); idUser++) {
            if (usernameInput.equals(users.get(idUser).username)) {
                validUser = true;
                idVerified = idUser;
            } else {
                verifyUsername();
            }
        }
        if (validUser) {
            printLine('+');
            System.out.printf("\n Usuário '%s' cadastrado! \n ID do Usuário: '%s' ", users.get(idVerified).username, users.get(idVerified).id);
            followUp("fazer login nessa conta");         // 120: System.out.printf("\n ???  %d = %d  ???", idVerified, users.get(idVerified).id);
            printLine('+');
        }

        return (idVerified);                   // 124: System.out.printf("\n ???  %d = %d  ???", idVerified, users.get(idVerified).id);
    }
    static int verifyPassword(int p) {
        cleanConsole();
        printLine('#');
        String title = "VERIFICAÇÃO de senha";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printLine('-');
        String concat = "USERNAME: " + users.get(p).username;
        System.out.printf("\n%s %-94s %3s", "|", concat, "|");
        printLine('=');
        System.out.print("\n| Digite sua senha: ");
        String passwordInput = input.nextLine();
        boolean validPassword = false;
        int idPassword = -1;
        if (!passwordInput.equals(users.get(p).password)) {
            printLine('!');
            System.out.print("\n Acesso NEGADO!!! Tente novamente! \n");
            printLine('!');
            verifyPassword(p);
        } else {
            validPassword = true;
            idPassword = p;
        }
        if (validPassword) {
            printLine('+');
            System.out.printf("\n Senha do usuário '%s' correta! Bem vindo '%S' ! ", users.get(idPassword).username, users.get(idPassword).name);
            printLine('+');
        }
        return idPassword;
    }
    private static void close() {
        printLine('#');
        System.out.printf("\n| " + "%-96s" + " |", "O programa será fechado e os dados não serão salvos!");
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
        for (int i = 0; i < 10; ++i)
            System.out.println();
    }
}