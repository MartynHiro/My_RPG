package ru.martyn_hiro.game_characters.monsters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.martyn_hiro.GameScreen;
import ru.martyn_hiro.game_characters.GameCharacter;

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
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(hpBar, position.x + 25 - (texture.getWidth() / 2.0f), position.y + 155 -
                        (texture.getHeight() / 2.0f), 0, 0, currentHP,
                65, 1, 1, 0, 0, 0, 120, 65, false, false);

        batch.draw(texture, position.x - (texture.getWidth() / 2.0f), position.y - (texture.getHeight() / 2.0f));
    }

    @Override
    public void update(float deltaTime) {

        actionTimer -= deltaTime;

        if (actionTimer <= 0.0f) {
            actionTimer = MathUtils.random(3.0f, 5.0f);//случайное время от 3 до 5 сек
            //если сбрасывается таймер, то выбираем направление (случайное)
            movementDirection
                    .set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f))
                    .nor();//нормирование направления
        }

        //преследование героя, когда он входит в радиус аггро
        if (gameScreen.getHero().getPosition().dst(position) <= aggroRange) {

            //вычитаем координаты героя из наших для вычисления вектора движения
            temporaryPosition
                    .set(position)
                    .sub(gameScreen.getHero().getPosition())
                    .nor();//обязательно выравниваем к единице

            position.mulAdd(temporaryPosition, speed * deltaTime);

        } else {
            position.mulAdd(movementDirection, speed * deltaTime);

            checkScreenBorders(texture);
        }
    }
}
