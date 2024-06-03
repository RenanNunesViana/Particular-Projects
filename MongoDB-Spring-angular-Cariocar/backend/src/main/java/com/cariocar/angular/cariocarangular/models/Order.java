package com.cariocar.angular.cariocarangular.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@Document(value = "Order")
public class Order {
    @Id
    private ObjectId id;
    private String description;
    private String note;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    private String customerCpf;
    private String carPlate;
    private PaymentStats paymentStats;
    private float quote;
    private float paymentMade;
    private float outstanding;

    public Order() {
        this.checkin = LocalDateTime.now();
    }

    public Order(String description, String note, LocalDateTime checkin, LocalDateTime checkout, String customerCpf, String carPlate, PaymentStats paymentStats, float quote, float paymentMade, float outstanding) {
        this.description = description;
        this.note = note;
        this.checkin = checkin;
        this.checkout = checkout;
        this.customerCpf = customerCpf;
        this.carPlate = carPlate;
        this.paymentStats = paymentStats;
        this.quote = quote;
        this.paymentMade = paymentMade;
        this.outstanding = outstanding;
    }
}
