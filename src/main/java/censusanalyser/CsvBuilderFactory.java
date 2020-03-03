package censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public class CsvBuilderFactory  {
    public static ICsvBuilder getCsvBuilder(){

        return new OpenCsvbuilder();
    }

}
