class Player{
    int id;
    List<Card> cards;
    int money;
    boolean isPlaying;
    
    Player(int money, List<Card> cards, int id){
        this.id = id;
        this.cards = cards;
        this.money = money;
        this.isPlaying = true;
    }
    
    void play(Board board){
        System.out.println("Press 1 for call, 2 for fold, 3 for raise, 4 for allIn");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextLine();
        while(true){
            boolean isValid = false;
            switch(input){
                case 1 : 
                    isValid = call();
                    break;
                case 2 :
                    isValid = fold();
                    break;
                case 3 :
                    isValid = raise();
                    break;
                case 4 :
                    isValid = allIn();
                    break;
                default :
                    System.out.println("Wrong input!");
                    break;
            if(isValid)
                break;
            }   
        }
    }
    
    boolean call(Board board){
        if(money >= board.currMoney){
            money -= board.currMoney;
            board.addMoneyToBoard(board.currMoney);
            return true;
        }
        return false;
    }
    
    void fold(){
        this.isPlaying = false;
        this.cards = null;
        return true;
        
    }
    
    boolean raise(Board board){
        System.out.println("Enter amount to raise");
        Scanner scanner = new Scanner(System.in);
        int raise = scanner.nextLine();
        if(money >= board.currMoney + raise){
            money -= board.currMoney + raise;
            board.addMoneyToBoard(board.currMoney + raise);
            board.increaseCurrMoney(raise);
            return true;
        }
        return false;
    }
    
    boolean allIn(){
        if(money > 0){
            board.addMoneyToBoard(money);
            money = 0;
        }
        return false;
    }
}

class Dealer{

    Deck deck;
}

class Board{
    int currMoney;
    int totalMoney;
    List<Card> cardsOnBoard;
    
    void addMoneyToBoard(int money){
        totalMoney += money;
    }
    
    //raise
    void increaseCurrMoney(int money){
        currMoney += money;
    }
}

class CardOnBoard extends Card{
    int status; // 0 - closed, 1 - open
}

enum CardType{
    spades, hearts, diamonds, clubs;
}


class Card{
    int value;
    CardType cardType;
}

class Deck{
    List<Card> cards;
}

class Game{
    Board board;
    List<Player> players;
    
    Game(Board board, List<Player> players){
        this.board = board;
        this.players = players;
    }
    
    void play(){
        Player winner = null;
        while(true){
            for(var player : players){
                if(player.isPlaying){
                    player.play();
                    if(board.hasWinner()){
                        winner = player;
                        break;
                    }
                }
            }
            if(winner != null)
                break;
        }
        System.out.println("Winner: " + winner.id + " Money:" + board.money);
    } 
}


