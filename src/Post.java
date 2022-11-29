import java.util.Arrays;
public class Post {
    int idPost;
    String timestamp;
    String content;
    int userId;
    
    public Post(int idPost, String timestamp, String content, int userId) {
        this.idPost = idPost;
        this.timestamp = timestamp;
        this.content = content;
        this.userId = userId;
    }
    
    public void printUserPosts(int id) {
        Main.printLine('#');
        System.out.printf("\n NOME: %s - USERNAME: %s ", Main.users.get(id).name, Main.users.get(id).username);
        Main.printLine('=');
        for (int j = 0; j < User.userPosts.size(); j++) {
            String idPost = String.valueOf(Main.users.get(id).userPosts.get(j).idPost);
            String timestamp = Main.users.get(id).userPosts.get(j).timestamp;
            String content = Main.users.get(id).userPosts.get(j).content;
            String s = (" Post Nº " + idPost + " - Em " + timestamp + " - Conteúdo: " + content);
            System.out.printf("\n%s %-94s %3s", "|", s, "|");
        }
    }
    
    public void printPosts(int i) {
        Main.printLine('#');
        System.out.printf("\n NOME: %s - USERNAME: %s ", Main.users.get(i).name, Main.users.get(i).username);
        Main.printLine('=');
        for (int j = 0; j < User.userPosts.size(); j++) {
            String idPost = String.valueOf(User.userPosts.get(j).idPost);
            String timestamp = User.userPosts.get(j).timestamp;
            String content = User.userPosts.get(j).content;
            String s = (" Post Nº " + idPost + " Em " + timestamp + " Conteúdo: " + content);
            System.out.printf("\n %s", s);
        }
        for (String s : Arrays.asList(" Post Nº " + idPost, " Em " + timestamp, " Conteúdo: " + content)) {
            System.out.printf("\n %s", s);
        }
    }
}