package me.alexconnell;

public class CustomRandom {

    long seed;

    public CustomRandom() {
        seed = System.currentTimeMillis();
    }

    public double nextDouble() {
        return Math.random();
    }

    public long getSeed() {
        return seed;
    }
}
