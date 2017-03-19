package com.legostaiev.aqatest.specifications

import spock.lang.Shared
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class TwitterConfigSetupSpecification extends BaseConfigSetupSpecification {
	
	@Shared
	private Twitter twitter;
	
	@Override
	def setupSpec() {
		ConfigurationBuilder twitterConfiguration = new ConfigurationBuilder();

		twitterConfiguration.setDebugEnabled(true)
				.setOAuthConsumerKey(config.oAuthConsumerKey)
				.setOAuthConsumerSecret(config.oAuthConsumerSecret)
				.setOAuthAccessToken(config.oAuthAccessToken)
				.setOAuthAccessTokenSecret(config.oAuthAccessTokenSecret);

		TwitterFactory twitterFactory = new TwitterFactory(twitterConfiguration.build());
		twitter = twitterFactory.getInstance();
	}

}
