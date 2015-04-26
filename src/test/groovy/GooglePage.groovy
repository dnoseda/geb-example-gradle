import geb.Page;


class GooglePage extends Page {

	static url = "http://www.google.com"

	static content = {
		query { $("input[name=q]") }
	}
}