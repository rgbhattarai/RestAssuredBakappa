package com.ApiTesting.Pojo.test;

public class BookingDates {

	public BookingDates() {

	}

	public BookingDates(String checkin, String checkout) {
       this.setCheckin(checkin);
       this.setCheckout(checkout);
	}

	private String checkin;

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	private String checkout;

}
