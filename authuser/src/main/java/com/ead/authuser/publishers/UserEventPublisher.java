package com.ead.authuser.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ead.authuser.dtos.UserEventDto;
import com.ead.authuser.enums.ActionType;

@Component
public class UserEventPublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value(value = "${ead.broker.exchange.userEventExchange}")
	private String exchangeUserEvent;
	
	public void publishUserEvent(UserEventDto userEventDto, ActionType actionType) {
		userEventDto.setActionType(actionType.toString());
		rabbitTemplate.convertAndSend(exchangeUserEvent, "", userEventDto);
	}
	
}
