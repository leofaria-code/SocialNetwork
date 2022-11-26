import java.util.Arrays;
public class Post {
    int idPost;
    String date;
    String time;
    String timestamp;
    String content;
    public Post(int idPost, String timestamp, String content) {
        this.idPost = idPost;
        this.date = TimeStamp.getDate();
        this.time = TimeStamp.getTime();
        this.timestamp = timestamp;
        this.content = content;
    }
    public void printPosts() {
        Main.printLine('=');
        for (String s : Arrays.asList("Post: " + idPost, " Em " + timestamp, " Conteúdo: " + content)) {
            System.out.printf("\n %s", s);
        }
    }
}