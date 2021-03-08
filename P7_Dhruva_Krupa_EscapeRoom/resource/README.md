# Game play for testing

Files with game play user inputs for testing escape room. Use these files as STDIN for the game

Example:

	$ javac -cp src -d out src/WizardLabDriver.java
	$ java -cp out WizardLabDriver < resource/play_exhaust.txt
	$ java -cp out WizardLabDriver < resource/play_win.txt

## Winning game

File `play_win.txt` contains some valid and some invalid inputs but will eventually result in winning

## Exhausting tries

File `play_exhaust.txt` contains multiple valid inputs with the aim of exhausting allowed tries resulting in losing the game
