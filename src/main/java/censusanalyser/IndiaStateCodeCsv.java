package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCsv {

    @CsvBindByName(column = "State Name", required = true)
    public String state;

    @CsvBindByName(column = "TIN", required = true)
    public String tin;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @CsvBindByName(column = "SrNo", required = true)
    public String srno;

//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getTin() {
//        return tin;
//    }
//
//    public void setTin(String tin) {
//        this.tin = tin;
//    }
//
//    public String getStateCode() {
//        return stateCode;
//    }
//
//    public void setStateCode(String stateCode) {
//        this.stateCode = stateCode;
//    }
}
