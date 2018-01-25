package fraction;

/**
 * This is the Fraction class that perform exact fraction arithmetic.
 * 
 * @author BasPasut
 *
 */
public class Fraction implements Comparable<Fraction> {

	private long numerator;
	private long denominator;
	public static final Fraction ZERO = new Fraction(0);

	/**
	 * Create a fraction with specific numerator and denominator.
	 * 
	 * @param n
	 *            is the numerator of the fraction.
	 * @param d
	 *            is the denominator of the fraction.
	 */
	public Fraction(long n, long d) {
		long gcd = gcd(n, d);
		if (n != 0 && d != 0) {
			numerator = n / gcd;
			denominator = d / gcd;
		} else {
			numerator = n;
			denominator = d;
		}

	}

	/**
	 * Create a fraction with specific numerator ,but denominator equals 1
	 * 
	 * @param n
	 *            is the numerator of the fraction.
	 */
	public Fraction(long n) {
		numerator = n;
		denominator = 1;
	}

	/**
	 * Create a fraction from a String value. The String must contain a long
	 * ("123"), long/long("12/34"), or a decimal value ("12.34").
	 * 
	 * @param value
	 *            is the String that contain value of long, long/long, or a
	 *            decimal value.
	 */
	public Fraction(String value) {
		String[] s;
		if (value.contains("/")) {
			s = value.split("/");
			numerator = Long.parseLong(s[0]);
			denominator = Long.parseLong(s[1]);
		} else if (value.contains(".")) {
			s = value.split(".");
			int denoPow = s[1].length();
			numerator = Long.parseLong(s[0]);
			denominator = (long) Math.pow(10, denoPow);
		} else {
			numerator = Long.parseLong(value);
			denominator = 1;
		}

	}

	/**
	 * Method to add f fraction with current fraction.
	 * 
	 * @param f
	 *            is the fraction that you want to add
	 * @return a new Fraction that is sum of this fraction and f.
	 */
	public Fraction add(Fraction f) {
		if (this.toString().equals("infinity") && f.toString().equals("infinity")) return new Fraction(1, 0);
		else if (this.toString().equals("-infinity") && f.toString().equals("-infinity")) return new Fraction(-1, 0);
		else if (this.toString().equals("infinity") && f.toString().equals("-infinity")) return new Fraction(0, 0);
		else if (f.toString().equals("infinity") && this.toString().equals("0")) return new Fraction(1, 0);
		else if (this.toString().equals("infinity") && f.toString().equals("0")) return new Fraction(1, 0);
		else if (f.toString().equals("NaN") || this.toString().equals("NaN")) return new Fraction(0, 0);
		if (this.denominator == f.denominator) {
			return new Fraction(f.numerator + this.numerator, f.denominator);
		} else {
			long newNum = (this.denominator * f.numerator) + (f.denominator * this.numerator);
			long newDeno = (this.denominator * f.denominator);
			return new Fraction(newNum, newDeno);
		}

	}

	/**
	 * Method to subtract f fraction with current fraction.
	 * 
	 * @param f
	 *            is the fraction that you want to subtract by.
	 * @return a new fraction that is difference of this fraction and f.
	 */
	public Fraction subtract(Fraction f) {
		if (this.toString().equals("Infinity") && f.toString().equals("Infinity"))
			return new Fraction(0, 0);
		else if (this.toString().equals("-Infinity") && f.toString().equals("Infinity"))
			return new Fraction(-1, 0);
		else if (f.toString().equals("-Infinity") && this.toString().equals("Infinity"))
			return new Fraction(-1, 0);
		if (this.denominator == f.denominator) {
			return new Fraction(this.numerator - f.numerator, f.denominator);
		} else {
			long newNum = (f.denominator * this.numerator) - (this.denominator * f.numerator);
			long newDeno = (this.denominator * f.denominator);
			return new Fraction(newNum, newDeno);
		}

	}

	/**
	 * Method to multiply f fraction with the current fraction.
	 * 
	 * @param f
	 *            is the fraction that you want to multiply by.
	 * @return a new fraction that is product of this fraction and f.
	 */
	public Fraction multiply(Fraction f) {
		if ((this.toString().equals("Infinity") && f.toString().equals("0"))
				|| (f.toString().equals("infinity")) && this.toString().equals("0")) {
			return new Fraction(0, 0);
		}
		if (this.toString().equals("NaN") || f.toString().equals("NaN")) {
			return new Fraction(0, 0);
		}
		return new Fraction(this.numerator * f.numerator, this.denominator * f.denominator);
	}

	/**
	 * Method to divide f fraction with current fraction.
	 * 
	 * @param f
	 *            the fraction that you want to divided by.
	 * @return a new fraction that come from current fraction divided by f.
	 */
	public Fraction divide(Fraction f) {
		if (f.toString().equals("Infinity")) {
			return new Fraction(0);
		}
		return new Fraction(this.numerator * f.denominator, this.denominator * f.numerator);

	}

	/**
	 * Method that compute and return the negative of this Fraction.
	 * 
	 * @return a new fraction that is the negative of this Fraction. Negate of
	 *         Infinity is -Infinity. negate of NaN is NaN
	 */
	public Fraction negate() {
		return new Fraction(this.numerator * -1, this.denominator);

	}

