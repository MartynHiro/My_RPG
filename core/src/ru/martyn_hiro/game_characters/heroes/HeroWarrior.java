package ru.martyn_hiro.game_characters.heroes;

import com.badlogic.gdx.math.Vector2;
import ru.martyn_hiro.GameScreen;

public class HeroWarrior extends Hero{
    public HeroWarrior(GameScreen game) {
        super("main_warrior.png", game, 100.0f, 30.0f, 300.0f, new Vector2(300.0f , 300.0f));
    }
}
