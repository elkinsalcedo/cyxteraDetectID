package com.cyxtera.detectid.core.facade;

import java.util.List;

import com.cyxtera.detectid.core.dto.ClientRequestDto;
import com.cyxtera.detectid.core.dto.ClientResponseDto;

public interface DetectIdCoreFacade {
	
	/**
	 * Allow add a client
	 * @param clientDto
	 * @throws Exception
	 */
	ClientResponseDto addClient(ClientRequestDto clientDto) throws Exception;
	
	/**
	 * Allow retreieve all clients
	 * @return
	 * @throws Exception
	 */
	List<ClientRequestDto> getAllClients() throws Exception;
	
	/**
	 * Allor retrieve clients depending shared key
	 * @param sharedKey
	 * @return
	 * @throws Exception
	 */
	List<ClientRequestDto> getClientBySharedKey(String sharedKey) throws Exception;
}