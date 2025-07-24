package hu.cursom.service;

import hu.cursom.client.FlagClient;
import hu.cursom.dto.*;
import hu.cursom.model.GameData;
import hu.cursom.util.Util;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final FlagClient client;
    private final Cache<String, GameData> cache;

    public GenerateResp generateGame() {
        List<CountryDto> countries = client.fetchCountries();
        CountryDto picked = Util.pickRandom(countries);
        String code = Util.genCode(12);
        cache.put(code, new GameData(picked, Instant.now()));
        return new GenerateResp(picked.flags().svg(), code, "0083");
    }

    public GuessResp evaluateGuess(GuessReq req) {
        GameData data = cache.getIfPresent(req.code());
        if (data == null) return new GuessResp(null, "0078", "EXPIRED", 0);

        long elapsed = Duration.between(data.created(), Instant.now()).toSeconds();
        String common = data.country().name().common();
        boolean match = Util.matchName(req.guess(), data.country().name());

        cache.invalidate(req.code());

        return match
                ? new GuessResp(common, "0061", "OK", elapsed)
                : new GuessResp(common, "0097", "WRONG", elapsed);
    }
}