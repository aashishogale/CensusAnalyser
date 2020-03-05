package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCsv {

    @CsvBindByName(column = "State Id", required = true)
    public String state_id;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Population Density", required = true)
    public double population_density;

    @CsvBindByName(column = "Housing Density", required = true)
    public double housing_density;

}

