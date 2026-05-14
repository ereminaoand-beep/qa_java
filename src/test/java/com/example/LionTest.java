package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Predator mockPredator;

    @RunWith(Parameterized.class)
    public static class LionValidSexTest {

        @Parameters(name = "Пол: {0}, Грива: {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    {"Самец", true},
                    {"Самка", false}
            });
        }

        @Parameter(0)
        public String sex;

        @Parameter(1)
        public boolean expectedHasMane;

        @Mock
        private Predator mockPredator;

        @Test
        public void lionConstructorShouldSetCorrectMane() throws Exception {
            Lion lion = new Lion(sex, mockPredator);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }

    @RunWith(Parameterized.class)
    public static class LionInvalidSexTest {

        @Parameters(name = "Неправильный пол: \"{0}\"")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    {"самец"},
                    {"самка"},
                    {"male"},
                    {"female"},
                    {"Самец "},
                    {" Самка"},
                    {"САМЕЦ"},
                    {"САМКА"},
                    {""},
                    {" "},
                    {"Лев"},
                    {"Кот"},
                    {"Тигр"},
                    {null}
            });
        }

        @Parameter(0)
        public String invalidSex;

        @Mock
        private Predator mockPredator;

        @Test(expected = Exception.class)
        public void lionConstructorShouldThrowExceptionForInvalidSex() throws Exception {
            new Lion(invalidSex, mockPredator);
        }
    }

    @Test
    public void getFoodShouldReturnPredatorFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockPredator.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", mockPredator);
        assertEquals(expectedFood, lion.getFood());

        verify(mockPredator, times(1)).eatMeat();
    }

    @Test(expected = Exception.class)
    public void getFoodShouldPropagateException() throws Exception {
        when(mockPredator.eatMeat()).thenThrow(new Exception("Ошибка при получении еды"));

        Lion lion = new Lion("Самец", mockPredator);
        lion.getFood();
    }

    @Test
    public void doesHaveManeShouldReturnCorrectValue() throws Exception {
        Lion maleLion = new Lion("Самец", mockPredator);
        assertTrue(maleLion.doesHaveMane());

        Lion femaleLion = new Lion("Самка", mockPredator);
        assertFalse(femaleLion.doesHaveMane());
    }
}