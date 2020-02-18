package com.company.SoamiCohlyU1Capstone.viewmodel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class ReceiptViewModel {

    @NotEmpty(message = "Please supply a value for name")
    @Size(max = 80)
    private String name;

    @NotEmpty(message = "Please supply a value for street")
    @Size(max = 30)
    private String street;

    @NotEmpty(message = "Please supply a value for city")
    @Size(max = 30)
    private String city;

    @NotEmpty(message = "Please supply a value for state")
    @Size(max = 30)
    private String state;

    @NotEmpty(message = "Please supply a value for zipcode")
    @Size(max = 5)
    private String zipCode;

//
//    @NotEmpty(message = "Please supply a value for subtotal")
//    private BigDecimal subTotal;
//
//    @NotEmpty(message = "Please supply a value for tax")
//    private BigDecimal tax;
//
//    @NotEmpty(message = "Please supply a value for processing_fee")
//    private BigDecimal processingFee;

    @NotEmpty(message = "Please supply a value for item_type")
    @Size(max = 20)
    private String itemType;

    @NotEmpty(message = "Please supply a value for unit_price")
    private BigDecimal unitPrice;

    @NotEmpty(message = "Please supply a value for quantity")
    private int quantity;


    @NotNull
    private BigDecimal subTotal;

    @NotNull
    private BigDecimal tax;

    @NotNull
    private BigDecimal processingFee;

    @NotNull
    private BigDecimal total;

    //name, street, city, state, zipcode, item_type,
    // unit price, quantity, subtotal, tax, fee, total

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptViewModel that = (ReceiptViewModel) o;
        return getQuantity() == that.getQuantity() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getState(), that.getState()) &&
                Objects.equals(getZipCode(), that.getZipCode()) &&
                Objects.equals(getItemType(), that.getItemType()) &&
                Objects.equals(getUnitPrice(), that.getUnitPrice()) &&
                Objects.equals(getSubTotal(), that.getSubTotal()) &&
                Objects.equals(getTax(), that.getTax()) &&
                Objects.equals(getProcessingFee(), that.getProcessingFee()) &&
                Objects.equals(getTotal(), that.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreet(), getCity(), getState(), getZipCode(), getItemType(), getUnitPrice(), getQuantity(), getSubTotal(), getTax(), getProcessingFee(), getTotal());
    }
}
