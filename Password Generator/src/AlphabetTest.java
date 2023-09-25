import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlphabetTest {
    private Alphabet alphabet;

    @BeforeEach
    public void setUp() {
        alphabet = new Alphabet(true, true, true, true);
    }

    @Test
    @DisplayName("Test getAlphabet with all options")
    public void testGetAlphabetAllOptionsEnabled() {
        String expectedAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-_=+\\/~?";
        String actualAlphabet = alphabet.getAlphabet();
        assertEquals(expectedAlphabet, actualAlphabet);
    }

    @Test
    @DisplayName("Test getAlphabet with only uppercase letters")
    public void testGetAlphabetUppercaseEnabled() {
        alphabet = new Alphabet(true, false, false, false);
        String expectedAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String actualAlphabet = alphabet.getAlphabet();
        assertEquals(expectedAlphabet, actualAlphabet);
    }

    @Test
    @DisplayName("Test getAlphabet with only lowercase letters")
    public void testGetAlphabetLowercaseEnabled() {
        alphabet = new Alphabet(false, true, false, false);
        String expectedAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String actualAlphabet = alphabet.getAlphabet();
        assertEquals(expectedAlphabet, actualAlphabet);
    }

    @Test
    @DisplayName("Test getAlphabet with only numbers")
    public void testGetAlphabetNumbersEnabled() {
        alphabet = new Alphabet(false, false, true, false);
        String expectedAlphabet = "1234567890";
        String actualAlphabet = alphabet.getAlphabet();
        assertEquals(expectedAlphabet, actualAlphabet);
    }

    @Test
    @DisplayName("Test getAlphabet with only special characters")
    public void testGetAlphabetSpecialCharactersEnabled() {
        alphabet = new Alphabet(false, false, false, true);
        String expectedAlphabet = "!@#$%^&*()-_=+\\/~?";
        String actualAlphabet = alphabet.getAlphabet();
        assertEquals(expectedAlphabet, actualAlphabet);
    }

    @Test
    @DisplayName("Test getAlphabet without any")
    public void testGetAlphabetAllOptionsDisabled() {
        alphabet = new Alphabet(false, false, false, false);
        String expectedAlphabet = "";
        String actualAlphabet = alphabet.getAlphabet();
        assertEquals(expectedAlphabet, actualAlphabet);
    }
}
