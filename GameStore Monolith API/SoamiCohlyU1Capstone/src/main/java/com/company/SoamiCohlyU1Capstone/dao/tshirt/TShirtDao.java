package com.company.SoamiCohlyU1Capstone.dao.tshirt;

import com.company.SoamiCohlyU1Capstone.model.TShirt;

import java.util.List;

public interface TShirtDao {

    List<TShirt>getAllTshirts();

    TShirt addTshirt(TShirt tShirt);

    TShirt getTshirt(int id);

    List<TShirt> getTshirtBySize( String size);

    List<TShirt> getTshirtByColor(String color);

    void deleteTshirt(int id);

    void updateTshirt(TShirt tShirt);
}
