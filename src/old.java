//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class old {
//
//	Card[] yourhand, AIhand;
//	Deck yourdeck, AIdeck, leftextra, rightextra, left, right;
//	int AIspeed;
//	JFrame jfrm;
//	JLabel AIdeckicon, yourdeckicon, AIhand1, AIhand2, AIhand3, AIhand4, AIhand5, leftextraicon, rightextraicon, lefticon, righticon, mode, time, ycl, AIcl;
//	JButton yourhand1, yourhand2, yourhand3, yourhand4, yourhand5, flip, start, reset;
//	Color board = new Color(34, 177, 76);
//	Timer globaltimer;
//	Timer AIaction;
//	int clock = 0;
//
//	public old() {
//		initialize();
//		display();
//		jfrm.setVisible(true);
//		String[] modes = {"Easy Mode", "Normal Mode", "Hard Mode", "Lunatic Mode"};
//		int n = JOptionPane.showOptionDialog(jfrm, "What difficulty setting do you want?", "Difficulty Selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
//		if (n == 0) {
//			AIspeed = 12000;
//			mode.setText("Easy Mode");
//			jfrm.setTitle("Speed - Easy Mode");
//		} else if (n == 1) {
//			AIspeed = 4000;
//			mode.setText("Normal Mode");
//			jfrm.setTitle("Speed - Normal Mode");
//		} else if (n == 2) {
//			AIspeed = 2000;
//			mode.setText("Hard Mode");
//			jfrm.setTitle("Speed - Hard Mode");
//		} else {
//			AIspeed = 1000;
//			mode.setText("Lunatic Mode");
//			jfrm.setTitle("Speed - Lunatic Mode");
//		}
//	}
//
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				new Speed();
//			}
//		});
//	}
//
//	public void initialize() {
//		Deck start = new Deck();
//		start.shuffle();
//		yourhand = new Card[5];
//		AIhand = new Card[5];
//		yourdeck = new Deck(0);
//		AIdeck = new Deck(0);
//		leftextra = new Deck(0);
//		rightextra = new Deck(0);
//		left = new Deck(0);
//		right = new Deck(0);
//		for (int x = 0; x < 5; x++) {
//			leftextra.addCard(start.draw());
//			rightextra.addCard(start.draw());
//		}
//		left.addCard(start.draw());
//		right.addCard(start.draw());
//		for (int x = 0; x < 5; x++) {
//			yourhand[x] = start.draw();
//			AIhand[x] = start.draw();
//		}
//		for (int x = 0; x < 15; x++) {
//			yourdeck.addCard(start.draw());
//			AIdeck.addCard(start.draw());
//		}
//		start = null;
//	}
//
//	public void display() {
//		jfrm = new JFrame("Speed");
//		jfrm.setSize(731, 679);
//		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		// Default BorderLayout
//		jfrm.getContentPane().setBackground(board);
//		JMenuBar jmb = new JMenuBar();
//		JMenu help = new JMenu("Help");
//		JMenuItem inst = new JMenuItem("Instructions");
//		inst.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				JOptionPane.showMessageDialog(jfrm, "<html>This is a game of speed, where speed matters. \nAfter pressing \"Start\", your goal will be to \nget rid of all of the cards in your hand and deck before \nGeorge, the AI, does. You can do this by looking at \nthe cards in the center. If one of the cards in your hand is within \n1 number of a card in the middle, clicking on \nit will automatically remove the card from your hand and place \nit on the corresponding card. For example, if there is a 5 in \nthe middle, you may add a 4, 5, or 6 from your hand \nto that pile. J = 11, Q = 12, K = 13, A = 1, \nand you may put a K on an A or vice versa. If neither you nor George \nhave any plays to make, press \"Flip\" to get new cards in the \nmiddle. You may not flip while you or George has plays to make. Good luck!");
//			}
//		});
//		help.add(inst);
//		JMenuItem info = new JMenuItem("Info");
//		info.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				JOptionPane.showMessageDialog(jfrm, "O++O George Zhang O++O");
//			}
//		});
//		help.add(info);
//		jmb.add(help);
//		JMenu difficulty = new JMenu("Difficulty");
//		JMenuItem easy = new JMenuItem("Easy Mode");
//		easy.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				AIspeed = 12000;
//				mode.setText("Easy Mode");
//				jfrm.setTitle("Speed - Easy Mode");
//				reset();
//			}
//		});
//		JMenuItem normal = new JMenuItem("Normal Mode");
//		normal.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				AIspeed = 4000;
//				mode.setText("Normal Mode");
//				jfrm.setTitle("Speed - Normal Mode");
//				reset();
//			}
//		});
//		JMenuItem hard = new JMenuItem("Hard Mode");
//		hard.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				AIspeed = 2000;
//				mode.setText("Hard Mode");
//				jfrm.setTitle("Speed - Hard Mode");
//				reset();
//			}
//		});
//		JMenuItem lunatic = new JMenuItem("Lunatic Mode");
//		lunatic.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				AIspeed = 1000;
//				mode.setText("Lunatic Mode");
//				jfrm.setTitle("Speed - Lunatic Mode");
//				reset();
//			}
//		});
//		difficulty.add(easy);
//		difficulty.add(normal);
//		difficulty.add(hard);
//		difficulty.add(lunatic);
//		jmb.add(difficulty);
//		jfrm.setJMenuBar(jmb);
//
//
//		JPanel table = new JPanel(new GridLayout(3, 1, 0, 0));
//		table.setPreferredSize(new Dimension(600, 600));
//		JPanel AIside = new JPanel(new GridLayout(1, 2, 0, 0));
//		JPanel AIsidea = new JPanel(new GridLayout(1, 2, 0, 0));
//		AIdeckicon = new JLabel(AIdeck.peek().getBack());
//		AIdeckicon.setPreferredSize(new Dimension(150, 200));
//		AIdeckicon.setBorder(BorderFactory.createLineBorder(board, 10));
//		AIsidea.add(AIdeckicon);
//		JLabel green1 = new JLabel(new ImageIcon("green.png"));
//		green1.setPreferredSize(new Dimension(150, 200));
//		AIsidea.add(green1);
//		AIside.add(AIsidea);
//		JPanel AIhandicons = new JPanel(new GridLayout(1, 2, 0, 0));
//		AIhand1 = new JLabel(AIhand[0].getBack());
//		AIhand1.setPreferredSize(new Dimension(130, 180));
//		AIhand2 = new JLabel(AIhand[1].getBackSideStub());
//		AIhand2.setPreferredSize(new Dimension(37, 180));
//		AIhand3 = new JLabel(AIhand[2].getBackSideStub());
//		AIhand3.setPreferredSize(new Dimension(37, 180));
//		AIhand4 = new JLabel(AIhand[3].getBackSideStub());
//		AIhand4.setPreferredSize(new Dimension(37, 180));
//		AIhand5 = new JLabel(AIhand[4].getBackSideStub());
//		AIhand5.setPreferredSize(new Dimension(37, 180));
//		AIhandicons.add(AIhand1);
//		JPanel AIhandiconsa = new JPanel(new GridLayout(1, 4, 0, 0));
//		AIhandiconsa.add(AIhand2);
//		AIhandiconsa.add(AIhand3);
//		AIhandiconsa.add(AIhand4);
//		AIhandiconsa.add(AIhand5);
//		AIhandiconsa.setBackground(board);
//		AIhandicons.add(AIhandiconsa);
//		AIhandicons.setPreferredSize(new Dimension(300, 200));
//		AIhandicons.setBorder(BorderFactory.createLineBorder(board, 10));
//		AIside.add(AIhandicons);
//		AIside.setPreferredSize(new Dimension(600, 200));
//		table.add(AIside);
//
//
//		JPanel mid = new JPanel(new GridLayout(1, 4, 0, 0));
//		leftextraicon = new JLabel(leftextra.peek().getBack());
//		leftextraicon.setBorder(BorderFactory.createLineBorder(board, 10));
//		mid.add(leftextraicon);
//		lefticon = new JLabel(left.peek().getBack());
//		lefticon.setBorder(BorderFactory.createLineBorder(board, 10));
//		mid.add(lefticon);
//		righticon = new JLabel(right.peek().getBack());
//		righticon.setBorder(BorderFactory.createLineBorder(board, 10));
//		mid.add(righticon);
//		rightextraicon = new JLabel(rightextra.peek().getBack());
//		rightextraicon.setBorder(BorderFactory.createLineBorder(board, 10));
//		mid.add(rightextraicon);
//		table.add(mid);
//
//
//		JPanel yourside = new JPanel(new GridLayout(1, 2, 0, 0));
//		JPanel yourhandicons = new JPanel(new GridLayout(1, 2, 0, 0));
//		yourhandicons.setPreferredSize(new Dimension(300, 200));
//		yourhand1 = new JButton(yourhand[0].getSideStub());
//		yourhand1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				playeraction(1);
//			}
//		});
//		yourhand2 = new JButton(yourhand[1].getSideStub());
//		yourhand2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				playeraction(2);
//			}
//		});
//		yourhand3 = new JButton(yourhand[2].getSideStub());
//		yourhand3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				playeraction(3);
//			}
//		});
//		yourhand4 = new JButton(yourhand[3].getSideStub());
//		yourhand4.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				playeraction(4);
//			}
//		});
//		yourhand5 = new JButton(yourhand[4].getFace());
//		yourhand5.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				playeraction(5);
//			}
//		});
//		JPanel yourhandiconsa = new JPanel(new GridLayout(1, 4, 0, 0));
//		yourhandiconsa.add(yourhand1);
//		yourhandiconsa.add(yourhand2);
//		yourhandiconsa.add(yourhand3);
//		yourhandiconsa.add(yourhand4);
//		yourhandicons.add(yourhandiconsa);
//		yourhandicons.add(yourhand5);
//		yourhandicons.setBorder(BorderFactory.createLineBorder(board, 10));
//		yourside.add(yourhandicons);
//		JPanel yoursidea = new JPanel(new GridLayout(1, 2, 0, 0));
//		yoursidea.add(new JLabel(new ImageIcon("green.png")));
//		yourdeckicon = new JLabel(yourdeck.peek().getBack());
//		yourdeckicon.setBorder(BorderFactory.createLineBorder(board, 10));
//		yoursidea.add(yourdeckicon);
//		yourside.add(yoursidea);
//		table.add(yourside);
//		jfrm.add(table, BorderLayout.WEST);
//
//
//		JPanel panel = new JPanel(new GridLayout(12, 1, 0, 0));
//		JLabel speed = new JLabel("Speed", SwingConstants.CENTER);
//		speed.setPreferredSize(new Dimension(100, 30));
//		mode = new JLabel("Normal Mode", SwingConstants.CENTER);
//		mode.setPreferredSize(new Dimension(100, 20));
//		panel.add(speed);
//		panel.add(mode);
//		time = new JLabel("Time: 0 seconds", SwingConstants.CENTER);
//		panel.add(time);
//		ycl = new JLabel("Cards left: 20", SwingConstants.CENTER);
//		panel.add(ycl);
//		AIcl = new JLabel("AI's cards left: 20", SwingConstants.CENTER);
//		panel.add(AIcl);
//		for (int x = 0; x < 4; x++) {
//			panel.add(new JLabel());
//		}
//		start = new JButton("Start");
//		start.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				start();
//			}
//		});
//		panel.add(start);
//		reset = new JButton("Reset");
//		reset.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				reset();
//			}
//		});
//		panel.add(reset);
//		flip = new JButton("Flip");
//		flip.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				flip();
//			}
//		});
//		flip.setEnabled(false);
//		panel.add(flip);
//		panel.setPreferredSize(new Dimension(100, 658));
//		jfrm.add(panel, BorderLayout.EAST);
//	}
//
//	public void playeraction(int a) {
//		a--;
//		if (!start.isEnabled()) {
//			if (canplay(yourhand[a])) {
//				if ((left.peek().getNum() - 1 == yourhand[a].getNum()) || (left.peek().getNum() == yourhand[a].getNum()) || (left.peek().getNum() + 1 == yourhand[a].getNum()) || ((left.peek().getNum() == 12) && (yourhand[a].getNum() == 0)) || (left.peek().getNum() == 0) && (12 == yourhand[a].getNum())) {
//					left.addCard(yourhand[a]);
//					lefticon.setIcon(left.peek().getFace());
//					if (yourdeck.getSize() != 0) {
//						yourhand[a] = yourdeck.draw();
//					} else {
//						yourhand[a] = null;
//					}
//					renderhand();
//				} else {
//					right.addCard(yourhand[a]);
//					righticon.setIcon(right.peek().getFace());
//					if (yourdeck.getSize() != 0) {
//						yourhand[a] = yourdeck.draw();
//					} else {
//						yourhand[a] = null;
//					}
//					renderhand();
//				}
//			}
//		}
//		if (yourdeck.getSize() == 0) {
//			yourdeckicon.setIcon(new ImageIcon("green.png"));
//		}
//		if (nomoves()) {
//			flip.setEnabled(true);
//		} else {
//			flip.setEnabled(false);
//		}
//		if (isWinner() == 1) {
//			globaltimer.stop();
//			AIaction.stop();
//			JOptionPane.showMessageDialog(jfrm, "You win!");
//			reset();
//		}
//		if (isWinner() == 2) {
//			globaltimer.stop();
//			AIaction.stop();
//			JOptionPane.showMessageDialog(jfrm, "You lose.");
//			reset();
//		}
//	}
//
//	public void start() {
//		start.setEnabled(false);
//		lefticon.setIcon(left.peek().getFace());
//		righticon.setIcon(right.peek().getFace());
//		globaltimer = new Timer(1000, new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				clock++;
//				time.setText("Time: " + clock + " seconds");
//			}
//		});
//		globaltimer.start();
//		AIaction = new Timer(AIspeed, new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				AIturn();
//			}
//		});
//		AIaction.start();
//	}
//
//	public void AIturn() {
//		for (int x = 0; x < 5; x++) {
//			if (canplay(AIhand[x])) {
//				System.out.println("I recognize that I can make a play here with card " + (x + 1) + ", which is a " + (AIhand[x].getNum() + 1));
//				if ((left.peek().getNum() - 1 == AIhand[x].getNum()) || (left.peek().getNum() == AIhand[x].getNum()) || (left.peek().getNum() + 1 == AIhand[x].getNum()) || ((left.peek().getNum() == 12) && (AIhand[x].getNum() == 0)) || (left.peek().getNum() == 0) && (AIhand[x].getNum() == 12)) {
//					System.out.println("So I put it in the left stack");
//					left.addCard(AIhand[x]);
//					lefticon.setIcon(left.peek().getFace());
//					if (AIdeck.getSize() != 0) {
//						AIhand[x] = AIdeck.draw();
//					} else {
//						AIhand[x] = null;
//					}
//					renderAIhand();
//					x = 5; // Only one play per turn for the AI
//				} else {
//					System.out.println("So I put it in the right stack");
//					right.addCard(AIhand[x]);
//					righticon.setIcon(right.peek().getFace());
//					if (AIdeck.getSize() != 0) {
//						AIhand[x] = AIdeck.draw();
//					} else {
//						AIhand[x] = null;
//					}
//					renderAIhand();
//					x = 5; // Only one play per turn for the AI
//				}
//			}
//
//		}
//		if (AIdeck.getSize() == 0) {
//			AIdeckicon.setIcon(new ImageIcon("green.png"));
//		}
//		if (nomoves()) {
//			flip.setEnabled(true);
//		} else {
//			flip.setEnabled(false);
//		}
//		if (isWinner() == 1) {
//			globaltimer.stop();
//			AIaction.stop();
//			JOptionPane.showMessageDialog(jfrm, "You win!");
//			reset();
//		}
//		if (isWinner() == 2) {
//			globaltimer.stop();
//			AIaction.stop();
//			JOptionPane.showMessageDialog(jfrm, "You lose.");
//			reset();
//		}
//	}
//
//	public void renderhand() {
//		boolean changemade = true;
//		boolean done = false;
//		Card temp;
//		int cardsinhand = 0;
//		while (changemade) {
//			changemade = false;
//			for (int x = 4; x >= 0; x--) {
//				if (yourhand[x] == null) {
//					done = true;
//					for (int y = x; y >= 0; y--) {
//						if (yourhand[y] != null) {
//							done = false;
//						}
//					}
//					if (!done) {
//						for (int y = x; y > 0; y--) {
//							changemade = true;
//							temp = yourhand[y];
//							yourhand[y] = yourhand[y - 1];
//							yourhand[y - 1] = temp;
//						}
//					}
//				}
//			}
//		}
//		if (yourhand[0] != null) {
//			yourhand1.setIcon(yourhand[0].getSideStub());
//		} else {
//			yourhand1.setIcon(new ImageIcon("green.png"));
//		}
//		if (yourhand[1] != null) {
//			yourhand2.setIcon(yourhand[1].getSideStub());
//		} else {
//			yourhand2.setIcon(new ImageIcon("green.png"));
//		}
//		if (yourhand[2] != null) {
//			yourhand3.setIcon(yourhand[2].getSideStub());
//		} else {
//			yourhand3.setIcon(new ImageIcon("green.png"));
//		}
//		if (yourhand[3] != null) {
//			yourhand4.setIcon(yourhand[3].getSideStub());
//		} else {
//			yourhand4.setIcon(new ImageIcon("green.png"));
//		}
//		if (yourhand[4] != null) {
//			yourhand5.setIcon(yourhand[4].getFace());
//		} else {
//			yourhand5.setIcon(new ImageIcon("green.png"));
//		}
//		for (int x = 0; x < 5; x++) {
//			if (yourhand[x] != null) {
//				cardsinhand++;
//			}
//		}
//		ycl.setText("Cards left: " + (yourdeck.getSize() + cardsinhand));
//	}
//
//	public void renderAIhand() {
//		boolean changemade = true;
//		boolean done;
//		Card temp;
//		int cardsinhand = 0;
//		while (changemade) { // concatenate the hand, I guess?
//			changemade = false;
//			for (int x = 0; x < 5; x++) {
//				if (AIhand[x] == null) {
//					done = true;
//					for (int y = x; y < 5; y++) {
//						if (AIhand[y] != null) {
//							done = false;
//						}
//					}
//					if (!done) {
//						for (int y = x; y < 4; y++) {
//							changemade = true;
//							temp = AIhand[y];
//							AIhand[y] = AIhand[y + 1];
//							AIhand[y + 1] = temp;
//						}
//					}
//				}
//			}
//		}
//		if (AIhand[0] != null) {
//			AIhand1.setIcon(AIhand[0].getBack());
//		} else {
//			AIhand1.setIcon(new ImageIcon("green.png"));
//		}
//		if (AIhand[1] != null) {
//			AIhand2.setIcon(AIhand[1].getBackSideStub());
//		} else {
//			AIhand2.setIcon(new ImageIcon("green.png"));
//		}
//		if (AIhand[2] != null) {
//			AIhand3.setIcon(AIhand[2].getBackSideStub());
//		} else {
//			AIhand3.setIcon(new ImageIcon("green.png"));
//		}
//		if (AIhand[3] != null) {
//			AIhand4.setIcon(AIhand[3].getBackSideStub());
//		} else {
//			AIhand4.setIcon(new ImageIcon("green.png"));
//		}
//		if (AIhand[4] != null) {
//			AIhand5.setIcon(AIhand[4].getBackSideStub());
//		} else {
//			AIhand5.setIcon(new ImageIcon("green.png"));
//		}
//		for (int x = 0; x < 5; x++) {
//			if (AIhand[x] != null) {
//				cardsinhand++;
//			}
//		}
//		AIcl.setText("AI's cards left: " + (AIdeck.getSize() + cardsinhand));
//	}
//
//	public void reset() {
//		initialize();
//		AIdeckicon.setIcon(AIdeck.peek().getBack());
//		AIhand1.setIcon(AIhand[0].getBack());
//		AIhand2.setIcon(AIhand[1].getBackSideStub());
//		AIhand3.setIcon(AIhand[2].getBackSideStub());
//		AIhand4.setIcon(AIhand[3].getBackSideStub());
//		AIhand5.setIcon(AIhand[4].getBackSideStub());
//		lefticon.setIcon(left.peek().getBack());
//		righticon.setIcon(right.peek().getBack());
//		leftextraicon.setIcon(leftextra.peek().getBack());
//		rightextraicon.setIcon(rightextra.peek().getBack());
//		yourhand1.setIcon(yourhand[0].getSideStub());
//		yourhand2.setIcon(yourhand[1].getSideStub());
//		yourhand3.setIcon(yourhand[2].getSideStub());
//		yourhand4.setIcon(yourhand[3].getSideStub());
//		yourhand5.setIcon(yourhand[4].getFace());
//		yourdeckicon.setIcon(yourdeck.peek().getBack());
//		globaltimer.stop();
//		clock = 0;
//		AIaction.stop();
//		start.setEnabled(true);
//		time.setText("Time: 0 seconds");
//		ycl.setText("Cards left: 20");
//		AIcl.setText("AI's cards left: 20");
//		// various other things including the toolbar and timers
//	}
//
//	public void flip() {
//		if ((leftextra.getSize() == 0) || (rightextra.getSize() == 0)) {
//			resupplyextras();
//		}
//		left.addCard(leftextra.draw());
//		right.addCard(rightextra.draw());
//		lefticon.setIcon(left.peek().getFace());
//		righticon.setIcon(right.peek().getFace());
//		if (leftextra.getSize() == 0) {
//			leftextraicon.setIcon(new ImageIcon("green.png"));
//		}
//		if (rightextra.getSize() == 0) {
//			rightextraicon.setIcon(new ImageIcon("green.png"));
//		}
//		if (nomoves()) {
//			flip.setEnabled(true);
//		} else {
//			flip.setEnabled(false);
//		}
//	}
//
//	public void resupplyextras() {
//		int total = left.getSize() + right.getSize();
//		if (total <= 3) {
//			JOptionPane.showMessageDialog(jfrm, "Unwinnable game. Client will abort.");
//			System.exit(0);
//		} else {
//			Card templeft = left.draw();
//			Card tempright = right.draw();
//			Deck mixing = new Deck(0);
//			while (left.getSize() != 0) {
//				mixing.addCard(left.draw());
//			}
//			while (right.getSize() != 0) {
//				mixing.addCard(right.draw());
//			}
//			mixing.shuffle();
//			if (leftextra.getSize() == 0) {
//				while (mixing.getSize() != 0) {
//					leftextra.addCard(mixing.draw());
//					if (mixing.getSize() != 0) {
//						rightextra.addCard(mixing.draw());
//					}
//				}
//			} else {
//				while (mixing.getSize() != 0) {
//					rightextra.addCard(mixing.draw());
//					if (mixing.getSize() != 0) {
//						leftextra.addCard(mixing.draw());
//					}
//				}
//			}
//			leftextraicon.setIcon(leftextra.peek().getBack());
//			rightextraicon.setIcon(rightextra.peek().getBack());
//			left.addCard(templeft);
//			right.addCard(tempright);
//			lefticon.setIcon(templeft.getFace());
//			righticon.setIcon(tempright.getFace());
//		}
//	}
//
//	public boolean nomoves() { // Checks each card of the hand to see if it can be played. Used to toggle the "Flip" button.
//		for (int x = 0; x < 5; x++) {
//			if (canplay(AIhand[x])) {
//				return false;
//			}
//			if (canplay(yourhand[x])) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public boolean canplay(Card c) { // Checks if a card can be applied to the middle.
//		if (c == null) {
//			return false;
//		}
//		int value = c.getNum();
//		if (left.peek().getNum() == value) {
//			return true;
//		}
//		if (left.peek().getNum() == value + 1) {
//			return true;
//		}
//		if (left.peek().getNum() == value - 1) {
//			return true;
//		}
//		if ((value == 0) && (left.peek().getNum() == 12)) {
//			return true;
//		}
//		if ((value == 12) && (left.peek().getNum() == 0)) {
//			return true;
//		}
//		if (right.peek().getNum() == value) {
//			return true;
//		}
//		if (right.peek().getNum() == value + 1) {
//			return true;
//		}
//		if (right.peek().getNum() == value - 1) {
//			return true;
//		}
//		if ((value == 0) && (right.peek().getNum() == 12)) {
//			return true;
//		}
//		if ((value == 12) && (right.peek().getNum() == 0)) {
//			return true;
//		}
//		return false;
//	}
//
//	public int isWinner() {
//		if ((yourhand[0] == null) && (yourhand[1] == null) && (yourhand[2] == null) && (yourhand[3] == null) && (yourhand[4] == null)) {
//			return 1;
//		}
//		if ((AIhand[0] == null) && (AIhand[1] == null) && (AIhand[2] == null) && (AIhand[3] == null) && (AIhand[4] == null)) {
//			return 2;
//		}
//		return 0;
//	}
//}
