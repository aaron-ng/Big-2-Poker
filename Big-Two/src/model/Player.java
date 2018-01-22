package model;

import java.util.ArrayList;

/**
 * Represents a player.
 */
public class Player {

  private ArrayList<Card> holdingHand;
  private PlayerType pt;
  private String playerName;
  private int positionInPlay;
  private boolean passing = false;

  public Player(ArrayList<Card> holdingHand, PlayerType pt, String playerName) {
    this.holdingHand = holdingHand;
    this.pt = pt;
    this.playerName = playerName;

  }

  public Player(PlayerType pt, String playerName) {
    this.holdingHand = new ArrayList<>();
    this.pt = pt;
    this.playerName = playerName;
  }


  /**
   * Returns the player information as a string.
   * @return the player information.
   */
  public String cardsAsString() {

    String finalResult = "model.Player type: " + this.pt.name() + ", model.Player name: " + playerName
            + ", Positing at table: " + positionInPlay + ", Is Passing: " + this.passing + "\n";

    finalResult = finalResult + "Cards currently holding: ";



    for (Card c: this.holdingHand) {
      finalResult = finalResult + c.toString();
    }
    return finalResult + "\n";
  }

  /**
   * Takes in the card index's for a players holding hand and creates a hand.
   * @param givenCards represents the index's of the hands.
   * @param ht represents the hand type.
   * @return the hand.
   */
  public Hand generateHand(int[] givenCards, HandType ht) {

    ArrayList<Card> handCards = new ArrayList<>();

    for (int i = 0; i < givenCards.length; i++) {

      handCards.add(this.holdingHand.get(givenCards[i]));

    }
    return new Hand(handCards, ht);

  }



  public ArrayList<Card> getHoldingHand() {
    return holdingHand;
  }

  public PlayerType getPt() {
    return pt;
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getPositionInPlay() {
    return positionInPlay;
  }

  public void setPositionInPlay(int positionInPlay) {
    this.positionInPlay = positionInPlay;
  }

  public boolean isPassing() {
    return passing;
  }

  public void setPassing(boolean passing) {
    this.passing = passing;
  }
}
