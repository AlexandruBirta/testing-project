package ro.unibuc.fmi.testingproject;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ZumaNonequivalentMutantDeadTest {

    public static final int VALID_OUTPUT = 2;
    final ZumaNonequivalentMutantDead zumaNonequivalentMutantDead = new ZumaNonequivalentMutantDead();


//    @Disabled("Test should fail because mutant should be eliminated.")
    @Test
    @DisplayName("Equivalence partitioning and cause-effect graphing")
    void givenValidBoardAndHandWhenFindMinimumOfStepsIsCalledThenReturnTheMinimumOfStepsToClearBoard() {

        String board = "WWRRBBWW";
        String hand = "WRBRW";

        int actualOutput = zumaNonequivalentMutantDead.findMinimumOfSteps(board, hand);

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