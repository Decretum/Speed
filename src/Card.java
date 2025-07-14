import javax.swing.*;

public class Card {

	private static final String FACE_IMAGE_ASSET_SUFFIX = ".png";
	private static final String TOP_STUB_IMAGE_ASSET_SUFFIX = "t.png";
	private static final String SIDE_STUB_IMAGE_ASSET_SUFFIX = "s.png";
	private static final String BACK_IMAGE_ASSET = "assets/back.png";
	private static final String BACK_SIDE_STUB_IMAGE_ASSET = "assets/backsidestub.png";

	private Suit suit;
	private CardNumber cardNumber;

	private ImageIcon face;
	private ImageIcon back;
	private ImageIcon topStub;
	private ImageIcon sideStub;
	private ImageIcon backSideStub;


	public enum Suit {

		DIAMOND(3),
		CLUB(2),
		HEART(1),
		SPADE(0);

		private int value;

		public int getValue() {
			return value;
		}

		Suit(int value) {
			this.value = value;
		}
	}

	public enum CardNumber {

		ACE(1),
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5),
		SIX(6),
		SEVEN(7),
		EIGHT(8),
		NINE(9),
		TEN(10),
		JACK(11),
		QUEEN(12),
		KING(13);

		private int value;

		public int getValue() {
			return value;
		}

		CardNumber(int value) {
			this.value = value;
		}
	}

	public Card(Suit suit, CardNumber cardNumber) {
		this.suit = suit;
		this.cardNumber = cardNumber;

		String cardValue = String.valueOf(computeCardValue());

		face = new ImageIcon("assets/" + cardValue + FACE_IMAGE_ASSET_SUFFIX);
		back = new ImageIcon(BACK_IMAGE_ASSET);
		topStub = new ImageIcon("assets/" + cardValue + TOP_STUB_IMAGE_ASSET_SUFFIX);
		sideStub = new ImageIcon("assets/" + cardValue + SIDE_STUB_IMAGE_ASSET_SUFFIX);
		backSideStub = new ImageIcon(BACK_SIDE_STUB_IMAGE_ASSET);
	}

	private int computeCardValue() {
		int suitValue = suit.getValue() * 13;
		int cardNumberValue = cardNumber.getValue();

		return suitValue + cardNumberValue;
	}

	public Suit getSuit() {
		return suit;
	}

	public CardNumber getCardNumber() {
		return cardNumber;
	}

	public ImageIcon getFace() {
		return face;
	}

	public ImageIcon getBack() {
		return back;
	}

	public ImageIcon getTopStub() {
		return topStub;
	}

	public ImageIcon getSideStub() {
		return sideStub;
	}

	public ImageIcon getBackSideStub() {
		return backSideStub;
	}

	public String toString() {
		return cardNumber.name() + " of " + suit.name() + "S";
	}

}
