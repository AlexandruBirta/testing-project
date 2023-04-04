package ro.unibuc.fmi.testingproject;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ZumaNonequivalentMutantAliveTest {

    public static final int VALID_OUTPUT = 2;
    final ZumaNonequivalentMutantAlive zumaNonequivalentMutantAlive = new ZumaNonequivalentMutantAlive();


    @Test
    @DisplayName("Equivalence partitioning and cause-effect graphing")
    void givenValidBoardAndHandWhenFindMinimumOfStepsIsCalledThenReturnTheMinimumOfStepsToClearBoard() {

        String board = "WWRRBBWW";
        String hand = "WRBRW";

        int actualOutput = zumaNonequivalentMutantAlive.findMinimumOfSteps(board, hand);

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