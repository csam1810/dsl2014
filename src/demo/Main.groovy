package demo

import static controller.App.*

import groovy.sql.Sql
import model.Model
import demo.controller.*
import demo.view.*


/**
 * Demo Implementation with demoDB, user = demoUser and password = password
 * Authors: Mathias Hoertnagl, Alexandra Jaeger, Carmen Vierthaler
 */
model = new Model(
	"jdbc:mysql://localhost:3306/demoDB",
	"demoUser",
	"password",
	"com.mysql.jdbc.Driver"
)


//defined views and their controller have to be initialized
StartView.init()
StartController.init()

DetailView.init()
DetailController.init()

NewView.init()
NewController.init()

//start with the first view in the demo application 
navigate "StartView"