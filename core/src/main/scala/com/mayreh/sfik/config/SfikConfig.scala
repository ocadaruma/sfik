package com.mayreh.sfik.config

import java.io.{File, FileReader, Reader, StringReader}

import cats.syntax.either._
import com.mayreh.sfik.artifact.Artifact
import io.circe._
import io.circe.generic.auto._

case class SfikConfig(mainClassName: String,
                      artifact: Artifact)

object SfikConfig {
  def load(file: File): SfikConfig = load(new FileReader(file))

  def load(yaml: String): SfikConfig = load(new StringReader(yaml))

  private[this] def load(reader: Reader): SfikConfig = {
    yaml.parser.parse(reader)
      .leftMap(err => err: Error)
      .flatMap(_.as[SfikConfig])
      .valueOr(throw _)
  }
}
