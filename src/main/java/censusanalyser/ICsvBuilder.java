package censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICsvBuilder<T> {
    public   Iterator<T> getCsvFileIterator(Reader reader, Class csvClass);
}
