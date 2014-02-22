package com.github.gabrielcs.bench

import java.util.List

import scala.collection.JavaConversions.asScalaBuffer

import org.eclipse.egit.github.core.RepositoryContents
import org.eclipse.egit.github.core.RepositoryId
import org.eclipse.egit.github.core.service.ContentsService

import com.github.gabrielcs.bench.domain.GithubDirectory
import com.github.gabrielcs.bench.domain.GithubFile
import com.github.gabrielcs.bench.domain.GithubRepository

class GithubRepositoryReader(owner: String, name: String, var maxDepth: Int) {

  var repositoryId = new RepositoryId(owner, name)
  var contentsService = new ContentsService()

  def read: GithubRepository = {
    val githubRepository = new GithubRepository(repositoryId.getOwner, repositoryId.getName)
    readDirectoryContent(githubRepository.rootDirectory, "")
    githubRepository
  }

  private def readDirectory(directory: RepositoryContents): GithubDirectory = {
    val githubDirectory = new GithubDirectory(directory.getPath, directory.getName)
    if (githubDirectory.calculateDepth < maxDepth) {
      readDirectoryContent(githubDirectory, directory.getPath)
    }
    githubDirectory
  }

  // this method mutates 'githubDirectory'
  private def readDirectoryContent(githubDirectory: GithubDirectory, directoryPath: String) {
    var contents: List[RepositoryContents] =
      if (directoryPath.isEmpty) contentsService.getContents(repositoryId) // root directory
      else contentsService.getContents(repositoryId, directoryPath) // non-root directory

    contents.foreach { c =>
      if (c.getType.equals("dir")) githubDirectory.addSubDirectory(readDirectory(c))
      else if (c.getType.equals("file")) githubDirectory.addFile(readFile(c))
    }
  }

  private def readFile(file: RepositoryContents): GithubFile = {
    val githubFile = new GithubFile(file.getPath, file.getName, file.getSize)
    githubFile
  }
}
