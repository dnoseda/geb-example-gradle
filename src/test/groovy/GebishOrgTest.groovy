import geb.junit4.GebReportingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.saucelabs.junit.Parallelized;
import org.junit.runners.Parameterized.Parameters

@RunWith(Parallelized.class)
class GebishOrgTest extends GebReportingTest {
	
	

	def toSearch = null
	
	@Parameters
	static data(){
		def dataList = []
		["dulce","tortita"].each{ toQuery ->
			dataList << [toQuery] as Object[]
		}
		return dataList
	}
	
	public GebishOrgTest(String toQuery){
		toSearch = toQuery
	}
	
	@Test
	void canGetToTheCurrentBookOfGeb() {
		to GooglePage

		//hover over to expand the menu
		query = "${toSearch}\n"
		 
	}
}