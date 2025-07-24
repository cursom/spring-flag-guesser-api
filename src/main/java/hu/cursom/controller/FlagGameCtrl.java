package hu.cursom.controller;

import hu.cursom.dto.GenerateResp;
import hu.cursom.dto.GuessReq;
import hu.cursom.dto.GuessResp;
import hu.cursom.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class FlagGameCtrl {

    private final GameService svc;

    @GetMapping
    public ResponseEntity<Map<String, String>> root() {
        return ResponseEntity.ok(Map.of(
                "status", "Available",
                "author", "cursom",
                "github", "https://github.com/cursom/spring-flag-guesser-api"
        ));
    }

    @PostMapping("/generate")
    public ResponseEntity<GenerateResp> generate() {
        return ResponseEntity.ok(svc.generateGame());
    }

    @PostMapping("/guess")
    public ResponseEntity<GuessResp> guess(@RequestBody GuessReq req) {
        return ResponseEntity.ok(svc.evaluateGuess(req));
    }
}