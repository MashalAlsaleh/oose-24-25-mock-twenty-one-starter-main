package card;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import card.entity.Card;
import card.entity.Hand;
import card.entity.Player;
import card.entity.PlayerType;
import utility.LoadCSV;
import card.Game;
import communication.InOutInterface;
import communication.ConsoleInOut;

public class MockGameTest{

    Scanner mockScanner = mock(Scanner.class);
    InOutInterface consoleInOut = new ConsoleInOut();
    LoadCSV mockLoadCSV = mock(LoadCSV.class);
    Game game;

    @BeforeEach
    private void setUp(){
        consoleInOut.setScanner(mockScanner);
        game = new Game() {
            @Override
            protected void beforePlayOfRound() {}
            @Override
            protected void afterPlayOfRound() {}
            @Override
            protected void userPlays(Player player) {}
            @Override
            protected void computerPlays(Player player) {}
            @Override
            protected void initiate() {}
        };
        game.setInOut(consoleInOut, false);
    }

    @Test
    void testGetComputerPlayersNamesDealer(){
        when(mockLoadCSV.getRows("test.csv")).thenReturn(Arrays.asList("DEALER,Derek"));
        game.setLoadScanner(mockScanner, "test.csv");
        assertEquals("Derek", game.getComputerPlayersNames().get(0));
    }

    @Test
    void testGetComputerPlayersNamesSecondComputer(){
        when(mockLoadCSV.getRows("test.csv")).thenReturn(Arrays.asList("COMPUTER,Derek", "COMPUTER,Xi"));
        game.setLoadScanner(mockScanner, "test.csv");
        assertEquals("Xi", game.getComputerPlayersNames().get(1));
    }
    
    @Test
    void testCreateComputerCompetitors(){
        when(mockLoadCSV.getRows("test.csv")).thenReturn(Arrays.asList("DEALER,Derek", "COMPUTER,Xi"));
        game.setLoadScanner(mockScanner, "test.csv");
        game.createComputerCompetitors(2);
        assertEquals("Derek", game.getPlayer(0).getName());
    }
    
    @Test
    void testGetInputInteger(){
        when(mockScanner.nextLine()).thenReturn("2");
        assertEquals(2, consoleInOut.getInputInteger("Enter a number:"));
    }
    
    @Test
    void testGetInputString(){
        when(mockScanner.nextLine()).thenReturn("Derek");
        assertEquals("Derek", consoleInOut.getInputString("Enter your name:"));
    }
    
    @Test
    void testGetNumberOfPlayers(){
        when(mockScanner.nextLine()).thenReturn("3");
        assertEquals(3, game.getNumberOfPlayers());
    }
    
    @Test
    void testInitiatePlayers(){
        game.initiatePlayers(3, "Derek");
        assertEquals(3, game.getPlayersSize());
    }
}
