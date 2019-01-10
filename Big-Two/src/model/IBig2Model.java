package model;

import java.util.ArrayList;

public interface IBig2Model {

  /**
   * Removes and Adds the hand from the player onto the main pile, if and only if it satisfies all
   * requirements.
   * @param playerName represents the name of the player.
   * @param h represents the hand being played
   */
  void move(String playerName, Hand h);

  /**
   * Makes the given player pass the current set.
   * @param playerName the player passing.
   */
  void pass(String playerName);


  /**
   * Gets a standard 52 card deck.
   * @return a deck.
   */
  ArrayList<Card> getDeck();

  /**
   * Initializes an instance of the game.
   */
  void startGame(ArrayList<Card> deck, int startingPlayer);

  /**
   * Get starting player
   */
  int startingPlayer();

  /**
   * Sees if the game is over or not.
   * @return represents if the game is over.
   */
  boolean isGameOver();

  /**
   * Returns the list of players playing in the game.
   * @return represents the players that are currently playing.
   */
  Player[] getPlayer();

  /**
   * Get the current player
   *
   */
  int getCurrentPlayer();

  /**
   * Returns the mainPile.
   * @return the mainPile.
   */
  ArrayList<Hand> getMainPile();

  /**
   * Prints the main pile only
   */
  String printMainPile();

  /**
   * Prints the current state with all player hands and main pile.
   * @return the game state.
   */
  String printState();



}
