package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    private static Map<Character, Integer> PRICES = Map.of(
            'A', 50,
            'B', 30,
            'C', 20,
            'D', 15
    );

    public Integer checkout(String skus) {
        // TODO: Not clear from requirements:
        //      - Structure of the list (comma-separated? - assume one word with unsorted chars)
        //      - Invalid characters (single characters that are not SKUs, spaces)

        final Map<Character, Integer> order = new HashMap<>();

        skus.codePoints().collect(Collec)

        throw new SolutionNotImplementedException();
    }

    prtiva

    private static boolean isValidSKU(final Character sku) {
        return sku != null && PRICES.containsKey(sku);
    }

}

