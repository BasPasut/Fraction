package fraction;

/**
 * 
 * @author BasPasut
 *
 */
public class Fraction implements Comparable<Fraction> {

	private long numerator;
	private long denominator;
	public static final Fraction ZERO = new Fraction(0);

	/**
	 * 
	 * @param n
	 * @param d
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
	 * 
	 * @param n
	 */
	public Fraction(long n) {
		numerator = n;
		denominator = 1;
	}

	/**
	 * 
	 * @param value
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
	 * 
	 * @param f
	 * @return
	 */
	public Fraction add(Fraction f) {
		if (this.toString().equals("infinity") && f.toString().equals("infinity")) {
			System.out.println(1);
			return new Fraction(1, 0);
		} else if (this.toString().equals("-infinity") && f.toString().equals("-infinity")) {
			System.out.println(2);
			return new Fraction(-1, 0);
		} else if (this.toString().equals("infinity") && f.toString().equals("-infinity")) {
			System.out.println(3);
			return new Fraction(0, 0);
		} else if (this.toString().equals("infinity") && f.toString().equals("-infinity")) {
			System.out.println(4);
			return new Fraction(0, 0);
		} else if (f.toString().equals("infinity") && this.toString().equals("0")) {
			return new Fraction(1, 0);
		} else if (this.toString().equals("infinity") && f.toString().equals("0")) {
			return new Fraction(1, 0);
		} else if (f.toString().equals("infinity") && this.toString().equals("infinity")) {
			return new Fraction(1, 0);
		} else if (f.toString().equals("-infinity") && this.toString().equals("-infinity")) {
			return new Fraction(-1, 0);
		} else if (f.toString().equals("NaN") || this.toString().equals("NaN")) {
			return new Fraction(0, 0);
		}
		if (this.denominator == f.denominator) {
			return new Fraction(f.numerator + this.numerator, f.denominator);
		} else {
			long newNum = (this.denominator * f.numerator) + (f.denominator * this.numerator);
			long newDeno = (this.denominator * f.denominator);
			return new Fraction(newNum, newDeno);
		}

	}

	/**
	 * 
	 * @param f
	 * @return
	 */
	public Fraction subtract(Fraction f) {
		if (this.toString().equals("Infinity") && f.toString().equals("Infinity")) {
			return new Fraction(0, 0);
		} else if (this.toString().equals("-Infinity") && f.toString().equals("Infinity")) {
			return new Fraction(-1, 0);
		} else if (f.toString().equals("-Infinity") && this.toString().equals("Infinity")) {
			return new Fraction(-1, 0);
		}
		if (this.denominator == f.denominator) {
			return new Fraction(this.numerator - f.numerator, f.denominator);
		} else {
			long newNum = (f.denominator * this.numerator) - (this.denominator * f.numerator);
			long newDeno = (this.denominator * f.denominator);
			return new Fraction(newNum, newDeno);
		}

	}

	/**
	 * 
	 * @param f
	 * @return
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
	 * 
	 * @param f
	 * @return
	 */
	public Fraction divide(Fraction f) {
		if (f.toString().equals("Infinity")) {
			return new Fraction(0);
		}
		return new Fraction(this.numerator * f.denominator, this.denominator * f.numerator);

	}

	/**
	 * 
	 * @return
	 */
	public Fraction negate() {
		return new Fraction(this.numerator * -1, this.denominator);

	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public Fraction pow(int n) {
		return new Fraction((long) Math.pow(this.numerator, n), (long) Math.pow(this.denominator, n));

	}

	/**
	 * 
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
	 * 
	 * @return
	 */
	public double doubleValue() {
		return (double) numerator / denominator;

	}

	/**
	 * 
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
	 * 
	 * @return
	 */
	public boolean isNaN() {
		if (numerator == 0 && denominator == 0) {
			return true;
		}
		return false;

	}

	/**
	 * 
	 * @return
	 */
	public boolean isInfinite() {
		if (denominator == 0 && numerator != 0) {
			return true;
		}
		return false;

	}

	/**
	 * 
	 * @param f
	 * @return
	 */
	public static boolean isNaN(Fraction f) {
		return f.isNaN();

	}

	/**
	 * 
	 * @param f
	 * @return
	 */
	public static boolean isInfinite(Fraction f) {
		return f.isInfinite();

	}

	/**
	 * 
	 * @return
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
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long gcd(long a, long b) {
		if (b == 0) {
			return Math.abs(a);
		}
		return gcd(b, a % b);
	}

	/**
	 * 
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
			} else if (denominator == -1) {
				numerator = numerator * -1;
				Math.abs(denominator);
				return numerator + "";
			} else if (denominator < 0) {
				numerator = numerator * -1;
				denominator = denominator * -1;
			}
			if (numerator == denominator) {
				if (signum() == 1)
					return "1";
				else if (signum() == -1)
					return "-1";
			}
			return numerator + "/" + denominator;
		}
	}
}
