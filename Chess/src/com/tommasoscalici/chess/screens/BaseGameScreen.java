package com.tommasoscalici.chess.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseGameScreen implements Screen {

	final Game game;
	BitmapFont font;
	InputMultiplexer inputMultiplexer;
	Vector2 screenSize;

	
	public InputMultiplexer getInputMultiplexer() {
		
		return inputMultiplexer;
	}
	
	public Vector2 getScreenSize() {
		return screenSize;
	}
	
	
	public BaseGameScreen(final Game game) {

		this.game = game;

		screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font = new BitmapFont();
		inputMultiplexer = new InputMultiplexer();

		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

		font.dispose();
	}
}