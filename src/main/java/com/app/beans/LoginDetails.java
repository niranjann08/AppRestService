package com.app.beans;

import lombok.Data;

@Data
public class LoginDetails {
	private String usernameOrEmail;
	private String password;
}
