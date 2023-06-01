package ru.martyn_hiro.game_characters.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.martyn_hiro.GameScreen;
import ru.martyn_hiro.game_characters.GameCharacter;

public abstract class Hero extends GameCharacter {

    public Hero(String png, GameScreen gameScreen, float maxHP, float dmg, float speed, Vector2 position) {
        super(gameScreen, png, position, maxHP, dmg, speed);
    }

    //должен уметь сам себя отрисовывать(стартовая позиция, будет зависеть от сохранялки)
    public void render(SpriteBatch batch) {
        //деления нужны для оцентровки персонажа
        batch.draw(texture, position.x - (texture.getWidth() / 2.0f), position.y - (texture.getHeight() / 2.0f));

        batch.draw(hpBar, position.x - (texture.getWidth() / 2.0f), position.y - (texture.getHeight() / 2.0f) +
                        120, 0, 0, currentHP, 65, 1, 1,
                0, 0, 0, 120, 65, false, false);

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
        //управление
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

    public Vector2 getPosition() {
        return position;
    }

    public float getDmg() {
        return dmg;
    }
}
