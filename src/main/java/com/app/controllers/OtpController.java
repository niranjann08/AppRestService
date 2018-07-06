package com.app.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.UserWithOtp;
import com.app.entities.AppUser;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.UserRepository;
import com.app.service.EmailService;
import com.app.service.OtpService;
import com.app.service.SMSService;

@RestController
@RequestMapping("/api")
@Api
public class OtpController {

	private static final String SUCCESS = "Entered Otp is valid";
	private static final String FAIL = "Entered Otp is NOT valid. Please Retry!";

	@Autowired
	private OtpService otpService;

	@Autowired
	private SMSService smsService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/otps/sms")
	@ApiOperation(value = "Generate OTP for user and send via SMS")
	public String generateOTPAndSendViaSMS(@Valid @RequestBody AppUser appUser) {
		int otp = otpService.generateOTP(appUser.getEmail());
		AppUser user = userRepository.findByEmail(appUser.getEmail())
				.orElseThrow(
						() -> new ResourceNotFoundException("User", "email",
								appUser.getEmail()));
		return smsService.sendSMS(user.getPhoneNumber(),
				"Your android app OTP code is : " + otp);
	}

	@PostMapping("/otps/email")
	@ApiOperation(value = "Generate OTP for user and send via email")
	public void generateOTPAndSendViaEmail(@Valid @RequestBody AppUser appUser) {
		int otp = otpService.generateOTP(appUser.getEmail());
		AppUser user = userRepository.findByEmail(appUser.getEmail())
				.orElseThrow(
						() -> new ResourceNotFoundException("User", "email",
								appUser.getEmail()));
		emailService.sendMail(user.getEmail(),
				"One Time Password(OTP) for your Android App",
				"Your android app OTP code is : " + otp);
	}

	@PostMapping("/otps/validate")
	@ApiOperation(value = "Validate OTP")
	public String validateOtp(@Valid @RequestBody UserWithOtp userWithOtp) {
		int otpnum = userWithOtp.getOtpnum();
		String email = userWithOtp.getUser().getEmail();
		// Validate the Otp
		if (otpnum > 0) {
			int serverOtp = otpService.getOtp(email);
			if (otpnum == serverOtp) {
				otpService.clearOTP(email);
				return SUCCESS;
			}
		}
		return FAIL;
	}

}
