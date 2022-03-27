package spinoza.view;

/*******************************************
 * File Name: View.java
 * Purpose: GUI interface of English Vocabulary Application
 * Programmer: Gabriel.Espinoza
 * Last Update: 12/03/2020
 *
*******************************************/

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

public class View {
	// Swing Components
	// ****** JTabbedPane 1 ******
	// JPanel top
	JLabel labelWord = new JLabel("Word:");
	JTextField fieldWord = new JTextField(15); // v1 was 15
	JLabel labelTranslation = new JLabel("Translation:");
	JTextField fieldTranslation = new JTextField(15); // v1 was 15
	JLabel labelPriority = new JLabel("Priority:");
	JComboBox<Integer> comboBoxPriority = new JComboBox<>(new Integer[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
	JButton getImages = new JButton("Get Images");
	public JRadioButton t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
	JButton saveWord = new JButton("Add Word");
	ImageIcon defaultImg;
	ButtonGroup group;
	// JPanel Bottom
	JTable table;
	DefaultTableModel model; // handles all data
	String DeletedWord;
	public ButtonColumn buttonColumn;
	// ****** JTabbedPane 2 ******
	// JPanel top
	public JRadioButton practiceOp1;
	public JRadioButton practiceOp2;
	public JRadioButton practiceOp3;
	public JRadioButton practiceOp4;
	public JButton PracticeButton;
	// JPanel Bottom
	JLabel PicLabel;
	JLabel WordPicLabel;
	JTextField TypeYourWord;
	public JButton CheckNextButton;
	JLabel score;
	JLabel correct;
	JLabel incorrect;
    // Table Default Constructor
    @SuppressWarnings("serial")
	/**
	 * Default Constructor, creates a new GUI Interface
     */
	public View() throws Exception{

		// Default placeholder for Thumbnails
    	defaultImg = new ImageIcon("resources/defaultPlaceholder.jpg");
    //defaultImg = new ImageIcon(new URL("URL"),"description");  // if image is on web
		//System.out.println(defaultImg.getDescription());

	    //Create the radio buttons.
	    t1 = new JRadioButton(defaultImg);
	    t2 = new JRadioButton(defaultImg);
	    t3 = new JRadioButton(defaultImg);
	    t4 = new JRadioButton(defaultImg);
	    t5 = new JRadioButton(defaultImg);
	    t6 = new JRadioButton(defaultImg);
	    t7 = new JRadioButton(defaultImg);
	    t8 = new JRadioButton(defaultImg);
	    t9 = new JRadioButton(defaultImg);
	    t10 = new JRadioButton(defaultImg);
	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(t1);
	    group.add(t2);
	    group.add(t3);
	    group.add(t4);
	    group.add(t5);
	    group.add(t6);
	    group.add(t7);
	    group.add(t8);
	    group.add(t9);
	    group.add(t10);

        // Create and set up the frame or window.
        JFrame myframe = new JFrame("English Vocabulary");
        myframe.setSize(850,750);
        myframe.setResizable (true);
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Optional: What happens when the frame closes?

        // create new JTabbedPane (where Tabs will be)
		JTabbedPane myTabbedPane = new JTabbedPane();  // create TabbedPane

		// create inner panel  BACKGROUND
		ImageIcon icon = new ImageIcon("src/temperature-converter.pngx"); // icon for tab
		JPanel BackgroundPanel = new JPanel();  // new panel
		BackgroundPanel.setLayout(new BoxLayout(BackgroundPanel, BoxLayout.Y_AXIS)); // create GridLayout for Panel{

		//Panel TOP
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		panelTop.setLayout(new FlowLayout()); // create GridLayout for Panel
		panelTop.setMaximumSize(new Dimension (830, 200));
		panelTop.setPreferredSize(new Dimension (830, 260));
		// add Components to TOP PANEL
		panelTop.add(labelWord);
		panelTop.add(fieldWord);
		panelTop.add(labelTranslation);
		panelTop.add(fieldTranslation);
		panelTop.add(labelPriority);
		panelTop.add(comboBoxPriority);
		panelTop.add(getImages);
		panelTop.add(t1);
		panelTop.add(t2);
		panelTop.add(t3);
		panelTop.add(t4);
		panelTop.add(t5);
		panelTop.add(t6);
		panelTop.add(t7);
		panelTop.add(t8);
		panelTop.add(t9);
		panelTop.add(t10);
		panelTop.add(saveWord);


    	// Table Data Row (Empty / default for initialization)
    	Object data[][] = { };
    	// Table Title Column
    	String column[] = {"Word","Translation","Image","Priority","Action"};

    	// Create TableModel ("BluePrint") using the DefaultTableModel
    	model = new DefaultTableModel(data, column){
    		//  Overriding getColumnClass to render and treat URL(icon) as image and not as a string
    	    public Class<?> getColumnClass(int column) {
    	        switch(column) {
    	            case 2: return ImageIcon.class;
    	            default: return Object.class;
    	        }
    	    }
    	};
    	// use TableModel to Create Table
    	table = new JTable(model);
    	// add properties to table
    	table.setBounds(30,40,200,300);
    	// set cell height for images
    	table.setRowHeight(84);

    	// USE ButtonColumn Class
    	buttonColumn = new ButtonColumn(table, 4); // column to place delete button
    	//delete = buttonColumn.returnAction();
    	buttonColumn.setMnemonic(KeyEvent.VK_D);

    	// place table inside JScrollPane
    	JScrollPane scrollPane = new JScrollPane(table);


		//Panel BOTTOM
		JPanel panelBottom = new JPanel();
		//panelBottom.setBackground(Color.green);
		panelBottom.setLayout(new GridLayout(1, 2)); // create GridLayout for Panel
		// add Components SCROLLPANE to BOTTOM PANEL
		panelBottom.add(scrollPane);

		// add Top and Bottom Panel into BackgroundPanel
		BackgroundPanel.add(panelTop);
		BackgroundPanel.add(panelBottom);


		// create and add tab to TabbedPane, tab is created by adding
		myTabbedPane.addTab("Dictionary", icon, BackgroundPanel, "Manage or add new words");  //  "TabName", iconImage, JPanel, "ToolTip"


		// create another tab
		ImageIcon icon2 = new ImageIcon("resources/icon.png"); // icon for second tab
        // create another inner panel BACKGROUND for a new tab
		JPanel Background2Panel = new JPanel();  // new panel
		Background2Panel.setLayout(new BoxLayout(Background2Panel, BoxLayout.Y_AXIS));


		//Panel TOP 2
		JPanel panel2Top = new JPanel();
		//panel2Top.setBackground(Color.LIGHT_GRAY);
		panel2Top.setMaximumSize(new Dimension (820, 200));
		panel2Top.setPreferredSize(new Dimension (820, 260));
		FlowLayout topFLowLayout = new FlowLayout();
		topFLowLayout.setHgap(20);
		panel2Top.setLayout(topFLowLayout); // create GridLayout for Panel

		// Initialize Jradio Buttons for Options
		practiceOp1 = new JRadioButton(new ImageIcon("resources/NewWords.png"));
		practiceOp2 = new JRadioButton(new ImageIcon("resources/RandomWords.png"));
		//	practiceOp2 = new JRadioButton(new ImageIcon("ExternalFiles/OldWords.png"));
		practiceOp3 = new JRadioButton(new ImageIcon("resources/PriorityWords.png"));
		practiceOp4 = new JRadioButton(new ImageIcon("resources/IncorrectWords.png"));

	    //Group the radio buttons.
	    ButtonGroup myGroup = new ButtonGroup();
	    myGroup.add(practiceOp1);
	    myGroup.add(practiceOp2);
		myGroup.add(practiceOp3);
		myGroup.add(practiceOp4);

		JLabel ManyWords = new JLabel("Number of Word:");
		JComboBox<Integer> numOfWords = new JComboBox<>(new Integer[] {10, 20, 30, 40, 50});
		PracticeButton = new JButton("start Practicing");

		// add Components to TOP PANEL
		panel2Top.add(practiceOp1);
		panel2Top.add(practiceOp2);
		panel2Top.add(practiceOp3);
		panel2Top.add(practiceOp4);
		panel2Top.add(ManyWords);
		panel2Top.add(numOfWords);
		panel2Top.add(PracticeButton);

		//Panel BOTTOM 2
		JPanel panel2Bottom = new JPanel();
		panel2Bottom.setBackground(Color.WHITE);
		FlowLayout bottomFLowLayout = new FlowLayout();
		bottomFLowLayout.setHgap(50);
		bottomFLowLayout.setVgap(100);
		panel2Bottom.setLayout(bottomFLowLayout); // create GridLayout for Panel

		// create new label
		PicLabel = new JLabel(defaultImg); // label inside inner panel
		PicLabel.setHorizontalAlignment(JLabel.CENTER); // X-Axis    position of label use LEFT, CENTER, or RIGHT.

		WordPicLabel = new JLabel("Word");

		TypeYourWord = new JTextField("Type Translation",15);
		TypeYourWord.setBackground(Color.WHITE);

		// reset on mouse click
		TypeYourWord.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  TypeYourWord.setText("");
			  }
			});

