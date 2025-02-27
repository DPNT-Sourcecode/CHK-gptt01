package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {

    private CheckoutSolution checkout;

    @BeforeEach
    public void setup() {
        checkout = new CheckoutSolution();
    }

    // Singular test cases

    @Test
    public void checkout_null() {
        assertThat(checkout.checkout(null), equalTo(0));
    }

    @Test
    public void checkout_empty() {
        assertThat(checkout.checkout(""), equalTo(0));
    }

    @Test
    public void checkout_invalid_lowercase() {
        assertThat(checkout.checkout("a"), equalTo(-1));
    }

    @Test
    public void checkout_invalid_specialChar() {
        assertThat(checkout.checkout("!"), equalTo(-1));
    }

    // Single item value tests

    @Test
    public void checkout_A() {
        assertThat(checkout.checkout("A"), equalTo(50));
    }

    @Test
    public void checkout_B() {
        assertThat(checkout.checkout("B"), equalTo(30));
    }

    @Test
    public void checkout_C() {
        assertThat(checkout.checkout("C"), equalTo(20));
    }

    @Test
    public void checkout_D() {
        assertThat(checkout.checkout("D"), equalTo(15));
    }

    @Test
    public void checkout_E() {
        assertThat(checkout.checkout("E"), equalTo(40));
    }

    @Test
    public void checkout_F() {
        assertThat(checkout.checkout("F"), equalTo(10));
    }

    // Simple multi-item sum test

    @Test
    public void checkout_multiItem_noOffer() {
        assertThat(checkout.checkout("ABCDEF"), equalTo(165));
    }

    // Simple Offer Tests

    @Test
    public void checkout_offer_3A() {
        assertThat(checkout.checkout("AAA"), equalTo(130));
    }

    @Test
    public void checkout_offer_5A() {
        assertThat(checkout.checkout("AAAAA"), equalTo(200));
    }

    @Test
    public void checkout_offer_2B() {
        assertThat(checkout.checkout("BB"), equalTo(45));
    }

    @Test
    public void checkout_offer_2E_freeB() {
        assertThat(checkout.checkout("EEB"), equalTo(80));
    }

    @Test
    public void checkout_offer_2F_freeF() {
        assertThat(checkout.checkout("FFF"), equalTo(20));
    }

    // Multi and over offer tests

    @Test
    public void checkout_offer_twoOffers() {
        assertThat(checkout.checkout("AAABB"), equalTo(175));
    }

    @Test
    public void checkout_offer_overOffer() {
        assertThat(checkout.checkout("AAAAAAAAAAAAAA"), equalTo(580));
    }

    @Test
    public void checkout_offer_twoOpposingOffers() {
        assertThat(checkout.checkout("EEEEBBBBB"), equalTo(235));
    }

    @Test
    public void checkout_offer_nonNegativeOnFree() {
        assertThat(checkout.checkout("EEEEB"), equalTo(160));
    }

    // Group offer tests

    @Test
    public void checkout_offer_groupOffer() {
        assertThat(checkout.checkout("STXYZ"), equalTo(82));
    }

    @Test
    public void checkout_offer_groupOfferReversed() {
        assertThat(checkout.checkout("ZYXYS"), equalTo(82));
    }

}