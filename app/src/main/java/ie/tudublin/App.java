package ie.tudublin;

import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected a number");
            System.exit(1);
        }

        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid numeric value \"" + args[0] + "\"");
            System.exit(1);
        }

        Sieve eratosthenes = new Sieve();
        List<Integer> output = eratosthenes.sieve(n);

        System.out.println("The composite numbers from 1 to " + args[0] + " are:");
        output.forEach(System.out::println);
    }
}
