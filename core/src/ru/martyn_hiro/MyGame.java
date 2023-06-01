package ru.martyn_hiro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGame extends ApplicationAdapter {
    private GameScreen gameScreen;
    private SpriteBatch batch;
    private float dt;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.gameScreen = new GameScreen(batch);
        this.gameScreen.create();
    }

    //метод отрисовки
    @Override
    public void render() {
        gameScreen.render();
    }

    //метод с логикой
    public void update() {

	}

    @Override
    public void dispose() {
        batch.dispose();
    }
}

