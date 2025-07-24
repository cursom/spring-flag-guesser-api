package hu.cursom.dto;

import java.util.Map;

public record NameDto(String common, String official, Map<String, NativeNameDto> nativeName) {}