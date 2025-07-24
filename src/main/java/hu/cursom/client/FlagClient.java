package hu.cursom.client;

import hu.cursom.dto.CountryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Component
public class FlagClient {

    private static final String URL = "https://restcountries.com/v3.1/all?fields=name,flags";
    private final RestTemplate rt = new RestTemplate();

    public List<CountryDto> fetchCountries() {
        ResponseEntity<CountryDto[]> resp = rt.getForEntity(URL, CountryDto[].class);
        return List.of(requireNonNull(resp.getBody()));
    }
}