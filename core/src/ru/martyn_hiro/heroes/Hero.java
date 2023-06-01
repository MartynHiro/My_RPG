package ru.martyn_hiro.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Hero {
    private final Texture texture;
    private final Texture hpBar;
    private final float maxHP;
    private float currentHP;
    private float dmg;
    private final float speed;
    private Vector2 position;

    public Hero(Texture texture, float maxHP, float speed, float dmg) {
        this.texture = texture;
        this.hpBar = new Texture("HP_Bar.png");

        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.speed = speed;
        this.dmg = dmg;

        this.position = new Vector2(0.0f + (texture.getWidth() / 2.0f), 300.0f + (texture.getHeight() / 2.0f));
    }

    //должен уметь сам себя отрисовывать(стартовая позиция, будет зависеть от сохранялки)
    public void render(SpriteBatch batch) {
        //деления нужны для оцентровки персонажа
        batch.draw(texture, position.x - (texture.getWidth() / 2.0f), position.y - (texture.getHeight() / 2.0f));
        batch.draw(hpBar, position.x - (texture.getWidth() / 2.0f), position.y - (texture.getHeight() / 2.0f) +
                        120, 0,0, currentHP, 65, 1,1,
                0,0,0, 120, 65, false, false);

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
            position.x -= speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * dt;
        }

        if (position.x >= (1920.0f - (texture.getWidth() / 2.0f))) {
            position.x = (1920.0f - (texture.getWidth() / 2.0f));
        }
        if (position.x <= 0.0f + (texture.getWidth() / 2.0f)) {
            position.x = 0.0f + (texture.getWidth() / 2.0f);
        }
        if (position.y >= (980.0f - (texture.getHeight() / 2.0f))) {
            position.y = (980.0f - (texture.getHeight() / 2.0f));
        }
        if (position.y <= 0.0f + (texture.getHeight() / 2.0f)) {
            position.y = 0.0f + (texture.getHeight() / 2.0f);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getDmg() {
        return dmg;
    }
}
