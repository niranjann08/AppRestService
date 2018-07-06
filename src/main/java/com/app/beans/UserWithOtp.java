package com.app.beans;

import com.app.entities.AppUser;

import lombok.Data;

@Data
public class UserWithOtp {
	private AppUser user;
	private int otpnum;
}
