import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {

    @Test
    @DisplayName("Test Generated Password Length 8")
    public void testGeneratePassword() {
        Generator generator = new Generator(new Scanner(System.in));
        Password password = generator.GeneratePassword(8);
        assertNotNull(password);
        assertEquals(8, password.toString().length());
    }

    @Test
    @DisplayName("GeneratePassword with length 0")
    public void testGeneratePasswordLength0() {
        Generator generator = new Generator(new Scanner(System.in));
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
        Generator generator = new Generator(new Scanner(System.in));
        boolean result = generator.isInclude("yes");
        assertTrue(result);
    }

    @Test
    @DisplayName("Test isInclude with 'no'")
    public void testIsIncludeWithNo() {
        Generator generator = new Generator(new Scanner(System.in));
        boolean result = generator.isInclude("no");
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Password Request Error with valid input")
    public void testPasswordRequestErrorWithValidInput() {
        Generator generator = new Generator(new Scanner(System.in));
        assertDoesNotThrow(() -> generator.PasswordRequestError("yes"));
    }

    @Test
    @DisplayName("Test Password Request Error with invalid input")
    public void testPasswordRequestErrorWithInvalidInput() {
        Generator generator = new Generator(new Scanner(System.in));
        assertThrows(RuntimeException.class, () -> generator.PasswordRequestError("invalid"));
    }

    @Test
    @DisplayName("Test mainLoop with '2' option")
    public void testMainLoopOption2() {
        String input = "\n2\n4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();
    }

    @Test
    @DisplayName("Test mainLoop with '3' option")
    public void testMainLoopOption3() {
        String input = "3\n4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();
    }

    @Test
    @DisplayName("Test mainLoop with '4' option")
    public void testMainLoopOption4() {
        String input = "4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "abc"})
    @DisplayName("Test mainLoop with invalid options")
    public void testMainLoopWithInvalidOption(String option) {
        String input = option + "\n4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();
    }

    @Test
    @DisplayName("Test mainLoop with '1' option")
    public void testMainLoopOption1() {
        String input = "1\nyes\nno\nyes\nyes\n8\n4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();
    }

    @Test
    @DisplayName("Test requestPassword method")
    public void testRequestPassword() {
        String input = "yes\nno\nyes\nyes\n8\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.requestPassword();
    }

    private void provideInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}
