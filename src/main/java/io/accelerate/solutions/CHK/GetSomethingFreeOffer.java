package io.accelerate.solutions.CHK;

import java.util.Map;

public class GetSomethingFreeOffer implements Offer {

    private final char freeSKu


    @Override
    public int apply(final Map<Character, Integer> order) {
        order.put('B', Math.max(0, order.getOrDefault('B', 0) - order.getOrDefault('E', 0) / 2));
        return 0;
    }

}
