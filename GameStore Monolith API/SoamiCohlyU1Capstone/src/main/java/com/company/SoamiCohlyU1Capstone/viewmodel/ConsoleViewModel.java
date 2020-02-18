package com.company.SoamiCohlyU1Capstone.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class ConsoleViewModel {

    private int consoleId;

    @NotEmpty(message = "Please supply a value for model")
    @Size(max = 50)
    private String model;

    @NotEmpty(message = "Please supply a value for manufacturer")
    @Size(max = 50)
    private String manufacturer;

    @NotEmpty(message = "Please supply a value for memory_amount")
    @Size(max = 20)
    private String memoryAmount;

    @NotEmpty(message = "Please supply a value for processor")
    @Size(max = 20)
    private String processor;

    @NotNull
    @Positive
    private BigDecimal price;

//    @NotEmpty(message = "Please supply a value for quantity")
    @PositiveOrZero
    private int quantity;

    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
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
        ConsoleViewModel that = (ConsoleViewModel) o;
        return consoleId == that.consoleId &&
                quantity == that.quantity &&
                model.equals(that.model) &&
                manufacturer.equals(that.manufacturer) &&
                Objects.equals(memoryAmount, that.memoryAmount) &&
                Objects.equals(processor, that.processor) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consoleId, model, manufacturer, memoryAmount, processor, price, quantity);
    }
}
