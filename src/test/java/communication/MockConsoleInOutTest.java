package communication;

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

public class MockConsoleInOutTest{

    Scanner mockScanner = mock(Scanner.class);
    ConsoleInOut consoleInOut = new ConsoleInOut();
    LoadCSV mockLoadCSV = mock(LoadCSV.class);

    @BeforeEach
    private void setUp(){
        consoleInOut.setScanner(mockScanner);
    }

    @Test
    void testGetInteger(){
        when(mockScanner.nextLine()).thenReturn("2");
        assertEquals(2, consoleInOut.getInteger());
    }
    
    @Test
    void testGetInputInteger(){
        when(mockScanner.nextLine()).thenReturn("2");
        assertEquals(2, consoleInOut.getInputInteger("Enter a number:"));
    }
    
    @Test
    void testGetInputIntegerSecondTime(){
        when(mockScanner.nextLine()).thenReturn("Del", "2");
        assertEquals(2, consoleInOut.getInputInteger("Enter a number:"));
    }
    
    @Test
    void testGetListIndex(){
        List<String> options = Arrays.asList("One", "Two");
        when(mockScanner.nextLine()).thenReturn("1");
        assertEquals(1, consoleInOut.getListIndex(options));
    }

    @Test
    void testGetListIndexSecondTime(){
        List<String> options = Arrays.asList("One", "Two");
        when(mockScanner.nextLine()).thenReturn("3", "1");
        assertEquals(1, consoleInOut.getListIndex(options));
    }
    
    @Test
    void testGetListIndexWithQuestion(){
        List<String> options = Arrays.asList("One", "Two");
        when(mockScanner.nextLine()).thenReturn("1");
        assertEquals(1, consoleInOut.getListIndex("Select an option:", options));
    }

    @Test
    void testGetPlayersCard(){
        Player player = new Player(PlayerType.USER, "TestPlayer");
        player.addHand(new Hand("C3,C4,C5"));
        when(mockScanner.nextLine()).thenReturn("1");
        assertEquals("C4", consoleInOut.getPlayersCard(player).toString());
    }
    
    @Test
    void testGetYesOrNoYes(){
        when(mockScanner.nextLine()).thenReturn("Yes");
        assertEquals(YesOrNo.YES, consoleInOut.getYesOrNo("Confirm?"));
    }

    @Test
    void testGetYesOrNoY(){
        when(mockScanner.nextLine()).thenReturn("Y");
        assertEquals(YesOrNo.YES, consoleInOut.getYesOrNo("Confirm?"));
    }

    @Test
    void testGetYesOrNoNo(){
        when(mockScanner.nextLine()).thenReturn("N");
        assertEquals(YesOrNo.NO, consoleInOut.getYesOrNo("Confirm?"));
    }

    @Test
    void testGetYesOrNoOther(){
        when(mockScanner.nextLine()).thenReturn("");
        assertEquals(YesOrNo.NO, consoleInOut.getYesOrNo("Confirm?"));
    }

    @Test
    void testGetRows(){
        List<String> mockData = Arrays.asList("Row1,Data1", "Row2,Data2");
        when(mockLoadCSV.getRows("test.csv")).thenReturn(mockData);
        assertEquals(2, mockLoadCSV.getRows("test.csv").size());
    }
    
    @Test
    void testGetCSVRows(){
        List<String[]> mockData = new ArrayList<>();
        mockData.add(new String[]{"Row1", "Data1"});
        mockData.add(new String[]{"Row2", "Data2"});
        when(mockLoadCSV.getCSVRows("test.csv")).thenReturn(mockData);
        assertEquals(2, mockLoadCSV.getCSVRows("test.csv").size());
    }
}