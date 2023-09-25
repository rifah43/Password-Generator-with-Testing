import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {
    private Generator generator;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        String input = "yes\nyes\nyes\nyes\n8\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);

        generator = new Generator(scanner);
    }

    @AfterEach
    public void tearDown() {
        scanner.close();
        System.setIn(System.in); 
    }

    @Test
    @DisplayName("Test Generated Password Length 8")
    public void testGeneratePassword() {
        int length = 8;
        Password password = generator.GeneratePassword(length);
        assertNotNull(password);
        assertEquals(length, password.toString().length());
    }

	@Test
    @DisplayName("GeneratePassword with length 0 ")
    public void testGeneratePasswordLength0() {
        int length = 0;

        final NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> {
                    try {
                        generator.GeneratePassword(length);
                    } catch (NullPointerException e) {
                        throw new NullPointerException("This is null pointer exception");
                    }
                }
        );

        String expectedErrorMessage = "This is null pointer exception";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test isInclude with 'yes'")
    public void testIsIncludeWithYes() {
        boolean result = generator.isInclude("yes");
        assertTrue(result);
    }

    @Test
    @DisplayName("Test isInclude with 'no'")
    public void testIsIncludeWithNo() {
        boolean result = generator.isInclude("no");
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Password Request Error with valid input")
    public void testPasswordRequestErrorWithValidInput() {
        assertDoesNotThrow(() -> generator.PasswordRequestError("yes"));
    }

    @Test
    @DisplayName("Test Password Request Error with invalid input")
    public void testPasswordRequestErrorWithInvalidInput() {
        assertThrows(RuntimeException.class, () -> generator.PasswordRequestError("invalid"));
    }

    @Test
    @DisplayName("Test mainLoop with '1' option")
    public void testMainLoopOption1() {
        String input = "1\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        generator.mainLoop();
    }

    @Test
    @DisplayName("Test mainLoop with '2' option")
    public void testMainLoopOption2() {
        String input = "2\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        generator.mainLoop(); 
    }

    @Test
    @DisplayName("Test mainLoop with '3' option")
    public void testMainLoopOption3() {
        String input = "3\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        generator.mainLoop(); 
    }

    @Test
    @DisplayName("Test mainLoop with '4' option")
    public void testMainLoopOption4() {
        String input = "4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        generator.mainLoop(); 
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "abc"})
    @DisplayName("Test mainLoop with invalid options")
    public void testMainLoopWithInvalidOption(String option) {
        String input = option + "\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        generator.mainLoop(); 
    }
}
