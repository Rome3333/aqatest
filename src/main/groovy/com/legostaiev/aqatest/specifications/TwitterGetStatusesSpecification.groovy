package com.legostaiev.aqatest.specifications

import static com.legostaiev.aqatest.utils.StatusUtils.getUniqueStatusMessage

import spock.lang.Shared

class TwitterGetStatusesSpecification extends TwitterConfigSetupSpecification {

	@Shared
	private def createdStatus

	@Shared
	private def createdStatusId;
	
	@Shared
	private def statusMessage

	@Override
	def setupSpec() {
		statusMessage = getUniqueStatusMessage()
		createdStatus = twitter.updateStatus(statusMessage);
		createdStatusId = createdStatus.getId();
	}

	def cleanupSpec() {
		twitter.destroyStatus(createdStatusId);
	}

	def "should return date of creation for given status id"() {
		when: "I get created_at for given status id"
		def actualCreatedAt = twitter.showStatus(createdStatusId).getCreatedAt();

		then: "returned created_at date is equal to the date when status has been created"
		actualCreatedAt == createdStatus.getCreatedAt();
	}

	def "should return retweet count for given status id"() {
		when: "I get retweet_count for newly created status with given id"
		def actualRetweetCount = twitter.showStatus(createdStatusId).getRetweetCount();

		then: "returned retweet_count is equal to 0"
		actualRetweetCount == 0;
	}

	def "should return text of status with given id"() {
		when: "I get text for given status id"
		def actualStatusText = twitter.showStatus(createdStatusId).getText();

		then: "returned text is equal to text with which status has been created"
		actualStatusText == statusMessage;
	}

}
