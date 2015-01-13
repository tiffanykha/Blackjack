public class Deck {
  
  // A class describing a Deck object.
  // L. McGeoch, 9/2014
  
  private Card[] cards; // A Deck contains cards.
  private int count; // This variable tells how much of the array is being
  // used, i.e. how many cards there are. The cards are in
  // positions 0 through count-1 of the array.
  // We'll think of position count-1 as the current top of the deck.
  
  ////////////////////////////////////////////////////////////////////////////////////////
  public Deck() { // This is the constructor. It is called when you write
    // "new Deck()"
    
    cards = new Card[52];
    count = 0;
    
    for (int s = 0; s < 4; ++s)
      for (int v = 2; v <= 14; ++v)
      cards[count++] = new Card(s, v);
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////
  public void shuffle() { // This method should shuffle the deck.
    // this is method that you'll need to fill in
    count = 52;
    for (int i = 51; i >= 0; i=i-1){
      int randomCard = (int)(Math.random()*(i+1));
      // randomly choose a card in position 0 thru i 
      // w/ position i 
      Card cardHolder = cards[randomCard];
      cards[randomCard]= cards[i];
      cards[i] = cardHolder;
    }
    
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////
  public void print() { // This method prints the deck.
    for (int i = 0; i < count; ++i)
      System.out.println(cards[i]);
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////
  public boolean isEmpty() { // This method returns true if the deck is empty.
    return count == 0;
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////
  public Card drawCard() { // This method should "draw a card" from the deck.
    return cards[--count];
 //   throw new UnsupportedOperationException();
    
  }
  
}
