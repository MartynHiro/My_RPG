package ru.martyn_hiro.game_characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    protected Vector2 temporaryPosition;//необходим для хранения промежуточных координат в расчетах

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

    public void takeDMG(float dmg, float dt) {
        currentHP -= dt * dmg;

        if (currentHP <= 0) {
            //TODO СМЕРТЬ
            currentHP = 0;
        }
    }

    public abstract void render(SpriteBatch batch);
    public abstract void update(float deltaTime);

    //метод ограничения передвижения рамками экрана
    protected void checkScreenBorders(Texture texture) {
        float halfWidth = (texture.getWidth() / 2.0f);
        float halfHeight = (texture.getHeight() / 2.0f);

        if (position.x >= (1920.0f - halfWidth)) {
            position.x = (1920.0f - halfWidth);
        }
        if (position.x <= 0.0f + halfWidth) {
            position.x = 0.0f + halfWidth;
        }
        if (position.y >= (980.0f - halfHeight)) {
            position.y = (980.0f - halfHeight);
        }
        if (position.y <= 0.0f + halfHeight) {
            position.y = 0.0f + halfHeight;
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getDmg() {
        return dmg;
    }

}
