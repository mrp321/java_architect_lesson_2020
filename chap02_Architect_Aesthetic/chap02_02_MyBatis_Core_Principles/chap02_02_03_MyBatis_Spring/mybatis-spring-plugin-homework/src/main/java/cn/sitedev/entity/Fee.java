package cn.sitedev.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Fee {
    private Integer id;

    private BigDecimal feeAmt;

    private Date feeDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public Date getFeeDate() {
        return feeDate;
    }

    public void setFeeDate(Date feeDate) {
        this.feeDate = feeDate;
    }

    public Fee(BigDecimal feeAmt, Date feeDate) {
        this.feeAmt = feeAmt;
        this.feeDate = feeDate;
    }
}