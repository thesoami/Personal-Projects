package com.company.SoamiCohlyU1Capstone.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class GameViewModel {


    private int gameId;

    @NotEmpty(message = "Please supply a value for title")
    @Size(max = 50)
    private String title;

    @NotEmpty(message = "Please supply a value for esrb_rating")
    @Size(max = 50)
    private String esrbRating;

    @NotEmpty(message = "Please supply a value for description")
    @Size(max = 255)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotEmpty(message = "Please supply a value for studio")
    @Size(max = 50)
    private String studio;

    @PositiveOrZero
    private int quantity;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
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
        GameViewModel that = (GameViewModel) o;
        return getGameId() == that.getGameId() &&
                getQuantity() == that.getQuantity() &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getEsrbRating(), that.getEsrbRating()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getStudio(), that.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getTitle(), getEsrbRating(), getDescription(), getPrice(), getStudio(), getQuantity());
    }
}
