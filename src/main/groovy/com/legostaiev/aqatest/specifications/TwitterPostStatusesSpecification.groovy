package com.legostaiev.aqatest.specifications

import static com.legostaiev.aqatest.utils.StatusUtils.getUniqueStatusMessage

import spock.lang.Shared
import twitter4j.Status
import twitter4j.TwitterException

class TwitterPostStatusesSpecification extends TwitterConfigSetupSpecification {
	
	def "should remove status by id"() {
		given: "I update status"
		def createdStatus = twitter.updateStatus(getUniqueStatusMessage());
		
		when: "I remove created status by id"
		def destroyedStatus = twitter.destroyStatus(createdStatus.getId());

		then: "destroyed status is returned"
		destroyedStatus == createdStatus;

		when: "I get status by id which is already removed"
		twitter.showStatus(createdStatus.getId());

		then: "TwitterException is thrown"
		def e = thrown(TwitterException.class);

		and: "StatusCode is 404"
		e.getStatusCode() == 404;
	}

	def "should update status text"() {
		def statusMessage = getUniqueStatusMessage();
		
		when: "I update status"
		def createdStatus = twitter.updateStatus(statusMessage);
		
		then: "Text in returned status is equal to given text with which status has been created"
		statusMessage == createdStatus.getText();
		
		when: "I get status by ID which has been returned"
		def actualCreatedStatus = twitter.showStatus(createdStatus.getId());
		
		then: "Returned status is equal to status that has been returned during creation"
		actualCreatedStatus == createdStatus;
		
		cleanup: "Remove creted status"
		twitter.destroyStatus(createdStatus.getId());
	}

	def "should return 403 in case of tweet duplication"() {
		def statusMessage = getUniqueStatusMessage();
		
		given: "I update status"
		def createdStatus = twitter.updateStatus(statusMessage);
		
		when: "I update status with the same text second time in a row"
		twitter.updateStatus(statusMessage);

		then: "TwitterException is thrown"
		def e = thrown(TwitterException.class);
		
		and: "StatusCode is 403"
		e.getStatusCode() == 403;
		
		cleanup: "Remove creted status"
		twitter.destroyStatus(createdStatus.getId());
	}

}
