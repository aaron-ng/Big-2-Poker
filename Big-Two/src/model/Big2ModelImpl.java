package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Big2ModelImpl implements IBig2Model {

  private ArrayList<Hand> mainPile = new ArrayList<>();
  private Player[] players;
  private int currentPlayer;
  private boolean isGameOver = false;


  public Big2ModelImpl(Player[] players) {
    if (players.length != 4) {
      throw new IllegalArgumentException("Only allow 4 players for this model");
    }

    this.players = players;
    this.mainPile.add(null);

    // Set the player position of the player
    for (int i = 0; i < this.players.length; i++) {

      this.players[i].setPositionInPlay(i);
    }
  }


  @Override
  public void move(String playerName, Hand h) {

    int givenPlayer = -1;

    for (Player p: this.players) {
      if (p.getPlayerName().equals(playerName)) {
        givenPlayer = p.getPositionInPlay();
      }
    }

    // Check if the player is found
    if (givenPlayer == -1) {
      throw new IllegalArgumentException("model.Player not found");
    }

    // Make sure the player moving is his turn
    if (givenPlayer != currentPlayer) {
      throw new IllegalArgumentException("Not your turn");
    }

    Hand prevHand = this.mainPile.get(this.mainPile.size() - 1);

    // If last mainPile hand is null therefore everyone passes and you can play anything
    if (prevHand == null) {
      this.mainPile.add(h);
      for (Card c: h.getHand()) {
        this.players[givenPlayer].getHoldingHand().remove(c);
      }

      if (this.currentPlayer == 3) {
        this.currentPlayer = 0;
      } else {
        this.currentPlayer++;
      }

      return;
    }

    // If singles, doubles or trips the previous hand types must match the current, else check if equal or greater strength
    if (prevHand.getHandType().equals(HandType.singleCard)
            || prevHand.getHandType().equals(HandType.pair)
            || prevHand.getHandType().equals(HandType.trips)) {


      if (!prevHand.getHandType().equals(h.getHandType())) {

        throw new IllegalArgumentException("Wrong hand type, previous hand:" + prevHand.getHandType() +
                "Given hand:" + h.getHandType());

      }
    } else {

      if (prevHand.getHandType().value > h.getHandType().value) {
        throw new IllegalArgumentException("Current hand type is not strong enough");
      }
    }

    // If the givenHand can be placed on the mainPile

    if (this.handStrongEnough(prevHand, h)) {

      // Add the hand to the mainPile
      this.mainPile.add(h);
      // Remove the cards in the hand from the player
      for (Card c: h.getHand()) {
        this.players[givenPlayer].getHoldingHand().remove(c);
      }
    } else {
      throw new IllegalArgumentException("Cannot move card.");
    }


    // Check if the player has any card left, if he has none left then set isGameOver to true.
    if (this.players[givenPlayer].getHoldingHand().isEmpty()) {
      this.isGameOver = true;
    }

  }

  @Override
  public void pass(String playerName) {

    // Finding the player
    int temp = -1;

    for (Player p: this.players) {
      if (p.getPlayerName().equals(playerName)) {
        temp = p.getPositionInPlay();
      }
    }

    // Checking if it the current player can pass or not.

    // Check if the player is found
    if (temp == -1) {
      throw new IllegalArgumentException("model.Player not found");
    }

    // Make sure the player moving is his turn
    if (temp != currentPlayer) {
      throw new IllegalArgumentException("Not your turn");
    }


    Player givenPlayer = this.players[temp];

    // Setting the player to pass, moving onto next player
    givenPlayer.setPassing(true);

    if (this.currentPlayer == 3) {
      this.currentPlayer = 0;
    } else {
      this.currentPlayer++;
    }


    // If three people have passed then set player to the person who has not passed and insert null
    // into the mainPile

    boolean p1Pass = this.players[0].isPassing();
    boolean p2Pass = this.players[1].isPassing();
    boolean p3Pass = this.players[2].isPassing();
    boolean p4Pass = this.players[3].isPassing();

    int passCount = 0;

    if (p1Pass) {
      passCount++;
    }

    if (p2Pass) {
      passCount++;
    }

    if (p3Pass) {
      passCount++;
    }

    if (p4Pass) {
      passCount++;
    }

    // Three people have passed.
    if (passCount == 3) {
      // Finding the person that has not passed.

      Player stillIn = null;
      for (Player p: this.players) {
        if (!p.isPassing()) {
          stillIn = p;
        }
      }

      // Set the currentPlayer to the person that has not passed
      this.currentPlayer = stillIn.getPositionInPlay();
      // New round put in a break in the arraylist
      this.mainPile.add(null);

      // Set everyone that passed to not passing since new round
      for (Player p: this.players) {
        p.setPassing(false);
      }
    }


  }

  // Builds a Standard Deck, Non Shuffled
  @Override
  public ArrayList getDeck() {

    ArrayList<Card> finalResult = new ArrayList<Card>();

    // Building the Deck, with every single possible suite
    for (CardSuite cs : CardSuite.values()) {
      for (CardValue cv : CardValue.values()) {
        finalResult.add(new Card(cs, cv));
      }
    }

    return finalResult;
  }

  @Override
  public void startGame(ArrayList<Card> deck, int startingPlayer) {

    // Dealing cards
    int currPlayer = 0;

    for (Card aDeck : deck) {

      if (currPlayer != players.length) {
        this.players[currPlayer].getHoldingHand().add(aDeck);
        currPlayer++;

      } else {

        currPlayer = 0;
        this.players[currPlayer].getHoldingHand().add(aDeck);
        currPlayer++;
      }
    }

    this.currentPlayer = startingPlayer;

  }

  @Override
  public boolean isGameOver() {
    return this.isGameOver;
  }

  @Override
  public Player[] getPlayer() {
    return this.players;
  }

  @Override
  public ArrayList<Hand> getMainPile() {
    return this.mainPile;
  }

  @Override
  public String printState() {

    String finalResult = "";

    // Printing player information
    for (Player p: this.players) {
      finalResult = finalResult + p.cardsAsString() + "\n";
    }

    // Printing main pile
    int roundCount = 1;

    for (Hand h: this.mainPile) {

      if (h == null) {
        finalResult = finalResult + "Round " + roundCount + ": \n";
        roundCount++;
        continue;
      }
      finalResult = finalResult + h.toString() + "\n";
    }

    // Print out who's turn it is right now.
    finalResult = finalResult + "\nCurrent turn: " + this.players[this.currentPlayer].getPlayerName();

    return finalResult;
  }


  /**
   * Checks two hands and sees which one is the stronger one is. If givenHand is stronger then
   * return true, else return false. Assumes all hands are valid hands.
   * @param prevHand the previous played hand.
   * @param givenHand the next given hand.
   * @return a boolean if the hand is playable.
   */
  private boolean handStrongEnough(Hand prevHand, Hand givenHand) {


    if (!(prevHand.getHandType().equals(HandType.singleCard)
            || prevHand.getHandType().equals(HandType.pair)
            || prevHand.getHandType().equals(HandType.trips))) {

      if (prevHand.getHandType().value < givenHand.getHandType().value) {
        return true;
      }
    }

    Card prevHighest = prevHand.mostValuableCardInHand();
    Card givenHighest = givenHand.mostValuableCardInHand();

    // if the prevHand and the givenHand has the same hand value
    switch (prevHand.getHandType()) {

      case singleCard:
        // Same card number, then check suites
        if (prevHand.getHand().get(0).getCv().toInt() == givenHand.getHand().get(0).getCv().toInt()) {
          return prevHand.getHand().get(0).getCs().getValue() < prevHand.getHand().get(0).getCs().getValue();
        }

        else return prevHand.getHand().get(0).getCv().toInt() < givenHand.getHand().get(0).getCv().toInt();


      case pair:
        // Get the most valuable card in the pair (the one with the higher suite)

        if (prevHighest.getCv().toInt() == givenHighest.getCv().toInt()) {
          return prevHighest.getCs().getValue() < givenHighest.getCs().getValue();
        }
        else return prevHighest.getCv().toInt() < givenHighest.getCv().toInt();



      case trips:

        return prevHand.getHand().get(0).getCv().toInt() < givenHand.getHand().get(0).getCv().toInt();

      case straight:

        return prevHighest.getCv().toInt() < givenHighest.getCv().toInt();

      case flush:

        return prevHighest.getCs().getValue() < givenHighest.getCs().getValue();

      case fullHouse:

        int prevMaxInt = this.mostRecurringValue(prevHand);
        int givenMaxInt = this.mostRecurringValue(givenHand);

        return prevMaxInt < givenMaxInt;

      case quad:

        return prevHighest.getCv().toInt() < givenHighest.getCv().toInt();


      case straightFlush:

        // highest base integer wins
        if (prevHighest.getCv().toInt() < givenHighest.getCv().toInt())  {
          return true;
        } else {
          return prevHighest.getCs().getValue() < givenHighest.getCs().getValue();
        }



      case royalFlush:

        return prevHighest.getCs().getValue() < givenHighest.getCs().getValue();

      default:
        throw new IllegalArgumentException("Should never reach here");
    }


  }


  /**
   * Find the card value that occurs the most in a single hand. Used to determine who has the
   * higher full house.
   * @param givenH the hand that is being searched.
   * @return the value of the most recurring card value.
   */
  private int mostRecurringValue(Hand givenH) {
    // Find the most repeated card in both files

    HashMap<Integer, Integer> counterPrev = new HashMap<>();

    for (Card prevC: givenH.getHand()) {
      if (counterPrev.containsKey(prevC.getCv().toInt())) {
        counterPrev.put(prevC.getCv().toInt(), counterPrev.get(prevC.getCv().toInt()) + 1);
      } else {
        counterPrev.put(prevC.getCv().toInt(), 0);
      }
    }

    int prevMaxKey = -1;
    int prevMaxVal = -1;

    for (Map.Entry<Integer, Integer> entry: counterPrev.entrySet()) {
      if (entry.getValue() > prevMaxVal) {
        prevMaxKey = entry.getKey();
        prevMaxVal = entry.getValue();
      }
    }

    return prevMaxKey;
  }

}
