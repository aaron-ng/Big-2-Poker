package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Card;
import model.Hand;
import model.HandType;
import model.IBig2Model;
import model.Player;

public class stringView implements IView<String> {

  final Readable rd;
  final Appendable ap;

  public stringView(Readable rd, Appendable ap) {

    if (rd != null && ap != null) {
      this.rd = rd;
      this.ap = ap;
    } else {
      throw new IllegalArgumentException("Invalid inputs");
    }
  }


  @Override
  public String go(IBig2Model model) {

    Scanner myScanner = new Scanner(this.rd);

    // Setting up the game
    ArrayList<Card> deck = model.getDeck();
    model.startGame(deck, -1);

    while (!model.isGameOver()) {

      Player currentPlayer = model.getPlayer()[model.getCurrentPlayer()];
      String currentPlayerName = currentPlayer.getPlayerName();

      // Card input is structured as the following:
      // First string = Type of hand
      // Following numbers = Card position starting at 0;

      System.out.print(currentPlayer.cardsAsString());
      System.out.print(model.printMainPile());

      String cardInput;
      // Ask for hand
      System.out.print("Your turn " + currentPlayerName + " select your cards: \n");

      cardInput = myScanner.nextLine();

      String[] tempInput = cardInput.split(" ");

      // If pass do pass and ignore rest
      if (tempInput[0].equals("Pass")) {
        model.pass(currentPlayerName);
        continue;
      }


      // TODO Check Handtype name is correct
      String handTypeTemp = tempInput[0];

      HandType handtype;

      try {
        handtype = stringToHandType(handTypeTemp);
      } catch(Exception e) {
        System.out.print("Invalid hand type, try reformatting it correctly \n");
        continue;
      }

      ArrayList<Integer> input = new ArrayList<Integer>();

      for (int i = 1; i < tempInput.length; i++) {
        input.add(Integer.parseInt(tempInput[i]));
      }

      Hand currHand;

      try {
        // Generate the actual hand
        currHand = currentPlayer.generateHand(input, handtype);
      } catch(Exception e) {
        System.out.print("Invalid hand try again");
        continue;
      }


      try {
        model.move(currentPlayerName, currHand);
      } catch(Exception e) {
        System.out.print("Invalid move try again, \n");
        continue;
      }

    }


    // Game is over
    System.out.print("Game is over. Winner is:"
            + model.getPlayer()[model.getCurrentPlayer()].getPlayerName());


    return null;
  }

  private HandType stringToHandType(String given) {

    HandType result = null;

    switch (given) {
      case "Single":
        result = HandType.singleCard;
        break;

      case "Pair":
        result = HandType.pair;
        break;

      case "Trips":
        result = HandType.trips;
        break;

      case "Straight":
        result = HandType.straight;
        break;

      case "Flush":
        result = HandType.flush;
        break;

      case "FullHouse":
        result = HandType.fullHouse;
        break;

      case "Quad":
        result = HandType.quad;
        break;

      case "StraightFLush":
        result = HandType.straightFlush;
        break;

      case "RoyalFlush":
        result = HandType.royalFlush;
        break;

      default:
        throw new IllegalArgumentException("Unknown Hand Type");

    }


    return result;
  }

}
