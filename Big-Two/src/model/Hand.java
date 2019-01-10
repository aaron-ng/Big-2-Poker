package model;

import java.util.ArrayList;
import java.util.Comparator;

public class Hand {

  private ArrayList<Card> hand;
  private HandType handType;

  public Hand(ArrayList<Card> hand, HandType handType) {

    if (this.handMatch(hand, handType)) {
      this.hand = hand;
      this.handType = handType;
    } else {
      throw new IllegalArgumentException("The hand does not match its handType");
    }

  }


  /**
   * Gets the most highly rated card in the deck.
   * @return the most valuable card in the deck.
   */
  public Card mostValuableCardInHand() {

    Card result = hand.get(0);

    for (Card currC: hand) {
      if (result.getCv().toInt() == currC.getCv().toInt()
              && result.getCs().getValue() < currC.getCs().getValue()) {
        result = currC;
      }

      if (result.getCv().toInt() < currC.getCv().toInt()) {
        result = currC;
      }
    }

    return result;
  }

  @Override
  public String toString() {

    String finalResult =  "(" + handType.name() + ", Cards: ";

    finalResult = finalResult;

    for (Card c: this.hand) {
      finalResult = finalResult + c.toString();
    }

    return finalResult  + ")";
  }

  public ArrayList<Card> getHand() {
    return hand;
  }

  public void setHand(ArrayList<Card> hand) {
    this.hand = hand;
  }

  public HandType getHandType() {
    return handType;
  }


  /**
   * Checks if the given hand matches the given handType.
   * @param hand represents the hand being taken in.
   * @param handType represents the handType being taken in.
   * @return a boolean true if the given hand matches the given handType else false.
   */
  private boolean handMatch(ArrayList<Card> hand, HandType handType) {

    hand.sort(new Comparator<Card>() {
      @Override
      public int compare(Card o1, Card o2) {
        return o1.getCv().toInt() - o2.getCv().toInt();
      }
    });

    switch (handType) {
      case singleCard:
        return hand.size() == 1;

      case pair:
        return hand.size() == 2 && hand.get(0).getCv().toInt() == hand.get(1).getCv().toInt();

      case trips:
        return hand.size() == 3 && hand.get(0).getCv().toInt() == hand.get(1).getCv().toInt()
                && hand.get(1).getCv().toInt() == hand.get(2).getCv().toInt();

      case straight:
        return this.checkStraight(hand);

      case flush:
        return this.checkFlush(hand);

      case fullHouse:

        int cardValue1 = hand.get(0).getCv().toInt();
        int cardValueCount1 = 1;

        int cardValue2 = -1;
        int cardValueCount2 = 0;

        for (int i = 1; i < hand.size(); i++) {
          if (hand.get(i).getCv().toInt() == cardValue1) {
            cardValueCount1++;
          } else {
            cardValue2 = hand.get(i).getCv().toInt();
            cardValueCount2++;
          }
        }

        return (cardValueCount1 == 3 && cardValueCount2 == 2)
                ||  (cardValueCount1 == 2 && cardValueCount2 == 3);

      case quad:
        int cardValue3 = hand.get(0).getCv().toInt();
        int cardValueCount3 = 1;

        int cardValue4 = -1;
        int cardValueCount4 = 0;

        for (int i = 1; i < hand.size(); i++) {

          if (hand.get(i).getCv().toInt() == cardValue3) {
            cardValueCount3++;
          } else {
            cardValue4 = hand.get(i).getCv().toInt();
            cardValueCount4++;
          }
        }

        return (cardValueCount3 == 4 && cardValueCount4 == 1)
                ||  (cardValueCount3 == 1 && cardValueCount4 == 4);

      case straightFlush:

        return this.checkStraight(hand) && this.checkFlush(hand);

      case royalFlush:
        return this.checkStraight(hand) && this.checkFlush(hand) && hand.get(0).getCv().toInt() == 8;

      default:
        throw new IllegalArgumentException("Should never reach here");
    }

  }

  /**
   * Checks if the given hand is a straight.
   * @param hand the hand being checked.
   * @return boolean checking if the hand is a straight.
   */
  private boolean checkStraight(ArrayList<Card> hand) {
    int currValue = hand.get(0).getCv().toInt();
    for (Card c: hand) {
      if (c.getCv().toInt() == currValue) {
        currValue++;
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if the given hand is a flush.
   * @param hand the hand being checked
   * @return boolean true if the hand is a flush else false
   */
  private boolean checkFlush(ArrayList<Card> hand) {
    CardSuite currSuite = hand.get(0).getCs();
    for (Card c: hand) {
      if (!c.getCs().equals(currSuite)) {
        return false;
      }
    }
    return true;
  }


}
