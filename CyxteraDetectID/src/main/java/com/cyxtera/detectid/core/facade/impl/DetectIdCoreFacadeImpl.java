package com.cyxtera.detectid.core.facade.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cyxtera.detectid.controller.DetectIdController;
import com.cyxtera.detectid.core.dto.ClientRequestDto;
import com.cyxtera.detectid.core.dto.ClientResponseDto;
import com.cyxtera.detectid.core.facade.DetectIdCoreFacade;
import com.cyxtera.detectid.persistence.facade.ClientFacade;
import com.cyxtera.detectid.persistence.pojo.ClientPojo;
import com.cyxtera.detectid.utility.ObjectBuilder;

@Service
public class DetectIdCoreFacadeImpl implements DetectIdCoreFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(DetectIdCoreFacadeImpl.class);
	
	@Autowired
	private ClientFacade clientFacadeImpl;
	
	@Override
	public ClientResponseDto addClient(ClientRequestDto clientDto) throws Exception {
		
		logger.info("Entry -> addClient from Core");
		
		ClientResponseDto clientResponseDto = new ClientResponseDto();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ClientPojo clientPojo = new ClientPojo();
		
		if (null != clientDto && null != clientDto.getNombre().trim()) {
			clientPojo.setNombre(clientDto.getNombre().trim());
		}else {
			throw new Exception("User name is mandatory");
		}
		
		if (null != clientDto && null != clientDto.getPhone()) {
			clientPojo.setPhone(clientDto.getPhone());
		}else {
			throw new Exception("Phone number is mandatory");
		}
		
		if (null != clientDto && null != clientDto.getEmail()) {
			clientPojo.setEmail(clientDto.getEmail());
		}else {
			throw new Exception("Email is mandatory");
		}
		
		clientPojo.setRegisteredAt(LocalDateTime.now());
		String[] sharedKey = clientDto.getEmail().trim().split("@");
		clientPojo.setSharedKey(sharedKey[0]);
		clientPojo.setStartDate(LocalDate.parse(clientDto.getStartDate().toString(), formatter));
		clientPojo.setEndDate(LocalDate.parse(clientDto.getEndDate().toString(), formatter));
		
		logger.info("Start check -> clientFacadeImpl.findClientByEmail from Core");
		ClientPojo resultClientPojo = clientFacadeImpl.findClientByEmail(clientDto.getEmail());
		logger.info("End check -> clientFacadeImpl.findClientByEmail from Core");
		
		if (null != resultClientPojo) {
			clientResponseDto.setStatus(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString()));
			clientResponseDto.setMessage("we're sorry, client entered already exist !!!");
		}else {
			clientFacadeImpl.addClient(clientPojo);
			clientResponseDto.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
			clientResponseDto.setMessage("Client registered successfully !!!");
		}
		return clientResponseDto;
	}

	@Override
	public List<ClientRequestDto> getAllClients() throws Exception {
		logger.info("Entry -> getAllClients from Core");
		List<ClientRequestDto> lstClientsDto = new ArrayList<ClientRequestDto>();
		logger.info("Start Call -> clientFacadeImpl.getAllClients");
		List<ClientPojo> lstClientsPojo = clientFacadeImpl.getAllClients();
		logger.info("End Call -> clientFacadeImpl.getAllClients");
		
		lstClientsPojo.forEach(clientPojo -> {
			ClientRequestDto clientRequestDto = (ClientRequestDto)ObjectBuilder.convertObject(ClientPojo.class, ClientRequestDto.class, clientPojo);
			lstClientsDto.add(clientRequestDto);
		});
		
		return lstClientsDto;
	}
	
	@Override
	public List<ClientRequestDto> getClientBySharedKey(String sharedKey) throws Exception {
		logger.info("Entry -> getClientBySharedKey from Core");
		List<ClientRequestDto> lstClientsDto = new ArrayList<ClientRequestDto>();
		logger.info("Start Call -> clientFacadeImpl.getClientBySharedKey");
		List<ClientPojo> lstClientsPojo = clientFacadeImpl.getClientBySharedKey(sharedKey);
		logger.info("End Call -> clientFacadeImpl.getClientBySharedKey");
		
		lstClientsPojo.forEach(clientPojo -> {
			ClientRequestDto clientRequestDto = (ClientRequestDto)ObjectBuilder.convertObject(ClientPojo.class, ClientRequestDto.class, clientPojo);
			lstClientsDto.add(clientRequestDto);
		});
		
		return lstClientsDto;
	}
	
	
	
}