package demo
import static controller.App.*

	builder.view(id: 'MatrixTestView', padding: 10) {
			matrixGrid(rows: 3, cols: 3) {
				button(id: 'TestButton1', text: 'TestButton1', row: 1, col: 2)
				button(id: 'TestButton2', text: 'TestButton2', row: 2, col: 2)
				button(id: 'TestButton3', text: 'TestButton3', row: 0, col: 0)
			}
	}
	
	init('MatrixTestView')

