package model;

/**
 * Enum for a card value.
 */
public enum CardValue {
  Ace, two, three, four, five, six, seven, eight, nine, ten, Jack, Queen, King;

  /*
    Turns the model.CardValue enum into a String
   */
  @Override
  public String toString() {
    String result;

    switch (this) {
      case Ace:
        result = "A";
        break;
      case two:
        result = "2";
        break;
      case three:
        result = "3";
        break;
      case four:
        result = "4";
        break;
      case five:
        result = "5";
        break;
      case six:
        result = "6";
        break;
      case seven:
        result = "7";
        break;
      case eight:
        result = "8";
        break;
      case nine:
        result = "9";
        break;
      case ten:
        result = "10";
        break;
      case Jack:
        result = "J";
        break;
      case Queen:
        result = "Q";
        break;
      case King:
        result = "K";
        break;
      default: throw new IllegalArgumentException("Invalid model.CardValue");
    }
    return result;
  }

  /**
   * Produces an int based on the enum. Most valuable card is 2, lowest card is 3 of diamonds.
   * @return returns a int based on the enum
   */

  public int toInt() {
    int result;

    switch (this) {
      case Ace:
        result = 12;
        break;
      case two:
        result = 13;
        break;
      case three:
        result = 1;
        break;
      case four:
        result = 2;
        break;
      case five:
        result = 3;
        break;
      case six:
        result = 4;
        break;
      case seven:
        result = 5;
        break;
      case eight:
        result = 6;
        break;
      case nine:
        result = 7;
        break;
      case ten:
        result = 8;
        break;
      case Jack:
        result = 9;
        break;
      case Queen:
        result = 10;
        break;
      case King:
        result = 11;
        break;
      default: throw new IllegalArgumentException("Invalid model.CardValue");
    }
    return result;
  }


}
