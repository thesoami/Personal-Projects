package com.company.SoamiCohlyU1Capstone.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class OrderViewModel {

    @NotNull
    @Size(max = 80)
    private String name;

    @NotNull
    @Size(max = 30)
    private String street;

    @NotNull
    @Size(max = 30)
    private String city;

    @NotNull
    @Size(max = 2)      //our state tax table only has two length character string, only makes sense
    private String state;

    @NotNull
    @Size(max = 5)
    private String zipCode;

    @NotNull
    @Size(max = 20)
    private String itemType;

    @NotNull
    private int itemId;

    @Min(1)
    private int quantity;

    //Properties:::
    /*
    Name,Street, City, State, Zip, item_type, item_id
     */

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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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
        OrderViewModel that = (OrderViewModel) o;
        return getItemId() == that.getItemId() &&
                getQuantity() == that.getQuantity() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getState(), that.getState()) &&
                Objects.equals(getZipCode(), that.getZipCode()) &&
                Objects.equals(getItemType(), that.getItemType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreet(), getCity(), getState(), getZipCode(), getItemType(), getItemId(), getQuantity());
    }
}
