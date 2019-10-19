import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Speed {

	public static final int CARDS_IN_EXTRA_DECKS = 5;
	public static final int CARDS_PER_HAND = 5;


	private List<Card> playersHand;
	private List<Card> opponentsHand;
	private Deck playersDeck;
	private Deck opponentsDeck;
	private Deck leftExtraDeck;
	private Deck rightExtraDeck;
	private Deck leftDeck;
	private Deck rightDeck;

	private int opponentsSpeedMillis;

	private Timer golbalTimer;
	private Timer opponentsActionTimer;

	private int clock;

	// All the items below are related to the layout of the board

	private JFrame jFrame;

	private JLabel playersDeckIcon;
	private JLabel opponentsDeckIcon;
	private JLabel opponentsHandLabel1;
	private JLabel opponentsHandLabel2;
	private JLabel opponentsHandLabel3;
	private JLabel opponentsHandLabel4;
	private JLabel opponentsHandLabel5;
	private JLabel leftExtraIcon;
	private JLabel rightExtraIcon;
	private JLabel leftIcon;
	private JLabel rightIcon;
	private JLabel modeLabel;
	private JLabel timeLabel;
	private JLabel playersCardsRemainingLabel;
	private JLabel opponentsCardsRemainingLabel;

	private JButton playersHandButton1;
	private JButton playersHandButton2;
	private JButton playersHandButton3;
	private JButton playersHandButton4;
	private JButton playersHandButton5;

	private JButton flip;
	private JButton start;
	private JButton reset;

	Color boardColor = new Color(34, 177, 76);

	public Speed() {
		initializeData();
		initializeDisplay();

		jFrame.setVisible(true);

		displayDifficultySelect();
	}

	private void initializeData() {
		Deck startingDeck = Deck.createDeck();
		startingDeck.shuffle();

		initializeEmptyStates();
		initializeCardsOnBoard(startingDeck);
		initializeCardsForPlayers(startingDeck);

		start = null;
	}

	private void initializeEmptyStates() {
		playersDeck = Deck.createEmptyDeck();
		opponentsDeck = Deck.createEmptyDeck();
		leftExtraDeck = Deck.createEmptyDeck();
		rightExtraDeck = Deck.createEmptyDeck();
		leftDeck = Deck.createEmptyDeck();
		rightDeck = Deck.createEmptyDeck();

		playersHand = new ArrayList<>(5);
		opponentsHand = new ArrayList<>(5);
	}

	private void initializeCardsOnBoard(Deck startingDeck) {
		for (int x = 0; x < CARDS_IN_EXTRA_DECKS; x++) {
			leftExtraDeck.addCard(startingDeck.drawCard());
			rightExtraDeck.addCard(startingDeck.drawCard());
		}

		leftDeck.addCard(startingDeck.drawCard());
		rightDeck.addCard(startingDeck.drawCard());
	}

	private void initializeCardsForPlayers(Deck startingDeck) {
		for (int x = 0; x < CARDS_PER_HAND; x++) {
			playersHand.add(startingDeck.drawCard());
			opponentsHand.add(startingDeck.drawCard());
		}

		while (startingDeck.getSize() != 0) {
			playersDeck.addCard(startingDeck.drawCard());
			opponentsDeck.addCard(startingDeck.drawCard());
		}
	}

	private void initializeDisplay() {
		jFrame = new JFrame("Speed");
		jFrame.setSize(getDimensionsOfDisplay());
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.getContentPane().setBackground(boardColor);
		jFrame.setJMenuBar(initializeMenu());

		jFrame.add(initializeTable(), BorderLayout.WEST);
		jFrame.add(initializeInfoPanel(), BorderLayout.EAST);
	}

	private Dimension getDimensionsOfDisplay() {
		// todo maybe this can be dynamic based on OS, idk
		return new Dimension(731, 679);
	}

	private JMenuBar initializeMenu() {
		JMenuBar menu = new JMenuBar();

		JMenu helpMenu = new JMenu("Help");

		JMenuItem instructions = new JMenuItem("Instructions");
		instructions.addActionListener(ae -> {
			JOptionPane.showMessageDialog(jFrame, "message");
		});
		helpMenu.add(instructions);

		JMenuItem info = new JMenuItem("Info");
		info.addActionListener(ae -> {
			JOptionPane.showMessageDialog(jFrame, "O++O George Zhang O++O");
		});
		helpMenu.add(info);
		menu.add(helpMenu);

		JMenu difficultyMenu = new JMenu("Difficulty");

		JMenuItem easy = new JMenuItem("Easy Mode");
		easy.addActionListener(ae -> {
			setGameToEasyMode();
			reset();
		});
		difficultyMenu.add(easy);

		JMenuItem normal = new JMenuItem("Normal Mode");
		normal.addActionListener(ae -> {
			setGameToNormalMode();
			reset();
		});
		difficultyMenu.add(normal);

		JMenuItem hard = new JMenuItem("Hard Mode");
		hard.addActionListener(ae -> {
			setGameToHardMode();
			reset();
		});
		difficultyMenu.add(hard);

		JMenuItem lunatic = new JMenuItem("Lunatic Mode");
		lunatic.addActionListener(ae -> {
			setGameToLunaticMode();
			reset();
		});
		difficultyMenu.add(lunatic);

		menu.add(difficultyMenu);

		return menu;
	}

	public JPanel initializeTable() {
		JPanel table = new JPanel(new GridLayout(3, 1, 0, 0));
		table.setPreferredSize(new Dimension(600, 600));

		table.add(initializeOpponentsTablePanel());
		table.add(initializeMiddleTablePanel());
		table.add(initializePlayersTablePanel());

		return table;
	}

	private JPanel initializeOpponentsTablePanel() {
		JPanel opponentsPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		opponentsPanel.setPreferredSize(new Dimension(600, 200));

		JPanel opponentsLeftPanel = new JPanel(new GridLayout(1, 2, 0, 0));

		opponentsDeckIcon = new JLabel(opponentsDeck.peek().getBack());
		opponentsDeckIcon.setPreferredSize(new Dimension(150, 200));
		opponentsDeckIcon.setBorder(BorderFactory.createLineBorder(boardColor, 10));
		opponentsLeftPanel.add(opponentsDeckIcon);

		JLabel greenPanel1 = new JLabel(new ImageIcon("green.png"));
		greenPanel1.setPreferredSize(new Dimension(150, 200));
		opponentsLeftPanel.add(greenPanel1);

		opponentsPanel.add(opponentsLeftPanel);

		JPanel opponentsRightPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		opponentsRightPanel.setPreferredSize(new Dimension(300, 200));
		opponentsRightPanel.setBorder(BorderFactory.createLineBorder(boardColor, 10));

		opponentsHandLabel1 = new JLabel(opponentsHand.get(0).getBack());
		opponentsHandLabel1.setPreferredSize(new Dimension(130, 180));
		opponentsRightPanel.add(opponentsHandLabel1);

		JPanel opponentsRightMostPanel = new JPanel(new GridLayout(1, 4, 0, 0));
		opponentsRightMostPanel.setBackground(boardColor);


		opponentsHandLabel2 = new JLabel(opponentsHand.get(1).getBackSideStub());
		opponentsHandLabel2.setPreferredSize(new Dimension(37, 180));
		opponentsRightMostPanel.add(opponentsHandLabel2);

		opponentsHandLabel3 = new JLabel(opponentsHand.get(2).getBackSideStub());
		opponentsHandLabel3.setPreferredSize(new Dimension(37, 180));
		opponentsRightMostPanel.add(opponentsHandLabel3);

		opponentsHandLabel4 = new JLabel(opponentsHand.get(3).getBackSideStub());
		opponentsHandLabel4.setPreferredSize(new Dimension(37, 180));
		opponentsRightMostPanel.add(opponentsHandLabel4);

		opponentsHandLabel5 = new JLabel(opponentsHand.get(4).getBackSideStub());
		opponentsHandLabel5.setPreferredSize(new Dimension(37, 180));
		opponentsRightMostPanel.add(opponentsHandLabel5);

		opponentsRightPanel.add(opponentsRightMostPanel);

		opponentsPanel.add(opponentsRightPanel);

		return opponentsPanel;
	}

	private JPanel initializeMiddleTablePanel() {
		JPanel middlePanel = new JPanel(new GridLayout(1, 4, 0, 0));

		leftExtraIcon = new JLabel(leftExtraDeck.peek().getBack());
		leftExtraIcon.setBorder(BorderFactory.createLineBorder(boardColor, 10));
		middlePanel.add(leftExtraIcon);

		leftIcon = new JLabel(leftExtraDeck.peek().getBack());
		leftIcon.setBorder(BorderFactory.createLineBorder(boardColor, 10));
		middlePanel.add(leftIcon);

		rightIcon = new JLabel(rightDeck.peek().getBack());
		rightIcon.setBorder(BorderFactory.createLineBorder(boardColor, 10));
		middlePanel.add(rightIcon);

		rightExtraIcon = new JLabel(rightExtraDeck.peek().getBack());
		rightExtraIcon.setBorder(BorderFactory.createLineBorder(boardColor, 10));
		middlePanel.add(rightExtraIcon);

		return middlePanel;
	}

	private JPanel initializePlayersTablePanel() {
		JPanel playersPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		JPanel playersHandPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		playersHandPanel.setPreferredSize(new Dimension(300, 200));
		playersHandPanel.setBorder(BorderFactory.createLineBorder(boardColor, 10));

		JPanel playersHandLeftPanel = new JPanel(new GridLayout(1, 4, 0, 0));

		playersHandButton1 = new JButton(playersHand.get(0).getSideStub());
		playersHandButton1.addActionListener(ae -> playCard(0));
		playersHandLeftPanel.add(playersHandButton1);

		playersHandButton2 = new JButton(playersHand.get(1).getSideStub());
		playersHandButton2.addActionListener(ae -> playCard(1));
		playersHandLeftPanel.add(playersHandButton2);

		playersHandButton3 = new JButton(playersHand.get(2).getSideStub());
		playersHandButton3.addActionListener(ae -> playCard(2));
		playersHandLeftPanel.add(playersHandButton3);

		playersHandButton4 = new JButton(playersHand.get(3).getSideStub());
		playersHandButton4.addActionListener(ae -> playCard(3));
		playersHandLeftPanel.add(playersHandButton4);

		playersHandPanel.add(playersHandLeftPanel);

		playersHandButton5 = new JButton(playersHand.get(4).getFace());
		playersHandButton5.addActionListener(ae -> playCard(4));
		playersHandPanel.add(playersHandButton5);

		playersPanel.add(playersHandPanel);

		JPanel playersRightPanel = new JPanel(new GridLayout(1, 2, 0, 0));

		playersRightPanel.add(new JLabel(new ImageIcon("green.png")));

		playersDeckIcon = new JLabel(playersDeck.peek().getBack());
		playersDeckIcon.setBorder(BorderFactory.createLineBorder(boardColor, 10));
		playersRightPanel.add(playersDeckIcon);

		playersPanel.add(playersRightPanel);

		return playersPanel;
	}

	private JPanel initializeInfoPanel() {
		JPanel infoPanel = new JPanel(new GridLayout(12, 1, 0, 0));

		JLabel speedLabel = new JLabel("Speed", SwingConstants.CENTER);
		speedLabel.setPreferredSize(new Dimension(100, 30));
		infoPanel.add(speedLabel);

		modeLabel = new JLabel("Normal Mode", SwingConstants.CENTER);
		modeLabel.setPreferredSize(new Dimension(100, 20));
		infoPanel.add(modeLabel);

		timeLabel = new JLabel("Time: 0 seconds", SwingConstants.CENTER);
		infoPanel.add(timeLabel);

		playersCardsRemainingLabel = new JLabel("Cards left: 20", SwingConstants.CENTER);
		infoPanel.add(playersCardsRemainingLabel);

		opponentsCardsRemainingLabel = new JLabel("AI's cards left: 20", SwingConstants.CENTER);
		infoPanel.add(opponentsCardsRemainingLabel);

		for (int x = 0; x < 4; x++) {
			infoPanel.add(new JLabel(/* some blank panels for spacing */));
		}

		start = new JButton("Start");
		start.addActionListener(ae -> start());
		infoPanel.add(start);

		reset = new JButton("Reset");
		reset.addActionListener(ae -> reset());
		infoPanel.add(reset);

		flip = new JButton("Flip");
		flip.addActionListener(ae -> flip());
		flip.setEnabled(false);
		infoPanel.add(flip);

		infoPanel.setPreferredSize(new Dimension(100, 658));

		return infoPanel;
	}

	private void displayDifficultySelect() {
		String[] modes = {"Easy Mode", "Normal Mode", "Hard Mode", "Lunatic Mode"};
		int selection = JOptionPane.showOptionDialog(
				jFrame,
				"What difficulty setting do you want?",
				"Difficulty Selection",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				modes,
				modes[0]

		);
		if (selection == 0) {
			setGameToEasyMode();
		} else if (selection == 1) {
			setGameToNormalMode();
		} else if (selection == 2) {
			setGameToHardMode();
		} else {
			setGameToLunaticMode();
		}
	}

	private void setGameToEasyMode() {
		setGameToMode(12000, "Easy");
	}

	private void setGameToNormalMode() {
		setGameToMode(4000, "Normal");
	}

	private void setGameToHardMode() {
		setGameToMode(2000, "Hard");
	}

	private void setGameToLunaticMode() {
		setGameToMode(1000, "Lunatic");
	}

	private void setGameToMode(int opponentsSpeedMillis, String modeName) {
		this.opponentsSpeedMillis = opponentsSpeedMillis;
		modeLabel.setText(modeName + " Mode");
		jFrame.setTitle("Speed - " + modeName + " Mode");
	}

	private void reset() {
		initializeData();
		resetDisplay();
		// todo
	}

	private void resetDisplay() {
		AIdeckicon.setIcon( AIdeck.peek().getBack() );
		AIhand1.setIcon( AIhand[0].getBack() );
		AIhand2.setIcon( AIhand[1].getBackSideStub() );
		AIhand3.setIcon( AIhand[2].getBackSideStub() );
		AIhand4.setIcon( AIhand[3].getBackSideStub() );
		AIhand5.setIcon( AIhand[4].getBackSideStub() );
		lefticon.setIcon( left.peek().getBack() );
		righticon.setIcon( right.peek().getBack() );
		leftextraicon.setIcon( leftextra.peek().getBack() );
		rightextraicon.setIcon( rightextra.peek().getBack() );
		yourhand1.setIcon( yourhand[0].getSideStub() );
		yourhand2.setIcon( yourhand[1].getSideStub() );
		yourhand3.setIcon( yourhand[2].getSideStub() );
		yourhand4.setIcon( yourhand[3].getSideStub() );
		yourhand5.setIcon( yourhand[4].getFace() );
		yourdeckicon.setIcon( yourdeck.peek().getBack() );
	}

	private void playCard(int index) {
//		if (!start.isEnabled()) {
//			playCardIfValidMove(playersHand.get(index));
//		}
//
//		if (playersDeck.getSize() == 0) {
//			playersDeckIcon.setIcon(new ImageIcon("green.png"));
//		}
//
//		if (noMovesAvailable()) {
//			flip.setEnabled(true);
//		} else {
//			flip.setEnabled(false);
//		}
//
//		endGameIfWinnerExists();
	}

	private void start() {

	}

	private void flip() {

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Speed::new);
	}


}
