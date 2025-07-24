package hu.cursom.config;

import hu.cursom.model.GameData;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CacheConfig {
    @Bean
    public Cache<String, GameData> cache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(60))
                .build();
    }
}
