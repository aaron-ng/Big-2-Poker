package model;

public enum HandType {
  singleCard(1), pair(2), trips(3), straight(4), flush(5), fullHouse(6), quad(7), straightFlush(8),
  royalFlush(9);

  // Higher Number the better.
  int value;

  HandType(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }



}
