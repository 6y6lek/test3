import parcs.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Algorithm implements AM {

    private final static BigInteger ZERO = BigInteger.valueOf(0);
    private final static BigInteger ONE = BigInteger.valueOf(1);
    private final static BigInteger TWO = BigInteger.valueOf(2);
    private final static SecureRandom random = new SecureRandom();

    public static BigInteger sqrt(BigInteger x)
            throws IllegalArgumentException {
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x == BigInteger.ZERO || x == BigInteger.ONE) {
            return x;
        } // end if
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
             y.compareTo(x.divide(y)) > 0;
             y = ((x.divide(y)).add(y)).divide(two));
        if (x.compareTo(y.multiply(y)) == 0) {
            return y;
        } else {
            return y.add(BigInteger.ONE);
        }
    }
    public BigInteger FermatFactor(BigInteger N)
    {
        BigInteger a = sqrt(N);
        BigInteger b2 = a.multiply(a).subtract(N);
        if(b2.compareTo(ZERO)==-1)
        {
            a = a.add(ONE);
            b2 = (a.multiply(a)).subtract(N);
        }
        while (!isSquare(b2))
        {
            a = a.add(ONE);
            b2 = a.multiply(a).subtract(N);
        }
        BigInteger r1 = a.subtract(sqrt(b2));
        return r1;
        //display(r1, r2);
    }

    public boolean isSquare(BigInteger N)
    {
        BigInteger sqr = sqrt(N);
        if (sqr.multiply(sqr).equals(N) || (sqr.add(ONE)).multiply(sqr.add(ONE)).equals(N))
            return true;
        return false;
    }

    public void run(AMInfo amInfo) {
        Result result = new Result();
        String obj = amInfo.parent.readObject().toString();
        BigInteger N = new BigInteger(obj);
        if (N.isProbablePrime(1) || N.compareTo(ONE) == 0) result.add(N);
        else {
            BigInteger divisor = FermatFactor(N);
            point p = amInfo.createPoint();
            channel c = p.createChannel();
            p.execute("Algorithm");
            c.write(divisor.toString());
            point p1 = amInfo.createPoint();
            channel c1 = p1.createChannel();
            p1.execute("Algorithm");
            c1.write(N.divide(divisor).toString());
            Result result1 = (Result) (c.readObject());
            Result result2 = (Result) (c1.readObject());
            for (BigInteger bi : result1.getList())
                result.add(bi);
            for (BigInteger bi : result2.getList())
                result.add(bi);
        }
        amInfo.parent.write(result);
    }
}
