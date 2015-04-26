package geb.junit4

import org.junit.Before
import org.junit.After
import org.junit.Rule
import org.junit.rules.TestName

import checkout.CheckoutGebTest
import geb.report.ReporterSupport

class GebReportingTest extends CheckoutGebTest {

	static private testCounters = [:]
	static private testCleanFlags = [:]
	private instanceTestCounter = 1

	@Rule
	public TestName gebReportingTestTestName = new TestName()

	void report(String label) {
		browser.report(ReporterSupport.toTestReportLabel(getTestCounterValue(), instanceTestCounter++, gebReportingTestTestName.methodName, label))
	}

	@Before
	void setupReporting() {
		reportGroup getClass()
		incrementTestCounterValue()

		// We need to clean the inner reports dir just once for this class so we have to
		// use this static tracking data to see if we are about to run the first test.
		def key = getKeyNameForTracking()
		if (!testCleanFlags.containsKey(key)) {
			testCleanFlags[key] = true
			cleanReportGroupDir()
		}
	}

	@After
	void writeGebReport() {
		report "end"
	}

	private incrementTestCounterValue() {
		def key = getKeyNameForTracking()
		if (testCounters.containsKey(key)) {
			testCounters[key] = ++testCounters[key]
		} else {
			testCounters[key] = 1
		}
	}

	private getTestCounterValue() {
		testCounters[getKeyNameForTracking()] ?: 1
	}

	private getKeyNameForTracking() {
		getClass().name
	}

}