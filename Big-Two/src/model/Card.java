package model;

/**
 * Represents a card.
 */
public class Card {
  private final CardSuite cs;
  private final CardValue cv;

  public Card(CardSuite cs, CardValue cv) {
    this.cs = cs;
    this.cv = cv;
  }


  @Override
  public String toString() {
    return cs.toString() + cv.toString();
  }

  public CardSuite getCs() {
    return cs;
  }

  public CardValue getCv() {
    return cv;
  }
}
