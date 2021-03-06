package com.cafe24.devbit004.pop.social.config.support;

import com.cafe24.devbit004.pop.social.api.Cafe24;
import com.cafe24.devbit004.pop.social.connect.Cafe24OAuth2Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 *
 * Interface defining the operations required for an API helper.
 * An API helper will be used to either fetch or intantiate an instance of the API binding class.
 */
public class Cafe24ApiHelper implements ApiHelper<Cafe24> {
	
	private final Logger logger = LoggerFactory.getLogger(Cafe24ApiHelper.class);
	private final UsersConnectionRepository usersConnectionRepository;

	private final UserIdSource userIdSource;

	public Cafe24ApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
		logger.info("Cafe24ApiHelper created");
		this.usersConnectionRepository = usersConnectionRepository;
		this.userIdSource = userIdSource;
		logger.info("Cafe24ApiHelper userIdSource: " + userIdSource.getUserId());

	}

	@Override
	public Cafe24 getApi() {
		logger.info("getApi() called...");
		if (logger.isDebugEnabled()) {
			logger.debug("Getting API binding instance for Cafe24");
		}
		// TODO Auto-generated method stub
		logger.info("getApi() userIdSource.getUserId(): " + userIdSource.getUserId());

		String mallId = Cafe24OAuth2Template.getMallId();
		logger.info("getApi mallId: " + mallId);
		Connection<Cafe24> connection = usersConnectionRepository
										.createConnectionRepository(mallId)
										.findPrimaryConnection(Cafe24.class);
		if (logger.isDebugEnabled() && connection == null) {
			logger.debug("No current connection. Returning default Cafe24Template instance.");
		}

		if (connection != null) {
			logger.info("getApi is not null");
		} else {
			logger.info("getApi is null");

		}
		return connection != null ? connection.getApi() : null;
	}

}
