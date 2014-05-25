import static view.View.*
import static view.View.Widgets.*
import view.FormBuilder
import view.ViewComponent
//import static view.ViewComponent.LayoutEnum.*

/*
view "StartView" grid (3, 2)

	add "ContinueButton" type GButton to "StartView"
	add "ExitButton" type GButton to "StartView"
	
	// My first controller :)
	on "StartView" button "ContinueButton" click { ViewComponent view ->
		
		println "Continue Button clicked!"
	}
	
	// My second controller :)
	on "StartView" button "ExitButton" click { ViewComponent view ->
		
		println "Exit Button clicked!"
	}
*/

builder.view(id: 'StartView') {
	grid(rows: 3, cols: 2) {
		button(id: 'ContinueButton', text: 'Continue')
		button(id: 'ExitButton',     text: 'Exit')
		button(id: 'AButton',        text: 'A Button')
		button(id: 'BButton',        text: 'B Button')
	}
}
//println b.log

// My first controller :)
on 'StartView' button 'ContinueButton' click { ViewComponent view ->
	
	println "Continue Button clicked!"
}

// My second controller :)
on 'StartView' button 'ExitButton' click { ViewComponent view ->
	
	println "Exit Button clicked!"
}

init "StartView"

//class SpoofBuilder extends BuilderSupport{
//	def log = []
//	protected void setParent(Object parent, Object child){
//		log << "sp"
//		log << parent
//		log << child
//	}
//	protected Object createNode(Object name){
//		log << 'cn1'
//		log <<  name
//		return 'rcn1'
//	}
//	protected Object createNode(Object name, Object value){
//		log << 'cn2'
//		log << name
//		log << value
//		return 'rcn2'
//	}
//	protected Object createNode(Object name, Map attributes){
//		log << 'cn3'
//		log << name
//		attributes.each{entry -> log << entry.key; log << entry.value}
//		return 'rcn3'
//	}
//	protected Object createNode(Object name, Map attributes, Object value){
//		log << 'cn4'
//		log << name
//		attributes.each{entry -> log << entry.key; log << entry.value}
//		log << value
//		return 'rcn4'
//	}
//	protected void nodeCompleted(Object parent, Object node) {
//		log << 'nc'
//		log << parent
//		log << node
//	}
//}
//
//def b = new SpoofBuilder()
//println b.log
//
//b = new SpoofBuilder()
//b.sau()
//println b.log
//
//b = new SpoofBuilder()
//b.fock(name: 'hans')
//println b.log
//
//b = new SpoofBuilder()
//b.fock() {
//	sau()
//	hur()
//}
//println b.log