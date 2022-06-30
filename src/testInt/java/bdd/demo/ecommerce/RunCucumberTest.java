package bdd.demo.ecommerce;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("bdd/demo/ecommerce")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "bdd.demo.ecommerce")
public class RunCucumberTest {
}