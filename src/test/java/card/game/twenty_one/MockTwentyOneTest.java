package card.game.twenty_one;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import card.entity.Hand;
import card.entity.Player;
import card.entity.PlayerType;
import card.game.twenty_one.TwentyOne;
import communication.YesOrNo;

public class MockTwentyOneTest {
    TwentyOne twentyOne = new TwentyOne("H2,H3,H4,H5,H6");
    Scanner mockScanner = mock(Scanner.class);

    @Test
    void testUserPlaysStick(){
        Player player = new Player(PlayerType.USER, "Derek");
        twentyOne.createHumanPlayer("Derek");
        when(mockScanner.nextLine()).thenReturn("Stick");
        twentyOne.setConsoleScanner(mockScanner);
        twentyOne.userPlays(player);
        assertFalse(player.hasHand());
    }

    @Test
    void testUserPlaysTwistAndBust(){
        Player player = new Player(PlayerType.USER, "Derek");
        twentyOne.createHumanPlayer("Derek");
        player.addHand(new Hand("H10,H9,H5"));
        when(mockScanner.nextLine()).thenReturn("Twist");
        twentyOne.setConsoleScanner(mockScanner);
        twentyOne.userPlays(player);
        assertEquals(0, twentyOne.scoreHand(player.getHand()));
    }

    @Test
    void testUserPlaysTwistNotBust(){
        Player player = new Player(PlayerType.USER, "Derek");
        twentyOne.createHumanPlayer("Derek");
        player.addHand(new Hand("H2,H3"));
        when(mockScanner.nextLine()).thenReturn("Twist");
        twentyOne.setConsoleScanner(mockScanner);
        twentyOne.userPlays(player);
        assertTrue(twentyOne.scoreHand(player.getHand()) > 5);
    }

    @Test
    void testPlayStick(){
        twentyOne.createHumanPlayer("Derek");
        when(mockScanner.nextLine()).thenReturn("Stick");
        twentyOne.setConsoleScanner(mockScanner);
        twentyOne.play();
        assertFalse(twentyOne.getUser().hasHand());
    }

    @Test
    void testPlayTwistAndBust(){
        twentyOne.createHumanPlayer("Derek");
        twentyOne.getUser().addHand(new Hand("H10,H9,H5"));
        when(mockScanner.nextLine()).thenReturn("Twist");
        twentyOne.setConsoleScanner(mockScanner);
        twentyOne.play();
        assertEquals(0, twentyOne.scoreHand(twentyOne.getUser().getHand()));
    }

    @Test
    void testPlayTwistNotBust(){
        twentyOne.createHumanPlayer("Derek");
        twentyOne.getUser().addHand(new Hand("H2,H3"));
        when(mockScanner.nextLine()).thenReturn("Twist");
        twentyOne.setConsoleScanner(mockScanner);
        twentyOne.play();
        assertTrue(twentyOne.scoreHand(twentyOne.getUser().getHand()) > 5);
    }
}