package models

import play.api.Play.current
import play.api.Play
import java.net.MalformedURLException

case class Project(name: String, title: String, summary: String, description : String, history: String)

object Project {
  def all() : List[Project] = List(
    "rus-etrain"
  ).map(name => find(name).get)

  def find(name: String) :Option[Project] = try {
    val project = xml.XML.load(Play.classloader.getResourceAsStream(s"projects/$name.xml"))
    Some(
      Project(name,
        (project \ "title").text,
        (project \ "summary").text,
        (project \ "description").text,
        (project \ "history").text)
    )
  } catch {
    case ex: MalformedURLException => None
  }
}
