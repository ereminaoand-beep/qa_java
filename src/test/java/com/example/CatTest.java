package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline feline;

    @Test
    public void getSoundShouldReturnMeow() {
        Cat cat = new Cat(feline);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void getFoodShouldReturnPredatorFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(feline);
        assertEquals(expectedFood, cat.getFood());

        verify(feline, times(1)).eatMeat();
    }

    @Test(expected = Exception.class)
    public void getFoodShouldPropagateException() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Test exception"));

        Cat cat = new Cat(feline);
        cat.getFood();

        verify(feline, times(1)).eatMeat();
    }
}