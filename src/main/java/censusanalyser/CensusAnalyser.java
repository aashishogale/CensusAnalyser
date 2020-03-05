package censusanalyser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.collections.ArrayStack;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser  {

    List<IndiaCensusDTO> censusCSVList;
    HashMap<String, IndiaCensusDTO> censusCsvMap;

    public CensusAnalyser(){
        censusCSVList = new ArrayList<IndiaCensusDTO>();
        censusCsvMap = new HashMap<>();
    }

    public int loadIndiaCensusData(String csvFilePath)  throws CensusAnalyserException  {
        try( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICsvBuilder icvbuilder = CsvBuilderFactory.getCsvBuilder();
            Iterator<IndiaCensusCSV> censusIterator =  icvbuilder.getCsvFileIterator(reader, IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> indiaCensusCSVIterable = () -> censusIterator;
            StreamSupport.stream(indiaCensusCSVIterable.spliterator(), false).forEach(census -> censusCsvMap.put(census.state, new IndiaCensusDTO(census)));
            censusCSVList = censusCsvMap.values().stream().collect(Collectors.toList());
            return censusCsvMap.size();
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

    public int loadUSCensusData(String csvFilePath)  throws CensusAnalyserException  {
        try( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICsvBuilder icvbuilder = CsvBuilderFactory.getCsvBuilder();
            Iterator<USCensusCsv> censusIterator =  icvbuilder.getCsvFileIterator(reader, USCensusCsv.class);
            Iterable<USCensusCsv> indiaCensusCSVIterable = () -> censusIterator;
            StreamSupport.stream(indiaCensusCSVIterable.spliterator(), false).forEach(census -> censusCsvMap.put(census.state, new IndiaCensusDTO(census)));
            censusCSVList = censusCsvMap.values().stream().collect(Collectors.toList());
            return censusCsvMap.size();
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
    private <E> int returnCount(Iterator<E> censusCSVIterator) {
        Iterable<E>  csvIterable = () -> censusCSVIterator;
        int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEateries;

    }

    private <T>  Iterator<T> getCsvFileIterator(Reader reader, Class csvClass) {
        CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        CsvToBean<T> csvToBean = csvToBeanBuilder.build();
        return csvToBean.iterator();
    }

    public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {
        try( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICsvBuilder icvbuilder = CsvBuilderFactory.getCsvBuilder();
            return returnCount(icvbuilder.getCsvFileIterator(reader, IndiaStateCodeCsv.class));

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }

    }


    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }

            Comparator<IndiaCensusDTO> indiaCensusCSVComparator = Comparator.comparing(census -> census.state);
            this.sort(indiaCensusCSVComparator);
            String sortedStateCensus = new Gson().toJson(censusCSVList);
            return sortedStateCensus;
    }

    public void sort(Comparator<IndiaCensusDTO> indiaCensusCSVComparator){
        for (int i=0;i < censusCSVList.size() - 1;i++){
            for (int j=0;j < censusCSVList.size() - i -1;j++){
                IndiaCensusDTO census1 =  censusCSVList.get(j);
                IndiaCensusDTO census2 =  censusCSVList.get(j+1);
                if (indiaCensusCSVComparator.compare(census1, census2) > 0){
                    censusCSVList.set(j, census2);
                    censusCSVList.set(j+1, census1);
                }
            }
        }

    }


}
