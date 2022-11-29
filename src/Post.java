import java.util.Arrays;
public class Post {
    int idUser;
    int idPost;
    String date;
    String time;
    String timestamp;
    String content;
    public Post(int idUser, int idPost,String timestamp, String content) {
        this.idUser = idUser;
        this.idPost = idPost;
        this.date = TimeStamp.getDate();
        this.time = TimeStamp.getTime();
        this.timestamp = timestamp;
        this.content = content;
    }
    public void printPosts() {
        for (String s : Arrays.asList(" Post Nº " + idPost, " Em " + timestamp, " Conteúdo: " + content)) {
            System.out.printf("\n %s", s);
        }
    }
}