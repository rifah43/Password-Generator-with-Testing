import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class PasswordTest {
    private Password password;

    @Before
    public void setUpBefore() {
        System.out.println("Before all tests");
    }

    @BeforeEach
    public void setUp() {
        password = new Password("Abc123!");
    }

    @AfterEach
    public void tearDown() {
        password = null;
    }

    @After
    public void tearDownAfter() {
        System.out.println("After all tests");
    }

    // Character type
    @Test
    @Displayname("Uppercase")
    public void testCharTypeUpperCase() {
        int result = password.CharType('A');
        assertEquals(1, result);
    }

    @Test
    @Displayname("Lowercase")
    public void testCharTypeLowerCase() {
        int result = password.CharType('b');
        assertEquals(2, result);
    }

    @Test
    @Displayname("Digit")
    public void testCharTypeDigit() {
        int result = password.CharType('5');
        assertEquals(3, result);
    }

    @Test
    @Displayname("Symbol")
    public void testCharTypeSymbol() {
        int result = password.CharType('!');
        assertEquals(4, result);
    }

    // Strength Calculation
    @Test
    @Displayname("Weak Password")
    public void testPasswordStrengthWeak() {
        Password weakPassword = new Password("abcdef");
        int result = weakPassword.PasswordStrength();
        assertEquals(1, result);
    }

    @Test
    @Displayname("Medium Password")
    public void testPasswordStrengthMedium() {
        Password mediumPassword = new Password("aB8s3");
        int result = mediumPassword.PasswordStrength();
        assertEquals(3, result);
    }

    @Test
    @Displayname("Good Password")
    public void testPasswordStrengthGood() {
        Password goodPassword = new Password("Abc123!");
        int result = goodPassword.PasswordStrength();
        assertEquals(4, result);
    }

    @Test
    @Displayname("Very Good Password")
    public void testPasswordStrengthVeryGood() {
        Password veryGoodPassword = new Password("Abc123!XYZ3iev*p%2*jhg@87nbl457");
        int result = veryGoodPassword.PasswordStrength();
        assertEquals(6, result);
    }

    // Strength Display
    @Test
    @Displayname("Very Good Password Response")
    public void testCalculateScoreVeryGood() {
        String result = password.calculateScore();
        assertEquals("This is a very good password :D", result);
    }

    @Test
    @Displayname("Good Password Response")
    public void testCalculateScoreGood() {
        Password goodPassword = new Password("Abc123");
        String result = goodPassword.calculateScore();
        assertEquals("This is a good password :)", result);
    }

    @Test
    @Displayname("Medium Password Response")
    public void testCalculateScoreMedium() {
        Password mediumPassword = new Password("abc123");
        String result = mediumPassword.calculateScore();
        assertEquals("This is a medium password :/", result);
    }

    @Test
    @Displayname("Weak Password Response")
    public void testCalculateScoreWeak() {
        Password weakPassword = new Password("abcdef");
        String result = weakPassword.calculateScore();
        assertEquals("This is a weak password :(", result);
    }
}
