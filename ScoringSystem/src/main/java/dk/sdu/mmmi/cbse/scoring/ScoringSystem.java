package dk.sdu.mmmi.cbse.scoring;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoringSystem {

    private final AtomicInteger currentScore = new AtomicInteger();

    @GetMapping("/score")
    public String updateScore(@RequestParam int points) {
        return "Score updated: " + currentScore.addAndGet(points);
    }

    @GetMapping("/getScore")
    public String getScore() {
        return "" + currentScore.get();
    }

}
