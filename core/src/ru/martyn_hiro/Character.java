package ru.martyn_hiro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Character {
    private final Texture texture;
    private final Texture hpBar;
    private final float maxHP;
    private float currentHP;
    private float dmg;
    private final float speed;
    private float x;
    private float y;

    public Character() {
        this.texture = new Texture("main_warrior.png");
        this.hpBar = new Texture("HP_Bar.png");

        this.maxHP = 100;
        this.currentHP = maxHP;
        this.speed = 300.0f;
        this.dmg = 30;

        this.x = 0.0f;
        this.y = 300.0f;
    }

    //должен уметь сам себя отрисовывать(стартовая позиция, будет зависеть от сохранялки)
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
        batch.draw(hpBar, x, y + 120, 0,0, currentHP, 65, 1,1, 0,0,0, 120, 65, false, false);

    }

    public void takeDMG(float dmg, float dt) {
        currentHP -= dt * dmg;

        if (currentHP <= 0) {
            //TODO СМЕРТЬ
            currentHP = 0;
        }
    }

    //что происходит с нашим персонажем во времени
    public void update(float dt) {

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= speed * dt;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDmg() {
        return dmg;
    }
}
