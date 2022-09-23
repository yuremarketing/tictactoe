package br.com.softblue.tictactoe.core;

import br.com.softblue.tictactoe.ui.UI;

public class Player {



	private String name;
	private Board board;
	private char symbol;
	
	
	
	
	public Player(String name, Board board, char symbol) {
		
		this.name = name;
		this.board = board;
		this.symbol = symbol;
	}

	private Move inputMove() {
		String moveStr = UI.readInput("Jogador'"+name +"'=>");
		return new Move(moveStr);		
	}
	
	public boolean play() {
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
