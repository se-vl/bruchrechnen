package bruchrechnen;

public class Rational
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
        if (obj instanceof Rational)
        {
            Rational that = (Rational) obj;
            return this._numerator == that._numerator
                    && this._denominator == ((Rational) obj)._denominator;
        }
        return false;
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
        Rational inverse = Rational.valueOf(that._denominator, that._numerator);
        return this.times(inverse);
    }

    public Rational plus(Rational that)
    {
        int num = this._numerator * that._denominator
                + that._numerator * this._denominator;
        int den = this._denominator * that._denominator;
        return Rational.valueOf(num, den);
    }
}
