import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private int currentIndex;
	private List<Card> cards;

	private Deck() {
		currentIndex = 51;
		cards = new ArrayList<>();

		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.CardNumber cardNumber : Card.CardNumber.values()) {
				cards.add(new Card(suit, cardNumber));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		throwExceptionIfDeckIsEmpty();
		return cards.get(currentIndex--);
	}

	public Card peek() {
		throwExceptionIfDeckIsEmpty();
		return cards.get(currentIndex);
	}

	public int getSize() {
		return currentIndex + 1;
	}

	public void addCard(Card card) {
		throwExceptionIfDeckIsFull();
		currentIndex++;
		cards.set(currentIndex, card); // This feels wrong. But it probably works.
	}


	private void throwExceptionIfDeckIsEmpty() {
		if (currentIndex < 0) {
			throw new RuntimeException("You're out of cards in your deck.");
		}
	}

	private void throwExceptionIfDeckIsFull() {
		if (currentIndex == 51) {
			throw new RuntimeException("You can't add a card to a full deck.");
		}
	}

	public static void main(String[] args) {
		Deck unshuffled = new Deck();
		System.out.println("Unshuffled Deck contents: ");
		while (unshuffled.getSize() != 0) {
			System.out.println(unshuffled.drawCard().toString());
		}

		Deck shuffled = new Deck();
		shuffled.shuffle();
		System.out.println("Shuffled Deck contents: ");
		while (shuffled.getSize() != 0) {
			System.out.println(shuffled.drawCard().toString());
		}
	}

	public static Deck createDeck() {
		return new Deck();
	}

	public static Deck createEmptyDeck() {
		Deck deck = new Deck();
		deck.currentIndex = -1;
		return deck;
	}

}
