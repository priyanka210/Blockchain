import java.math.BigInteger;

public class DigitalSignature2 {
    public static void main(String[] args) {
        // Given DSA parameters
        BigInteger q = new BigInteger("101");
        BigInteger p = new BigInteger("7879");
        BigInteger g = new BigInteger("170");
        BigInteger x = new BigInteger("75"); // Private key
        BigInteger y = new BigInteger("4567"); // Public key
        BigInteger k = new BigInteger("50"); // Random number
        BigInteger h = new BigInteger("1234"); // Hash of the message

        System.out.println("Random Number: " + k);
        
        // Calculate r = (g^k mod p) mod q
        BigInteger r = g.modPow(k, p).mod(q);

        // Calculate kInverse = k^-1 mod q
        BigInteger kInverse = k.modInverse(q);

        // Calculate s = kInverse * (h + x * r) mod q
        BigInteger s = kInverse.multiply(h.add(x.multiply(r))).mod(q);

        // Display the signature
        System.out.println("Signature (r, s): " + "(" + r + "," + s + ")");
    }
}
