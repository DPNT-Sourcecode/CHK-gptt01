package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.Map;

public class CheckoutSolution {

    private static Map<Character, Integer> PRICES = Map.of(
            'A', 50,
            'B', 30,
            'C', 20,
            'D', 15
    );

    public Integer checkout(String skus) {

        skus = skus.toUpperCase();
        if (!isValidSKUs(skus)) return -1;

        // TODO: Not clear from requirements:
        //      - Structure of the list (comma-separated? - assume one word with unsorted chars)
        //      - Invalid characters (single characters that are not SKUs, spaces)

        throw new SolutionNotImplementedException();
    }

    private boolean isValidSKUs(final String skus) {
        return skus.codePoints().allMatch(sku -> PRICES.containsKey((char) sku));
    }

}


