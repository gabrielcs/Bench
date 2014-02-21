package com.github.gabrielcs.bench.domain

abstract class GithubContent(var path: String) {
  def calculateDepth: Int = {
    path.count(_ == '/') + 1
  }
}