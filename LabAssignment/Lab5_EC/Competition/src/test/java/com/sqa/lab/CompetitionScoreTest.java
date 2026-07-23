package com.sqa.lab;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#5.2 – Equivalence Class Testing
 * Strong Robust Equivalence Class Testing for CompetitionScore
 */
public class CompetitionScoreTest {

    private CompetitionScore competitionScore;

    @BeforeEach
    public void setUp() {
        competitionScore = new CompetitionScore();
    }

    // --- Valid Test Cases (TC001 - TC005) ---

    @ParameterizedTest(name = "{0}: scores=({1},{2},{3}) -> expected={4}")
    @DisplayName("Valid Scores Test for 3-parameter findMaxScore (TC001-TC005)")
    @MethodSource("validScoresProvider")
    public void testFindMaxScoreThreeParamsValid(String tcId, int score1, int score2, int score3, int expectedMax, int[] scores) {
        int actualMax = competitionScore.findMaxScore(score1, score2, score3);
        assertEquals(expectedMax, actualMax, tcId + " failed for 3-param method");
    }

    @ParameterizedTest(name = "{0}: scores={5} -> expected={4}")
    @DisplayName("Valid Scores Test for array findMaxScore (TC001-TC005)")
    @MethodSource("validScoresProvider")
    public void testFindMaxScoreArrayValid(String tcId, int score1, int score2, int score3, int expectedMax, int[] scores) {
        int actualMax = competitionScore.findMaxScore(scores);
        assertEquals(expectedMax, actualMax, tcId + " failed for array method");
    }

    static Stream<Arguments> validScoresProvider() {
        return Stream.of(
            Arguments.of("TC001", 0, 0, 0, 0, new int[]{0, 0, 0}),
            Arguments.of("TC002", 500, 500, 500, 500, new int[]{500, 500, 500}),
            Arguments.of("TC003", 100, 250, 50, 250, new int[]{100, 250, 50}),
            Arguments.of("TC004", 0, 500, 250, 500, new int[]{0, 500, 250}),
            Arguments.of("TC005", 500, 0, 250, 500, new int[]{500, 0, 250})
        );
    }

    // --- Invalid Score Values Test Cases (TC006 - TC011) ---

    @ParameterizedTest(name = "{0}: scores=({1},{2},{3}) -> expect IllegalArgumentException")
    @DisplayName("Invalid Score Range Test for 3-parameter findMaxScore (TC006-TC011)")
    @MethodSource("invalidScoresProvider")
    public void testFindMaxScoreThreeParamsInvalid(String tcId, int score1, int score2, int score3, int[] scores) {
        assertThrows(IllegalArgumentException.class, () -> {
            competitionScore.findMaxScore(score1, score2, score3);
        }, tcId + " 3-param method should throw IllegalArgumentException");
    }

    @ParameterizedTest(name = "{0}: scores={4} -> expect IllegalArgumentException")
    @DisplayName("Invalid Score Range Test for array findMaxScore (TC006-TC011)")
    @MethodSource("invalidScoresProvider")
    public void testFindMaxScoreArrayInvalid(String tcId, int score1, int score2, int score3, int[] scores) {
        assertThrows(IllegalArgumentException.class, () -> {
            competitionScore.findMaxScore(scores);
        }, tcId + " array method should throw IllegalArgumentException");
    }

    static Stream<Arguments> invalidScoresProvider() {
        return Stream.of(
            Arguments.of("TC006", -1, 100, 200, new int[]{-1, 100, 200}),
            Arguments.of("TC007", 100, -1, 200, new int[]{100, -1, 200}),
            Arguments.of("TC008", 100, 200, -1, new int[]{100, 200, -1}),
            Arguments.of("TC009", 501, 100, 200, new int[]{501, 100, 200}),
            Arguments.of("TC010", 100, 501, 200, new int[]{100, 501, 200}),
            Arguments.of("TC011", -1, 501, 200, new int[]{-1, 501, 200})
        );
    }

    // --- Invalid Array Structural Test Cases (TC012 - TC014) ---

    @ParameterizedTest(name = "{0}: expect IllegalArgumentException for array structure")
    @DisplayName("Invalid Array Structure Test for array findMaxScore (TC012-TC014)")
    @MethodSource("invalidArrayStructureProvider")
    public void testFindMaxScoreInvalidArrayStructure(String tcId, int[] scores) {
        assertThrows(IllegalArgumentException.class, () -> {
            competitionScore.findMaxScore(scores);
        }, tcId + " should throw IllegalArgumentException");
    }

    static Stream<Arguments> invalidArrayStructureProvider() {
        return Stream.of(
            Arguments.of("TC012", (Object) null),
            Arguments.of("TC013", new int[]{100, 200}),
            Arguments.of("TC014", new int[]{100, 200, 300, 400})
        );
    }
}
