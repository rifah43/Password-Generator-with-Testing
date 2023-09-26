import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {
    private Generator generator;
    private InputStream originalSystemIn;
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        generator = new Generator(new Scanner(System.in));
        originalSystemIn = System.in;
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    private void provideInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }

    private String getOutput() {
        return outputStream.toString();
    }

    @Test
    @DisplayName("Test Generated Password Length 8")
    public void testGeneratePassword() {
        Password password = generator.GeneratePassword(8);
        assertNotNull(password);
        assertEquals(8, password.toString().length());
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
    @DisplayName("Test mainLoop with '2' option")
    public void testMainLoopOption2() {
        String input = "\n2\n4\n";
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

    @Test
    @DisplayName("Test mainLoop with '1' option")
    public void testMainLoopOption1() {
        provideInput("1\n4\n");

        generator.mainLoop();

        String expectedOutput = "Welcome to Ziz Password Services :)\n"
                + "1. Request Password\n"
                + "2. Check Password\n"
                + "3. Print Useful Info\n"
                + "4. Quit\n"
                + "\nDo you want Lowercase letters \"abcd...\" to be used? "
                + "Do you want Uppercase letters \"ABCD...\" to be used? "
                + "Do you want Numbers \"1234...\" to be used? "
                + "Do you want Symbols \"!@#$...\" to be used? "
                + "Great! Now enter the length of the password\n";

        assertEquals(expectedOutput, getOutput());
    }

    @Test
    @DisplayName("Test requestPassword method")
    public void testRequestPassword() {
        provideInput("yes\nno\nyes\nyes\n8\n");

        generator.requestPassword();

        System.setIn(originalSystemIn);

        String expectedOutput = "Hello, welcome to the Password Generator :) answer"
                + " the following questions by Yes or No \n"
                + "Do you want Lowercase letters \"abcd...\" to be used? "
                + "Do you want Uppercase letters \"ABCD...\" to be used? "
                + "Do you want Numbers \"1234...\" to be used? "
                + "Do you want Symbols \"!@#$...\" to be used? "
                + "Great! Now enter the length of the password\n";

        assertEquals(expectedOutput, getOutput());
    }

}
