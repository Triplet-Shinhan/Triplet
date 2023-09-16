package com.ssafy.triplet.payment.util;

import org.springframework.stereotype.Component;

@Component
public class PaymentUtility {
    public String getCurrencySymbol(String currencyCode) {
        return switch (currencyCode) {
            case "USD" -> "$";
            case "JPY" -> "¥";
            case "EUR" -> "€";
            case "CNY" -> "¥";
            case "HKD" -> "$";
            case "THB" -> "฿";
            case "AUD" -> "$";
            case "CAD" -> "$";
            case "GBP" -> "£";
            case "SGD" -> "$";
            case "TWD" -> "NT$";
            case "CHF" -> "CHF";
            case "MYR" -> "RM";
            case "PHP" -> "₱";
            case "VND" -> "₫";
            case "NZD" -> "$";
            case "IDR" -> "Rp";
            case "INR" -> "₹";
            case "AED" -> "د.إ";
            default -> "Unknown";
        };
    }
}
