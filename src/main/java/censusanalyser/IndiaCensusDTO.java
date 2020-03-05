package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusDTO {
    public String state;

    public int population;

    public int areaInSqKm;

    public int densityPerSqKm;

    public double population_density;

    public double housing_density;

    public IndiaCensusDTO(IndiaCensusCSV stateCsv){
        this.state = stateCsv.state;
        this.population = stateCsv.population;
        this.areaInSqKm = stateCsv.areaInSqKm;
        this.densityPerSqKm = stateCsv.densityPerSqKm;

    }

    public IndiaCensusDTO(USCensusCsv stateCsv){
        this.state = stateCsv.state;
        this.population = stateCsv.population;
        this.population_density = stateCsv.population_density;
        this.housing_density = stateCsv.housing_density;

    }

}
