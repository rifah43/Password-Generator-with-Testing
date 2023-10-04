import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {

    @Test
    @DisplayName("Test Generated Password Length 8")
    public void testGeneratePassword() {
        Generator generator = new Generator(true, true, true, true);
        Password password = generator.GeneratePassword(8);
        assertNotNull(password);
        assertEquals(8, password.toString().length());
    }


    @Test
    @DisplayName("GeneratePassword with length 0")
    public void testGeneratePasswordLength0() {
        Generator generator = new Generator(true, true, true, true);
        int length = 0;
        Password password = generator.GeneratePassword(0);
        assertNotNull(password);
        assertEquals(0, password.toString().length());
        String expectedErrorMessage = "This is null pointer exception";
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

        final RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> {
                    generator.PasswordRequestError("invalid");
                    throw new RuntimeException("Invalid input");
                }
        );

        String expectedErrorMessage = "Invalid input";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }


    @Test
    @DisplayName("Test mainLoop with '2' option")
    public void testMainLoopOption2() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "2\na88GkKJH@#\n4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();

        String expectedOutput = "Enter your password:";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    @DisplayName("Test mainLoop with '3' option")
    public void testMainLoopOption3() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "3\n4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();

        String expectedOutput = "Use a minimum password length of 8 or more characters if permitted";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }


    @Test
    @DisplayName("Test mainLoop with '4' option")
    public void testMainLoopOption4() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();

        String expectedOutput = "Closing the program bye bye!";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "abc"})
    @DisplayName("Test mainLoop with invalid options")
    public void testMainLoopWithInvalidOption(String option) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = option+"\n4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();

        String expectedOutput = "Kindly select one of the available commands";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    @DisplayName("Test mainLoop with '1' option")
    public void testMainLoopOption1() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String input = "1\nyes\nno\nyes\nyes\n8\n4\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.mainLoop();
        String expectedOutput = "Hello, welcome to the Password Generator :)";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    @DisplayName("Test requestPassword normal")
    public void testRequestPasswordNormal() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String input = "yes\nyes\nyes\nyes\n8\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.requestPassword();
        String expectedOutput = "Do you want Lowercase letters \"abcd...\" to be used?";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    @DisplayName("Test requestPassword with wrong input")
    public void testRequestPasswordWrong() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String input = "hi\nyes\nhi\nyes\nhi\nyes\nhi\nyes\n8\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.requestPassword();
        String expectedOutput = "Do you want Lowercase letters \"abcd...\" to be used?";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    @DisplayName("Test requestPassword with wrong input and all no")
    public void testRequestPasswordNo() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String input = "hi\nno\nhi\nno\nhi\nno\nhi\nno\nyes\nno\nno\nno\n8\n";
        provideInput(input);
        Generator generator = new Generator(new Scanner(System.in));
        generator.requestPassword();

        String expectedOutput = "You have selected no characters to generate your";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    private void provideInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}
