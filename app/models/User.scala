package models

import anorm._
import anorm.SqlParser._

import play.api.db._
import play.api.Play.current
import java.security.MessageDigest
import java.nio.ByteBuffer
import org.apache.commons.codec.binary.Hex

object User {
  def authenticate(name: String, password: String) : Boolean = DB.withConnection {implicit c =>
    println(name)
    println(password)
    val digest = new String(Hex.encodeHex(MessageDigest.getInstance("MD5").digest(password.getBytes)))
    println(digest)

    SQL("select 1 from users where name={name} and password_digest={password_digest}")
      .on("name" -> name, "password_digest" -> digest)
      .as(scalar[Long].singleOpt).isDefined
  }
}