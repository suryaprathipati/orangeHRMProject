package com.orangeHRM.orangeHRM;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "./src/test/java/resources",
		glue="com.orangeHRM.orangeHRM",
		monochrome =true,
		plugin={"pretty","html:target/Cucumber"})
public class TestRunner {

}
