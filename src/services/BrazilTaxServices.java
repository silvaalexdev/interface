package services;

public class BrazilTaxServices {
    public double tax(double amount) {
        if (amount <= 100.00) {
            return amount * .2;
        } else {
            return amount * .15;
        }
    }
}
