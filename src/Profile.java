import java.util.ArrayList;
import java.util.Scanner;
public class Profile {
    int id;
    String name;
    String username;
    String password;
    ArrayList<Post> posts = new ArrayList<>();
    
    public Profile (int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    
    static Scanner input = new Scanner(System.in);
    static char optionAtUserMenu;
    
    void openUserMenu(String name, String login) {
        do {
            System.out.print("\n### MENU DO USUÁRIO ###\n\n");
            System.out.println("P - para POSTAR");
            System.out.println("T - para TIMELINE");
            System.out.println("S - para SAIR");
            System.out.print("\nDigite o caractere correspondente à opção escolhida: ");
            optionAtUserMenu = input.next().toUpperCase().charAt(0);
        } while (optionAtUserMenu != 'P' && optionAtUserMenu != 'T' && optionAtUserMenu != 'S');
        
        switch (optionAtUserMenu) {
            case 'P':
                makeNewPost();
                break;
            case 'T':
                showMyPosts(name, login);
                break;
            case 'S':
                Main.openMainMenu();
                break;
            default:
                System.out.println("ERRO!");
        }
        openUserMenu(name, login);
    }
    
    void makeNewPost() {
        System.out.print("\n## Novo Post do usuário ##\n");
        System.out.print("Digite o conteúdo: ");
        String content = input.next();
        posts.add(new Post(content));
    }
    
    void showMyPosts(String name, String login) {
        System.out.println("Nome: " + name);
        System.out.println("Login: " + login);
        for (Post post : posts) {
            post.printPosts();
        }
    }
}
