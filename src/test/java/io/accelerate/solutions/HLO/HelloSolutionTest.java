package io.accelerate.solutions.HLO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class HelloSolutionTest {

    private HelloSolution hello;

    @BeforeEach
    public void setup() {
        hello = new HelloSolution();
    }

    @Test
    public void hello_null() {
        assertThat(hello.hello(null), equalTo("Hello, !"));
    }

    @Test
    public void hello() {
        assertThat(hello.hello("John"), equalTo("Hello, John!"));
    }

    @Test
    public void hello_complex_name() {
        assertThat(hello.hello("John Smith XYZ1234"), equalTo("Hello, John Smith XYZ1234!"));
    }

}