import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import model.Big2ModelImpl;
import model.Card;
import model.Hand;
import model.HandType;
import model.IBig2Model;
import model.Player;
import model.PlayerType;

public class Big2ModelTest {

  Player p1 = new Player(PlayerType.Human, "Bob");
  Player p2 = new Player(PlayerType.Human, "Aaron");
  Player p3 = new Player(PlayerType.Human, "Nicky");
  Player p4 = new Player(PlayerType.Human, "Jeff");

  Player[] lop = new Player[4];

  IBig2Model dud;
  ArrayList<Card> basicDeckNoShuffle;


  public void init() {
    lop[0] = p1;
    lop[1] = p2;
    lop[2] = p3;
    lop[3] = p4;

    dud = new Big2ModelImpl(lop);
    basicDeckNoShuffle = dud.getDeck();

  }


  @Test
  public void testStartGame() {
    this.init();



    Assert.assertEquals(4, dud.getPlayer().length);

    dud.startGame(basicDeckNoShuffle, 1);

    Assert.assertEquals("model.Player type: Human, model.Player name: Bob, Positing at table: 0, " +
            "Is Passing: false\n" +
            "Cards currently holding: ♦A♦5♦9♦K♣4♣8♣Q♥3♥7♥J♠2♠6♠10\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Aaron, Positing at table: 1, Is Passing: false\n" +
            "Cards currently holding: ♦2♦6♦10♣A♣5♣9♣K♥4♥8♥Q♠3♠7♠J\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Nicky, Positing at table: 2, Is Passing: false\n" +
            "Cards currently holding: ♦3♦7♦J♣2♣6♣10♥A♥5♥9♥K♠4♠8♠Q\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Jeff, Positing at table: 3, Is Passing: false\n" +
            "Cards currently holding: ♦4♦8♦Q♣3♣7♣J♥2♥6♥10♠A♠5♠9♠K\n" +
            "\n" +
            "Round 1: \n" +
            "\n" +
            "Current turn: Aaron\n", dud.printState());

  }

