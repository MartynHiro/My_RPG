package ru.martyn_hiro.game_characters.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.martyn_hiro.game_characters.GameCharacter;
import ru.martyn_hiro.GameScreen;

public abstract class Monster extends GameCharacter {
    protected float actionTimer; //частота действий

    protected final float aggroRange;
    protected Vector2 movementDirection;


    public Monster(String png, GameScreen gameScreen, float aggroRange, float maxHP, float dmg, float speed, Vector2 position) {
        super(gameScreen, png, position, maxHP, dmg, speed);

        this.actionTimer = 0.0f;
        this.movementDirection = new Vector2(0.0f, 0.0f);//в начале нет направления
        this.aggroRange = aggroRange;
    }

    //должен уметь сам себя отрисовывать
    public void render(SpriteBatch batch) {
        batch.draw(hpBar, position.x + 25 - (texture.getWidth() / 2.0f), position.y + 155 -
                        (texture.getHeight() / 2.0f), 0, 0, currentHP,
                65, 1, 1, 0, 0, 0, 120, 65, false, false);

        batch.draw(texture, position.x - (texture.getWidth() / 2.0f), position.y - (texture.getHeight() / 2.0f));
    }

    public void takeDMG(float dmg, float dt) {
        currentHP -= dt * dmg;

        if (currentHP <= 0) {
            //TODO СМЕРТЬ
            currentHP = 0;
        }
    }

    public void update(float deltaTime, float distance) {

        actionTimer -= deltaTime;

        if (actionTimer <= 0.0f) {
            actionTimer = MathUtils.random(3.0f, 5.0f);//случайное время от 3 до 5 сек
            //если сбрасывается таймер, то выбираем направление (случайное)
            movementDirection.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f));
            movementDirection.nor();//нормирование направления
        }

        //преследование героя, когда он входит в радиус аггро
        if (distance <= aggroRange) {

            //TODO преследование


        } else {
            position.mulAdd(movementDirection, speed * deltaTime);

            float halfWidth = (texture.getWidth() / 2.0f);
            float halfHeight = (texture.getHeight() / 2.0f);

            //если дошел до края экрана, то не может пройти дальше
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
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getDmg() {
        return dmg;
    }
}
