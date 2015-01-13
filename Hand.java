import java.util.List;
import java.util.LinkedList;

public class Hand {
  
  public List<Card> hand = new LinkedList<Card>(); 
  private Deck deck;
  private int handCount; 
//  private int cardsInHand = 0;
  public boolean yesNoSplit = false;
  public boolean bust = false; 
  public boolean containsAce = false;
  
  public Hand (Deck d, int numcards) {
    deck = d;
    for (int i = 0; i < numcards; i++){
       hand.add(deck.drawCard()); 
  //     cardsInHand++; 
       }
  }
  
  public void addCard() {
    //cardsInHand +=1;
    hand.add(deck.drawCard()); 
  }
  
  public int yourNumCount() {
    handCount = 0; 
    for (Card c : hand){
      int val = c.getValue(); 
      if (val == 14) {
        containsAce = true;
        handCount += 11;
      }
      else if (val >= 10 && val != 14) // for all other cards not ace hehe. 
        handCount += 10;
      else
        handCount += val;
    }
    
    // fixing because ace can count as 1 or 11
    if (handCount > 21){
      if (containsAce)
        handCount -= 10;
      else
        bust = true; 
    }
    return handCount;
  }
  /*
  public boolean checkSplit() {
    if (cardsInHand != 2)
      return false;
    if (hand.get(0).getValue() == hand.get(1).getValue())
      return true; 
    return false;
  }
  */
  
  public String toString() {
    String fullstring = ""; 
    for (Card val : hand)
      fullstring += (" " + val.toString());
      return fullstring;
  }
  
  public LinkedList<Card> reverseList (LinkedList<Card> list) {
    LinkedList<Card> reverse = new LinkedList<Card>(); 
    for (Card c: list) {
      c.next = reverse.header;
      reverse.header = c; 
    }
  return reverse; 
  }
}