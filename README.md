# Fraction
Fraction class is the class that perform fraction arithmetic. It contains the method below:
### add
return a new fraction that is sum of this fraction and f.  Don't modify value of this fraction or f.
### subtract
return a new fraction that is difference of this fraction and f.    Don't modify value of this fraction or f. 
### multiply
return a new fraction that is product of this fraction and f.
### divide
return a new fraction that is this fraction divided by f. 
### negate
return a new fraction that is the negative of this Fraction. negate of Infinity is -Infinity. negate of NaN is NaN. 
### pow
return a new fraction that is this fraction raised to the power n.  n may be zero or negative. 
### compareTo
compare this fraction to f.  The return value should be: a.compareTo(b) < 0    if a is less than b a.compareTo(b) = 0    if a has same value as b a.compareTo(b) > 0    if a is greater than b a.compareTo(Fraction.NaN) < 0 for any a != NaN 
### doubleValue
return value of this fraction as a double. May be inaccurate. 
### equals
return true if obj is a Fraction and has the same value. 
### isNaN
return true if this fraction is Not a Number (NaN).  Internally, NaN is represented as numerator = denominator = 0. 
### isInfinite
return true if this fraction is positive or negative infinity. 
### static isNaN
It is the static version of isNaN to make it easy to test values.
### static isInfinite
It is the static version of isInfinite to make it easy to test values.
### signum
Return +1 if this fraction is greater than zero (including +Infinity), 0 if fraction is 0 or NaN, -1 if this fraction is less than zero (including -Infinity). 
### toString
return a String representation of the fraction, with no spaces.  Return the String  "Infinity",  "-Infinity", or "NaN" for extended numbers.