package spinoza.model;

/*******************************************
 * File Name: Word.java
 * Purpose: creates a new Object Word
 * Programmer: Gabriel.Espinoza
 * Last Update: 12/03/2020
 *
*******************************************/

public class Word {
	private String word;
	private String translation;
	private String image;
	private int priority;

	/** Default Constructor, creates a new Word Object */
	public Word() {
		word = null;
		translation = null;
		image = null;
		priority = 10;
	}

	/** Parameterized Constructor, creates a new Word Object
	 * @param word - name of word
	 * @param translator - translation of word
	 * @param url - URL image for word
     */
	public Word(String word, String translation, String image, int priority) {
		this.word = word;
		this.translation = translation;
		this.image = image;
		this.priority = priority;
	}

	// Getters

	/** Gets the name of the word from the Word object.
	 * @return The name of word
     */
	public String getWord() {
		return word;
	}

	/** Gets the translation of the word from the Word object.
	 * @return The translation of word
     */
	public String getTranslation() {
		return translation;
	}

	/** Gets the image URL from the Word object.
	 * @return The image URL of word
     */
	public String getImage() {
		return image;
	}

	public int getPriority() {
		return priority;
	}

	 // Setters

	/** Sets the name of the word
	 * @param word - the name of the word for the Word object
     */
	public void setWord(String word) {
		this.word = word;
	}

	/** Sets the translation of the word
	 * @param translation - the translation of the word
     */
	public void setTranslation(String translation) {
		this.translation = translation;
	}

	/** Sets the URL image for the word
	 * @param image - the URL image for the word
     */
	public void setImage(String image) {
		this.image = image;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

} // end class
