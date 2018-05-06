package org.team619.rutor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {

    public static <T> List<List<T>> batch(List<T> source, int length) {
        int size = source.size();
        if (size <= 0) return new ArrayList<>();
        int fullChunks = (size - 1) / length;

        return IntStream.range(0, fullChunks + 1)
                .mapToObj(n ->
                        source.subList(n * length, n == fullChunks ? size : (n + 1) * length))
                .collect(Collectors.toList());
    }

}
