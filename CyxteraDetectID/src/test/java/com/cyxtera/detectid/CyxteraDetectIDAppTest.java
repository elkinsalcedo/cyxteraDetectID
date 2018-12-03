package com.cyxtera.detectid;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyxtera.detectid.core.dto.ClientRequestDto;
import com.cyxtera.detectid.core.dto.ClientResponseDto;
import com.cyxtera.detectid.core.facade.DetectIdCoreFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CyxteraDetectIDAppTest  {
 
	private static final Logger logger = LoggerFactory.getLogger(CyxteraDetectIDAppTest.class);
	
	@Autowired
	DetectIdCoreFacade detectIdService;
	
	private ClientRequestDto clientRequestDto = null;
	private String sharedKey = "";
	
	@Before
    public void setUp() throws Exception {
		logger.error("Call -> setUp ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localStartDate = LocalDate.parse("01/11/2017", formatter);
		LocalDate localEndDate = LocalDate.parse("05/08/2019", formatter);
		clientRequestDto = new ClientRequestDto();
		clientRequestDto.setNombre("Elkin Salcedo");
		clientRequestDto.setEmail("elkinsalcedo@cyxteratest.com");
		clientRequestDto.setPhone(new BigInteger("3002559010"));
		clientRequestDto.setStartDate(localStartDate);
		clientRequestDto.setEndDate(localEndDate);
		
		this.sharedKey = "elkinsalcedo";
	}
	
	@Test
	public void addClientTest() throws Exception {
		logger.error("Call -> addClientTest ");
		try {
			logger.error("Validate -> assertNotNull -> clientRequestDto");
			assertNotNull("Request cannot be null", clientRequestDto);
			ClientResponseDto clientResponseDto = detectIdService.addClient(clientRequestDto);
			assertEquals(200, clientResponseDto.getStatus());
			logger.info(clientResponseDto.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception detectIdService.addClient -> " + e.getMessage());
		}
		
	}
	
	@Test
	public void getClientBySharedKeyTest() throws Exception {
		logger.error("Call -> getClientBySharedKeyTest ");
		try {
			logger.error("Validate -> assertTrue isNotEmpty -> sharedKey");
			assertTrue("sharedKey cannot be empty", StringUtils.isNotEmpty(sharedKey.trim()));
			List<ClientRequestDto> resClienDto = detectIdService.getClientBySharedKey(sharedKey);
			assertNotNull("Response ClientBySharedKey is null or isEmpty", resClienDto);
			resClienDto.forEach(clientDto -> {
				logger.info("Cliente #: [" + clientDto.getSharedKey() + "," + clientDto.getNombre() + "," + clientDto.getEmail() + "]");
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception detectIdService.getClientBySharedKey -> " + e.getMessage());
		}
		
	}
	
	@After
	public void getAllClients(){
		logger.error("Call -> getAllClients");
		try {
			List<ClientRequestDto> resClienDto = detectIdService.getAllClients();
			assertNotNull("Response AllClients is null or isEmpty", resClienDto);
			resClienDto.forEach(clientDto -> {
				logger.info("Cliente #: [" + clientDto.getSharedKey() + "," + clientDto.getNombre() + "," + clientDto.getEmail() + "]");
			});
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception getAllClients -> " + e.getMessage());
		}
		
	}
}
