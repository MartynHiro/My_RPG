package ru.martyn_hiro.game_characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ru.martyn_hiro.GameScreen;

public abstract class GameCharacter {
    protected GameScreen gameScreen;
    protected Texture texture;
    protected final Texture hpBar;
    protected final float maxHP;
    protected float currentHP;
    protected float dmg;
    protected final float speed;
    protected Vector2 position;

    public GameCharacter(GameScreen gameScreen, String png, Vector2 position, float maxHP, float dmg, float speed) {
        this.gameScreen = gameScreen;
        this.texture = new Texture(png);
        this.hpBar = new Texture("HP_Bar.png");
        this.position = position;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.dmg = dmg;
        this.speed = speed;
    }
}