	/**
	 * Method to create a new fraction that current fraction powered by n.
	 * 
	 * @param n
	 *            is the number that you want the current fraction to power by.
	 * @return a new fraction that is this fraction raised to the power n. n may
	 *         be zero or negative.
	 */
	public Fraction pow(int n) {
		return new Fraction((long) Math.pow(this.numerator, n), (long) Math.pow(this.denominator, n));

	}

	/**
	 * Method to compare this fraction with f fraction. It will return -1 if
	 * current fraction is less that o fraction, 0 if it's equal ,and 1 if this
	 * fraction is more than o fraction.
	 * 
	 * @return -1 if this fraction is less than o fraction. 0 if it's equal, and
	 *         1 if it's higher.
	 */
	public int compareTo(Fraction o) {
		if (this.denominator != o.denominator) {
			this.numerator = this.numerator * o.denominator;
			o.numerator = o.numerator * this.denominator;
		}
		if (this.numerator < o.numerator) {
			return -1;
		} else if (this.numerator == o.numerator) {
			return 0;
		}
		return 1;
	}

	/**
	 * Method to compute current fraction and return it in double.
	 * 
	 * @return a value of the current fraction in double.
	 */
	public double doubleValue() {
		return (double) numerator / denominator;

	}

	/**
	 * 
	 * This method test whether two fraction have the same value or not. If it's
	 * equal, return true. If not, return false.
	 * 
	 * @param obj
	 *            fraction that we want to compare.
	 * 
	 * @return true if two fraction are equal and false if it's not equal.
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		Fraction f = (Fraction) obj;
		if (this.toString().equals(f.toString()))
			return true;
		if (f.numerator == this.numerator && f.denominator == this.denominator)
			return true;
		return false;
	}

	/**
	 * Method to check whether this fraction is Not a Number or not. NaN is
	 * happen when numerator and denominator is 0.
	 * 
	 * @return true if this fraction is NaN ,and false if not.
	 */
	public boolean isNaN() {
		if (numerator == 0 && denominator == 0) {
			return true;
		}
		return false;

	}

	/**
	 * Method to check whether this fraction is infinite or -infinity or not.
	 * Both of them are happen when denominator is 0 and numerator is not equal
	 * to 0.
	 * 
	 * @return true if this fraction is infinity or -infinity, and false if it's
	 *         not.
	 */
	public boolean isInfinite() {
		if (denominator == 0 && numerator != 0) {
			return true;
		}
		return false;

	}

	/**
	 * Method to check whether f fraction is Not a Number or not. NaN is happen
	 * when numerator and denominator is 0.
	 * 
	 * @param f
	 *            is the fraction that you want to test.
	 * @return true if f is NaN ,and false if's not.
	 */
	public static boolean isNaN(Fraction f) {
		return f.isNaN();

	}

	/**
	 * Method to check whether f fraction is infinity or -infinity or not. Both
	 * of them are happen when denominator is 0 ,and numerator is not equal to
	 * 0.
	 * 
	 * @param f
	 *            is the fraction that you want to test.
	 * @return true if f is infinity or -infinity ,and false if's not.
	 */
	public static boolean isInfinite(Fraction f) {
		return f.isInfinite();

	}

	/**
	 * Method to check whether this fraction is greater than 0, equal to 0, and
	 * less than 0.
	 * 
	 * @return 1 if this fraction is greater than 0 or +infinity. 0 if this
	 *         fraction is 0 or NaN. 1 if this fraction is less than 0 or
	 *         -infinity.
	 */
	public int signum() {
		if (denominator == 0 && numerator > 0)
			return 1;
		else if (denominator != 0 && numerator / denominator > 0)
			return 1;
		else if (denominator == 0 && numerator < 0)
			return -1;
		else if (denominator != 0 && numerator / denominator < 0)
			return -1;
		{
			return 0;
		}

	}

	/**
	 * Method to find a common factors from numerator and denominator of this
	 * fraction.
	 * 
	 * @param a
	 *            is a numerator of the fraction.
	 * @param b
	 *            is a denominator of the fraction.
	 * @return common factors from numerator and denominator.
	 */
	public static long gcd(long a, long b) {
		if (b == 0) {
			return Math.abs(a);
		}
		return gcd(b, a % b);
	}

	/**
	 * String representation of the fraction.
	 * 
	 * @return Infinity if the fraction is infinity. -Infinity if the fraction
	 *         is -infinity. NaN for extended numbers.
	 */
	public String toString() {
		if (isInfinite()) {
			if (signum() == 1) {
				return "Infinity";
			}
			if (signum() == -1) {
				return "-Infinity";
			}
		}
		if (isNaN()) {
			return "NaN";
		} else {
			if (numerator == 0) {
				denominator = 1;
			}
			if (denominator == 1) {
				return numerator + "";
			}
			if (denominator == -1) {
				numerator = numerator * -1;
				Math.abs(denominator);
				return numerator + "";
			} else if (denominator < 0) {
				numerator = numerator * -1;
				denominator = denominator * -1;
			}
			return numerator + "/" + denominator;
		}
	}
}
