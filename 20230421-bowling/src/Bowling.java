import models.Game;
import models.NormalScoreCalculator;
import models.Player;
import models.ScoreCalculator;

public class Bowling {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Player player = new Player("황금오른손");
        ScoreCalculator scoreCalculator = new NormalScoreCalculator();
        Game game = new Game(scoreCalculator);

        while (!game.finished()) {
            player.throwBall(game);
        }

        System.out.println(game.frames());
    }
}
