package com.github.gabrielcs.bench.domain

class GithubDirectory(path: String, var name: String)
  extends GithubContent(path) with Comparable[GithubDirectory] {

  var subDirectories: Seq[GithubDirectory] = Seq[GithubDirectory]()
  var files: Seq[GithubFile] = Seq[GithubFile]()

  def getSubDirectories: Seq[GithubDirectory] = {
    subDirectories.sorted
  }

  // returns "this" as in the Builder design pattern
  def addSubDirectory(directory: GithubDirectory): GithubDirectory = {
    subDirectories = subDirectories :+ directory
    this
  }

  def getFiles: Seq[GithubFile] = {
    files.sorted
  }

  // returns "this" as in the Builder design pattern
  def addFile(file: GithubFile): GithubDirectory = {
    files = files :+ file
    this
  }
  
  override def compareTo(otherDirectory: GithubDirectory): Int = {
    name.compareTo(otherDirectory.name)
  }
}
