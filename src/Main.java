import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Profile> profiles = new ArrayList<>();            // inicializa ArrayList de perfis
    static ArrayList<MainMenu> mainMenuOptions = new ArrayList<>();    // inicializa ArrayList de opções do MENU PRINCIPAL
    static int id = 0;
    static Scanner input = new Scanner(System.in);             // abre o Scanner
    static String optionAtMainMenu;                            // instancia opção de menu
    static final int PRINT_width = 100;                        // delimita a largura de impressão
    
    public static void main(String[] args) {
        profiles.add(new Profile(0, "User TEST", "user", "123")); // insere um usuário inicial, via construtor, para teste
        LocalDateTime now = LocalDateTime.now();                                         // guarda DATA e HORA da execução, via java.time.LocalDateTime
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");     // formata a DATA, via java.time.format.DateTimeFormatter
        String dateToday = formatterData.format(now);                                    // salva DATA como String no formato 'DD/MM/AAAA'
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");       // formata a HORA, via java.time.format.DateTimeFormatter
        String timeNow = formatterHora.format(now);                                      // salva a HORA no formato 'HH:mm:ss.'
        printHead('*');                                                           // cabeçalho ABERTURA
        String saudacao = "Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA"; // guarda texto de SAUDAÇÃO
        System.out.printf("\n    %s - %s, %s", saudacao, dateToday, timeNow);                     // imprime SAUDAÇÃO com DATA e HORA atuais
        printFoot('*');                                                           // rodapé ABERTURA
        populateMainMenu();                            // chama método que popula o MENU PRINCIPAL com as opções cadastradas
        openMainMenu();                                // chama método que abre o MENU PRINCIPAL
    }
    
    public static void populateMainMenu() {         // método que popula o MENU PRINCIPAL com as opções cadastradas
        mainMenuOptions.add(new MainMenu(0, "U", "Listar USUÁRIOS cadastrados"));              // opção acrescentada de propósito (ocultar???)
        mainMenuOptions.add(new MainMenu(1, "C", "CADASTRAR novo usuário"));                   // REQUISITO do projeto
        mainMenuOptions.add(new MainMenu(2, "E", "ENTRAR com seus dados de login"));           // REQUISITO do projeto
        mainMenuOptions.add(new MainMenu(3, "F", "FECHAR a aplicação sem salvar os dados!"));  // REQUISITO do projeto
    }
    
    private static void openMainMenu() {             //método que abre o MENU PRINCIPAL
        System.out.println();                                  // salta uma linha por escolha de design
        printMainMenu();                                       // chama método que imprime na tela o MENU PRINCIPAL
        optionAtMainMenu = input.nextLine().toUpperCase();     // recebe entrada do usuário, converte para maiúsculas, para definir opção no MENU PRINCIPAL
        
        switch (optionAtMainMenu) {
            case "C": {
                createNewProfile();            // chama método que cadastra um novo usuário
                break;
            }
            case "E": {
                signIn();                      // chama método que faz login em um perfil já existente
                break;
            }
            case "F": {
                close();                       // chama método que fecha a aplicação
                break;
            }
            case "U": {
                showAllUsers();                // chama método imprime ID e NOME dos usuários cadastrado
                break;
            }
            default: {
                System.out.print("\n Opção inválida! Tente novamente.\n");
            }
        }
        openMainMenu();
    }
    
    static void printMainMenu() {                    // método que imprime na tela o MENU PRINCIPAL com as devidas opções
        printHead('#');
        String title = "MENU PRINCIPAL: seleciona as ações a executar";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printHead('#');
        System.out.printf("\n%-7s %-88s |", "| OPÇÃO |", "FUNÇÃO");
        printLine('-');
        for (MainMenu mainMenuOption : mainMenuOptions) {
            System.out.printf("\n|   %s   | %-88s |", mainMenuOption.option, mainMenuOption.function);
        }
        printFoot('=');
        System.out.print("\n Digite o caractere correspondente à opção escolhida: ");
    }
    
    static void createNewProfile() {                 // método que cadastra um novo usuário
        printHead('#');
        String title = "CADASTRO de novo usuário";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printFoot('=');
        id++;
        System.out.print("\n Digite seu nome: ");
        String name = input.nextLine();
        System.out.print("Digite seu username: ");
        String username = input.nextLine();
        System.out.print("Digite sua senha: ");
        String password = input.nextLine();
        profiles.add(new Profile(id, name, username, password));
        System.out.printf("\nID do Usuário: %d - Nome: %s", profiles.get(id).id, profiles.get(id).name);
        System.out.printf("\nUsername: %s - Senha: %s\n", profiles.get(id).username, profiles.get(id).password);
        System.out.print("\nCadastrado com sucesso!\n");
    }
    
    static void signIn() {                     // método que faz login em um perfil já existente
        printHead('#');
        String title = "LOGIN de usuário ativo";
        System.out.printf("\n%s %-94s %3s", "|",  title, "|");
        printFoot('=');
        verifyPassword(verifyUsername());      // chama método que verifica a senha de acordo ID do usuário
    }                                                // ID do usuário retornado na verificação positiva do username
    
    static int verifyUsername() {                    // método que verifica se o username digitado está cadastrado e, se sim, retorna o ID do usuário
        System.out.print("\n Digite seu username: ");
        String usernameInput = input.nextLine();       // recebe entrada do usuário de um username que será verificado no cadastro
        boolean validUser = false;                     // variável para verificar se o username existe
        for (int j = 0; j < profiles.size(); j++) {                        // percorre todos os usernames cadastrados
            if (usernameInput.equals(profiles.get(j).username)) {      // compara a entrada fornecida com os usernames cadastrados
                validUser = true;
            }
            if (validUser) {
                id = j;
            } else {
                verifyUsername();
            }
        }
        return (id);                             // retorno do método = ID do usuário
    }
    
    static void verifyPassword(int id) {
        System.out.print("\n Digite sua senha: ");
        String passwordInput = input.nextLine();
        if (passwordInput.equals(profiles.get(id).password)) {
            System.out.printf("\n Bem vindo %S", profiles.get(id).name);
        } else {
            System.out.println(" Denied!");
            verifyPassword(id);
        }
    }
    
    private static void close() {              // método que fecha a aplicação
        System.out.println(" O programa será fechado e os dados não serão salvos!");
        System.exit(0);
    }
    
//        System.out.printf("\nBem vindo %s!", profiles[p].name);
//        System.out.print("\nValidação de senha.");
//        String passwordInput = null;
//        int passwordTry = 1;
//        while ((!passwordInput.equals(passwordUser)) && (passwordTry <= MAX_PASSWORD_TRIES)) {
//            System.out.printf("\nTENTATIVA %d \nDigite a senha: ", passwordTry);
//            passwordInput = input.next();
//            passwordTry++;
//        }
//        if (passwordInput.equals(passwordUser)) {
//            System.out.println("SENHA CORRETA!");
//            Profile.openUserMenu(profiles[p].name, profiles[p].username);
//        } else {
//            System.out.println("SENHA INCORRETA!");

//        }

//    void makePostPublic() {
//        //pedir os dados
//        //profile.makePost("data, ", "hora, ", "Teste post 01.");
//    }
//
//    void showTimeline() {
//
//    }
    
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
        System.out.printf("\n| %-5s | %-88s |", " ID ", "NOME");
        printLine('-');
        for (Profile profile : profiles) {
            System.out.printf("\n| %5d | %-88s |", profile.id, profile.name);
        }
        printFoot('_');
    }
}