package demo.view

import static controller.App.*

/**
 * Defined view for demo application
 * View shows data from table PersonTable with buttons DetailButton and ExitButton
 */
class StartView {
	
	static def init() {

		builder.view(id: 'StartView', padding: 10) {
			vbox() {
				table(id: 'PersonTable')
			}
		}
	}
}
