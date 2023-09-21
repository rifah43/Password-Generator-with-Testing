import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class PasswordTest {
    private Password password;

    @BeforeEach
    public void setUp() {
        // Create a Password object with a sample password
        password = new Password("Abc123!");
    }

    @Test
    public void testCharTypeUpperCase() {
        // Test for an uppercase letter 'A'
        int result = password.CharType('A');
        assertEquals(1, result);
    }

    @Test
    public void testCharTypeLowerCase() {
        // Test for a lowercase letter 'b'
        int result = password.CharType('b');
        assertEquals(2, result);
    }

    @Test
    public void testCharTypeDigit() {
        // Test for a digit '5'
        int result = password.CharType('5');
        assertEquals(3, result);
    }

    @Test
    public void testCharTypeSymbol() {
        // Test for a symbol '!'
        int result = password.CharType('!');
        assertEquals(4, result);
    }

    @Test
    public void testPasswordStrengthWeak() {
        // Test for a weak password with only lowercase letters
        Password weakPassword = new Password("abcdef");
        int result = weakPassword.PasswordStrength();
        assertEquals(1, result);
    }

    @Test
    public void testPasswordStrengthMedium() {
        // Test for a medium-strength password with lowercase letters and digits
        Password mediumPassword = new Password("aB8s3");
        int result = mediumPassword.PasswordStrength();
        assertEquals(3, result);
    }

    @Test
    public void testPasswordStrengthGood() {
        // Test for a good password with lowercase letters, digits, and symbols
        Password goodPassword = new Password("Abc123!");
        int result = goodPassword.PasswordStrength();
        assertEquals(4, result);
    }

    @Test
    public void testPasswordStrengthVeryGood() {
        // Test for a very good password with a mix of characters
        Password veryGoodPassword = new Password("Abc123!XYZ3iev*p%2*jhg@87nbl457");
        int result = veryGoodPassword.PasswordStrength();
        assertEquals(6, result);
    }

    @Test
    public void testCalculateScoreVeryGood() {
        // Test for a very good password with a score of 6
        String result = password.calculateScore();
        assertEquals("This is a very good password :D check the Useful Information section to make sure it satisfies the guidelines", result);
    }

    @Test
    public void testCalculateScoreGood() {
        // Test for a good password with a score of 4
        Password goodPassword = new Password("Abc123");
        String result = goodPassword.calculateScore();
        assertEquals("This is a good password :) but you can still do better", result);
    }

    @Test
    public void testCalculateScoreMedium() {
        // Test for a medium-strength password with a score of 3
        Password mediumPassword = new Password("abc123");
        String result = mediumPassword.calculateScore();
        assertEquals("This is a medium password :/ try making it better", result);
    }

    @Test
    public void testCalculateScoreWeak() {
        // Test for a weak password with a score of 1
        Password weakPassword = new Password("abcdef");
        String result = weakPassword.calculateScore();
        assertEquals("This is a weak password :( definitely find a new one", result);
    }
}
