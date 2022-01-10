
/*
Online Java - IDE, Code Editor, Compiler

Online Java is a quick and easy tool that helps you to build, compile, test your programs online.
*/
//Entities
// Game, Player, Board

import java.util.Scanner; 

class Player{
    String name;
    long id;
    char symbol;
    
    Player(char symbol, String name){
        this.symbol = symbol;
        this.name = name;
    }
    void play(Board board){
        int xCoord = -1;
        int yCoord = -1;
        System.out.println(name + " is playing!");
        do{
            System.out.println("Enter location");
            Scanner input = new Scanner(System.in);
            xCoord = input.nextInt();
            yCoord = input.nextInt();
        }while(!board.tryPlace(xCoord, yCoord, symbol));
    }
    
    String getName(){
        return name;
    }
    
    Character getSymbol(){
        return symbol;
    }
}

class Board{
    char[][] board;
    int n;
    
    Board(int n){
        this.board = new char[n][n];
        this.n = n;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '-';
            }
        }
    }
    
    boolean tryPlace(int x, int y, char symbol){
        
        if(x < 0 || y < 0 || x >= n || y >= n || board[x][y] != '-'){
            System.out.println("Wrong move. Try again!");
            return false;
        }else{
            board[x][y] = symbol;
            System.out.println(symbol + " placed at location: " + x + " " + y);
            return true;
        }
    }
    
    void print(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    Character checkWinner(){
        
        for(int i = 0; i < n; i++){
            Character symbol = board[i][0];
            boolean pass = true;
            for(int j = 1; j < n; j++){
                if(board[i][j] != symbol){
                    pass = false;
                    break;
                }
            }
            if(pass)
                return symbol;
            
        }
        
        for(int i = 0; i < n; i++){
            Character symbol = board[0][i];
            boolean pass = true;
            for(int j = 1; j < n; j++){
                if(board[j][i] != symbol){
                    pass = false;
                    break;
                }
            }
            if(pass)
                return symbol;
        }
        
        Character symbol = board[0][0];
        boolean pass = true;
        for(int i = 1; i < n; i++){
            if(board[i][i] != symbol){
                pass = false;
                break;
            }
        }
        if(pass)
            return symbol;
            
        symbol = board[0][n - 1];
        pass = true;
        for(int i = 1; i < n; i++){
            if(board[i][n - i - 1] != symbol){
                pass = false;
                break;
            }
        }
        if(pass)
            return symbol;    
        
        return '-';
    }
}

class Game{
    Player playerA;
    Player playerB;
    Board board;
    Player currentPlayer;
    
    Game(){
        this.playerA = new Player('X', "Player A");
        this.playerB = new Player('O', "Player B");
        this.board = new Board(3);
        currentPlayer = playerA;
    }
    
    void run(){
        String name = "";
        Character symbol = board.checkWinner();
        while(symbol == '-'){
            currentPlayer.play(board);
            board.print();
            currentPlayer = (currentPlayer == playerB) ? playerA : playerB;
            symbol = board.checkWinner();
        }
        
        if(playerA.getSymbol() == symbol)
            System.out.println(playerA.getName() + " wins!");
        else
            System.out.println(playerB.getName() + " wins!");
        
    }
}

public class TicTacToe
{
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
        BoardUT boardUT = new BoardUT();
        boardUT.testTryPlace();
        boardUT.testCheckWinner();
    }
}


class BoardUT{
    Board testBoard;
    
    BoardUT(){
        this.testBoard = new Board(3);
    }
    
    <T> void validate(T actual, T expected){
        if(expected == actual)
            System.out.println("Test passed!");
        else
            System.out.println("Test failed!");
    }
    
    void testTryPlace(){
        
        validate(true, testBoard.tryPlace(0, 0, 'X'));
        validate(false, testBoard.tryPlace(0, 0, 'X'));
    }
    
    void testCheckWinner(){
        validate(false, testBoard.tryPlace(0, 0, 'X'));
        validate(true, testBoard.tryPlace(0, 1, 'Y'));
        validate(true, testBoard.tryPlace(1, 1, 'X'));
        validate(true, testBoard.tryPlace(2, 0, 'Y'));
        validate(true, testBoard.tryPlace(2, 2, 'X'));
        validate('X', testBoard.checkWinner());
    }
}