		CheckNextButton = new JButton("Check");

		// add Components to BOTTOM PANEL
		panel2Bottom.add(PicLabel);
		panel2Bottom.add(WordPicLabel);
		panel2Bottom.add(TypeYourWord);
		panel2Bottom.add(CheckNextButton);
		//
		score = new JLabel();
		correct = new JLabel();
		correct.setForeground(Color.green);
		incorrect = new JLabel();
		incorrect.setForeground(Color.red);
		setScoreInvisible();

		panel2Bottom.add(score);
		panel2Bottom.add(correct);
		panel2Bottom.add(incorrect);

		// add top and bottom jpanels to background panel
		Background2Panel.add(panel2Top);
		Background2Panel.add(panel2Bottom);
		// create and add tab to TabbedPane, tab is created by adding
		myTabbedPane.addTab("Practice", icon2, Background2Panel, "Practice your vocabulary!");  //  "TabName", iconImage, JPanel, "ToolTip"

		// choose which tab you want to be selected when you open the program.
		myTabbedPane.setSelectedIndex(0); // just change index number.


		// put or add swing components (JTabbedPane) in the frame.
		myframe.getContentPane().add(myTabbedPane,
				BorderLayout.CENTER);

        // Display the frame.
        myframe.setVisible(true);

    }

    // ************ JTabbedPane 1 ************

    // ****** Getters ******

	/** Gets the word entered
	 * @return String word entered
     */
    public String getNewWord() {
    	return fieldWord.getText();
    }

	/** Gets the translation entered
	 * @return String translation entered
     */
    public String getNewTranslation() {
    	return fieldTranslation.getText();
    }

		public Integer getNewPriority() {
        return (Integer) comboBoxPriority.getSelectedItem();
    }

    // ****** Setters ******

	/**
	 * Sets ActionListener for "Get Images" Button
	 * @param action listener that we're adding to the button
     */
    public void getImagesListener(ActionListener listenForGetImages) {
    getImages.addActionListener(listenForGetImages);
    }

	/**
	 * Resets or Clears all Words from the JTable
     */
    public void resetTable() {
    	model.setRowCount(0);
    }

	/**
	 * displays words in the JTable
	 * @param String word - name of word
	 * @param String translation - translation of word
	 * @param String URL - picture that represents the word
     */
    public void displayDictionaryOnTable(String word, String translation, String url, int priority) throws Exception {
		ImageIcon icon = new ImageIcon(new URL(url), "image desc");

		// test
		//URL url8 = new URL("http://www.rontgen.com/bilder/icon/icon_earth.png");
		//BufferedImage image = ImageIO.read(url8.openStream());

		// add row dynamically into the table
    	model.addRow(new Object[] { word, translation, icon, priority, "Delete"});
    }

	/**
	 * Sets ActionListener for "Save Word" Button
	 * @param action listener that we're adding to the button
     */
    public void addWordListener(ActionListener listenForAddWord) {
    saveWord.addActionListener(listenForAddWord);
    }

	/**
	 * Gets deleted word from JTable
	 * @param String deleted word
     */
    public String getDeletedWord() {
		return String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
    }

	/**
	 * Resets input fields and pictures after a word a added to dictionary
     */
    public void resetInputFields() throws MalformedURLException {
    	fieldWord.setText(null);
    	fieldTranslation.setText(null);
			comboBoxPriority.setSelectedIndex(0);
    	// reset images
    	t1.setIcon(defaultImg);
    	t2.setIcon(defaultImg);
    	t3.setIcon(defaultImg);
    	t4.setIcon(defaultImg);
    	t5.setIcon(defaultImg);
    	t6.setIcon(defaultImg);
    	t7.setIcon(defaultImg);
    	t8.setIcon(defaultImg);
    	t9.setIcon(defaultImg);
    	t10.setIcon(defaultImg);

    	// reset image selection
		t1.setBackground(Color.WHITE);
		t2.setBackground(Color.WHITE);
		t3.setBackground(Color.WHITE);
		t4.setBackground(Color.WHITE);
		t5.setBackground(Color.WHITE);
		t6.setBackground(Color.WHITE);
		t7.setBackground(Color.WHITE);
		t8.setBackground(Color.WHITE);
		t9.setBackground(Color.WHITE);
		t10.setBackground(Color.WHITE);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
            	button.setBackground(Color.RED);
                return button.getText();
            }
        }

        return null;
    }

	/**
	 * Sets ActionListener for "JRadio Thumbnails Buttons"
	 * @param action listener that we're adding to the button
     */
    public void JRadioListener(ActionListener JradioListener) {
			// set action listeners
        t1.addActionListener(JradioListener);
        t2.addActionListener(JradioListener);
        t3.addActionListener(JradioListener);
        t4.addActionListener(JradioListener);
        t5.addActionListener(JradioListener);
        t6.addActionListener(JradioListener);
        t7.addActionListener(JradioListener);
        t8.addActionListener(JradioListener);
        t9.addActionListener(JradioListener);
        t10.addActionListener(JradioListener);
    }

	/**
	 * resets selected/highlighted color of JRadio Thumbnails Buttons
     */
    public void ImageHightlightReset() {
		t1.setBackground(Color.WHITE);
		t2.setBackground(Color.WHITE);
		t3.setBackground(Color.WHITE);
		t4.setBackground(Color.WHITE);
		t5.setBackground(Color.WHITE);
		t6.setBackground(Color.WHITE);
		t7.setBackground(Color.WHITE);
		t8.setBackground(Color.WHITE);
		t9.setBackground(Color.WHITE);
		t10.setBackground(Color.WHITE);
    }

	/**
	 * resizes word image
	 * @param ImageIcon - image to resize
	 * @param Int - new width
	 * @param Int - new Height
	 * @return Icon - resized Image
     */
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    // ************ JTabbedPane 2 ************

    // ****** Getters ******

	/** Gets the answer entered
	 * @return String answer entered
     */
    public String getUserAnswer() {
    	return TypeYourWord.getText();
    }

    // ****** Setters ******

	/**
	 * resets selected/highlighted color of JRadio Options Buttons
     */
    public void OptionHightlightReset() {
    	practiceOp1.setBackground(Color.WHITE);
    	practiceOp2.setBackground(Color.WHITE);
    }

	/**
	 * Sets ActionListener for "JRadio Options" Buttons
	 * @param action listener that we're adding to the button
     */
    public void JRadioListenerOptions(ActionListener JradioListenerOptions) {
			// set action listeners
    	practiceOp1.addActionListener(JradioListenerOptions);
    	practiceOp2.addActionListener(JradioListenerOptions);
			practiceOp3.addActionListener(JradioListenerOptions);
			practiceOp4.addActionListener(JradioListenerOptions);
    }

	/**
	 * Resets all input fields from practice JPanel
     */
    public void resetPracticeFields() {
    	WordPicLabel.setText("Word");
    	TypeYourWord.setText("");
    	ImageIcon currentPic = defaultImg;
    	PicLabel.setIcon(currentPic);
    }

	/**
	 * Sets ActionListener for "Practice" Buttons
	 * @param action listener that we're adding to the button
     */
    public void JRadioListenerPracticeButton(ActionListener JradioListenerPracticeButton) {
			// set action listeners
    	PracticeButton.addActionListener(JradioListenerPracticeButton);
    }

	/**
	 * Set current picture to display
	 * @param String currentPicture - current picture to display
     */
    public void setCurrentPic(String currentPicture) throws MalformedURLException {
    	ImageIcon currentPic = new ImageIcon(new URL(currentPicture));
    	PicLabel.setIcon(currentPic);
    }

	/**
	 * Set current word to display
	 * @param String currentWord - current word to display
     */
    public void setCurrenWord(String currentWord) {
    	WordPicLabel.setText(currentWord);
    }

	/**
	 * Set placeHolder to TypeYourWord field
	 * @param String placeHolder to display in TypeYourWord field
     */
    public void setAnswerField(String placeHolder) {
    	TypeYourWord.setText(placeHolder);
    }

	/**
	 * Set background color of TypeYourWord field
	 * @param String color to set for TypeYourWord background
     */
    public void setAnswerFieldColor(String color) {
    	Color myColor;

    	if (color.equals("GREEN"))
    		myColor = Color.GREEN;
    	else if (color.equals("RED"))
    		myColor = Color.RED;
    	else
    		myColor = Color.WHITE;

    	TypeYourWord.setBackground(myColor);
    }

	/**
	 * Set label of CheckNextButton and background of CheckNextButton
	 * @param String NextCheck - label to set
     */
    public void setCheckNextButtonText(String NextCheck){
    	CheckNextButton.setText(NextCheck); // set text to "Check" or "Next"
    	CheckNextButton.setForeground(Color.WHITE);
    	if (NextCheck.equals("Check"))
    		CheckNextButton.setBackground(new Color(101,171,0)); // green
    	else
    	CheckNextButton.setBackground(new Color(28,175,246)); // blue
    }

	/**
	 * Sets ActionListener for "Check/Next" Button
	 * @param action listener that we're adding to the button
     */
    public void CheckAnswerButton(ActionListener CheckAnswerButton) {
    	CheckNextButton.addActionListener(CheckAnswerButton);
    }

	/**
	 * Sets final score to score label
	 * @param String final score
     */
    public void setSCore(String score) {
    	this.score.setText(score);
    }

	/**
	 * Sets total number of correct answers to correct label
	 * @param String correct - total number of correct answers
     */
    public void setCorrect(String correct) {
    	this.correct.setText(correct);
    }

	/**
	 * Sets total number of incorrect answers to incorrect label
	 * @param String incorrect - total number of incorrect answers
     */
    public void setIncorrect(String incorrect) {
    	this.incorrect.setText(incorrect);
    }

	/**
	 * Sets score labels visible
     */
    public void setScoreVisible() {
		score.setVisible(true);
		correct.setVisible(true);
		incorrect.setVisible(true);
    }

	/**
	 * Sets score labels invisible
     */
    public void setScoreInvisible() {
		score.setVisible(false);
		correct.setVisible(false);
		incorrect.setVisible(false);
    }

}// end class
