/*
 * Filename: ScenarioTesting.java
 * Description: This file contains the JUnit tests for the LogIn User Story
 */

package com.example.googlemapdemo.test;

import android.test.ActivityInstrumentationTestCase2;


import com.example.googlemapdemo.LoginActivity;
import com.robotium.solo.Solo;

public class ScenarioTesting extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	//Initializing Robotium solo object
	private Solo solo;
	
	//Constructor
	public ScenarioTesting() {
		super(LoginActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}


	//Given that the Place-Its app is turned on
	public void testCreateAccount() {
		
		//When I press the register button
		solo.clickOnButton("Register");
		
		//And the user inputs a valid username and password
		solo.clickOnText("UserName");
		solo.enterText(0, "User1");
		solo.clickOnText("Password");
		solo.enterText(1, "Password1");
		solo.clickOnText("Renter Password");
		solo.enterText(2, "Password1");
		
		//Then an account will be created for me to be able to log into in the future
		solo.clickOnButton("Create Account");
		solo.assertCurrentActivity("Check on first activity", LoginActivity.class);
	}
	
	
	//Given that the Place-Its app is turned on
	public void testLogin() {
		
		//When the user inputs a valid existing username
		solo.clickOnText("UserName");
		solo.enterText(0, "User1");
		
		//And password
		solo.clickOnText("Password");
		solo.enterText(1, "Password1");
		
		//Then the Place-Its app will show all of the place-its pertaining to that account
		solo.clickOnButton("Sign In");
		solo.assertCurrentActivity("Check on first activity", LoginActivity.class);
		
	}
	
	public void testCancelButton() {
		
		//Given that I am creating in "Create Account"
		solo.clickOnButton("Register");
		
		//When I press the "Cancel" option
		solo.clickOnButton("Cancel");
		
		//Then the screen should revert to "Log In" screen
		solo.assertCurrentActivity("Check on first activity", LoginActivity.class);
	}
	


	//Given that I am in the “Log In” screen
	public void testInvalidAccount() {
		
		//When I enter invalid username: TestAccountX
		solo.clickOnText("UserName");
		solo.enterText(0, "TestAccountX");
		
		//And invalid password: TestPasswordX
		solo.clickOnText("Password");
		solo.enterText(1, "TestPasswordX");
		solo.clickOnButton("Sign In");
		
		//Then I should receive a message "This password is incorrect
		solo.clickOnText("This password is incorrect");
		solo.assertCurrentActivity("Check on first activity", LoginActivity.class);
	}
}
