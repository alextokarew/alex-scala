package helpers

import org.clapper.markwrap._
import java.util.{Locale, Date}
import java.text.{DateFormatSymbols, SimpleDateFormat}

object TextHelper {
  val textileParser = MarkWrap.parserFor(MarkupType.Textile)

  def textile(text: String) : String = textileParser.parseToHTML(text)

  def formatDate(date: Date) = new SimpleDateFormat("dd MMMMM yyyy, HH:mm", dateFormatSymbols).format(date)

  val dateFormatSymbols = new DateFormatSymbols(new Locale("ru")) {
    override def getMonths: Array[String] = Array("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
      "августа", "сентября", "октября", "ноября", "декабря")
  }
}
