package demo.view

import static controller.App.*

/**
 * Defined view for demo application
 * View shows detailed information to a person and has a back button
 */
class DetailView {
	static def init() {
		builder.view(id: 'DetailView', padding: 10) {
			matrixGrid(rows: 4, cols: 2) {
				label(text: 'Last Name:', row: 0, col: 0)
				text(id: 'LastNameText', row: 0, col: 1)
				
				label(text: 'First Name:', row: 1, col: 0)
				text(id: 'FirstNameText', row: 1, col: 1)
				
				label(text: 'Birthday:', row: 2, col: 0)
				text(id: 'BirthdayText', row: 2, col: 1)
				
				button(id: 'Back', text: 'Back', row: 3, col: 0)
				button(id: 'Save', text: 'Save', row: 3, col: 1)
			}
		}
	}
}
