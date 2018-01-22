package model;

/**
 * Represents a card suite.
 */
public enum CardSuite {
  Diamond("red", 1), Club("black", 2), Heart("red", 3), Spade("black", 4);

  private final String color;

  // Between 1 and 4, Higher the number the better.
  private int value;

  /**
   * Makes each suite have a respective color.
   * @param color represents the color of the card
   */

  CardSuite(String color, int value) {
    this.color = color;
    this.value = value;
  }

  @Override
  public String toString() {

    String result;

    switch (this) {
      case Diamond:
        result = "\u2666";
        break;

      case Club:
        result = "\u2663";
        break;

      case Heart:
        result = "\u2665";
        break;

      case Spade:
        result = "\u2660";
        break;

      default: throw new IllegalArgumentException("Invalid CardType");
    }
    return result;
  }

  public String getColor() {
    return color;
  }

  public int getValue() {
    return this.value;
  }

}
