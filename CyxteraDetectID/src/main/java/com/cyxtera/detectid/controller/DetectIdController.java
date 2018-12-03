package com.cyxtera.detectid.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cyxtera.detectid.core.dto.ClientRequestDto;
import com.cyxtera.detectid.core.dto.ClientResponseDto;
import com.cyxtera.detectid.core.facade.DetectIdCoreFacade;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.annotations.*;

/**
 * @author esalcedo
 *
 */

@Controller
@RequestMapping("/v1")
public class DetectIdController {

	private static final Logger logger = LoggerFactory.getLogger(DetectIdController.class);
	
	private final DetectIdCoreFacade detectIdService;
	
	@Autowired
    public DetectIdController(DetectIdCoreFacade detectIdCoreFacade) {
        this.detectIdService = detectIdCoreFacade;
    }

	/**
	 * 
	 * @param clientDto
	 * @return
	 * @throws JsonProcessingException
	 */
	@ApiOperation(value = "Add a client")
	@RequestMapping(value = "/addClient", method = RequestMethod.POST, produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addClient(@RequestBody ClientRequestDto clientDto) throws JsonProcessingException {
		logger.info("Entry /addClient from controller");
		ClientResponseDto clientResponseDto = new ClientResponseDto();
		
		try {
			logger.info("Start Call -> addClient("+ clientDto +")");
			clientResponseDto = 	detectIdService.addClient(clientDto);
			logger.info("End Call -> addClient ");
		} catch (Exception ex) {
			logger.error("Exception -> addClient : " +  ex.getMessage());
			clientResponseDto.setStatus(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString()));
			clientResponseDto.setMessage(ex.getMessage());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientResponseDto), HttpStatus.OK);

	}
	
	/**
	 * Allow to filter a specific client by shared key.
	 * @param sharedKey
	 * @return
	 */
	@RequestMapping(value = "/getClientBySharedKey", method = RequestMethod.POST, produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getClientBySharedKey(String sharedKey){
		logger.info("Entry /getClientBySharedKey from controller");
		List<ClientRequestDto> lstClientsDto = null;
		try {
			logger.info("Start Call -> getClientBySharedKey("+ sharedKey +")");
			lstClientsDto = detectIdService.getClientBySharedKey(sharedKey);
			logger.info("End Call -> getClientBySharedKey");
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			mapper.setSerializationInclusion(Include.NON_EMPTY);
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lstClientsDto), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception -> getClientBySharedKey : " +  e.getMessage());
			return new ResponseEntity<String>("!oops, ha ocurrido un error general en el sistema. " + e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * Allow to list all clients
	 * @return
	 */
	
	@RequestMapping(value = "/getAllClients", method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllClients(){
		logger.info("Entry /getAllClients from controller");
		List<ClientRequestDto> lstClientsDto = null;
		try {
			logger.info("Start Call -> getAllClients()");
			lstClientsDto = detectIdService.getAllClients();
			logger.info("End Call -> getAllClients() ");
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			mapper.setSerializationInclusion(Include.NON_EMPTY);
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lstClientsDto), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception -> getAllClients : " +  e.getMessage());
			return new ResponseEntity<String>("!oops, ha ocurrido un error general en el sistema. " + e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}

}