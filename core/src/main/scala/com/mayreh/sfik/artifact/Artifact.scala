package com.mayreh.sfik.artifact

import cats.syntax.functor._
import io.circe.Decoder
import io.circe.generic.auto._

sealed abstract class Artifact

object Artifact {
  implicit val decoder: Decoder[Artifact] = List[Decoder[Artifact]](
    Decoder[Maven].widen
  ).reduceLeft(_ or _)

  case class Maven(groupId: String,
                   artifactId: String,
                   version: String) extends Artifact
}
