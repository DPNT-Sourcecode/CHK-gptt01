package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    private static final Map<Character, Integer> PRICES = Map.of(
            'A', 50,
            'B', 30,
            'C', 20,
            'D', 15
    );

    private static final Map<Character, Map<Integer, Integer>> OFFERS = Map.of(
            'A', Map.of(3, 130),
            'B', Map.of(2, 45)
    );

    // TODO: Not clear from requirements:
    //      - Structure of the list (comma-separated? - assume one word with unsorted chars)
    //      - Invalid characters (single characters that are not SKUs, spaces)
    public Integer checkout(String skus) {

        // Validate SKUs
        if (skus == null) return 0;
        skus = skus.toUpperCase();
        if (!isValidSKUs(skus)) return -1;

        // Build a map of SKU to count - the 'order'
        final Map<Character, Integer> order = buildOrderFromSKUs(skus);

        return totalOffers(order) + totalOrder(order);
    }

    private static boolean isValidSKUs(final String skus) {
        return skus.codePoints().allMatch(sku -> PRICES.containsKey((char) sku));
    }

    private static Map<Character, Integer> buildOrderFromSKUs(final String skus) {
        final Map<Character, Integer> order = new HashMap<>();

        skus.codePoints()
                .forEach(sku -> order.put(
                        (char) sku,
                        order.getOrDefault((char) sku, 0) + 1));

        return order;
    }

    private static int totalOrder(final Map<Character, Integer> order) {
        return order.entrySet().stream()
                .mapToInt(entry ->
                        PRICES.get(entry.getKey()) * entry.getValue()
                )
                .sum();
    }

    // Warning - this modifies the map in place and removes products that have been purchased via an offer
    private static int totalOffers(final Map<Character, Integer> order) {
        int total = 0;

        // Loop through the items and apply offers
        for (final Map.Entry<Character, Integer> item : order.entrySet()) {

            if (!OFFERS.containsKey(item.getKey())) continue; // No offer for item

            int count = item.getValue();

            // TODO: If any more offers are added per item, ensure TreeMap used so offers are looped from high to low
            for (final Map.Entry<Integer, Integer> offer : OFFERS.get(item.getKey()).entrySet()) {
                total += (count / offer.getKey()) * offer.getValue();
                count = count % offer.getKey();
            }

            // Put the count of any items where no offer has been applied
            order.put(item.getKey(), count);
        }

        return total;
    }

}
