package com.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
@PropertySource("classpath:application.properties")
public class SMSService {

	@Value("${twilio.account.sid}")
	private String twilioAccountSid;

	@Value("${twilio.auth.token}")
	private String twilioAuthToken;

	@Value("${twilio.from.number}")
	private String twilioFromNumber;

	public String sendSMS(String mobileNumber, String text) {
		Twilio.init(twilioAccountSid, twilioAuthToken);
		Message message = Message.creator(new PhoneNumber(mobileNumber),
				new PhoneNumber(twilioFromNumber), text).create();

		return message.getSid();
	}

}
