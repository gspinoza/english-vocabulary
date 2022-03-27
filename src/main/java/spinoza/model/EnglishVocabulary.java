package spinoza.model;

/*******************************************
 * File Name: EnglishVocabulary.java
 * Purpose: Creates and English Vocabulary Application Using the Dictionary
 * Programmer: Gabriel.Espinoza
 * Last Update: 12/03/2020
 *
*******************************************/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class EnglishVocabulary extends Dictionary {
	@SuppressWarnings("unused")
	private Dictionary theDictionary;
	private Stack<Word> tempDictionary;
	private Word currentWord;
	private boolean answerChecked = true;
	private int correctWords = 0;
	private int incorrectWords = 0;
	private int selectedPracticeOption = 1;
	private boolean practiceInitialized = false;

	/** Default Constructor, creates a new EnglishVocabularyApp
     */
	public EnglishVocabulary(){
		this.theDictionary = new Dictionary();
	}

	/** Parameterized Constructor, creates a EnglishVocabularyApp
	 * @param dictionary to use for EnglishVocabularyApp
     */
	EnglishVocabulary(Dictionary theDictionary){
		this.theDictionary = theDictionary;
	}

	/** Adds a Word object to dictionary
	 * @param word - name of word
	 * @param translator - translation of word
	 * @param url - URL image for word
     */
	public void addWord(String word, String translator, String url, int priority ) {
		super.addWord(word, translator, url, priority );
	}

	/** Removes a word from the dictionary
	 * @param word - element to remove
	 * @return removed word
     */
	public Word removeWord(String word) {
		return super.removeWord(word);
	}

	public void changePriority(int newPriority) {
		super.changePriority(newPriority);
	}

	/** Gets size of dictionary
	 * @return size of dictionary
     */
	public int getDictionarySize() {
			return super.getSize();
	}

	/** Gets Object Word from dictionary
	 * @return Object word from dictionary
     */
	public Word getObjectWord(int i) {
		return dictionaryElement(i);
	}

	/** Sets the Practice Option "NewWords", which outputs words from new to old
     */
	public void PracticeNewWords() {
		Stack<Word> stackWords = new Stack<Word>();

		for (int i =0; i< getDictionarySize(); i++) {
			Word myWord = super.dictionaryElement(i);
			stackWords.push(myWord);
		}
		tempDictionary = stackWords; // set tempt stack
	}

	/** Sets the Practice Option "RandomWords", which outputs words Randomly
     */
	public void PracticeRandomWords() {
		Stack<Word> stackRandomWords = new Stack<Word>();
		for (int i =0; i< getDictionarySize(); i++) {
			Word myWord = super.dictionaryElement(i);
			stackRandomWords.push(myWord);
		}
		Collections.shuffle(stackRandomWords);
		tempDictionary = stackRandomWords; // set tempt stack
	}


	/** Gets tempDictionary for practice
	 * @return tempDictionary
     */
	public Stack<Word> getTempDictionary() {
		return tempDictionary;
	}

	/** Checks if tempDictionary is empty or not
	 * @return true if tempDictionary is empty, else false
     */
	public boolean tempDictionaryisEmpty() {
		return tempDictionary.isEmpty();
	}

	/** Removes word from tempDictionary
	 * and sets removed word as currentWord!
     */
	public void tempDictionaryPop() {
		currentWord = tempDictionary.pop();
		System.out.println(currentWord.getWord()); // -----------------------------------------------------------------------------------------------------
	}

	/** Gets name of current word
	 * @return word name of current word object
     */
	public String getCurrentWord() {
		return currentWord.getWord();
	}

	/** Gets URL image of current word
	 * @return URL image of current word object
     */
	public String getCurrentImage() {
		return currentWord.getImage();
	}

	/** Gets translation of current word
	 * @return translation of current word object
     */
	public String getCurrentTranslation() {
		return currentWord.getTranslation();
	}

	/** Check if user input is correct or incorrect
	 * @return true if answer is correct, else return false
     */
	public boolean checkAnswer(String userAnswer) {
		System.out.println("User Answer: " + userAnswer);
		System.out.println("Correct Answer: " + getCurrentWord());
		if (getCurrentWord().equals(userAnswer))
			return true;
		return false;
	}

	/** Set answer check sate
	 * @param bool state of answer check
     */
	public void setAnswerChecked(boolean bool){
		answerChecked = bool;
	}

	/** Checks if user answer has been checked
	 * @return true if used answer has been checked, else return false
     */
	public boolean getAnswerChecked(){
		return answerChecked;
	}

	/** increase number of correct words
     */
	public void increaseCorrect() {
		correctWords++;
	}

	/** increase number of incorrect words
     */
	public void increaseIncorrect() {
		incorrectWords++;
	}

	/** Gets total number of correct words
	 * @return total number of correct words
     */
	public int getTotalCorrect() {
		return correctWords;
	}

	/** Gets total number of incorrect words
	 * @return total number of incorrect words
     */
	public int getTotalIncorrect() {
		return incorrectWords;
	}

	/** Gets percentage score of "test"
	 * @return percentage score of "test"
     */
	public double getScorePercent(){
		return ((double)correctWords/(double)getDictionarySize()) * 100;
	}

	/** Resets score of "test"
     */
	public void resetScores() {
		correctWords = 0;
		incorrectWords = 0;
	}

	/** Sets the selected practice option for the "test"
	 * @param number of selected option
     */
	public void setPracticeOption(int optionNumber) {
		selectedPracticeOption = optionNumber;
	}

	/** Gets the selected practice option for the "test"
	 * @return number of selected option
     */
	public int getPracticeOption() {
		return selectedPracticeOption;
	}

	/** Checks if Practice Option has been selected/initialized
	 * @return true if practice option has been selected/initialized, else return false
     */
	public boolean PracticeInitialized() {
		return practiceInitialized;
	}

	/** InitializesPractice Option
     */
	public void InitializePractice() {
		practiceInitialized = true;
	}

	/**
	 * Reads file and adds the content (words) to a set
	 * @param filePath a String with the file path of the file you want to read
	 * @param arraySet the ArraySet<String> to add content (words) form file
	 */
	public void readFileToDcitionary(String filePath) throws IOException {

		try {
			// file to read
			Scanner inputFile = new Scanner(new File(filePath));
			// while there is another line in the file
			while (inputFile.hasNextLine()) {
				// get line
				String currentLine = inputFile.nextLine();
				if (!currentLine.equals("")) {
					// split line
					String[] word = currentLine.split(",");
					super.addWord(word[0],word[1],word[2], Integer.valueOf(word[3]));
				}
		}
			// close scanner
			inputFile.close();
			// catch exceptions
		} catch (FileNotFoundException e) {
			System.out.println("Specified File could not be found!");
		}
	}

	/**
	 * Reads file and adds the content (words) to a set
	 * @param filePath a String with the file path of the file you want to read -----------------------------------------------------------------------------
	 * @param arraySet the ArraySet<String> to add content (words) form file
	 */
	public void saveDictionaryToFile(String filePath, String word, String translation, String url, int priority) throws IOException {

		try {
			BufferedWriter outputFile = new BufferedWriter(new FileWriter(filePath, true));

			// format word string
			String myWord = word + "," + translation + "," + url + "," + priority;
			// write word to file
			outputFile.newLine(); // select next line
			outputFile.write(myWord); // write word

			// close file
			outputFile.close();
			// catch exceptions
		} catch (FileNotFoundException e) {
			System.out.println("Specified File could not be found!");
		}
	}

	/**
	 * Reads file and adds the content (words) to a set
	 * @param filePath a String with the file path of the file you want to read -----------------------------------------------------------------------------
	 * @param arraySet the ArraySet<String> to add content (words) form file
	 */
	public void deleteWordFromDictionaryFile(String filePath) throws IOException {
		// ok when we remove a word from our dictionary the word is also removed from the database
		// so I just have to make a copy of the database and overwrite the dictionary file.
		try {
			BufferedWriter outputFile = new BufferedWriter(new FileWriter(filePath));

			for (int i =0; i< getDictionarySize(); i++) {
				// current word
				Word theWord = super.dictionaryElement(i);
				// format word properties to string
				String myWord = theWord.getWord() + "," + theWord.getTranslation() + "," + theWord.getImage();

				// write the word string
				outputFile.newLine(); // select next line
				outputFile.write(myWord); // write word
			}

			// close file
			outputFile.close();
			// catch exceptions
		} catch (FileNotFoundException e) {
			System.out.println("Specified File could not be found!");
		}
	}

} // end class
