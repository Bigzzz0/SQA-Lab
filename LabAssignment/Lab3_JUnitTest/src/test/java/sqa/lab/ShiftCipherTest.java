package sqa.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ShiftCipherTest {

    private final ShiftCipher cipher = new ShiftCipher();

    // TC01: message = "SOFTWARE", key = 3, expected = "ZVMADHYL" 
    @Test
    public void testTC01_software_uppercase() {
        assertEquals("ZVMADHYL", cipher.shift("SOFTWARE", 3));
    }

    // TC02: message = "software", key = 3, expected = "zvmadhyl" 
    @Test
    public void testTC02_software_lowercase() {
        assertEquals("zvmadhyl", cipher.shift("software", 3));
    }

    // TC03: message = "HELLO", key = 3, expected = "KHOOR"
    @Test
    public void testTC03_hello_uppercase() {
        assertEquals("KHOOR", cipher.shift("HELLO", 3));
    }

    // TC04: message = "HELLO123WORLD", key = 3, expected = "KHOOR123ZRUOG"
    @Test
    public void testTC04_mixed_alphanumeric() {
        assertEquals("KHOOR123ZRUOG", cipher.shift("HELLO123WORLD", 3));
    }
}
