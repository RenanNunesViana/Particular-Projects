package com.cariocar.angular.cariocarangular.dtos;

import com.cariocar.angular.cariocarangular.models.PaymentStats;

import java.time.LocalDateTime;

public record OrderDTO(String description, String note, LocalDateTime checkin,
        LocalDateTime checkout, String customerCpf, String carPlate,
        PaymentStats paymentStats, float quote, float paymentMade,
        float outstanding) {
}
