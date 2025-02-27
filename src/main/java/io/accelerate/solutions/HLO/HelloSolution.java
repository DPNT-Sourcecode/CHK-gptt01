package io.accelerate.solutions.HLO;

public class HelloSolution {

    public String hello(String friendName) {
        return "Hello, " + (friendName == null ? "" : friendName) + "!";
    }

}
