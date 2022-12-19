package com.Prosper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.Prosper.response.model.SMS;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.type.PhoneNumber;

import com.twilio.rest.api.v2010.account.Message;

import java.time.LocalDateTime;

@Component
@Service
public class TwiloService {
	
	private  final String ACCOUNT_SID = "AC71e7dd882abb7032ccaf238e5286be48";
    public  final String AUTH_TOKEN = "97bf4932e155648fdb2b8b16cd1de9b1";
    
    private final String  TOPIC_DESTINATION = "/topic/sms";
    
    @Autowired
    private SimpMessagingTemplate webSocket;
	
//	public void addUser(String phoneNumber, String userName) {
//		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        ValidationRequest validationRequest = ValidationRequest.creator(
//                new com.twilio.type.PhoneNumber(phoneNumber))
//            .setFriendlyName(userName)
//            .create();
//
//        System.out.println(validationRequest.getFriendlyName());
//	}
	
	public void smsSubmit( SMS sms) {
        try{
            send(sms);
        }
        catch(ApiException e){

            webSocket.convertAndSend(TOPIC_DESTINATION, LocalDateTime.now() + ": Error sending the SMS: "+e.getMessage());
            throw e;
        }
        webSocket.convertAndSend(TOPIC_DESTINATION, LocalDateTime.now() + ": SMS has been sent!: "+sms.getTo());

    }
	
	

    private String FROM_NUMBER = "+19378073459";

    public void send(SMS sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

}
