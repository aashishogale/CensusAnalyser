package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCsvbuilder<T> implements ICsvBuilder {

    public Iterator<T> getCsvFileIterator(Reader reader, Class csvClass){
        return  getCSVBean(reader, csvClass).iterator();
    }

    public List getCsvFileList(Reader reader, Class csvClass){
        return  getCSVBean(reader, csvClass).parse();
    }

    public  CsvToBean<T>  getCSVBean(Reader reader, Class csvClass) {
        CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        CsvToBean<T> csvToBean = csvToBeanBuilder.build();
        return csvToBean;
    }

}
