package tommasoscalici.chess.actors;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;

/**
 * Represents a single square of the ChessBoard.<br />
 * To get the pixel position (position of the square on the screen) use {@link #getPixelPosition()}<br />
 * To get the indexed position use {@link #getIndexPosition()}<br />
 * To get or set the piece that currently lie on that square use {@link #getPiece()} or {@link #setPiece(ChessPiece)}<br />
 * 
 * @author Tommaso Scalici
 *
 */
public class ChessSquare extends Actor{

	private final char alphabet[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private final Texture lightSquareTile = new Texture(Gdx.files.internal("data/square_light_tile.png"));
	private final Texture darkSquareTile = new Texture(Gdx.files.internal("data/square_dark_tile.png"));
	private final Vector2 indexPosition, pixelPosition;
	private final ChessColor color;
	private ChessPiece piece;
	
	
	public Vector2 getIndexPosition() {

		return indexPosition;
	}

	public Vector2 getPixelPosition() {

		return pixelPosition;
	}
	
	public ChessPiece getPiece() {
	
		return piece;
	}
	
	public void setPiece(ChessPiece piece) {
	
		this.piece = piece;
	}


	public ChessSquare(Vector2 indexPosition, Vector2 pixelPosition, int size, ChessColor color) {

		this.indexPosition = indexPosition;
		this.pixelPosition = pixelPosition;
		this.color = color;

		setBounds(pixelPosition.x, pixelPosition.y, size, size);
		setName(alphabet[(int)indexPosition.x] + String.valueOf((int)indexPosition.y + 1));

		addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				ChessSquare squareTouched = (ChessSquare)event.getListenerActor();
				Vector2 stageCoords = squareTouched.localToStageCoordinates(new Vector2(x, y));
				Gdx.app.debug("Input",
						String.format("%s %s %s at position (Relative - X:%.2f, Y:%.2f) (Absolute - X:%.2f, Y:%.2f)", 
								event.getListenerActor().getClass().getSimpleName(), event.getListenerActor(), event.getType().name(), x, y, stageCoords.x, stageCoords.y));
				
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ChessSquare squareTouched = (ChessSquare)event.getListenerActor();
				Vector2 stageCoords = squareTouched.localToStageCoordinates(new Vector2(x, y));
				Gdx.app.debug("Input",
						String.format("%s %s %s at position (Relative - X:%.2f, Y:%.2f) (Absolute - X:%.2f, Y:%.2f)", 
								event.getListenerActor().getClass().getSimpleName(), event.getListenerActor(), event.getType().name(), x, y, stageCoords.x, stageCoords.y));
				
				super.touchUp(event, x, y, pointer, button);
			}
		});
	}


	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.setColor(getColor());
		
		if (color == ChessColor.DARK)
			batch.draw(darkSquareTile, getX(), getY(), getWidth(), getHeight());
		else if (color == ChessColor.LIGHT)
			batch.draw(lightSquareTile, getX(), getY(), getWidth(), getHeight());

		batch.setColor(Color.WHITE);

		super.draw(batch, parentAlpha);
	}
}