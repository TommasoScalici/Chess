package tommasoscalici.chess.actors;

import tommasoscalici.chess.engine.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;


public class ChessPiece extends Actor {

	//private static final Texture texture = new Texture(Gdx.files.internal("data/pieces_tile_set.png"));
	//private final int pieceWidth = 46, pieceHeight = 38;
	
	private ChessColor color;
	private PieceKind kind;
	private ChessSquare squareWherePositioned;
	private Texture texture;
	

	public ChessColor getPieceColor() {

		return color;
	}

	public PieceKind getPieceKind() {

		return kind;
	}

	public ChessSquare getSquareWherePositioned() {

		return squareWherePositioned;
	}

	public void setSquareWherePositioned(ChessSquare squareWherePositioned) {

		this.squareWherePositioned = squareWherePositioned;
		setPosition(squareWherePositioned.getX(), squareWherePositioned.getY());
	}
	
	
	public ChessPiece(ChessColor color, PieceKind kind, final ChessBoard chessBoard, ChessSquare squareWherePositioned) {

		this.color = color;
		this.kind = kind;
		this.squareWherePositioned = squareWherePositioned;
		

		switch (color) {

			case DARK:

				switch (kind) {

					case PAWN:
						texture = new Texture(Gdx.files.internal("data/pieces/dark_pawn.png"));
						break;

					case KNIGHT:
						texture = new Texture(Gdx.files.internal("data/pieces/dark_knight.png"));
						break;

					case BISHOP:
						texture = new Texture(Gdx.files.internal("data/pieces/dark_bishop.png"));
						break;

					case ROOK:
						texture = new Texture(Gdx.files.internal("data/pieces/dark_rook.png"));
						break;

					case QUEEN:
						texture = new Texture(Gdx.files.internal("data/pieces/dark_queen.png"));
						break;

					case KING:
						texture = new Texture(Gdx.files.internal("data/pieces/dark_king.png"));
						break;
				}

				break;

			case LIGHT:

				switch (kind) {

					case PAWN:
						texture = new Texture(Gdx.files.internal("data/pieces/light_pawn.png"));
						break;

					case KNIGHT:
						texture = new Texture(Gdx.files.internal("data/pieces/light_knight.png"));
						break;

					case BISHOP:
						texture = new Texture(Gdx.files.internal("data/pieces/light_bishop.png"));
						break;

					case ROOK:
						texture = new Texture(Gdx.files.internal("data/pieces/light_rook.png"));
						break;

					case QUEEN:
						texture = new Texture(Gdx.files.internal("data/pieces/light_queen.png"));
						break;

					case KING:
						texture = new Texture(Gdx.files.internal("data/pieces/light_king.png"));
						break;
				}

				break;
		}

		setName(color.toString() + " " + kind.toString());
		setBounds(squareWherePositioned.getX(), squareWherePositioned.getY(), texture.getWidth(), texture.getHeight());

		addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				ChessPiece piece = (ChessPiece)event.getListenerActor();
				ChessEngine.setLegalSquares(piece);
				ChessEngine.showLegalSquares();
				Gdx.app.debug("Input", String.format("%s %s %s in the square %s", 
							  piece.getClass().getSimpleName(), piece,
							  event.getType().name(), getSquareWherePositioned().getName()));
				return true;
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {

				ChessPiece piece = (ChessPiece)event.getListenerActor();
				
				setPosition(event.getStageX() - piece.getWidth() / 2, event.getStageY() - piece.getHeight() / 2);
				
				super.touchDragged(event, x, y, pointer);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ChessPiece piece = (ChessPiece)event.getListenerActor();
				ChessEngine.hideLegalSquares();
				
				for(ChessSquare[] squareArr : chessBoard.getSquares()) {		
					for(ChessSquare square : squareArr) {
						
						Rectangle squareRect = new Rectangle(square.getX(), square.getY(), square.getWidth(), square.getHeight());
						
						if(squareRect.contains(piece.getX() + piece.getWidth() / 2, piece.getY() + piece.getHeight() / 2)) {
							
							Gdx.app.debug("Input", String.format("%s %s %s in the square %s", 
								    	  piece.getClass().getSimpleName(), piece,
									      event.getType().name(), square));
							
							
							if(ChessEngine.isLegalMove(piece, square)) {			 	// If the move is legal...
								
								getSquareWherePositioned().setPiece(null); 			// Set to null the piece-reference of the square left by the piece that were moved.
								setSquareWherePositioned(square);				 	// Set the square-reference of the piece, to the square where the piece were moved.
								
								if(getSquareWherePositioned().getPiece() != null) { // If in the new square there was an opponent piece...
									getSquareWherePositioned().getPiece().remove(); // ... remove the piece from the stage...
									
								}
								
								getSquareWherePositioned().setPiece(piece);	  // ... and set the piece-reference of the square to the new piece.
							}
							else
								setSquareWherePositioned(piece.squareWherePositioned); // Else, come back to the original position
						}
					}
				}

				
				
				super.touchUp(event, x, y, pointer, button);
			}
		});

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		super.draw(batch, parentAlpha);
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());

	}
	
	
}
