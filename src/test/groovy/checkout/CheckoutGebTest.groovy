package checkout;

import geb.Browser;
import geb.Configuration;
import geb.ConfigurationLoader;
import org.junit.After;

class CheckoutGebTest {

	String gebConfEnv = null
	String gebConfScript = null

	private Browser browser

	def  threadId = Thread.currentThread().getId()
	Configuration createConf() {
		println "Conf start"
		Configuration conf  = new ConfigurationLoader(gebConfEnv, System.properties, new GroovyClassLoader(getClass().classLoader)).getConf(gebConfScript)
		
		conf.reportsDir = new File("build/reports/test/testReportFor-${threadId}")
		println "Conf END with ${conf}" 
		return conf		
	}

	Browser createBrowser() {
		println "Create START"
		def bro = new Browser(createConf())
		println "Create END"
		return bro
	}

	Browser getBrowser() {
		if (browser == null) {
			browser = createBrowser()
		}
		browser
	}

	@After
	void resetBrowser() {
		if (browser?.config?.autoClearCookies) {
			browser.clearCookiesQuietly()
		}
		browser = null
	}

	def methodMissing(String name, args) {
		getBrowser()."$name"(*args)
	}

	def propertyMissing(String name) {
		getBrowser()."$name"
	}

	def propertyMissing(String name, value) {
		getBrowser()."$name" = value
	}
}