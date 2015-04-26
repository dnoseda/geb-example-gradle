import geb.Browser;
import geb.junit4.GebReportingTest

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.saucelabs.junit.Parallelized;

import org.junit.runners.Parameterized.Parameters

@RunWith(Parallelized.class)
class GebishOrgTest extends GebReportingTest {

	Browser createBrowser() {

		println "creando browser"
		def conf = new geb.Configuration()
		File reportsDir = new File("build/reports/test/testReportFor-${Thread.currentThread().getId()}")
		conf.driver = driver
		conf.reportsDir = reportsDir
		new geb.Browser(conf)
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

		println "buscando '${toSearch}'"
		//hover over to expand the menu
		query = "${toSearch}\n"

		println "buscando '${toSearch}' Termino!"
	}
}