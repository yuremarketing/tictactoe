package br.com.softblue.tictactoe.core;

import br.com.softblue.tictactoe.ui.UI;

/*
 * Criao os jogadores e suas respectivas ações no jogo
 * */
public class Player {



	private String name;
	private Board board;
	private char symbol;
	
	Player[] players = new Player[2];
	
	
	
	public Player(String name, Board board, char symbol) {
		
		this.name = name;
		this.board = board;
		this.symbol = symbol;
	}
	/*
	 * retorna o movimento da jogada, ou seja, 
	 * qual potno do tabuleiro será marcado a jogada 
	 * */
	private Move inputMove() throws InvalidMoveException {
		String moveStr = UI.readInput("Jogador'"+name +"'=>");
		return new Move(moveStr);		
	}
	
	public boolean play() throws InvalidMoveException{
		Move move = inputMove();
		return board.play(this, move);		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
