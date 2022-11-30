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
//    public static void printUserPosts(int id) {
//        Main.printLine('#');
//        System.out.printf("\n NOME: %s - USERNAME: %s ", Main.users.get(id).name, Main.users.get(id).username);
//        Main.printLine('=');
//        for (int j = 0; j < User.userPosts.size(); j++) {
//            String idPost = String.valueOf(Main.users.get(id).userPosts.get(j).idPost);
//            String timestamp = Main.users.get(id).userPosts.get(j).timestamp;
//            String content = Main.users.get(id).userPosts.get(j).content;
//            String userId= String.valueOf(Main.users.get(id).userPosts.get(j).userId);
//            String s0 = (" Usuário: " + userId + " - Post Nº " + idPost + " - Em " + timestamp);
//            System.out.printf("\n%s %-94s %3s", "|", s0, "|");
//            String s1 = (" Conteúdo: " + content);
//            System.out.printf("\n%s %-94s %3s", "|", s1, "|");
//            Main.printLine('.');
//        }
//        Main.printLine('=');
//    }
    public static void printFormatedPost(int ip) {
        System.out.printf("\n| %-92s %03d |", "POST Nº", User.userPosts.get(ip).idPost);
        System.out.printf("\n| %96s |", User.userPosts.get(ip).timestamp);
        System.out.printf("\n| %-96s |", User.userPosts.get(ip).content);
        System.out.printf("\n| %91s %04d |", "ID do usuário:", User.userPosts.get(ip).userId);
    }
//    public static void printThisUserPosts() {
//        for (int i = 0; i < User.userPosts.size(); i++) {
//            printFormatedPost(i);
//            Main.printLine('.');
//        }
//    }
}