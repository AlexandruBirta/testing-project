package ro.unibuc.fmi.testingproject;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


class ZumaGameTest {

    /*
    Domain for board and hand:
    *************************
    B1 = {b in {R,Y,B,G,W} | |b| >= 1 and |b| <= 16}
    H1 = {h in {R,Y,B,G,W} | |h| >= 1 and |h| <= 5}

    Boundaries for board and hand:
    *************************
    B2 = {b in {R,Y,B,G,W} | |b| < 1}
    B3 = {b in {R,Y,B,G,W} | |b| > 16}
    B4 = {b not in {R,Y,B,G,W} | |b| >= 1 and |b| <= 16}

    H2 = {h in {R,Y,B,G,W} | |h| < 1}
    H3 = {h in {R,Y,B,G,W} | |h| > 5}
    H4 = {h not in {R,Y,B,G,W} | |h| >= 1 and |h| <= 5}

    Outputs:
    ********
    O1 = {0}
    O2 = {-1}
    O3 = {n | n > 1}

    Equivalence classes:
    ********************
    C112 = { (b,h) | b in B1, h in H1 and output O2}
    C113 = { (b,h) | b in B1, h in H1 and output O3}

    C211 = { (b,h) | b in B2, h in H1 and output O1}
    C121 = { (b,h) | b in B1, h in H2 and output O1}
    C221 = { (b,h) | b in B2, h in H2 and output O1}

    C311 = { (b,h) | b in B3, h in H1 and output O1}
    C131 = { (b,h) | b in B1, h in H3 and output O1}
    C331 = { (b,h) | b in B3, h in H3 and output O1}

    C411 = { (b,h) | b in B4, h in H1 and output O1}
    C141 = { (b,h) | b in B1, h in H4 and output O1}
    C441 = { (b,h) | b in B4, h in H4 and output O1}


    */

    public static final int INVALID_INPUTS_OUTCOME = 0;
    public static final int VALID_BOARD_CANNOT_BE_CLEARED = -1;
    public static final int VALID_OUTPUT = 2;
    final ZumaGame zumaGame = new ZumaGame();


    @ParameterizedTest()
    @CsvSource({
            "'',YBW",
            "WWWBBB,''",
            "'',''",
            "RRWWRRBBRRRRWWRRBBRRRRWWRRBBRRRRWWRRBBRR,RWB",
            "RWB,RRWWRRBBRRRRWWRRBBRRRRWWRRBBRRRRWWRRBBRR",
            "RRWWRRBBRRRRWWRRBBRRRRWWRRBBRRRRWWRRBBRR,RRRBBB",
            "123123,YBW",
            "WWWBBB,12",
            "123123,12"
    })
    @DisplayName("Boundary value analysis and cause-effect graphing")
    void givenInvalidBoardAndHandWhenFindMinimumOfStepsIsCalledThenReturnOutputForInvalidInputs(String board, String hand) {

        int actualOutput = zumaGame.findMinimumOfSteps(board, hand);

        assertThat(actualOutput)
                .withFailMessage("""
                                Attempted to start game with invalid inputs with board '%s' and hand '%s'!
                                Actual output is: %d.
                                Expected output is: %d.""",
                        board,
                        hand,
                        actualOutput,
                        INVALID_INPUTS_OUTCOME
                )
                .isEqualTo(INVALID_INPUTS_OUTCOME);

    }

    @Test
    @DisplayName("Equivalence partitioning and cause-effect graphing")
    void givenValidBoardAndHandWhenFindMinimumOfStepsIsCalledThenReturnBoardCannotBeClearedOutput() {

        String board = "WWBBWBBWW";
        String hand = "BB";

        int actualOutput = zumaGame.findMinimumOfSteps(board, hand);

        assertThat(actualOutput)
                .withFailMessage("""
                                Attempted to clear valid board with insufficient balls for a board clear. Board is '%s' and hand is '%s'!
                                Actual output is: %d.
                                Expected output is: %d.""",
                        board,
                        hand,
                        actualOutput,
                        VALID_BOARD_CANNOT_BE_CLEARED
                )
                .isEqualTo(VALID_BOARD_CANNOT_BE_CLEARED);

    }

    @Test
    @DisplayName("Equivalence partitioning and cause-effect graphing")
    void givenValidBoardAndHandWhenFindMinimumOfStepsIsCalledThenReturnTheMinimumOfStepsToClearBoard() {

        String board = "WWRRBBWW";
        String hand = "WRBRW";

        int actualOutput = zumaGame.findMinimumOfSteps(board, hand);

        assertThat(actualOutput)
                .withFailMessage("""
                                Attempted to clear board with inputs for board '%s' and hand '%s'!
                                Actual output is: %d.
                                Expected output is: %d.""",
                        board,
                        hand,
                        actualOutput,
                        VALID_OUTPUT
                )
                .isEqualTo(VALID_OUTPUT);

    }

}