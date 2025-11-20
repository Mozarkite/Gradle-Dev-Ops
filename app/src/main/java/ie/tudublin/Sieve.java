package ie.tudublin;

import java.util.ArrayList;
import java.util.List;

public class Sieve {

    public List<Integer> sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++) prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        List<Integer> composite = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!prime[i]) composite.add(i);
        }

        return composite;
    }
}
