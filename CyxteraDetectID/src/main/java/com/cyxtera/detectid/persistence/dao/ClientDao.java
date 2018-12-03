package com.cyxtera.detectid.persistence.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cyxtera.detectid.persistence.entity.Client;

/**
 * @author esalcedo
*/

@Repository
public interface ClientDao extends CrudRepository<Client, Long> {
	
	/**
	 * 
	 * @param sharedKey
	 * @return
	 * @throws SQLException
	 */
	List<Client> findBySharedKeyIgnoreCaseContaining(String sharedKey) throws SQLException;
	
	/**
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	Client findByEmail(String email) throws SQLException;
}
