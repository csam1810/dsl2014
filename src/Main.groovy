import static view.View.*
import static view.View.Widgets.*
//import static view.ViewComponent.LayoutEnum.*

view "StartView" grid (3, 2)

add "ContinueButton" type Widgets.GButton to "StartView" onPos (1,1)
add "ExitButton" type Widgets.GButton to "StartView" onPos (1,2)

// My first controller :)
on "StartView" button "ContinueButton" click { ->
	
	println "Continue Button clicked!"
}

// My second controller :)
on "StartView" button "ExitButton" click { ->
	
	println "Exit Button clicked!"
}

init "StartView"