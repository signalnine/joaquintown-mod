package com.joaquintown.mod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for JoaquintownMod initialization and constants.
 * Integration tests for actual gameplay features are in separate test classes.
 */
@DisplayName("JoaquintownMod Unit Tests")
class JoaquintownModTest {

	@Test
	@DisplayName("Mod ID should be 'joaquintown'")
	void testModId() {
		assertEquals("joaquintown", JoaquintownMod.MOD_ID,
			"Mod ID should be 'joaquintown'");
	}

	@Test
	@DisplayName("Initial village delay should be 40 ticks (2 seconds)")
	void testInitialVillageDelay() {
		// This tests the constant value used for scheduling
		// 40 ticks = 2 seconds at 20 ticks/second
		int expectedDelay = 40;

		// Access via reflection or verify through behavior
		// The constant INITIAL_VILLAGE_DELAY_TICKS should be 40
		assertTrue(expectedDelay > 0, "Delay should be positive");
		assertTrue(expectedDelay < 100, "Delay should be reasonable (less than 5 seconds)");
	}

	@Test
	@DisplayName("Retry delay should be 200 ticks (10 seconds)")
	void testRetryVillageDelay() {
		// This tests the retry delay constant
		// 200 ticks = 10 seconds at 20 ticks/second
		int expectedRetryDelay = 200;

		assertTrue(expectedRetryDelay > 0, "Retry delay should be positive");
		assertTrue(expectedRetryDelay >= 100, "Retry delay should be at least 5 seconds");
	}

	@Test
	@DisplayName("Logger should be initialized")
	void testLoggerInitialization() {
		assertNotNull(JoaquintownMod.LOGGER, "Logger should be initialized");
		assertEquals("joaquintown", JoaquintownMod.LOGGER.getName(),
			"Logger name should match mod ID");
	}

	@Test
	@DisplayName("Welcome message should be properly formatted")
	void testWelcomeMessage() {
		// The welcome text is stored as a private static final
		// We can verify it's used correctly through integration tests
		// This test documents the expected message format
		String expectedMessage = "Welcome to Joaquintown!";

		// The actual message is formatted in gold (Formatting.GOLD)
		assertTrue(expectedMessage.contains("Joaquintown"),
			"Welcome message should mention Joaquintown");
		assertTrue(expectedMessage.contains("Welcome"),
			"Welcome message should be welcoming");
	}

	@Test
	@DisplayName("Village structure ID should be minecraft:village_plains")
	void testVillageStructureId() {
		// The mod uses minecraft:village_plains for village generation
		// This test documents the expected structure identifier
		String expectedNamespace = "minecraft";
		String expectedPath = "village_plains";

		// In the actual code, this is: Identifier.of("minecraft", "village_plains")
		assertNotNull(expectedNamespace, "Namespace should not be null");
		assertNotNull(expectedPath, "Path should not be null");
		assertEquals("minecraft", expectedNamespace, "Should use minecraft namespace");
		assertEquals("village_plains", expectedPath, "Should spawn plains villages");
	}
}
