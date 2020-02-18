package com.company.SoamiCohlyU1Capstone.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class TShirtViewModel {


    private int tShirtId;

    @NotEmpty(message = "Please supply a value for size")
    @Size(max = 20)
    private String size;

    @NotEmpty(message = "Please supply a value for color")
    @Size(max = 20)
    private String color;

    @NotEmpty(message = "Please supply a value for description")
    @Size(max = 255)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    private int quantity;

    public int gettShirtId() {
        return tShirtId;
    }

    public void settShirtId(int tShirtId) {
        this.tShirtId = tShirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirtViewModel that = (TShirtViewModel) o;
        return gettShirtId() == that.gettShirtId() &&
                getQuantity() == that.getQuantity() &&
                Objects.equals(getSize(), that.getSize()) &&
                Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gettShirtId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }
}
