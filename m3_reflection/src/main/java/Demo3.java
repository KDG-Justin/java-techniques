import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;
import be.kdg.java2.gameproject.model.Item;
import be.kdg.java2.gameproject.reflection.ReflectionTools;

public class Demo3 {
    public static void main(String[] args) {
        ReflectionTools.classAnalysis(Item.class, Game.class, Games.class);
        ReflectionTools.runAnnotated(Game.class);
    }
}
