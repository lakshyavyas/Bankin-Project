package com.bank.kyc.dto;

public class KycRequest {
	 private Long userId;

	    private String aadhaarNumber;

	    private String panNumber;

	    private String address;

	    public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public String getAadhaarNumber() {
	        return aadhaarNumber;
	    }

	    public void setAadhaarNumber(String aadhaarNumber) {
	        this.aadhaarNumber = aadhaarNumber;
	    }

	    public String getPanNumber() {
	        return panNumber;
	    }

	    public void setPanNumber(String panNumber) {
	        this.panNumber = panNumber;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }
}
