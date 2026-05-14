package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Predator mockPredator;

    @Test
    public void testMaleLion() throws Exception {
        Lion lion = new Lion("Самец", mockPredator);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void testFemaleLion() throws Exception {
        Lion lion = new Lion("Самка", mockPredator);
        assertFalse(lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testInvalidSexThrowsException() throws Exception {
        Lion lion = new Lion("Неправильный_пол", mockPredator);
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockPredator.eatMeat()).thenReturn(expectedFood);
        Lion lion = new Lion("Самец", mockPredator);
        assertEquals(expectedFood, lion.getFood());
        verify(mockPredator, times(1)).eatMeat();
    }

    @Test(expected = Exception.class)
    public void testGetFoodException() throws Exception {
        when(mockPredator.eatMeat()).thenThrow(new Exception("Error"));
        Lion lion = new Lion("Самец", mockPredator);
        lion.getFood();
    }

    @Test
    public void testDoesHaveMane() throws Exception {
        Lion maleLion = new Lion("Самец", mockPredator);
        assertTrue(maleLion.doesHaveMane());
        Lion femaleLion = new Lion("Самка", mockPredator);
        assertFalse(femaleLion.doesHaveMane());
    }
}