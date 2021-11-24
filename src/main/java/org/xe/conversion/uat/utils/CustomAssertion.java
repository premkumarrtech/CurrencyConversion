package org.xe.conversion.uat.utils;

import java.util.List;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Lists;

import com.aventstack.extentreports.Status;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import io.qameta.allure.AllureConstants;


@SuppressWarnings("unused")
public class CustomAssertion extends Assertion {
	  
	  	//private List<String> assert_messages = Lists.newArrayList();
	   
	    @Override
	    public void onBeforeAssert(IAssert<?> assertCommand) {
	      //assert_messages.add("BeforeAssert:" + assertCommand.getMessage());
	    }
	    
	    @Override
	    public void onAfterAssert(IAssert<?> assertCommand) {
	      //assert_messages.add("AfterAssert:" + assertCommand.getMessage());
	    }
	    
	    @Override
	    public void onAssertSuccess(IAssert<?> assertCommand) {
	      //assert_messages.add("OnlyOnAssertSuccess:" + assertCommand.getMessage());
	    	try{
	    		ExtentTestManager.getTest().log(Status.PASS, "Assertion success: " + assertCommand.getMessage() + " --> Expected is [" + assertCommand.getExpected().toString() + "] and Actual is [" + assertCommand.getExpected().toString() + "]");
	    		allureSaveTextLog("Assertion success: " + assertCommand.getMessage() + " --> Expected is [" + assertCommand.getExpected().toString() + "] and Actual is [" + assertCommand.getExpected().toString() + "]");
	    	}
	    	catch(Exception e) {}
	    }
	    
	    @Override
	    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
	    	// assert_messages.add("OnlyOnAssertFailure:" + assertCommand.getMessage());
	    	try{	    		
	    		ExtentTestManager.getTest().log(Status.FAIL, "Assertion failed: " + assertCommand.getMessage() + " --> Expected is [" + assertCommand.getExpected().toString() + "] and Actual is [" + assertCommand.getExpected().toString() + "]");	    		
	    		allureSaveTextLog("Assertion failed: " + assertCommand.getMessage() + " --> Expected is [" + assertCommand.getExpected().toString() + "] and Actual is [" + assertCommand.getExpected().toString() + "]");		    	
	    		Allure.getLifecycle().updateStep(s -> s.setStatus(io.qameta.allure.model.Status.FAILED));
	    		Allure.getLifecycle().stopStep();
	    	}
	    	catch(Exception e) {}
	    }
	   
	    public List<String> getAssertMessages() {	    	
			return null;
	    	//return assert_messages;
	    }
	    
		@Attachment(value = "{0}", type = "text/plain")
		public static String allureSaveTextLog(String message) {
			return message;
		}
	}

	
/*	 @Override
	  public void onAfterAssert(IAssert<?> assertCommand) {
		 ExtentTestManager.getTest().log(Status.PASS, "Assertion success: " + assertCommand.getMessage());
	 }

	@Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
		ExtentTestManager.getTest().log(Status.PASS, "Assertion success: " + assertCommand.getMessage());
	}
}*/