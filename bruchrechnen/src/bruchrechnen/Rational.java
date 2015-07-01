package bruchrechnen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rational implements Comparable<Rational>
{
    private final int _numerator;
    private final int _denominator;

    private Rational(int numerator, int denominator)
    {
        int divisor = greatestCommonDivisor(numerator, denominator);
        _numerator = numerator / divisor;
        _denominator = denominator / divisor;
    }

    public static Rational valueOf(int numerator, int denominator)
    {
        return new Rational(numerator, denominator);
    }

    public int numerator()
    {
        return _numerator;
    }

    public int denominator()
    {
        return _denominator;
    }

    @Override
    public String toString()
    {
        return _numerator + " / " + _denominator;
    }

    public Rational times(Rational that)
    {
        int num = this._numerator * that._numerator;
        int den = this._denominator * that._denominator;
        return new Rational(num, den);
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof Rational) && equals((Rational) obj);
    }

    public boolean equals(Rational that)
    {
        return this._numerator == that._numerator
                && this._denominator == that._denominator;
    }

    // Wenn a.equals(b) gilt, dann muss auch a.hashCode() == b.hashCode() gelten!

    @Override
    public int hashCode()
    {
        return _numerator ^ _denominator;
    }

    private static int greatestCommonDivisor(int a, int b)
    {
        if (b == 0)
        {
            return a;
        }
        else
        {
            return greatestCommonDivisor(b, a % b);
        }
    }

    public Rational over(Rational that)
    {
        int num = this._numerator * that._denominator;
        int den = this._denominator * that._numerator;
        return new Rational(num, den);
    }

    public Rational plus(Rational that)
    {
        int num = this._numerator * that._denominator + that._numerator
                * this._denominator;
        int den = this._denominator * that._denominator;
        return Rational.valueOf(num, den);
    }

    public static Rational valueOf(String string)
    {
        Matcher matcher = regex.matcher(string);
        if (matcher.matches())
        {
            String num = matcher.group(1);
            String den = matcher.group(2);
            return Rational.valueOf(Integer.valueOf(num), Integer.valueOf(den));
        }
        throw new IllegalArgumentException(matcher.toString());
    }
    
    private static final Pattern regex = Pattern.compile("(\\d+) / ([1-9]\\d*)");

    @Override
    public int compareTo(Rational that)
    {
        Rational ratio = this.over(that);
        if (ratio._numerator < ratio._denominator) return -1;
        if (ratio._numerator > ratio._denominator) return +1;
        return 0;
    }
}
