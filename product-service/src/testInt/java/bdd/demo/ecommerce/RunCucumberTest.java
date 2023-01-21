package bdd.demo.ecommerce;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@SuiteDisplayName("Product Service - BDD Integration Tests")
@IncludeEngines("cucumber")
@SelectClasspathResource("bdd/demo/ecommerce")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "bdd.demo.ecommerce")
public class RunCucumberTest {
}