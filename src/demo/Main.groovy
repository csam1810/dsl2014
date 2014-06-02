package demo

import static controller.App.*

import groovy.sql.Sql
import model.Model
import demo.controller.StartController
import demo.view.StartView

/**
 * Agenda
 * 
 *   o Noch mehr layouts und componenten zum FormBuilder hinzufuegen.
 *     - JList
 *     - JComboBox
 *     - BorderLayout    Problem mit NORTH WEST SOUTH EAST.
 *     - FlowLayout      Unnoetig da auch mit hbox realisierbar?
 *     - Matrix Layout   Hier haben wir das selbe Problem wie mit BorderLayout oder JTabbedPane.
 *     Dann gaebs noch diese Panes zur Auswahl die Sinn machen wuerden
 *     - JTabbedPane
 *     - JScrollPane     Bis jetzt nur fuer JTable evtl unnoetig fuer andere anwendungen.
 *     - JSplitPane
 *     weiss nicht, ob wir die auch angenehm in unseren builder integrieren koennen.
 *     
 *   o Navigator implementieren um zwischen Panels navigieren zu koennen.
 *     - statische methode goto(String id) um zu anderer view zu wechseln.
 *     Evtl noch:
 *     - statische methode back() um zur vorherigen Seite zu wechseln.
 *       - Dafuer sollte der Navigator einen stack von ids unterhalten.
 *   
 *   o InitListener der ausgefuehrt wird wenn eine neu View angezeigt werden soll.    
 *       
 *   o Wenn view Komponenten halbwegs fix ausgewaehlt wurden koennten wir auch
 *     schon mit de Entwicklung einer Demo-App beginnen.
 *     
 * Probleme
 *     
 *   o Zur Zeit alles im mainframe. Evtl. auch in neuen Frame ladbar?
 *   o Probleme mit der Integration von BorderLayout, MatrixLayout
 */

// TODO: Datenbank-Verbindung schoener gestalten.
model = new Model(Sql.newInstance(
	"jdbc:mysql://localhost:3306/demoDB",
	"demoUser",
	"password",
	"com.mysql.jdbc.Driver"
))

StartView.init()
StartController.init()

init "StartView"