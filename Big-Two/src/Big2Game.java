import java.io.InputStreamReader;

import model.Big2ModelImpl;
import model.IBig2Model;
import model.Player;
import model.PlayerType;
import view.IView;
import view.stringView;

public class Big2Game {

  public static void main(String args[]) {

    // Change this later to take in names
    Player[] p = new Player[4];
    p[0] = new Player(PlayerType.Human, "Aaron");
    p[1] = new Player(PlayerType.Human, "Steven");
    p[2] = new Player(PlayerType.Human, "Nicky");
    p[3] = new Player(PlayerType.Human, "Jeff");

    IBig2Model model = new Big2ModelImpl(p);
    IView stringView = new stringView(new InputStreamReader(System.in), System.out);

    stringView.go(model);

  }


}
