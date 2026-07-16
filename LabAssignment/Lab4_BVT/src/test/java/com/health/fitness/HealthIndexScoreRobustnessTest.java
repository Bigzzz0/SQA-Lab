package com.health.fitness;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HealthIndexScoreRobustnessTest {

    @Test
    public void testTC001_Vo2MaxBelowMin() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HealthIndexScore(-1.0, 70, 15);
        });
    }

    @Test
    public void testTC002_Vo2MaxMin() {
        HealthIndexScore score = new HealthIndexScore(0.0, 70, 15);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC003_Vo2MaxMinPlus() {
        HealthIndexScore score = new HealthIndexScore(1.0, 70, 15);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC004_Nominal() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 15);
        assertEquals(8, score.getTotalScore());
    }

    @Test
    public void testTC005_Vo2MaxMaxMinus() {
        HealthIndexScore score = new HealthIndexScore(99.0, 70, 15);
        assertEquals(11, score.getTotalScore());
    }

    @Test
    public void testTC006_Vo2MaxMax() {
        HealthIndexScore score = new HealthIndexScore(100.0, 70, 15);
        assertEquals(11, score.getTotalScore());
    }

    @Test
    public void testTC007_Vo2MaxAboveMax() {
        // Robustness BVT: Expect IllegalArgumentException for VO2 Max > 100.0 (e.g. 101.0)
        assertThrows(IllegalArgumentException.class, () -> {
            new HealthIndexScore(101.0, 70, 15);
        });
    }

    @Test
    public void testTC008_RhrBelowMin() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HealthIndexScore(35.0, 39, 15);
        });
    }

    @Test
    public void testTC009_RhrMin() {
        HealthIndexScore score = new HealthIndexScore(35.0, 40, 15);
        assertEquals(10, score.getTotalScore());
    }

    @Test
    public void testTC010_RhrMinPlus() {
        HealthIndexScore score = new HealthIndexScore(35.0, 41, 15);
        assertEquals(10, score.getTotalScore());
    }

    @Test
    public void testTC011_RhrMaxMinus() {
        HealthIndexScore score = new HealthIndexScore(35.0, 219, 15);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC012_RhrMax() {
        HealthIndexScore score = new HealthIndexScore(35.0, 220, 15);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC013_RhrAboveMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HealthIndexScore(35.0, 221, 15);
        });
    }

    @Test
    public void testTC014_HrrBelowMin() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HealthIndexScore(35.0, 70, -1);
        });
    }

    @Test
    public void testTC015_HrrMin() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 0);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC016_HrrMinPlus() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 1);
        assertEquals(6, score.getTotalScore());
    }

    @Test
    public void testTC017_HrrMaxMinus() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 29);
        assertEquals(10, score.getTotalScore());
    }

    @Test
    public void testTC018_HrrMax() {
        HealthIndexScore score = new HealthIndexScore(35.0, 70, 30);
        assertEquals(10, score.getTotalScore());
    }

    @Test
    public void testTC019_HrrAboveMax() {
        // Robustness BVT: Expect IllegalArgumentException for HRR > 30 (e.g. 31)
        assertThrows(IllegalArgumentException.class, () -> {
            new HealthIndexScore(35.0, 70, 31);
        });
    }
}
