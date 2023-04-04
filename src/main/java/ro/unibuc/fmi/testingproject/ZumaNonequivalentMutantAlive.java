package ro.unibuc.fmi.testingproject;


import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class ZumaNonequivalentMutantAlive {

    public static final String VALID_BALL_COLORS_REGEX = "[RYBGW]+";
    private static final String END_FLAG = "$";

    public int findMinimumOfSteps(String board, String hand) {

        if (inputsAreInvalid(board, hand)) {
            log.error("Inputs for board '{}' and hand '{}' are invalid!", board, hand);
            return 0;
        }

        // count frequency of hand letter occurrence
        final Map<Character, Integer> handCharactersFrequency = new HashMap<>();

        for (char c : hand.toCharArray()) {
            handCharactersFrequency.put(c, handCharactersFrequency.getOrDefault(c, 0) + 1);
        }

        // basic data structure for BFS
        final Set<String> visited = new HashSet<>();
        final Queue<String[]> queue = new LinkedList<>();
        queue.offer(new String[]{board + END_FLAG, hand});
        int numberOfBalls = 0;

        // BFS start
        while (!queue.isEmpty()) {

            // iterate by level
            final int currentSize = queue.size();

            for (int size = 0; size < currentSize; size++) {

                final String[] currentPair = queue.poll();
                final String nowBoard = removeSame(currentPair[0]);

                // end condition
                if (nowBoard.equals(END_FLAG)) {
                    return numberOfBalls;
                }

                final String nowHand = currentPair[1];

                // algo, for each position, for each hand, make new board and new hand,
                // if not visited new board, add it into visited and queue.
                for (int boardIndex = 0; boardIndex < nowBoard.length(); boardIndex++) {

                    for (char handCharacter : nowHand.toCharArray()) {

                        // if we have only one letter, then it should be equal to its neighbor
                        // mutant is boardIndex > 2  where previously condition was boardIndex > 1
                        if (1 == handCharactersFrequency.getOrDefault(handCharacter, 0) && boardIndex > 2 && boardIndex < nowBoard.length() - 1 && nowBoard.charAt(boardIndex) != handCharacter && handCharacter != nowBoard.charAt(boardIndex + 1)) {
                            continue;
                        }

                        final String newString = nowBoard.substring(0, boardIndex) + handCharacter + nowBoard.substring(boardIndex);

                        if (!visited.contains(newString)) {

                            final String newHand = removeCharFromHand(nowHand, handCharacter);
                            visited.add(newString);
                            queue.offer(new String[]{newString, newHand});

                        }

                    }

                }

            }

            ++numberOfBalls;

        }

        return -1;

    }

    private boolean inputsAreInvalid(String board, String hand) {
        return board.length() < 1 || board.length() > 16 || hand.length() < 1 || hand.length() > 5 || colorsAreInvalid(board) || colorsAreInvalid(hand);
    }

    private boolean colorsAreInvalid(String string) {
        return !string.matches(VALID_BALL_COLORS_REGEX);
    }

    private String removeCharFromHand(String hand, char c) {

        final StringBuilder handBuild = new StringBuilder(hand);

        for (int i = 0; i < handBuild.length(); i++) {

            if (handBuild.charAt(i) == c) {

                handBuild.deleteCharAt(i);
                break;

            }

        }

        return handBuild.toString();

    }

    private String removeSame(String s) {

        int start = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == s.charAt(start)) {
                continue;
            }

            if (i - start >= 3) {
                return removeSame(s.substring(0, start) + s.substring(i));
            } else {
                start = i;
            }

        }

        return s;

    }

}