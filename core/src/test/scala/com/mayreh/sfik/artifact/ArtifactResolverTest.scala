package com.mayreh.sfik.artifact

import org.scalatest.flatspec.AnyFlatSpec

class ArtifactResolverTest extends AnyFlatSpec {
  it should "download Kafka artifact" in {
    val file = new ArtifactResolver().resolve(
      Artifact.Maven("org.apache.kafka", "kafka_2.12", "2.4.1"))

    println(file)
  }
}
