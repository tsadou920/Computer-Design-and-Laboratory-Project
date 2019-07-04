// Shofiqur Rahman
// shofi384@gmail.com
// CSC.22100
// Professor Nooreddin Naghibolhosseini
// This is a class to hold complex number type as well as to allow arithmatic operation on complex number

public class Complex {
    private double real;          // the real part
    private double imaginary;   // the imaginary part

    // create a new object with the given real and imaginary parts
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    private void setReal(double real) {
        this.real = real;
    }

    private void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    private double getReal() {
        return real;
    }

    private double getImaginary() { return imaginary; }

    // return a string representation of the invoking Complex object
    public String toString() {
        if (imaginary == 0) return real + "";
        if (real == 0) return imaginary + "i";
        if (imaginary < 0) return real + " - " + (-imaginary) + "i";
        return real + " + " + imaginary + "i";
    }

    // return a new Complex object whose value is (this + b)
    public Complex plus(Complex b) {
        Complex a = this;             // invoking object
        double real = a.real + b.real;
        double imaginary = a.imaginary + b.imaginary;
        return new Complex(real, imaginary);
    }

    // return a new Complex object whose value is (this - b)
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.real - b.real;
        double imag = a.imaginary - b.imaginary;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is (this * b)
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.real * b.real - a.imaginary * b.imaginary;
        double imag = a.real * b.imaginary + a.imaginary * b.real;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is the reciprocal of this
    public Complex reciprocal() {
        double scale = real * real + imaginary * imaginary;
        return new Complex(real / scale, -imaginary / scale);
    }

    // return a / b
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // See Section 3.3.
    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Complex that = (Complex) x;
        return (this.real == that.real) && (this.imaginary == that.imaginary);
    }

    // sample client for testing
    public static void main(String[] args) {
        Complex a = new Complex(5.0, 6.0);
        Complex b = new Complex(-3.0, 4.0);

        System.out.println("Creating two Complex objects... \nDoing some arithmetic operations on them ...");
        System.out.println("a            = " + a);
        System.out.println("b            = " + b);
        System.out.println("Real(a)      = " + a.getReal());
        System.out.println("Imaginary(a) = " + a.getImaginary());
        System.out.println("b + a        = " + b.plus(a));
        System.out.println("a - b        = " + a.minus(b));
        System.out.println("a * b        = " + a.times(b));
        System.out.println("b * a        = " + b.times(a));
        System.out.println("a / b        = " + a.divides(b));
    }
}
