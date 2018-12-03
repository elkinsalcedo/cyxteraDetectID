
package com.cyxtera.detectid.persistence.facade;

import java.sql.SQLException;
import java.util.List;
import com.cyxtera.detectid.persistence.pojo.ClientPojo;

/**
 * @author esalcedo
 *
 */
public interface ClientFacade {
	
	/**
	 * 
	 * @param clientPojo
	 * @throws SQLException
	 */
	void addClient(ClientPojo clientPojo) throws SQLException;
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<ClientPojo> getAllClients() throws SQLException;
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<ClientPojo> getClientBySharedKey(String sharedKey) throws SQLException;
	
	/**
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	ClientPojo findClientByEmail(String email) throws SQLException;
}