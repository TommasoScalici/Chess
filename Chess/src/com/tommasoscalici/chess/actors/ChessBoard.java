package com.tommasoscalici.chess.actors;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;

/**
 * 
 * Represents a ChessBoard, the class inherits from {@link Stage}.
 * Both Squares and Pieces are added as {@link Actor}s.
 * To get the squares of the ChessBoard use {@link #getSquares()}
 * 
 * @author Tommaso Scalici
 * 
 */
public class ChessBoard extends Stage {

	private int leftBorder = 0, bottomBorder = 0;
	private int size;
	private ChessSquare[][] squares;

	/**
	 * 
	 * @return {@link ChessSquare}[][]<br />
	 * the ChessBoard's squares as a NxN array
	 * 
	 */
	public ChessSquare[][] getSquares() {
		
		return squares;
	}
	
	
	/**
	 * Creates a new playable Chessboard.
	 * 
	 * @param size The side length of the ChessBoard (and of the inherited Stage Viewport)
	 * 
	 * @author Tommaso Scalici
	 */
	public ChessBoard(int size) {

		int k = size / 8; // k is the size length of a ChessBoard's square
		setViewport(size, size, true);
		squares = new ChessSquare[8][8];

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {

				if ((i + j) % 2 == 0)
					squares[i][j] = new ChessSquare(new Vector2(i, j), new Vector2(i  * k, j * k), k, ChessColor.LIGHT);
				else
					squares[i][j] = new ChessSquare(new Vector2(i, j), new Vector2(i * k, j * k), k, ChessColor.DARK);

				addActor(squares[i][j]);
			}
		}

		InitializePieces();

	}

	private void InitializePieces() {

		try {

			for (int i = 0; i < squares.length; i++) {

				for (int j = 0; j < squares[i].length; j++) {

					ChessPiece piece = null;
					
					
					if (i == 4 && j == 0)
						piece = new ChessPiece(ChessColor.LIGHT, PieceKind.KING, this, squares[i][j]);

					if (i == 4 && j == 7)
						piece = new ChessPiece(ChessColor.DARK, PieceKind.KING, this, squares[i][j]);

					if (i == 3 && j == 0)
						piece = new ChessPiece(ChessColor.LIGHT, PieceKind.QUEEN, this, squares[i][j]);

					if (i == 3 && j == 7)
						piece = new ChessPiece(ChessColor.DARK, PieceKind.QUEEN, this, squares[i][j]);

					if (i == 0 && j == 0 || i == 7 && j == 0)
						piece = new ChessPiece(ChessColor.LIGHT, PieceKind.ROOK, this, squares[i][j]);

					if (i == 0 && j == 7 || i == 7 && j == 7)
						piece = new ChessPiece(ChessColor.DARK, PieceKind.ROOK, this, squares[i][j]);

					if (i == 2 && j == 0 || i == 5 && j == 0)
						piece = new ChessPiece(ChessColor.LIGHT, PieceKind.BISHOP, this, squares[i][j]);
					
					if (i == 2 && j == 7 || i == 5 && j == 7)
						piece = new ChessPiece(ChessColor.DARK, PieceKind.BISHOP, this, squares[i][j]);
					
					if (i == 1 && j == 0 || i == 6 && j == 0)
						piece = new ChessPiece(ChessColor.LIGHT, PieceKind.KNIGHT, this, squares[i][j]);

					if (i == 1 && j == 7 || i == 6 && j == 7)
						piece = new ChessPiece(ChessColor.DARK, PieceKind.KNIGHT, this, squares[i][j]);

					if (j == 1)
						piece = new ChessPiece(ChessColor.LIGHT, PieceKind.PAWN, this, squares[i][j]);

					if (j == 6)
						piece = new ChessPiece(ChessColor.DARK, PieceKind.PAWN, this, squares[i][j]);
					
					if(piece != null)
						addActor(piece);
					squares[i][j].setPiece(piece);
					
				}
			}

		}

		catch (Exception ex) {

			Gdx.app.error(getClass().getSimpleName(), "Could not initialize pieces on the board.", ex);
		}
	}
}