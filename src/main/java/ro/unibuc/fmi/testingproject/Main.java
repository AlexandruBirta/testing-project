package ro.unibuc.fmi.testingproject;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;


@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        final ZumaGame zumaGame = new ZumaGame();

        HashMap<String, String> inputs = new HashMap<>();

        inputs.put("W", "W");
        inputs.put("RRWWRRBBRR", "WB");
        inputs.put("G", "GGGGG");
        inputs.put("WWRRBBWW", "WRBRW");
        inputs.put("WRRBBW", "RB");
        inputs.put("WWBBWBBWW", "BB");

        inputs.forEach((board, hand) -> log.info("Output for board '{}' and hand '{}' is: {}", board, hand, zumaGame.findMinimumOfSteps(board, hand)));

    }

}