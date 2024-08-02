package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/Features",
        glue = "StepDef",
        tags = "@Smoke",
        plugin = { "pretty",
                "html:target/cucumber-reports/dsalgo.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        dryRun=false,
        monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests {

}
