package ru.martyn_hiro.game_characters.monsters;

import com.badlogic.gdx.math.Vector2;
import ru.martyn_hiro.GameScreen;

public class Slime extends Monster {

    public Slime(GameScreen game) {
        super("slime.png", game, 500f, 200f, 5f, 100.0f, new Vector2(1000.0f, 300.0f));
    }
}
