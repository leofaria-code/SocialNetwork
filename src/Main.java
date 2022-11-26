import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static final int PRINT_width = 100;                        // delimita a largura de impressão => WIDTH = 100
    static ArrayList<User> users = new ArrayList<>(9); // inicializa ArrayList de usuários com capacidade inicial => N = ( )
    static ArrayList<MainMenu> mainMenu = new ArrayList<>();   // inicializa ArrayList de opções do MENU PRINCIPAL
    static int id;
    static Scanner input = new Scanner(System.in);
    static String optionAtMainMenu;                            // instancia opção de menu
    public static void main(String[] args) {
        users.add(new User(0, "Zero", "user", "0"));   // insere um usuário inicial, via construtor, para teste
        printHead('*');
        String saudacao = "Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA";
        System.out.printf("\n*  %s - %s     *", saudacao, TimeStamp.getDateTime()); // imprime SAUDAÇÃO com DATA e HORA atuais
        printFoot('*');
        MainMenu.populateMainMenu();   // popula o MENU PRINCIPAL com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        User.populateUserMenu();       // popula o MENU DO USUÁRIO com as opções cadastradas (chamar no main constrói o MENU só uma vez)
        openMainMenu();
    }
    public static void openMainMenu() {
        System.out.println();
        printMainMenu();                                       // imprime na tela o MENU PRINCIPAL
        optionAtMainMenu = input.nextLine().toUpperCase();     // recebe entrada do usuário, converte para maiúsculas. Define opção no MENU PRINCIPAL
        switch (optionAtMainMenu) {
            case "C": {
                id++;
                createNewUser();
                break;
            }
            case "E": {
                signIn();              // faz login em um perfil já criado
                break;
            }
            case "X": {
                close();               // fecha a aplicação
                break;
            }
            case "U": {
                showAllUsers();        // imprime ID e NOME dos usuários cadastrados
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
        printHead('#');
        String title = "MENU PRINCIPAL: o que você deseja fazer?";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printHead('#');
        System.out.printf("\n%-7s %-88s |", "| OPÇÃO |", "FUNÇÃO");
        printLine('-');
        // impressão das opções 'públicas' do MENU PRINCIPAL
        for (MainMenu mainMenuOption : mainMenu) {
            System.out.printf("\n|   %s   | %-88s |", mainMenuOption.option, mainMenuOption.function);
        }
        printFoot('=');
        System.out.print("\n Digite o caractere correspondente à opção escolhida: ");
    }
    static void createNewUser() {                 // método que cadastra um novo usuário
        printHead('#');
        String title = " CADASTRO de novo usuário ";
        System.out.printf("\n%s %-94s %3s", "|", title, "|");
        printFoot('=');
        int newId;
        newId = id;
        System.out.print("\n Digite seu nome: ");
        String newName = input.nextLine();
        System.out.print(" Digite seu username: ");
        String newUsername = input.nextLine();         // verifyUsername
        System.out.print(" Digite sua senha: ");
        String newPassword = input.nextLine();         // verify
        users.add(new User(newId, newName, newUsername, newPassword));
        printHead('#');
        System.out.printf("\n| %-4s | %-41s | %-21s | %-21s |", " ID ", "NOME", "USERNAME", "PASSWORD");
        printLine('-');
        System.out.printf("\n| %04d | %-41s | %-21s | %-21s |", newId, newName, newUsername, newPassword);
        printFoot('#');
        System.out.print("\n Cadastrado com sucesso!\n");
        System.out.print("\n Tecle ENTER para retornar ao MENU PRINCIPAL ");
        input.nextLine();
    }
    static void signIn() {                     // método que faz login em um perfil já existente
        printHead('#');
        String title = "LOGIN de usuário ativo";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printFoot('=');
        User.openUserMenu(verifyPassword(verifyUsername()));
    }
    static int verifyUsername() {                    // método que verifica se o username digitado está cadastrado e, se sim, retorna o ID do usuário
        System.out.print("\n Digite seu username: ");
        String usernameInput = input.nextLine();     // recebe entrada do usuário de um username que será verificado no cadastro
        boolean validUser = false;                   // variável para verificar se o username existe
        int idUser = -1;
        for (int j = 0; j < users.size(); j++) {                    // percorre todos os usernames cadastrados
            if (usernameInput.equals(users.get(j).username)) {      // compara a entrada fornecida com os usernames cadastrados
                validUser = true;
                idUser =j;
            } else {
                verifyUsername();
            }
        }
        if (validUser) {
            System.out.printf("\n Usuário '%s' cadastrado! \n Siga com o LOGIN! ", users.get(idUser).username);
        }
        return (idUser);
    }
    static int verifyPassword(int id) {
        System.out.printf(" USERNAME:         %s", users.get(id).username);
        System.out.print("\n Digite sua senha: ");
        String passwordInput = input.nextLine();
        boolean validPassword = false;
        int idPassword = -1;
        if (!passwordInput.equals(users.get(id).password)) {
            System.out.print("\n Acesso NEGADO!!! Tente novamente! ");
            verifyPassword(id);
        } else {
            validPassword = true;
            idPassword = id;
        }
        if (validPassword) {
            System.out.printf("\n Senha do usuário '%s' correta! \n Bem vindo %S\"", users.get(idPassword).username, users.get(idPassword).name);
        }
        return idPassword;
    }
    private static void close() {
        System.out.print("\n O programa será fechado e os dados não serão salvos!");
        System.out.print("\n Tecle ENTER para confirmar e SAIR! ");
        input.nextLine();
        System.exit(0);
    }
    static void printHead(char c) {
        System.out.println();
        for (int i = 0; i < PRINT_width; i++) {
            System.out.printf("%c", c);
        }
    }
    static void printLine(char c){
        System.out.println();
        for (int i = 0; i < PRINT_width; i++) {
            System.out.printf("%c", c);
        }
    }
    static void printFoot(char c) {
        System.out.println();
        for (int i = 0; i < PRINT_width; i++) {
            System.out.printf("%c", c);
        }
    }
    static void showAllUsers() {               // método imprime ID e NOME dos usuários cadastrados
        printHead('#');
        System.out.printf("\n| %-4s | %-89s |", " ID ", "NOME");
        printLine('-');
        for (int k = 0; k < users.size() ; k++) {
            User.printUser(k);
        }
        printFoot('#');
        System.out.print("\n Tecle ENTER para retornar ao MENU PRINCIPAL ");
        input.nextLine();
    }
    private static void powerShowAllUsers() {  // método SECRETO que imprime ID, NOME, USERNAME, PASSWORD dos usuários cadastrados
        printHead('#');
        System.out.printf("\n| %-4s | %-41s | %-21s | %-21s |", " ID ", "NOME", "USERNAME", "PASSWORD");
        printLine('-');
        for (int k = 0; k < users.size() ; k++) {
            User.powerPrintUser(k);
        }
        printFoot('#');
        System.out.print("\n Tecle ENTER para retornar ao MENU PRINCIPAL ");
        input.nextLine();
    }
}