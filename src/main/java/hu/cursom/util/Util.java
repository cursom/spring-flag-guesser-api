package hu.cursom.util;

import hu.cursom.dto.NameDto;
import hu.cursom.dto.NativeNameDto;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {

    private static final Random rnd = new SecureRandom();
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String genCode(int len) {
        return IntStream.range(0, len)
                .mapToObj(i -> String.valueOf(CHARS.charAt(rnd.nextInt(CHARS.length()))))
                .collect(Collectors.joining());
    }

    public static <T> T pickRandom(List<T> list) {
        return list.get(rnd.nextInt(list.size()));
    }

    public static boolean matchName(String guess, NameDto name) {
        String g = normalize(guess);
        if (g.equalsIgnoreCase(normalize(name.common()))) return true;
        if (g.equalsIgnoreCase(normalize(name.official()))) return true;
        if (name.nativeName() != null) {
            for (NativeNameDto n : name.nativeName().values()) {
                if (g.equalsIgnoreCase(normalize(n.common())) || g.equalsIgnoreCase(normalize(n.official())))
                    return true;
            }
        }
        return false;
    }

    private static String normalize(String input) {
        return Arrays.stream(input.trim().split("\\s+"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}