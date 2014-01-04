
package tommasoscalici.chess.screens;

import tommasoscalici.chess.actors.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;

public class MainMenuScreen extends BaseGameScreen {

	private Stage stage;
	private MenuItem localGameMenuItem, multiplayGameMenuItem, exitGameMenuItem;

	public MainMenuScreen(final Game game) {

		super(game);
		
		stage = new Stage(getScreenSize().x, getScreenSize().y, true);
		
		localGameMenuItem = new MenuItem(MenuType.LOCAL_GAME);
		multiplayGameMenuItem = new MenuItem(MenuType.MULTIPLAYER_GAME);
		exitGameMenuItem = new MenuItem(MenuType.EXIT_GAME);

		localGameMenuItem.setPosition((getScreenSize().x - localGameMenuItem.getWidth()) / 2, getScreenSize().y / 2 + 200);
		multiplayGameMenuItem.setPosition((getScreenSize().x - multiplayGameMenuItem.getWidth()) / 2, getScreenSize().y / 2);
		exitGameMenuItem.setPosition((getScreenSize().x - exitGameMenuItem.getWidth()) / 2, getScreenSize().y / 2 - 200);
		
		localGameMenuItem.addListener(new InputListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				Gdx.app.debug("Input", String.format("%s %s at position X:%.2f, Y:%.2f", event.getListenerActor(), event.getType().name(), x, y));
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			
				Gdx.app.debug("Input", String.format("%s %s at position X:%.2f, Y:%.2f", event.getListenerActor(), event.getType().name(), x, y));
				game.setScreen(new PlayScreen(game));
				super.touchUp(event, x, y, pointer, button);
			}
		});
		
		exitGameMenuItem.addListener(new InputListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			
				Gdx.app.debug("Input", String.format("%s %s at position X:%.2f, Y:%.2f", event.getListenerActor(), event.getType().name(), x, y));
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			
				Gdx.app.debug("Input", String.format("%s %s at position X:%.2f, Y:%.2f", event.getListenerActor(), event.getType().name(), x, y));
				super.touchUp(event, x, y, pointer, button);
				Gdx.app.exit();
			}
		});
		
		stage.addActor(localGameMenuItem);
		stage.addActor(multiplayGameMenuItem);
		stage.addActor(exitGameMenuItem);
		
		inputMultiplexer.addProcessor(stage);
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
		super.render(delta);
	}
}