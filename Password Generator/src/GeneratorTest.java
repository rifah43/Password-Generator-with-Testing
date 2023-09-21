import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;

public class GeneratorTest {
	private Generator generator;

	@BeforeEach
	public void setUp() {
		// Set up a Scanner with a custom InputStream for testing
		String input = "yes\nno\nno\nyes\n8\n"; // Sample user input
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		Scanner scanner = new Scanner(inputStream);

		generator = new Generator(scanner);
	}

	@Test
	public void testGeneratePassword() {
		Password password = generator.GeneratePassword(12);
		assertNotNull(password);
		assertEquals(12, password.toString().length());
	}

	@Test
	public void testIsInclude() {
		assertTrue(generator.isInclude("yes"));
		assertFalse(generator.isInclude("no"));
		assertFalse(generator.isInclude("invalid"));
	}

	@Test
	public void testPasswordRequestError() {
		// Create a custom InputStream to simulate user input
		String input = "invalid\nno\n"; // Invalid input followed by valid input
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		Scanner scanner = new Scanner(inputStream);
		generator = new Generator(scanner);

		// Redirect standard output to capture printed messages
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		generator.PasswordRequestError("invalid");
		generator.PasswordRequestError("no");

		String consoleOutput = outputStream.toString();
		assertTrue(consoleOutput.contains("You have entered something incorrect"));
	}

	@Test
	public void testCheckPassword() {
		// Create a custom InputStream to simulate user input
		String input = "Password123\n"; // Sample password input
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		Scanner scanner = new Scanner(inputStream);
		generator = new Generator(scanner);

		// Redirect standard output to capture printed messages
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		generator.checkPassword();

		String consoleOutput = outputStream.toString();
		assertTrue(consoleOutput!=null);
	}

	@Test
	public void testPrintMenu() {
		// Redirect standard output to capture printed messages
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		generator.printMenu();

		String consoleOutput = outputStream.toString();
		assertTrue(consoleOutput.contains("Enter 1 - Password Generator"));
		assertTrue(consoleOutput.contains("Enter 2 - Password Strength Check"));
		assertTrue(consoleOutput.contains("Enter 3 - Useful Information"));
		assertTrue(consoleOutput.contains("Enter 4 - Quit"));
		assertTrue(consoleOutput.contains("Choice:"));
	}

	@Test
	public void testPrintQuitMessage() {
		// Redirect standard output to capture printed messages
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		generator.printQuitMessage();

		String consoleOutput = outputStream.toString();
		assertTrue(consoleOutput.contains("Closing the program bye bye!"));
	}
}
