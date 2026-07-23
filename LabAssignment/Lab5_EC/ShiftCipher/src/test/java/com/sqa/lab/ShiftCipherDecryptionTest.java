package com.sqa.lab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#5.1 – Equivalence Class Testing (Decryption)
 * Weak Robust Equivalence Class Testing for ShiftCipher Decryption
 */
public class ShiftCipherDecryptionTest {

    private ShiftCipher shiftCipher;

    @BeforeEach
    public void setUp() {
        shiftCipher = new ShiftCipher();
    }

    @ParameterizedTest(name = "{0}: cipherText=\"{1}\", key={2} -> expectedPlainText=\"{3}\"")
    @DisplayName("Valid Decryption Test Cases (TC001-TC006, TC012-TC013)")
    @CsvSource(value = {
        "TC001, A, 0, A",
        "TC002, HELLO, 5, CZGGJ",
        "TC003, WORLD, -5, BTWQI",
        "TC004, TEST, 30, PAOP",
        "TC005, RKKRTB, 17, ATTACK",
        "TC006, ABC, 3, XYZ",
        "TC012, hello, 3, EBIIL",
        "TC013, ABC, 2147483647, DEF"
    })
    public void testDecryptValid(String tcId, String cipherText, int key, String expectedPlainText) {
        String actualPlainText = shiftCipher.decrypt(cipherText, key);
        assertEquals(expectedPlainText, actualPlainText, tcId + " failed");
    }

    @ParameterizedTest(name = "{0}: cipherText=\"{1}\", key={2} -> expect IllegalArgumentException")
    @DisplayName("Invalid Decryption Test Cases (TC007-TC011)")
    @CsvSource(value = {
        "TC007, NIL, 5",
        "TC008, '', 5",
        "TC009, HELLO1, 5",
        "TC010, 'HELLO WORLD', 5",
        "TC011, HELLO!, 5"
    }, nullValues = {"NIL"})
    public void testDecryptInvalid(String tcId, String cipherText, int key) {
        assertThrows(IllegalArgumentException.class, () -> {
            shiftCipher.decrypt(cipherText, key);
        }, tcId + " should throw IllegalArgumentException");
    }
}
