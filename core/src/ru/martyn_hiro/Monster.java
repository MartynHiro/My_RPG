package ru.martyn_hiro;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Monster {
    private final Texture texture;
    private final Texture hpBar;
    private final float maxHP;
    private float currentHP;
    private float dmg;
    private MyGame game;
    private final float speed;
    private final float aggroRange;
    private float x;
    private float y;

    public Monster(String png, MyGame game, float aggroRange, float hp, float dmg) {
        this.hpBar = new Texture("HP_Bar.png");
        this.texture = new Texture(png);

        this.game = game;

        this.x = 1000.0f;
        this.y = 300.0f;

        this.aggroRange = aggroRange;
        this.maxHP = hp;
        this.currentHP = maxHP;
        this.dmg = dmg;
        this.speed = 100.0f;
    }

    //должен уметь сам себя отрисовывать
    public void render(SpriteBatch batch) {
        batch.draw(hpBar, x + 75, y + 155, 0,0, currentHP, 65, 1,1, 0,0,0, 120, 65, false, false);
        batch.draw(texture, x, y);
    }

    public void takeDMG(float dmg, float dt) {
        currentHP -= dt * dmg;

        if (currentHP <= 0) {
            //TODO СМЕРТЬ
            currentHP = 0;
        }
    }

    public void update(float dt, float distance) {

        //преследование героя, когда он входит в радиус аггро
        if (distance <= aggroRange) {
            if (x < game.getHero().getX()) {
                x += speed * dt;
            }
            if (x > game.getHero().getX()) {
                x -= speed * dt;
            }
            if (y < game.getHero().getY()) {
                y += speed * dt;
            }
            if (y > game.getHero().getY()) {
                y -= speed * dt;
            }
        } else {
            //если дошел до края экрана, то переход на другую сторону
            x += speed * dt;
            if (x >= 1920.0f) {
                x = 0.0f;
            }
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
