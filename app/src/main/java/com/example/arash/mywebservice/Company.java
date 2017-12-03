package com.example.arash.mywebservice;

/**
 * Created by arash on 17/11/17.
 */

public class Company {
    private String compId,compName, acctNo;

    public Company(String compId, String compName, String acctNo) {
        this.compName = compName;
        this.acctNo = acctNo;
        this.compId = compId;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }
}
