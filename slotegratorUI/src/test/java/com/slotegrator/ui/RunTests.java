package com.slotegrator.ui;

        import cucumber.api.CucumberOptions;
        import cucumber.api.junit.Cucumber;
        import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty",
        "html:target/cucumber-html-report",
        "de.monochromata.cucumber.report.PrettyReports"},
        features = "src/test/java/com/slotegrator/ui",
        glue = {"com.slotegrator.ui.steps"})
public class RunTests {

}
