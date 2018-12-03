
package com.cyxtera.detectid.persistence.facade.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyxtera.detectid.core.facade.impl.DetectIdCoreFacadeImpl;
import com.cyxtera.detectid.persistence.dao.ClientDao;
import com.cyxtera.detectid.persistence.entity.Client;
import com.cyxtera.detectid.persistence.facade.ClientFacade;
import com.cyxtera.detectid.persistence.pojo.ClientPojo;
import com.cyxtera.detectid.utility.ObjectBuilder;

/**
 * @author esalcedo
 *
 */

@Service
public class ClientFacadeImpl implements ClientFacade{

	private static final Logger logger = LoggerFactory.getLogger(ClientFacadeImpl.class);
	
	@Autowired
	private ClientDao clientDao;
	
	@Transactional
	public void addClient(ClientPojo object) throws SQLException {
		logger.info("Start Saved addClient from Facade Implementation");
		Client client = (Client) ObjectBuilder.convertObject(ClientPojo.class, Client.class, object);
		clientDao.save(client);
		object.setId(client.getId());
		logger.info("End saved addClient from Facade Implementation");
	}
	
	@Transactional
	public List<ClientPojo> getAllClients() throws SQLException {
		logger.info("Start getAllClients from Facade Implementation");
		List<ClientPojo> result = new ArrayList<ClientPojo>();
		logger.info("Start call-> clientDao.findAll");
		List<Client> resultClients = (List<Client>) clientDao.findAll();
		logger.info("End call-> clientDao.findAll");
		for (Client client : resultClients) {
			result.add((ClientPojo) ObjectBuilder.convertObject(Client.class, ClientPojo.class, client));
		}
		return result;
	}

	@Override
	public List<ClientPojo> getClientBySharedKey(String sharedKey) throws SQLException {
		logger.info("Start getClientBySharedKey from Facade Implementation");
		List<ClientPojo> result = new ArrayList<ClientPojo>();
		logger.info("Start call-> clientDao.findBySharedKeyIgnoreCaseContaining");
		List<Client> resultClients = (List<Client>) clientDao.findBySharedKeyIgnoreCaseContaining(sharedKey);
		logger.info("End call-> clientDao.findBySharedKeyIgnoreCaseContaining");
		for (Client client : resultClients) {
			result.add((ClientPojo) ObjectBuilder.convertObject(Client.class, ClientPojo.class, client));
		}
		return result;
	}
	
	@Override
	public ClientPojo findClientByEmail(String email) throws SQLException {
		logger.info("Start findClientByEmail from Facade Implementation");
		logger.info("Start call-> clientDao.findByEmail");
		Client client = (Client) clientDao.findByEmail(email);
		logger.info("End call-> clientDao.findByEmail");
		return (ClientPojo) ObjectBuilder.convertObject(Client.class, ClientPojo.class, client);
	}
}