package com.mayreh.sfik.artifact

import java.io.File
import java.nio.file.Files

import org.apache.maven.repository.internal.MavenRepositorySystemUtils
import org.eclipse.aether.RepositorySystem
import org.eclipse.aether.artifact.DefaultArtifact
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory
import org.eclipse.aether.repository.{LocalRepository, RemoteRepository}
import org.eclipse.aether.resolution.ArtifactRequest
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory
import org.eclipse.aether.spi.connector.transport.TransporterFactory
import org.eclipse.aether.transport.file.FileTransporterFactory
import org.eclipse.aether.transport.http.HttpTransporterFactory

import scala.collection.JavaConverters._

class ArtifactResolver {
  def resolve(artifact: Artifact): File = {
    artifact match {
      case Artifact.Maven(groupId, artifactId, version) =>
        val jarArtifact = new DefaultArtifact(groupId, artifactId, "jar", version)
        val sourcesArtifact = new DefaultArtifact(groupId, artifactId, "sources", "jar", version)

        val locator = MavenRepositorySystemUtils.newServiceLocator()
          .addService(classOf[RepositoryConnectorFactory], classOf[BasicRepositoryConnectorFactory])
          .addService(classOf[TransporterFactory], classOf[FileTransporterFactory])
          .addService(classOf[TransporterFactory], classOf[HttpTransporterFactory])

        val repositorySystem = locator.getService(classOf[RepositorySystem])

        val session = MavenRepositorySystemUtils.newSession()

        val tmpDir = Files.createTempDirectory("sfik").toFile
        session.setLocalRepositoryManager(repositorySystem.newLocalRepositoryManager(session, new LocalRepository(tmpDir)))

        val request = new ArtifactRequest()
          .setArtifact(sourcesArtifact)
          .setRepositories(List(new RemoteRepository.Builder(
            "central", "default", "https://repo.maven.apache.org/maven2/").build()).asJava)

        val result = repositorySystem.resolveArtifacts(session, List(request).asJava)

        result.asScala.head.getArtifact.getFile
    }
  }
}
