package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusDTO {
    public String state;

    public int population;

    public int areaInSqKm;

    public int densityPerSqKm;

    public IndiaCensusDTO(IndiaCensusCSV stateCsv){
        this.state = stateCsv.state;
        this.population = stateCsv.population;
        this.areaInSqKm = stateCsv.areaInSqKm;
        this.densityPerSqKm = stateCsv.densityPerSqKm;

    }

}
