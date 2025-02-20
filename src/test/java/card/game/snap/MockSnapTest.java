package card.game.snap;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import card.entity.Hand;
import card.entity.Card;

public class MockSnapTest {
    Snap snap = new Snap("C3,H3,D3,S3");
    Scanner mockScanner = mock(Scanner.class);

    @Test
    void testPlayerPlaysHandScore(){
        Hand hand = new Hand("S5,S4,S3");
        snap.createHumanPlayer("Derek");
        snap.getUser().addHand(hand);
        snap.addToDiscarded(new Card("C3"));
        when(mockScanner.nextLine()).thenReturn("Yes");
        snap.setConsoleScanner(mockScanner);
        snap.playerPlaysHand(snap.getUser());
        assertEquals(1, snap.getUser().getScore());
    }

    @Test
    void testPlayerPlaysHandSnapYesMatchingCards(){
        Hand hand = new Hand("C3,C3");
        snap.createHumanPlayer("Derek");
        snap.getUser().addHand(hand);
        when(mockScanner.nextLine()).thenReturn("Yes");
        snap.setConsoleScanner(mockScanner);
        snap.playerPlaysHand(snap.getUser());
        assertEquals(1, snap.getUser().getScore());
    }

    @Test
    void testPlayerPlaysHandSnapYesDifferentCards(){
        Hand hand = new Hand("C3,H4");
        snap.createHumanPlayer("Derek");
        snap.getUser().addHand(hand);
        when(mockScanner.nextLine()).thenReturn("Yes");
        snap.setConsoleScanner(mockScanner);
        snap.playerPlaysHand(snap.getUser());
        assertEquals(-1, snap.getUser().getScore());
    }

    @Test
    void testPlaySnapYesMatchingCards(){
        snap = new Snap("C3,C3");
        snap.createHumanPlayer("Derek");
        when(mockScanner.nextLine()).thenReturn("Yes");
        snap.setConsoleScanner(mockScanner);
        snap.play();
        assertEquals(1, snap.getUser().getScore());
    }

    @Test
    void testPlaySnapYesDifferentCards(){
        snap = new Snap("C3,H4");
        snap.createHumanPlayer("Derek");
        when(mockScanner.nextLine()).thenReturn("Yes");
        snap.setConsoleScanner(mockScanner);
        snap.play();
        assertEquals(-1, snap.getUser().getScore());
    }
}

