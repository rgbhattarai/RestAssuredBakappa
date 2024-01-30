package com.ApiTesting.Pojo.test;

public class Booking {
	
	public Booking () {
		
	}
	
	public Booking (String fn, String ln,String anneeds
			,int tprice,boolean dpaid, BookingDates bid) {
		
		this.setFirstname(fn);
		this.setLastname(ln);
		this.setDepositpaid(dpaid);
		this.setTotalprice(tprice);
		this.setAdditionalneeds(anneeds);
		this.setBookingdates(bid);
	}
	private String firstname;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAdditionalneeds() {
		return additionalneeds;
	}
	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public boolean isDepositpaid() {
		return depositpaid;
	}
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
	public BookingDates getBookingdates() {
		return bookingdates;
	}
	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}
	private String lastname;
	private String additionalneeds;
	private int totalprice;
	private boolean depositpaid;
	private BookingDates bookingdates;
}
