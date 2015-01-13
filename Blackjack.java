import java.util.Scanner; 
import java.text.DecimalFormat;
import java.util.List; 
import java.util.LinkedList; 

// CS 112 Final Project
// Tiffany Kha

public class Blackjack {
  public static Scanner keyboard = new Scanner(System.in);
  public static DecimalFormat myFormatter = new DecimalFormat("$###,##0.00");
  
  public static void playRound(List<Player> players, DealerPlayer dealer){ 
    Deck d = new Deck();
    d.shuffle(); 
    
    for (Player currentPlayer : players) {
      // RESET ALL THINGS YOU MAY HAVE CHANGED AT BEGINNING. 
      currentPlayer.done = false;
      currentPlayer.instantWin = false;
      currentPlayer.bust = false;
      if (currentPlayer.money == 0){
        System.out.println(currentPlayer.name + " is broke and has left the game.");
        currentPlayer.h = null; 
        currentPlayer.done = true;
      }
      if (currentPlayer.h != null) {
        System.out.println(currentPlayer.name + " has " + myFormatter.format(currentPlayer.money));
        System.out.println("How much does " + currentPlayer.name + " bet?"); 
        currentPlayer.bet = keyboard.nextDouble();
        // To quit, you can enter an invalid bet. 
        if (currentPlayer.bet <= 0 || currentPlayer.bet > currentPlayer.money){ 
          System.out.println(currentPlayer.name + " has exited with " + myFormatter.format(currentPlayer.money));
          currentPlayer.h = null; 
          currentPlayer.done = true;
        }
        else {
          // now that we've checked it's all goood...... 
          currentPlayer.h = new Hand (d, 2);
          // check for blackjack :)
          if (currentPlayer.h.yourNumCount() == 21)
            currentPlayer.instantWin = true;
        }
      }
    }
    
    
    boolean anyPlayers = false;
    for (Player player: players) {
      if (player.h != null)
        anyPlayers = true;
    }
    
    if (anyPlayers) {
      dealer.h = new Hand(d,2);
      boolean roundEnd = false; 
      
      if (dealer.h.yourNumCount() == 21){
        dealer.instantWin = true;
        roundEnd = true; 
      }
      else 
        dealer.instantWin = false; 
      
      while (!roundEnd) {
        int numberGoing = 0;
        
        for (Player nowGoing : players) {
          if (!nowGoing.done){
            nowGoing.turn();
            numberGoing += 1; 
          }
        }      
        if (dealer.done && numberGoing == 0)
          roundEnd = true;
        else
          dealer.turn();
      }
      
      // now we need to compare their sums to the dealeeeeeeeeeeeer! 
      int dealernum = dealer.h.yourNumCount(); // so we don't have to check it a billion timezzzzz
      if (dealer.bust){
        System.out.println ("Dealer bust!");
        for (Player each : players){
          if (each.bust){
            System.out.println(each.name + " also bust and lost " + myFormatter.format(each.bet));
            each.money -= each.bet;
          }
          else {
            System.out.println(each.name + " wins " + myFormatter.format(each.bet));
            each.money += each.bet;
          }  
        }
      }
      else {
        System.out.println("Dealer's cards are:" + dealer.h 
                             + " which sum to " + dealernum);
        
        for (Player nowGoing : players){
          if (nowGoing.h != null){
            if (nowGoing.bust){
              System.out.println(nowGoing.name + " lost " + myFormatter.format(nowGoing.bet));
              nowGoing.money -= nowGoing.bet;
            }
            else { // not an elseif statement because if you both bust you still lose $$)
              if (nowGoing.h.yourNumCount() < dealernum) {
                System.out.println("Sorry, " + nowGoing.name + " lost " + myFormatter.format(nowGoing.bet));
                nowGoing.money -= nowGoing.bet;
              }
              else if (nowGoing.h.yourNumCount() == dealernum){
                if (nowGoing.instantWin && !dealer.instantWin){
                  // so if the user got a blackjack in their hand and the dealer DID NOT..... 
                  System.out.println(nowGoing.name + " wins " + myFormatter.format(nowGoing.bet * 1.5));
                  nowGoing.money += (nowGoing.bet * 1.5);
                }
                else 
                  System.out.println("Push. " + nowGoing.name + " doesn't win or lose any money."); 
              }
              else {
                System.out.println(nowGoing.name + " wins " + myFormatter.format(nowGoing.bet));
                nowGoing.money += nowGoing.bet;
              }
            }
          }
        }
      }
    }
  }
  
  public static List<Player> createPlayers() {
    List<Player> players = new LinkedList<Player>();
    System.out.println(" How many players?");
    int numPlayers = keyboard.nextInt(); 
    for (int i = 0; i < numPlayers; i++) {
      System.out.println( "Enter Player " + (i+1) + "'s name: ");
      String username = keyboard.next();
      System.out.println( "Is " + username + " a computer? Type 'y' or 'n'");
      char reply =  keyboard.next(".").charAt(0);
      if (reply == 'y')
        players.add((Player) (new ComputerPlayer())); 
      else
        players.add((Player)(new HumanPlayer()));
      players.get(i).name = username; 
    }
    return players;
  }
  
  public static void main (String [] args) {
    System.out.println( "Welcome to blackjack!" );
    DealerPlayer dealer = new DealerPlayer();
    List<Player> players = createPlayers();
    int playersout = 0;
    while (playersout < players.size() ){
      playRound(players, dealer);
      for (Player p : players) {
        if (p.h == null)
          playersout +=1;
      }
    }
    System.out.println("All players done. Thanks for playing!"); 
  }
}

