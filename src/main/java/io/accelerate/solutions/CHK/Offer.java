package io.accelerate.solutions.CHK;

import java.util.Map;

@FunctionalInterface
public interface Offer {

    /**
     * Apply an offer to an order map.
     * This function modifies the map in place so items used in an offer will be removed from the map.
     *
     * @param order order map, SKU character to product count
     * @return total amount of all offers
     */
    int apply(final Map<Character, Integer> order);

}
