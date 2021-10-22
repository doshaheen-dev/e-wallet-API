package com.tml.poc.Wallet.models.utilsmodels;

public class DashboardModel {

    private Object transactionCount;

    private Object lastWeekuserCount;
    private Object lastMonthuserCount;
    private Object totalUserCount;

    public Object getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Object transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Object getLastWeekuserCount() {
        return lastWeekuserCount;
    }

    public void setLastWeekuserCount(Object lastWeekuserCount) {
        this.lastWeekuserCount = lastWeekuserCount;
    }

    public Object getLastMonthuserCount() {
        return lastMonthuserCount;
    }

    public void setLastMonthuserCount(Object lastMonthuserCount) {
        this.lastMonthuserCount = lastMonthuserCount;
    }

    public Object getTotalUserCount() {
        return totalUserCount;
    }

    public void setTotalUserCount(Object totalUserCount) {
        this.totalUserCount = totalUserCount;
    }

}
