package ru.martyn_hiro.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.martyn_hiro.GameScreen;

public abstract class Monster {
    //общее
    private GameScreen gameScreen;
    private float actionTimer; //частота действий

    //текстуры
    private final Texture texture;
    private final Texture hpBar;
    //параметры
    private final float maxHP;
    private float currentHP;
    private float dmg;
    private final float speed;
    private final float aggroRange;
    //положение
    private Vector2 position;
    private Vector2 movementDirection;

    public Monster(String png, GameScreen gameScreen, float aggroRange, float hp, float dmg) {
        this.hpBar = new Texture("HP_Bar.png");
        this.texture = new Texture(png);

        this.gameScreen = gameScreen;
        this.actionTimer = 0.0f;

        this.position = new Vector2(1000.0f, 300.0f);
        this.movementDirection = new Vector2(0.0f, 0.0f);//в начале нет направления

        this.aggroRange = aggroRange;
        this.maxHP = hp;
        this.currentHP = maxHP;
        this.dmg = dmg;
        this.speed = 100.0f;
    }

    //должен уметь сам себя отрисовывать
    public void render(SpriteBatch batch) {
        batch.draw(hpBar, position.x + 25 - (texture.getWidth() / 2.0f), position.y + 155 -
                        (texture.getHeight() / 2.0f), 0,0, currentHP,
                65, 1,1, 0,0,0, 120, 65, false, false);
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

            





        } else {
            position.mulAdd(movementDirection, speed * deltaTime);

            //если дошел до края экрана, то не может пройти дальше (1920 ширина экрана - 200 ширина модельки)
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
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getDmg() {
        return dmg;
    }
}
