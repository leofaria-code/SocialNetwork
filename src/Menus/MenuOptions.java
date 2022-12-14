package Menus;

public enum MenuOptions {
    ENTRAR(1, 'E'), CADASTRAR(2, 'C'), LISTAR(3, 'L'), FECHAR(4, 'X');
    
    private final int optNum;
    private final char optChar;
    MenuOptions(int OptionNum, char OptionChar){
        optNum = OptionNum;
        optChar = OptionChar;
    }
    public int getOptNum(){
        return optNum;
    }
    public char getOptChar() {
        return optChar;
    }
}