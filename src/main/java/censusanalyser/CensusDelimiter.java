package censusanalyser;

import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.util.InputMismatchException;

public class CensusDelimiter extends CsvRequiredFieldEmptyException {

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE
    }

    CensusDelimiter.ExceptionType type;

    public CensusDelimiter(String message, CensusDelimiter.ExceptionType type) {
        super(message);
        this.type = type;
    }

}




