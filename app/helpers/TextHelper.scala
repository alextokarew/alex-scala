package helpers

import org.clapper.markwrap._

object TextHelper {
  val textileParser = MarkWrap.parserFor(MarkupType.Textile)

  def textile(text: String) : String = textileParser.parseToHTML(text)
}
