package demo.view

import static controller.App.*

class StartView {
	
	static def init() {

		builder.view(id: 'StartView', padding: 10) {
			vbox() {
				table(id: 'PersonTable')
				hbox() {
					button(id: 'DetailButton', text: 'Details')
					button(id: 'ExitButton', text: 'Exit')
				}
			}
		}
	}
}
