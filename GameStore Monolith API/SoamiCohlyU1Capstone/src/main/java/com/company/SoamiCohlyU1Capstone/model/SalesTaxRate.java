package com.company.SoamiCohlyU1Capstone.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class SalesTaxRate {
    @NotNull
    @Size(max = 4)
    private String state;

    @NotNull
    private BigDecimal rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTaxRate that = (SalesTaxRate) o;
        return Objects.equals(getState(), that.getState()) &&
                Objects.equals(getRate(), that.getRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getRate());
    }
}
