package hu.cursom.model;

import hu.cursom.dto.CountryDto;

import java.time.Instant;

public record GameData(CountryDto country, Instant created) {}