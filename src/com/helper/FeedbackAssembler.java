package com.helper;

import com.beans.FeedbackBean;

public class FeedbackAssembler {

public static FeedbackBean getInstance(String feedback) {
		
		
		FeedbackBean feed = new FeedbackBean();
		feed.setFeedback_content(feedback);
		
		return feed;
		
	}
	
}
