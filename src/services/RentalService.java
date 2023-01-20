package services;

import entities.CarRental;
import entities.Invoice;

import java.time.Duration;

public class RentalService {

    private Double pricePerHour;
    private Double pricePorDay;
    private BrazilTaxServices taxServices;

    public RentalService(Double pricePerHour, Double pricePorDay, BrazilTaxServices taxServices) {
        this.pricePerHour = pricePerHour;
        this.pricePorDay = pricePorDay;
        this.taxServices = taxServices;
    }

    public void processInvoice(CarRental carRental) {
        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;

        if (hours <= 12.0) {
            basicPayment = pricePerHour * Math.ceil(hours);
        } else {
            basicPayment = pricePorDay * Math.ceil(hours / 24.0);
        }

        double tax = taxServices.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }


}
