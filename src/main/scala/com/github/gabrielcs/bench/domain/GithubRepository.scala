package com.github.gabrielcs.bench.domain

class GithubRepository(var owner: String, var name: String) {
  var rootDirectory = new GithubDirectory("", "")
}
