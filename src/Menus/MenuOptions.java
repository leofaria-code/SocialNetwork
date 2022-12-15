package Menus;

public enum MenuOptions {
    ENTRAR(1, 'E', "E", "ENTRAR    com username e senha"),
    CADASTRAR(2, 'C', "C", "CADASTRAR novo usuário"),
    LISTAR(3, 'L', "L", "LISTAR    usuários cadastrados"),
    FECHAR(4, 'X', "X", "FECHAR    a aplicação sem salvar os dados!");
    
    private final int optNum;
    private final char optChar;
    private final String optString;
    private final String description;
    
    MenuOptions(int optionNum, char optionChar, String optionString, String description){
        this.optNum = optionNum;
        this.optChar = optionChar;
        this.optString = optionString;
        this.description = description;
    }
    public int getOptNum(){
        return optNum;
    }
    public char getOptChar() {
        return optChar;
    }
    public String getOptString() {
        return optString;
    }
    
    public String getDescription() {
        return description;
    }
}