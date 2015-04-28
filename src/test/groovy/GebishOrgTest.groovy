

import geb.Browser;
import geb.Configuration;
import geb.junit4.GebReportingTest
import geb.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.saucelabs.junit.Parallelized;

import org.junit.runners.Parameterized.Parameters
import org.openqa.selenium.chrome.ChromeDriver
import org.codehaus.groovy.runtime.StackTraceUtils
import org.apache.commons.lang.exception.ExceptionUtils

@RunWith(Parallelized.class)
class GebishOrgTest extends GebReportingTest {

	static def logException(def msg){
		println "desde donde llamo ${msg}"
		println ExceptionUtils.getFullStackTrace(StackTraceUtils.deepSanitize(new Exception()))
		println "desde donde llamo termina ${msg}"
	}



	def  threadId = Thread.currentThread().getId()
	Configuration createConf() {
		println "Conf start ${threadId}"
		Configuration conf  = new ConfigurationLoader(gebConfEnv, System.properties, new GroovyClassLoader(getClass().classLoader)).getConf(gebConfScript)

		conf.reportsDir = new File("build/reports/test/testReportFor-${threadId}")
		
		conf.cacheDriverPerThread = true
		println "Conf END with ${conf} ${threadId}"
		return conf
	}

	def toSearch = null

	@Parameters
	static data(){
		def dataList = []
		["dulce", "tortita"].each{ toQuery ->
			dataList << ([toQuery] as Object[])
		}


		return dataList
	}

	public GebishOrgTest(String toQuery){
		toSearch = toQuery
	}

	@Test
	void canGetToTheCurrentBookOfGeb() {
		to GooglePage

		println "buscando ${threadId} '${toSearch}'"
		//hover over to expand the menu
		query = "${toSearch}\n"

		println "buscando ${threadId} '${toSearch}' Termino!"
	}
}