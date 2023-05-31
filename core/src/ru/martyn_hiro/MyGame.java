package ru.martyn_hiro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Character hero;
    private Monster monster;
    private float dt;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.hero = new Character();
        this.monster = new Slime(this);
    }

    //метод отрисовки
    @Override
    public void render() {
        //спрашиваем у видеокарты про задержки, необходимо для корректного действия не зависимого от FPS
        dt = Gdx.graphics.getDeltaTime();
        update();
        ScreenUtils.clear(0.3f, 0.93f, 0.2f, 1);
        batch.begin();
        hero.render(batch);
        monster.render(batch);
        batch.end();
    }

    //метод с логикой
    public void update() {
		//считаем расстояние по Пифагору между героем и монстром
        float distance = (float) Math.sqrt(
                (hero.getX() - monster.getX()) * (hero.getX() - monster.getX()) +
						(hero.getY() - monster.getY()) * (hero.getY() - monster.getY()));

		hero.update(dt);
		monster.update(dt, distance);

        //получение урона зависит от радиуса атаки и того кто атакует
        if (distance <= 100f) {
            monster.takeDMG(hero.getDmg(), dt);
        }
        if (distance <= 100f) {
            hero.takeDMG(monster.getDmg(), dt);
        }
	}

    public Character getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

