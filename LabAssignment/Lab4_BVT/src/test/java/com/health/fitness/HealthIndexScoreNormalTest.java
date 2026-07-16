package com.health.fitness;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HealthIndexScoreNormalTest {

    @Test
    public void testTC001() {
        HealthIndexScore score = new HealthIndexScore(0.0, 70, 15);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC002() {
        HealthIndexScore score = new HealthIndexScore(1.0, 70, 15);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC003() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 15);
        assertEquals(8, score.getTotalScore());
    }

    @Test
    public void testTC004() {
        HealthIndexScore score = new HealthIndexScore(99.0, 70, 15);

        assertEquals(11, score.getTotalScore());
    }

    @Test
    public void testTC005() {
        HealthIndexScore score = new HealthIndexScore(100.0, 70, 15);
        assertEquals(11, score.getTotalScore());
    }

    @Test
    public void testTC006() {
        HealthIndexScore score = new HealthIndexScore(35.0, 40, 15);
        assertEquals(10, score.getTotalScore());
    }

    @Test
    public void testTC007() {
        HealthIndexScore score = new HealthIndexScore(35.0, 41, 15);
        assertEquals(10, score.getTotalScore());
    }

    @Test
    public void testTC008() {
        HealthIndexScore score = new HealthIndexScore(35.0, 219, 15);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC009() {
        HealthIndexScore score = new HealthIndexScore(35.0, 220, 15);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC010() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 0);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC011() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 1);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC012() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 29);
        assertEquals(10, score.getTotalScore());
    }

    @Test
    public void testTC013() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 30);
        assertEquals(10, score.getTotalScore());
    }
}
