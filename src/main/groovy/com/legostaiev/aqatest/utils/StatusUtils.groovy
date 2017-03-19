package com.legostaiev.aqatest.utils

class StatusUtils {
	
	private static final String DEFAULT_STATUS_MESSAGE = "Default status message"
	
	public static def getUniqueStatusMessage() {
		DEFAULT_STATUS_MESSAGE + System.currentTimeMillis()
	}
	
}
