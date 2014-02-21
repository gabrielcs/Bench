package com.github.gabrielcs.bench

import com.github.gabrielcs.bench.domain.GithubDirectory
import com.github.gabrielcs.bench.domain.GithubFile
import com.github.gabrielcs.bench.domain.GithubRepository

object GithubRepositoryPrinter {

  def printRepository(repository: GithubRepository) {
    printDirectoryContent(repository.rootDirectory, 1)
  }

  private def printDirectory(directory: GithubDirectory, depth: Int) {
    printIndentedString(directory.name, depth)
    println("/")
    printDirectoryContent(directory, depth)
  }

  private def printDirectoryContent(directory: GithubDirectory, depth: Int) {
    directory.getSubDirectories.foreach(d => printDirectory(d, depth + 1))
    directory.getFiles.foreach(f => printFile(f, depth + 1))
  }

  private def printFile(file: GithubFile, depth: Int) {
    printIndentedString(file.name, depth)
    println
  }

  private def printIndentedString(string: String, depth: Int) {
    // print indentation
    (1 to depth - 2).foreach(i => print("    "))
    // print string
    print(string)
  }
}
