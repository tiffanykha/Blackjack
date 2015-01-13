
import java.util.Scanner;

abstract class Player {
  public static Scanner keyboard = new Scanner(System.in);
  // do i really need to make a new scanner or can i just inherit it...... 
  public String name;
  // private Deck deck;
  public double money = 200;
  public double bet;
  public boolean done; 
  public boolean bust;
  public boolean instantWin; 
  public Hand h;
  abstract void turn(); 
} 

class HumanPlayer extends Player{
  public void turn() {
    if (!done && !bust){
      System.out.println(name + "'s cards are: " + h); 
      System.out.println(name + "'s number right now is: " + h.yourNumCount()); 
      if (h.yourNumCount() < 21){
        System.out.println(name + ", type 'H' to hit and 'S' to stay.");
        char response = keyboard.next(".").charAt(0);
        if (response == 'H') {
          h.addCard();
        }
        else // if the user chooses to stay!!!!! 
          super.done = true;
      }
      else if (h.yourNumCount() == 21){
        System.out.println(name + " got blackjack!");
        super.done = true;
      }
      else{ // so if the player has bust
        System.out.println(name + " bust.");
        super.bust = true;
        super.done = true; // does it change if i do super?
      }
    } 
  }
}

class ComputerPlayer extends Player {
// this computerplayer will eventually be the one that counts cards
  // unfortunately, i ran out of time...
  public void turn() {
    
    if (h.yourNumCount() < 21) { // So if he hasn't bust yet.
      if (h.yourNumCount() < 17) {
        h.addCard();
        System.out.println(name + " has drawn a card.");
      }  
      else { 
        // Computers will follow same rules as dealer
        System.out.println (name + " stays.");
        super.done = true;
      }
    }
    else {  // computer has bust
      super.done = true;
      super.bust = true;
    }
  }
}


class DealerPlayer extends Player {
  public void turn() {
    if (h.yourNumCount() < 21) { // So if he hasn't bust yet.
      System.out.println("The card you see of the dealer's is: " + h.hand.get(0));
      if (h.yourNumCount() < 17) {
        h.addCard();
        System.out.println("The dealer has drawn a card.");
      }  
      else { 
        // Dealer hits on soft 17's!! 
        System.out.println ("Dealer stays.");
        super.done = true;
      }
    }
    else {  // he has bust
      super.done = true;
      super.bust = true;
    }
  }
}


