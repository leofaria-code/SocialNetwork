import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static final int PRINT_width = 100;                        // delimita a largura de impressão => WIDTH = 100
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<MainMenu> mainMenu = new ArrayList<>();
    static int id;
    static Scanner input = new Scanner(System.in);
    static String optionAtMainMenu;
    public static void main(String[] args) {
        users.add(new User(0, "Zero", "user", "0"));   // insere um usuário inicial, via construtor, para teste
        printLine('*');
        String saudacao = "Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA";
        System.out.printf("\n*  %s - %s     *", saudacao, TimeStamp.getDateTime()); // imprime SAUDAÇÃO com DATA e HORA atuais
        printLine('*');
        MainMenu.populateMainMenu();   // popula o MENU PRINCIPAL com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        User.populateUserMenu();       // popula o MENU DO USUÁRIO com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        openMainMenu();
    }
    public static void openMainMenu() {
        System.out.println();
        printMainMenu();
        optionAtMainMenu = input.nextLine().toUpperCase();
        switch (optionAtMainMenu) {
            case "E": {
                signIn();
                break;
            }
            case "C": {
                id++;
                createNewUser();
                break;
            }
            case "L": {
                showAllUsers();
                break;
            }
            case "X": {
                close();
                break;
            }
            case "ADM": {
                powerShowAllUsers();   // método SECRETO que imprime ID, NOME, USERNAME e PASSWORD dos usuários cadastrados
                break;
            }
            default: {
                System.out.printf(" Opção '%S' inválida! Tente novamente.", optionAtMainMenu);
            }
        } openMainMenu();
    }
    static void printMainMenu() {
        printLine('#');
        String title = "MENU PRINCIPAL: o que você deseja fazer?";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printLine('#');
        System.out.printf("\n%-7s %-88s |", "| OPÇÃO |", "FUNÇÃO");
        printLine('-');
        // impressão das opções 'públicas' do MENU PRINCIPAL
        for (MainMenu mainMenuOption : mainMenu) {
            System.out.printf("\n|   %s   | %-88s |", mainMenuOption.option, mainMenuOption.function);
        }
        printLine('=');
        System.out.print("\n Digite o caractere correspondente à opção escolhida: ");
    }
    static void createNewUser() {                 // método que cadastra um novo usuário
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
        printLine('#');
        String title = "LOGIN de usuário ativo";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printLine('=');
        User.openUserMenu(verifyPassword(verifyUsername()));
    }
    static int verifyUsername() {
        System.out.print("\n Digite seu username: ");
        String usernameInput = input.nextLine();
        boolean validUser = false;
        int idUser = -1;
        for (int j = 0; j < users.size(); j++) {
            if (usernameInput.equals(users.get(j).username)) {
                validUser = true;
                idUser =j;
            } else {
                verifyUsername();
            }
        }
        if (validUser) {
            printLine('+');
            System.out.printf("\n Usuário '%s' cadastrado! \n Siga com o LOGIN! ", users.get(idUser).username);
            printLine('+');
        }
        return (idUser);
    }
    static int verifyPassword(int id) {
        System.out.printf("\n USERNAME: %s", users.get(id).username);
        System.out.print("\n Digite sua senha: ");
        String passwordInput = input.nextLine();
        boolean validPassword = false;
        int idPassword = -1;
        if (!passwordInput.equals(users.get(id).password)) {
            printLine('!');
            System.out.print("\n Acesso NEGADO!!! Tente novamente! \n");
            printLine('!');
            verifyPassword(id);
        } else {
            validPassword = true;
            idPassword = id;
        }
        if (validPassword) {
            printLine('+');
            System.out.printf("\n Senha do usuário '%s' correta! Bem vindo '%S' ! ", users.get(idPassword).username, users.get(idPassword).name);
            printLine('+');
        }
        return idPassword;
    }
    private static void close() {
        printLine('x');
        System.out.printf("\nX " + " %-94s " + " X", "O programa será fechado e os dados não serão salvos!");
        printLine('x');
        System.out.printf("\n %s " + " %s ", "Digite SAIR para confirmar", "ou tecle ENTER para voltar ao MENU PRINCIPAL:");
        String exit = input.nextLine();
        if (exit.equals("SAIR")) {
            System.exit(0);
        } else {
            openMainMenu();
        }
    }
    static void printLine(char c){
        System.out.println();
        for (int i = 0; i < PRINT_width; i++) {
            System.out.printf("%c", c);
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
        System.out.print("\n Tecle ENTER para retornar ao MENU PRINCIPAL ");
        input.nextLine();
    }
    private static void powerShowAllUsers() {
        printLine('#');
        System.out.printf("\n| %-4s | %-41s | %-21s | %-21s |", " ID ", "NOME", "USERNAME", "PASSWORD");
        printLine('-');
        for (int k = 0; k < users.size() ; k++) {
            User.powerPrintUser(k);
        }
        printLine('#');
        System.out.print("\n Tecle ENTER para retornar ao MENU PRINCIPAL ");
        input.nextLine();
    }
}