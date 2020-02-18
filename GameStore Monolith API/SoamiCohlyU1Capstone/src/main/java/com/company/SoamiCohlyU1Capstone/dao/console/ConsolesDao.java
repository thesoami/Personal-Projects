package com.company.SoamiCohlyU1Capstone.dao.console;

import com.company.SoamiCohlyU1Capstone.model.Console;

import java.util.List;

public interface ConsolesDao {

    List<Console>getAllConsoles();

    Console addConsole(Console console);

    Console getConsole(int id);

    List<Console> getConsoleByManuf(String manufact);

    void deleteConsole(int id);

    void updateConsole(Console console);
}
