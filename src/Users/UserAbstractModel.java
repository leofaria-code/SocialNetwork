package Users;

public abstract class UserAbstractModel {
    private int seqID;
    
    private String name;
    private String username;
    private String password;

    private boolean active = true;
    private boolean admin = false;
    UserAbstractModel(int id, String name, String username, String password) {
        this.seqID = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }
    
    public String getUsername() {
        return username;
    }
    void setUsername(String username) {
        this.username = username;
    }
    
    void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isActive() {
        return active;
    }
    void setActive(boolean active) {
        this.active = active;
    }
    
    boolean isAdmin() {
        return admin;
    }
    void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public abstract void makeInactive();
}