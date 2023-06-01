package ru.martyn_hiro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.martyn_hiro.game_characters.heroes.Hero;
import ru.martyn_hiro.game_characters.heroes.HeroWarrior;
import ru.martyn_hiro.game_characters.monsters.Monster;
import ru.martyn_hiro.game_characters.monsters.Slime;

public class GameScreen {
    private SpriteBatch batch;
    private Hero hero;
    private Monster monster;
    float deltaTime;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;

    }

    public void create() {
        this.hero = new HeroWarrior(this);
        this.monster = new Slime(this);
    }

    public void render() {
        //спрашиваем у видеокарты про задержки, необходимо для корректного действия не зависимого от FPS
        deltaTime = Gdx.graphics.getDeltaTime();
        update(deltaTime);
        ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1);
        batch.begin();
        hero.render(batch);
        monster.render(batch);
        batch.end();
    }

    public void update(float dt) {
        //считаем расстояние
        float distanceBetweenHeroMonster = hero.getPosition().dst(monster.getPosition());

        hero.update(dt);
        monster.update(dt, distanceBetweenHeroMonster);

        //получение урона зависит от радиуса атаки и того кто атакует
        if (distanceBetweenHeroMonster <= 100f) {
            monster.takeDMG(hero.getDmg(), dt);
        }
        if (distanceBetweenHeroMonster <= 100f) {
            hero.takeDMG(monster.getDmg(), dt);
        }
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }
}
