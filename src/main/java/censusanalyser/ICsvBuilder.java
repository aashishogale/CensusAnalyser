package censusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICsvBuilder<T> {
    public   Iterator<T> getCsvFileIterator(Reader reader, Class csvClass);
    public List getCsvFileList(Reader reader, Class csvClass);

}
