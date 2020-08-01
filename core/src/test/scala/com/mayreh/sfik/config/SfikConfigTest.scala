package com.mayreh.sfik.config

import com.mayreh.sfik.artifact.Artifact
import org.scalatest.flatspec.AnyFlatSpec

class SfikConfigTest extends AnyFlatSpec {
  it should "load config from YAML" in {
    val config = SfikConfig.load(
      """mainClassName: kafka.Kafka
        |artifact:
        |  groupId: org.apache.kafka
        |  artifactId: kafka_2.12
        |  version: 2.4.1
        |""".stripMargin)

    assert(config.mainClassName == "kafka.Kafka")
    assert(config.artifact == Artifact.Maven("org.apache.kafka", "kafka_2.12", "2.4.1"))
  }
}
