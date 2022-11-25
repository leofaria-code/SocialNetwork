public class Post {
    String date;
    String time;
    String content;
    
    public Post(String date, String time, String content) {
        this.date = date;
        this.time = time;
        this.content = content;
    }
    
    void printPosts () {
        System.out.println("Data: " + date);
        System.out.println("Hora: " + time);
        System.out.println("Content: " + content);
    }
}
