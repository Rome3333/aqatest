package com.legostaiev.aqatest.specifications

import spock.lang.Shared
import spock.lang.Specification

class BaseConfigSetupSpecification extends Specification {

	private static final String PATH_TO_CONFIG_PROPERTIES = './conf/config.properties'
	
	@Shared
	private def config
	
	def setupSpec() {
		config = new ConfigSlurper().parse(new File(PATH_TO_CONFIG_PROPERTIES).toURI().toURL());
	}

}
