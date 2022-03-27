package spinoza.controller;

/*******************************************
 * File Name: Controller.java
 * Purpose: Controls action by updating the view and the Model
 * Programmer: Gabriel.Espinoza
 * Last Update: 12/03/2020
 *
*******************************************/

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import spinoza.api.PixabayAPI;
import spinoza.model.EnglishVocabulary;
import spinoza.view.View;

public class Controller  {
	private View theView;
	private EnglishVocabulary theModel;

	@SuppressWarnings("serial")
	// Constructor
	public Controller(final View theView, final EnglishVocabulary theModel) throws IOException {
		this.theView = theView;
		this.theModel = theModel;

		// invoke read dictionary file
		readDictionaryFile();

		/**
		 * Gets and Displays the images from PixaBay using the PixaBay API
	     */
		this.theView.getImagesListener(new ActionListener() {
    		@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

    			// if Word & Translation Fields are not empty
    			if (!theView.getNewWord().equals("") && !theView.getNewTranslation().equals("")) {
	    			// search images
	    	    	try {
						PixabayAPI.searchImages(theView.getNewWord());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

	    	    	JRadioButton[] buttonsArray = {theView.t1,theView.t2,theView.t3,theView.t4,theView.t5,theView.t6,theView.t7,theView.t8,theView.t9,theView.t10};

	    	    	ImageIcon image;

	    	    	if (!PixabayAPI.isEmpty()) {
		    	    	for (int i = 0; i<buttonsArray.length;i++) {
							try {
								image = new ImageIcon(new URL(PixabayAPI.getImages(i)));
			    	    		buttonsArray[i].setIcon(theView.resizeIcon(image, 150, 84)); // sets new images to t1,t2... labels
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		    	    	}
	    	    	}
	    	    }
    		}
    	});


		/**
		 * Sets selected Image from JRadion buttons and controls the selected button color
	     */
		this.theView.JRadioListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

    			// if Word & Translation Fields are not empty
    			if (!theView.getNewWord().equals("") && !theView.getNewTranslation().equals("")) {
			    	if(e.getSource()== theView.t1) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(0));
			    		theView.ImageHightlightReset();
			    		theView.t1.setBackground(Color.BLUE);
			    	}
			    	if(e.getSource()== theView.t2) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(1));
			    		theView.ImageHightlightReset();
			    		theView.t2.setBackground(Color.BLUE);
			    	}
			    	if(e.getSource()== theView.t3) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(2));
			    		theView.ImageHightlightReset();
			    		theView.t3.setBackground(Color.BLUE);
			    	}
			    	if(e.getSource()== theView.t4) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(3));
			    		theView.ImageHightlightReset();
			    		theView.t4.setBackground(Color.BLUE);
			    	}
			    	if(e.getSource()== theView.t5) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(4));
			    		theView.ImageHightlightReset();
			    		theView.t5.setBackground(Color.BLUE);
			    	}
			    	if(e.getSource()== theView.t6) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(5));
			    		theView.ImageHightlightReset();
			    		theView.t6.setBackground(Color.BLUE);
			    	}
			    	if(e.getSource()== theView.t7) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(6));
			    		theView.ImageHightlightReset();
			    		theView.t7.setBackground(Color.BLUE);
			    	}
			    	if(e.getSource()== theView.t8) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(7));
			    		theView.ImageHightlightReset();
			    		theView.t8.setBackground(Color.BLUE);
			    	}
			    	if(e.getSource()== theView.t9) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(8));
			    		theView.ImageHightlightReset();
			    		theView.t9.setBackground(Color.BLUE);

			    	}
			    	if(e.getSource()== theView.t10) {
			    		PixabayAPI.setSelectedImage(PixabayAPI.getImages(9));
			    		theView.ImageHightlightReset();
			    		theView.t10.setBackground(Color.BLUE);
			    	}
			    }
    		}
    	});


		/**
		 * Saves new word to the dictionary
	     */
		this.theView.addWordListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				// get info from the view

				// if Word & Translation Fields are not empty
				if (!theView.getNewWord().equals("") && !theView.getNewTranslation().equals("")) {

					String word = theView.getNewWord();
					String url = PixabayAPI.getSelectedImage();
					String Translation = theView.getNewTranslation();
					int priority = theView.getNewPriority();

					theModel.addWord(word,Translation,url, priority);
					// save word to dictionary.txt
					try {theModel.saveDictionaryToFile("resources/Dictionary.txt", word, Translation, url, priority);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					// reset JTABLE, otherwise it will output all words again each time a new one is displayed
					theView.resetTable();
					// get info from dictionary and update the table or view
					for (int i = 0; i<theModel.getDictionarySize(); i++) {

						word = theModel.getObjectWord(i).getWord();
						url = theModel.getObjectWord(i).getImage();
						Translation = theModel.getObjectWord(i).getTranslation();
						priority = theModel.getObjectWord(i).getPriority();

							try {
								theView.displayDictionaryOnTable(word, Translation, url, priority);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

					}

					// save selected image
					try {
						theView.resetInputFields();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// print arraylist to see current added words.
					System.out.println(theModel);
				}
    		}
    	});

		/**
		 * deletes word from JTable, from dictionary and from dictionary.txt if any
	     */
		this.theView.buttonColumn.getActionfromCrontroller(new AbstractAction() {
    		public void actionPerformed(ActionEvent e) {
    			JTable table = (JTable)e.getSource();
    			int modelRow = Integer.valueOf( e.getActionCommand() );

    			// remove from Dictionary
    			theModel.removeWord(theView.getDeletedWord());
    			// update dictionary file, overwrite existing dictionary with current
    			try {
					theModel.deleteWordFromDictionaryFile("resources/Dictionary.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			System.out.println("word Removed from 'DataBase'");

    			((DefaultTableModel)table.getModel()).removeRow(modelRow);
    		}
    	});

		/**
		 * Sets selected Image from JRadioListenerOptions buttons and controls the selected button color
	     */
		this.theView.JRadioListenerOptions(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(e.getSource()== theView.practiceOp1) {
		    		// set Selected Option to the Model
		    		theModel.setPracticeOption(1);

		    		theView.OptionHightlightReset();
		    		theView.practiceOp1.setBackground(Color.BLUE);
		    	}
		    	if(e.getSource()== theView.practiceOp2) {
		    		// set Selected Option
		    		theModel.setPracticeOption(2);

		    		theView.OptionHightlightReset();
		    		theView.practiceOp2.setBackground(Color.BLUE);
		    	}
					if(e.getSource()== theView.practiceOp3) {
						// set Selected Option
						theModel.setPracticeOption(3);

						theView.OptionHightlightReset();
						theView.practiceOp3.setBackground(Color.BLUE);
		    }
				if(e.getSource()== theView.practiceOp4) {
					// set Selected Option
					theModel.setPracticeOption(4);

					theView.OptionHightlightReset();
					theView.practiceOp4.setBackground(Color.BLUE);
				}
			}
		});

		/**
		 * Prepares the Temporary dictionary for practice and starts practice
	     */
		this.theView.JRadioListenerPracticeButton(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

    			if (!theModel.isEmpty()) {

			    	// get selected option from the Model
			    	int selectedOption = theModel.getPracticeOption();

			    	if (selectedOption == 1)
				    	// invoke method to prepare Temp Dictionary
				    	theModel.PracticeNewWords();
			    	if (selectedOption == 2)
				    	// invoke method to prepare Temp Dictionary
				    	theModel.PracticeRandomWords();
					else if (selectedOption == 3)
						// invoke method to prepare Temp Dictionary
						theModel.PracticeNewWords();
					else
						// invoke method to prepare Temp Dictionary
						theModel.PracticeNewWords();

			    	//reset Scores
			    	theView.setScoreInvisible();
			    	theModel.resetScores();
			    	// reset
					theView.resetPracticeFields();

					// initialize Practice
					theModel.InitializePractice();
			    	// click next button
			    	theView.CheckNextButton.doClick();
			    }
    		}
		});

		/**
		 * Checks user answer, updates scores, updates next/check button, pops next word, finally displays results
	     */
		this.theView.CheckAnswerButton(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		    	if (theModel.PracticeInitialized() && !theView.getUserAnswer().equals("Type Translation")) { //&& !theView.getUserAnswer().equals("")

			    	// IF ANSWER NOT CHECKED - CHECK IT
			    	if (!theModel.getAnswerChecked()) {
				    	String userAnswer = theView.getUserAnswer();
				    	boolean answer = theModel.checkAnswer(userAnswer);

				    	if (answer) {
				    		// set color green
				    		theView.setAnswerFieldColor("GREEN");
				    		// change button text to next
				    		theView.setCheckNextButtonText("Next");
				    		// point to next word
				    		theView.setAnswerField(userAnswer);
				    		theModel.increaseCorrect();
				    		theModel.setAnswerChecked(true);
				    	} else {
				    		// set color red
				    		theView.setAnswerFieldColor("RED");
				    		// change button text to next
				    		theView.setCheckNextButtonText("Next");
				    		// and point to next word
				    		theView.setAnswerField(userAnswer);
				    		theModel.increaseIncorrect();
				    		theModel.setAnswerChecked(true);
				    	}

			    	}
			    	else {
			    		// ELSE ITERATE TO NEXT WORD
			    		if (!theModel.tempDictionaryisEmpty()) {
			    			// pop first word
			    			theModel.tempDictionaryPop();
			    			// get parameters
			    			try {
								theView.setCurrentPic(theModel.getCurrentImage());
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			    			theView.setCurrenWord(theModel.getCurrentTranslation());

			    			// RESET
			    			theView.setCheckNextButtonText("Check"); // set to check
			    			theView.setAnswerField("Type Translation");
			    			theModel.setAnswerChecked(false); // set to false
			    			theView.setAnswerFieldColor("WHITE");

			    		} else { //RESULTS!!!
			    			// reset
			    			theView.setAnswerFieldColor("WHITE");
			    			theView.resetPracticeFields();

			    			theView.setSCore("YOUR SCORE: %"+ theModel.getScorePercent() + "          Total Words: " + theModel.getDictionarySize() + "");
			    			theView.setCorrect("Correct: " + theModel.getTotalCorrect());
			    			theView.setIncorrect("Incorrect: " + theModel.getTotalIncorrect());
			    			theView.setScoreVisible();
			    		}
			    	}

			    	}
		    	}

		});

	} // end Controller Constructor

	/**
	 * reads dictionary.txt file and add them to the dictionary, finally displays dictionary in the JTable
     */
	public void readDictionaryFile() throws IOException {
		// read dictionary file
		theModel.readFileToDcitionary("resources/Dictionary.txt");

		// get info from dictionary and update the table or view
		for (int i = 0; i<theModel.getDictionarySize(); i++) {

			String word = theModel.getObjectWord(i).getWord();
			String url = theModel.getObjectWord(i).getImage();
			String Translation = theModel.getObjectWord(i).getTranslation();
			int priority = theModel.getObjectWord(i).getPriority();

				try {
					theView.displayDictionaryOnTable(word, Translation, url, priority);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		}
	}

} // end Controller class