  @Test
  public void testGame() {
    this.init();

    ArrayList<Integer> h1LOC = new ArrayList<>();
    h1LOC.add(0);

    dud.startGame(basicDeckNoShuffle, 1);

    Hand h1 = p2.generateHand(h1LOC, HandType.singleCard);

    dud.move("Aaron", h1);

    Assert.assertEquals("model.Player type: Human, model.Player name: Bob, Positing at table: 0, " +
            "Is Passing: false\n" +
            "Cards currently holding: ♦A♦5♦9♦K♣4♣8♣Q♥3♥7♥J♠2♠6♠10\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Aaron, Positing at table: 1, Is Passing: false\n" +
            "Cards currently holding: ♦6♦10♣A♣5♣9♣K♥4♥8♥Q♠3♠7♠J\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Nicky, Positing at table: 2, Is Passing: false\n" +
            "Cards currently holding: ♦3♦7♦J♣2♣6♣10♥A♥5♥9♥K♠4♠8♠Q\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Jeff, Positing at table: 3, Is Passing: false\n" +
            "Cards currently holding: ♦4♦8♦Q♣3♣7♣J♥2♥6♥10♠A♠5♠9♠K\n" +
            "\n" +
            "Round 1: \n" +
            "(singleCard, Cards: ♦2)\n" +
            "\n" +
            "Current turn: Nicky\n", dud.printState());

    dud.pass("Nicky");

    Assert.assertEquals("model.Player type: Human, model.Player name: Bob, Positing at table: 0, Is Passing: false\n" +
            "Cards currently holding: ♦A♦5♦9♦K♣4♣8♣Q♥3♥7♥J♠2♠6♠10\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Aaron, Positing at table: 1, Is Passing: false\n" +
            "Cards currently holding: ♦6♦10♣A♣5♣9♣K♥4♥8♥Q♠3♠7♠J\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Nicky, Positing at table: 2, Is Passing: true\n" +
            "Cards currently holding: ♦3♦7♦J♣2♣6♣10♥A♥5♥9♥K♠4♠8♠Q\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Jeff, Positing at table: 3, Is Passing: false\n" +
            "Cards currently holding: ♦4♦8♦Q♣3♣7♣J♥2♥6♥10♠A♠5♠9♠K\n" +
            "\n" +
            "Round 1: \n" +
            "(singleCard, Cards: ♦2)\n" +
            "\n" +
            "Current turn: Jeff\n", dud.printState());

    dud.pass("Jeff");

    dud.pass("Bob");

    Assert.assertEquals("model.Player type: Human, model.Player name: Bob, Positing at table: 0, " +
            "Is Passing: false\n" +
            "Cards currently holding: ♦A♦5♦9♦K♣4♣8♣Q♥3♥7♥J♠2♠6♠10\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Aaron, Positing at table: 1, Is Passing: false\n" +
            "Cards currently holding: ♦6♦10♣A♣5♣9♣K♥4♥8♥Q♠3♠7♠J\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Nicky, Positing at table: 2, Is Passing: false\n" +
            "Cards currently holding: ♦3♦7♦J♣2♣6♣10♥A♥5♥9♥K♠4♠8♠Q\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Jeff, Positing at table: 3, Is Passing: false\n" +
            "Cards currently holding: ♦4♦8♦Q♣3♣7♣J♥2♥6♥10♠A♠5♠9♠K\n" +
            "\n" +
            "Round 1: \n" +
            "(singleCard, Cards: ♦2)\n" +
            "Round 2: \n" +
            "\n" +
            "Current turn: Aaron\n", dud.printState());

    ArrayList<Integer> h2LOC = new ArrayList<>();
    h2LOC.add(1);
    h2LOC.add(11);
    h2LOC.add(8);
    h2LOC.add(5);
    h2LOC.add(4);

    Hand h2 = p2.generateHand(h2LOC, HandType.straight);

    dud.move("Aaron", h2);

    Assert.assertEquals("model.Player type: Human, model.Player name: Bob, Positing at table: 0, Is Passing: false\n" +
            "Cards currently holding: ♦A♦5♦9♦K♣4♣8♣Q♥3♥7♥J♠2♠6♠10\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Aaron, Positing at table: 1, Is Passing: false\n" +
            "Cards currently holding: ♦6♣A♣5♥4♥8♠3♠7\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Nicky, Positing at table: 2, Is Passing: false\n" +
            "Cards currently holding: ♦3♦7♦J♣2♣6♣10♥A♥5♥9♥K♠4♠8♠Q\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Jeff, Positing at table: 3, Is Passing: false\n" +
            "Cards currently holding: ♦4♦8♦Q♣3♣7♣J♥2♥6♥10♠A♠5♠9♠K\n" +
            "\n" +
            "Round 1: \n" +
            "(singleCard, Cards: ♦2)\n" +
            "Round 2: \n" +
            "(straight, Cards: ♣9♦10♠J♥Q♣K)\n" +
            "\n" +
            "Current turn: Nicky\n", dud.printState());

    ArrayList<Integer> h3LOC = new ArrayList<>();
    h3LOC.add(5);
    h3LOC.add(2);
    h3LOC.add(12);
    h3LOC.add(9);
    h3LOC.add(6);


    Hand h3 = p3.generateHand(h3LOC, HandType.straight);

    dud.move("Nicky", h3);

    Assert.assertEquals("model.Player type: Human, model.Player name: Bob, Positing at table: 0, Is Passing: false\n" +
            "Cards currently holding: ♦A♦5♦9♦K♣4♣8♣Q♥3♥7♥J♠2♠6♠10\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Aaron, Positing at table: 1, Is Passing: false\n" +
            "Cards currently holding: ♦6♣A♣5♥4♥8♠3♠7\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Nicky, Positing at table: 2, Is Passing: false\n" +
            "Cards currently holding: ♦3♦7♣2♣6♥5♥9♠4♠8\n" +
            "\n" +
            "model.Player type: Human, model.Player name: Jeff, Positing at table: 3, Is Passing: false\n" +
            "Cards currently holding: ♦4♦8♦Q♣3♣7♣J♥2♥6♥10♠A♠5♠9♠K\n" +
            "\n" +
            "Round 1: \n" +
            "(singleCard, Cards: ♦2)\n" +
            "Round 2: \n" +
            "(straight, Cards: ♣9♦10♠J♥Q♣K)\n" +
            "(straight, Cards: ♣10♦J♠Q♥K♥A)\n" +
            "\n" +
            "Current turn: Jeff\n", dud.printState());


  }


  @Test (expected = IllegalArgumentException.class)
  public void breakMoveNotYourTurn() {

    this.init();

    ArrayList<Integer> h1LOC = new ArrayList<>();

    dud.startGame(basicDeckNoShuffle, 1);

    Hand h1 = p2.generateHand(h1LOC, HandType.singleCard);

    dud.move("Nicky", h1);

  }


}
