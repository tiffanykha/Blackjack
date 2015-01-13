public class Card {

  // final public static String [] SUITS = { 
    //      "Spades", "Hearts", "Diamonds", "Clubs" }; 
  
  private int suit; // A card has a suit.
  private int value; // A card has a value.  11=Jack, 12=Queen, 13=King, 14=Ace
  
  ////////////////////////////////////////////////////////////////////////////////////////
  public Card(int s, int v) { // This is the constructor
    suit = s;
    value = v;
  }
  
  public int getSuit() {      // These are public "getter" methods.
    return suit;
  }
  
  public int getValue() {
    return value;
  }
  
  public String getSuitName() {    // This gives a string version of the name
    switch (suit) {
      case 0:
        return "Spades";
      case 1:
        return "Hearts";
      case 2:
        return "Diamonds";
      default:
        return "Clubs";
    }
  }
  
  
  
  ////////////////////////////////////////////////////////////////////////////////////////
  public String toString() { // This method creates a string
    // describing the card.
    //11=Jack, 12=Queen, 13=King, 14=Ace
    if (value==11)
      return "Jack of " + getSuitName();
    else if (value ==12)
      return "Queen of " + getSuitName();
    else if (value==13)
      return "King of " + getSuitName();
    else if (value==14)
      return "Ace of " + getSuitName();
    else
      return value + " of " + getSuitName();
  }
}
