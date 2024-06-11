
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class DigitalSignature1 {
        public static void main(String[] args) throws NoSuchAlgorithmException {
            // Given parameters
            BigInteger q = new BigInteger("101");
            BigInteger p = new BigInteger("7879");
            BigInteger g = new BigInteger("170");
            BigInteger x = new BigInteger("75"); // Private key
            BigInteger y = new BigInteger("4567"); // Public key
            BigInteger h = new BigInteger("1234"); // Hash of the message

            Random random = new Random();
            BigInteger k = new BigInteger(String.valueOf(random.nextInt(1000)));
            System.out.println("Random Number: " + k);

            BigInteger publicKey = g.modPow(x,p);
            System.out.println("Public Key: " + publicKey);

            // Signing
            // r = (g^k mod p) mod q
            BigInteger r = g.modPow(k, p).mod(q);
            // s = k^−1· (e + x · r) mod q.
            BigInteger kInverse = k.modInverse(q);
            BigInteger s = kInverse.multiply(h.add(x.multiply(r))).mod(q);

            // Display the signature
            System.out.println("Signature (r, s): " + "(" + r + "," + s + ")");

            // Verification
            // w = s^−1 mod q,
            BigInteger w = s.modInverse(q);
            // u1 = e·w mod q
            BigInteger u1 = h.multiply(w).mod(q);
            // u2 = r·w mod q
            BigInteger u2 = r.multiply(w).mod(q);
            // v = ((g^u1·y^u2 ) mod p) mod q
            BigInteger v = g.modPow(u1, p).multiply(y.modPow(u2, p)).mod(p).mod(q);
            System.out.println("v: " + v);

            // Check if the signature is valid
            boolean isValid = v.equals(r);
            if (isValid) {
                System.out.println("Signature is valid.");
            } else {
                System.out.println("Signature is not valid.");
            }
        }
    }