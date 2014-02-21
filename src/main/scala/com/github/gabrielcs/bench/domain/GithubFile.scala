package com.github.gabrielcs.bench.domain

class GithubFile(path: String, var name: String, var size: Long)
  extends GithubContent(path) with Comparable[GithubFile] {

  // bigger sizes first
  override def compareTo(otherFile: GithubFile): Int =
    -this.size.compareTo(otherFile.size)
}
