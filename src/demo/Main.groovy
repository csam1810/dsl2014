package demo

import static controller.App.*

import groovy.sql.Sql
import model.Model
import demo.controller.*
import demo.view.*


// TODO: Datenbank-Verbindung schoener gestalten.
model = new Model(Sql.newInstance(
	"jdbc:mysql://localhost:3306/demoDB",
	"demoUser",
	"password",
	"com.mysql.jdbc.Driver"
))

StartView.init()
StartController.init()

DetailView.init()
DetailViewController.init()

navigate "StartView"