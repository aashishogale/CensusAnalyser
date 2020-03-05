package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusLoader {

    public  <T> HashMap<String, IndiaCensusDTO> loadCensusData(String csvFilePath, Class<T>  censusCsvClass, CensusAnalyser.Country country)  throws CensusAnalyserException  {
        HashMap<String, IndiaCensusDTO> censusCsvMap;
        censusCsvMap = new HashMap<>();
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICsvBuilder icvbuilder = CsvBuilderFactory.getCsvBuilder();
            Iterator<T> censusIterator =  icvbuilder.getCsvFileIterator(reader, censusCsvClass);
            Iterable<T> indiaCensusCSVIterable = () -> censusIterator;
            if (country.equals(CensusAnalyser.Country.INDIA)){
                StreamSupport.stream(indiaCensusCSVIterable.spliterator(), false).map(IndiaCensusCSV.class::cast).forEach(census -> censusCsvMap.put(census.state, new IndiaCensusDTO(census)));

            }
            if (country.equals(CensusAnalyser.Country.US)) {
                StreamSupport.stream(indiaCensusCSVIterable.spliterator(), false).map(USCensusCsv.class::cast).forEach(census -> censusCsvMap.put(census.state, new IndiaCensusDTO(census)));

            }
            return censusCsvMap;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
        catch (InputMismatchException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.ILLEGAL_MATCH);
        }

    }


}
