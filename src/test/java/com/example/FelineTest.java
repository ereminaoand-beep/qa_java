package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineTest {

    private final Feline feline = new Feline();

    @Test
    public void eatMeatShouldReturnPredatorFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.eatMeat());
    }

    @Test
    public void getFamilyShouldReturnCorrectFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void getKittensWithoutParamsShouldReturnOne() {
        assertEquals(1, feline.getKittens());
    }

    @Parameters
    public static Collection<Object[]> getKittensData() {
        return Arrays.asList(new Object[][] {
                {0}, {1}, {5}, {10}, {100}
        });
    }

    @Parameter
    public int kittensCount;

    @Test
    public void getKittensWithParamsShouldReturnSpecifiedCount() {
        assertEquals(kittensCount, feline.getKittens(kittensCount));
    }
}