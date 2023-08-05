package com.hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	public static void main(String args[]) throws FileNotFoundException {

		Scanner keyboard = new Scanner(System.in);

		System.out.println("1 or 2 Players? : ");
		String players = keyboard.nextLine();
		String word = "";
		if (players.equals("1")) {
			Scanner scanner = new Scanner(new File("C:/Users/Documents/GitCodeBase/english-words_master.txt"));
			List<String> words = new ArrayList<>();
			while (scanner.hasNext()) {
				words.add(scanner.nextLine());
			}
			Random rand = new Random();
			int wordsSize = words.size();
			word = words.get(rand.nextInt(wordsSize));
		} else {
			System.out.println("Player 1, Please enter your word : ");
			word = keyboard.nextLine();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Ready for player 2 ");
		}
	
	
		List<Character> playerGuesses = new ArrayList<>();
		int wrongCnt = 0;
		while (true) {
			printHangMan(wrongCnt);
			if (wrongCnt >= 6) {
				break;
			}

			printWordState(word, playerGuesses);
			getPlayerGuess(keyboard, word, playerGuesses);
			if (printWordState(word, playerGuesses)) {
				System.out.println("You Won!");
				break;
			}
			System.out.println("Please enter your guess for the word : ");
			if (keyboard.nextLine().equals(word)) {
				System.out.println("Guessed Right, Genious.");
				System.out.println("You Won!");
				break;
			} else {
				System.out.println("Nope! Try again.");
				wrongCnt++;
			}
		}

	}

	private static void printHangMan(int wrongCnt) {
		System.out.println(" -------");
		System.out.println(" |     |");
		if (wrongCnt >= 1) {
			System.out.println(" O");
		}
		if (wrongCnt >= 2) {
			System.out.print("\\ ");
			if (wrongCnt >= 3) {
				System.out.println("/");
			} else {
				System.out.println("");
			}
		}
		if (wrongCnt >= 4) {
			System.out.println(" |");
		}
		if (wrongCnt >= 5) {
			System.out.print("/ ");
			if (wrongCnt >= 6) {
				System.out.println("\\");
				System.out.println("Game Over!..");
			} else {
				System.out.println("");
			}
		}
		System.out.println();
		System.out.println();
	}

	private static void getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
		System.out.println(word);
		System.out.println("Please Enter A Letter : ");
		String letterGuess = keyboard.nextLine();
		playerGuesses.add(letterGuess.charAt(0));
	}

	private static boolean printWordState(String word, List<Character> playerGuesses) {
		int correctCount = 0;
		for (int i = 0; i < word.length(); i++) {
			if (playerGuesses.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
				correctCount++;
			} else {
				System.out.print("-");
			}
		}
		System.out.println();
		if (correctCount == word.length()) {
			return true;
		}
		return false;
	}
}
