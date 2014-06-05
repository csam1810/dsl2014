package demo.view

import static controller.App.*

class DetailView {
	static def init() {
		builder.view(id: 'DetailView', padding: 10) {
			matrixGrid(rows: 3, cols: 3) {
				button(id: 'Back', text: 'Back', row: 1, col: 2)
				button(id: 'TestButton2', text: 'TestButton2', row: 2, col: 2)
				button(id: 'TestButton3', text: 'TestButton3', row: 0, col: 0)
			}
		}
	}
}
