package br.com.softblue.tictactoe.core;



import java.io.IOException;

import br.com.softblue.tictactoe.Constants;
import br.com.softblue.tictactoe.score.FileScoreManager;
import br.com.softblue.tictactoe.score.ScoreManager;
import br.com.softblue.tictactoe.ui.UI;
import br.com.softblue.tictactoe.core.Player;

public class Game {
	
	private Board board = new Board();
	private Player[] players = new Player[Constants.SYMBOL_PLAYERS.length];
	private int currentPLayerIndex = -1;
	private ScoreManager scoreManager;
	
	public void play() throws IOException{
		scoreManager = createScoreManager();
		
		UI.printGameTitle();
		
		for (int i = 0; i < players.length; i++) {
			players[i] = createPlayer(i);
		}
		
		boolean gameEnded = false;
		Player currentPlayer = nextPlayer();
		Player winner = null;
				
		while (!gameEnded) {
			board.print();
			
			boolean sequenceFound;
			try {
				sequenceFound =  currentPlayer.play();
				
			} catch (InvalidMoveException e) {
				UI.printText("ERRO :"+ e.getMessage());
				continue;
			}
			
			if (sequenceFound) {
				gameEnded = true;
				winner = currentPlayer;
			} else if (board.isFull()){
				gameEnded = true;
			}else {
				currentPlayer = nextPlayer();
			}
		}
		if(winner == null) {
			UI.printText("O jogo terminou empatado");
		}else {
			UI.printText("O ojgador '" + winner.getName()+"' venceu o jogo!");
			
			Integer score = scoreManager.saveScore(winner);

			
		}
		board.print();
		UI.printText("fim do jogo");
	}
	private Player createPlayer(int index) {
		String name = UI.readInput("jogador " + (index + 1)+"=>");
		char symbol = Constants.SYMBOL_PLAYERS[index];
		Player player = new Player(name, board, symbol);
		
		Integer score = scoreManager.getScore(player);
		
		if (score != null) {
			UI.printText("O jogador'"+ player.getName()+ "'já possui"+ "vitoria(s)");
		}
		
		UI.printText("O jogador '" + name +"' vai usar o símbolo ' " +symbol+"'");
		
		return player;
	}
	private Player nextPlayer() {
		/*
		currentPLayerIndex++;		
		if (currentPLayerIndex >= players.length) {
			currentPLayerIndex = 0;
		}
		return players[currentPLayerIndex];
		*/
		currentPLayerIndex = (currentPLayerIndex +1) % players.length;
		return players[currentPLayerIndex];
	}
	private ScoreManager createScoreManager() throws IOException {
		
		return new FileScoreManager();
	}
}
