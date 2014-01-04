package tommasoscalici.chess.screens;

import tommasoscalici.chess.actors.ChessBoard;
import tommasoscalici.chess.engine.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;

public class PlayScreen extends BaseGameScreen {

	private ChessBoard board;
	private ChessEngine engine;
	
	public PlayScreen(final Game game) {

		super(game);
		
		board = new ChessBoard(400);
		engine = new ChessEngine(board);
		inputMultiplexer.addProcessor(board);
	}


	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		board.act(delta);
		board.draw();
		super.render(delta);
	}
}
