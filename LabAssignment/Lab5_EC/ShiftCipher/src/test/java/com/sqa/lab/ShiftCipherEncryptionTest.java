package com.sqa.lab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#5.1 – Equivalence Class Testing (Encryption)
 * Weak Robust Equivalence Class Testing for ShiftCipher Encryption
 */
public class ShiftCipherEncryptionTest {

    private ShiftCipher shiftCipher;

    @BeforeEach
    public void setUp() {
        shiftCipher = new ShiftCipher();
    }

    @ParameterizedTest(name = "{0}: plainText=\"{1}\", key={2} -> expected=\"{3}\"")
    @DisplayName("Valid Encryption Test Cases (TC001-TC006, TC012-TC013)")
    @CsvSource(value = {
        "TC001, A, 0, A",
        "TC002, HELLO, 5, MJQQT",
        "TC003, WORLD, -5, RJMGY",
        "TC004, TEST, 30, XIWX",
        "TC005, ATTACK, 17, RKKRTB",
        "TC006, XYZ, 3, ABC",
        "TC012, hello, 3, KHOOR",
        "TC013, ABC, 2147483647, XYZ"
    })
    public void testEncryptValid(String tcId, String plainText, int key, String expectedCipherText) {
        String actualCipherText = shiftCipher.encrypt(plainText, key);
        assertEquals(expectedCipherText, actualCipherText, tcId + " failed");
    }

    @ParameterizedTest(name = "{0}: plainText=\"{1}\", key={2} -> expect IllegalArgumentException")
    @DisplayName("Invalid Encryption Test Cases (TC007-TC011)")
    @CsvSource(value = {
        "TC007, NIL, 5",
        "TC008, '', 5",
        "TC009, HELLO1, 5",
        "TC010, 'HELLO WORLD', 5",
        "TC011, HELLO!, 5"
    }, nullValues = {"NIL"})
    public void testEncryptInvalid(String tcId, String plainText, int key) {
        assertThrows(IllegalArgumentException.class, () -> {
            shiftCipher.encrypt(plainText, key);
        }, tcId + " should throw IllegalArgumentException");
    }
}
