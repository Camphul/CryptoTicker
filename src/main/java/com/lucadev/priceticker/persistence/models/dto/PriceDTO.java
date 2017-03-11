package com.lucadev.priceticker.persistence.models.dto;

/**
 * DTO for the average price.
 * @author Luca
 * @since 3/11/2017
 */
public class PriceDTO {

    private boolean success;
    private String message = "";
    private long timestamp;
    private double price;

    public PriceDTO(long timestamp, double price) {
        this.timestamp = timestamp;
        this.price = price;
        success = true;
    }

    public PriceDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
