package spinoza.model;

/*******************************************
 * File Name: Dictionary.java
 * Purpose: creates a new dictionary
 * Programmer: Gabriel.Espinoza
 * Last Update: 12/03/2020
 *
*******************************************/

import java.util.LinkedList;
import java.util.List;

public class Dictionary extends Word{
	private Word word;
	private List<Word> dictionary;

	/** Default Constructor, creates a new Dictionary
     */
	public Dictionary() {
		dictionary = new LinkedList<Word>(); // limit size?? from v1   dictionary = new ArrayList<Word>(50);
	}

	/** Checks if Dictionary is empty.
	 * @return True if dictionary is empty
     */
	public boolean isEmpty() {
		return dictionary.isEmpty();
	}

	/** Gets size of dictionary
	 * @return size of dictionary
     */
	public int getSize() {
		return dictionary.size();
	}

	/** Gets Object Word from dictionary
	 * @return Object word from dictionary
     */
	public Word getObjectWord() {
		return word;
	}

	/** Adds a Word object to dictionary
	 * @param word - name of word
	 * @param translator - translation of word
	 * @param url - URL image for word
     */
	public void addWord(String word, String translator, String url, int priority) {
		dictionary.add(new Word(word,translator, url, priority));
	}

	/** Removes a word from the dictionary
	 * @param word - element to remove
	 * @return removed word
     */
	public Word removeWord(String word) {
		Word removed = null;
		for (int i=0; i<dictionary.size();i++){
			if (dictionary.get(i).getWord() == word) {
				removed = dictionary.get(i);
				dictionary.remove(i); // i think this already returns deleted item, as is from API
			}
		}
		return removed;
	}

	// edit priority
	public void changePriority(int newPriority) {
		dictionary.get(0).setPriority(newPriority);
	}

	/** Gets Specific Word from dictionary
	 * @return Object word from dictionary
     */
	public Word dictionaryElement(int i) {
		return dictionary.get(i);
	}

	/** displays dictionary
	 * @return string representation of dictionary
     */
	public String toString() {
		String str = "";
		for (int i=0; i<dictionary.size(); i++) {
			Word currentWord = dictionary.get(i);
			str += "\n" + "[" + currentWord.getWord() + ", " + currentWord.getTranslation() + ", " + currentWord.getImage() + ", " + currentWord.getPriority() + "]";
		}
		// return String
		return str;
	}

}
