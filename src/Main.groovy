import static view.View.*
import view.ViewComponent

/**
 * Agenda
 * 
 *   o Noch mehr layouts und componenten zum FormBuilder hinzufuegen.
 *     - JTable waere cool
 *     - JList
 *     - JComboBox
 *     - BorderLayout
 *     - FlowLayout
 *     - Matrix Layout (von Alex)
 *     Dann gaebs noch diese Panes zur Auswahl die Sinn machen wuerden
 *     - JTabbedPane
 *     - JScrollPane
 *     - JSplitPane
 *     weiss nicht, ob wir die auch angenehm in unseren builder integrieren koennen.
 *     
 *   o Navigator implementieren um zwischen Panels navigieren zu koennen.
 *     - statische methode goto(String id) um zu anderer view zu wechseln.
 *     Evtl noch:
 *     - statische methode back() um zur vorherigen Seite zu wechseln.
 *       - Dafuer sollte der Navigator einen stack von ids unterhalten.
 *       
 *   o Wenn view Komponenten halbwegs fix ausgewaehlt wurden koennten wir auch
 *     schon mit de Entwicklung einer Demo-App beginnen.
 *     
 * Probleme
 * 
 *   o Zur Zeit steht view und controller code in einer grossen Main datei.
 *     Habe keine Ahnung, ob wir das aendern koennen.
 */

// Durch den statischen Import von view.View.* muesste man
// eigentlich nicht mehr View.builder schreiben.
builder.view(id: 'StartView', padding: 10) {
	vbox() {
		text(id: 'Text1')
		grid(rows: 3, cols: 2) {
			label (id: 'MyLabel',        text: 'Some text')
			button(id: 'ContinueButton', text: 'Continue')
			button(id: 'ExitButton',     text: 'Exit')
		}
	}
}

// My first controller :)
on 'StartView' button 'ContinueButton' click { ViewComponent view ->
	
	// Auf elemente der view zugreifen sollte spaeter mal so aussehen.
	// Dazu muss man ViewComponent.getAt(String id) implementieren.
	// view['MyLabel'].setText('Neuer label text:')
	
	println "Continue Button clicked!"
}

// My second controller :)
on 'StartView' button 'ExitButton' click { ViewComponent view ->
	
	println "Exit Button clicked!"
}

// Hier das selbe kein View.init noetig.
init "StartView"