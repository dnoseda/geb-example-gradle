import geb.Browser;
import geb.junit4.GebReportingTest

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.saucelabs.junit.Parallelized;

import org.junit.runners.Parameterized.Parameters
import org.codehaus.groovy.runtime.StackTraceUtils
import org.apache.commons.lang.exception.ExceptionUtils

@RunWith(Parallelized.class)
class GebishOrgTest extends GebReportingTest {

	static def logException(def msg){
		println "desde donde llamo ${msg}"
		println ExceptionUtils.getFullStackTrace(StackTraceUtils.deepSanitize(new Exception()))
		println "desde donde llamo termina ${msg}"
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