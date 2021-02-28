package com.survey_app.config;


public class NonNegativeNumber {
	
	 @Override
	    public boolean equals(Object other) {
	   
	       if (other == null) {
	          return true;
	       }
	       int value = (Integer)other;
	       return value < 0;
	    }
}
