package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
	/* TODO ADD:
		- wave system(s)
		- game over / life 0
		- game balance -> GAIN MONEY FOR KILLS, PAY MONEY FOR PLACING (UNLOCK TOO)
		- build apk -- CORE COMPLETE
		- additional fun stuff (tiles, effects, tooltips, additional allied units, enemy attacks, play/pause)
	 */
	static int gw = 1000, gh = 600;
	SpriteBatch batch;
	OrthographicCamera camera;

	enum screen {
		START,
		ABOUT,
		ACHIEVEMENTS,
		PLAY,
		PAUSE,
		LOSE
	}
	static screen which;

	//Scenes
	static Scene start;
	static Scene game;
	static Scene about;
	static Scene achievements;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, gw, gh);
		Maps.init_maps();
		start = new Start();
		game = new Game();
		about = new About();
		achievements = new Achievements();
		which = screen.START;
	}

	@Override
	public void render () {
		tap();
		ScreenUtils.clear(1, 0, 0, 1);
		System.out.println(camera.zoom);
		camera.update();
		batch.setProjectionMatrix(camera.combined);	//VISUAL SCALING
		batch.begin();
		switch(which){
			case START:
				start.draw(batch);
				break;
			case PLAY:
				game.draw(batch);
				break;
			case ABOUT:
				about.draw(batch);
				break;
			case ACHIEVEMENTS:
				achievements.draw(batch);
				break;
			default:
				break;
		}
		batch.end();
	}

	void tap(){
		if(!Gdx.input.justTouched()) return;
		Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(v);
		int x = (int)v.x, y = (int)v.y;
		switch(which){
			case START:
				start.tap(x, y);
				break;
			case PLAY:
				game.tap(x, y);
				break;
			case ABOUT:
				about.tap(x, y);
				break;
			case ACHIEVEMENTS:
				achievements.tap(x, y);
				break;
			default:
				break;
		}
	}

	//TODO: Don't need dispose for now, last line here.
	@Override
	public void dispose () {
		batch.dispose();
	}
}
