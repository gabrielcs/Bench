package com.github.gabrielcs.bench

object Application extends App {
  val ownerName = "10sheet"
  val repositoryName = "interview-repo"

  val depth = getDepth(args)
  val reader = new GithubRepositoryReader(ownerName, repositoryName, depth)
  val repository = reader.read
  GithubRepositoryPrinter.printRepository(repository)

  def getDepth(args: Array[String]): Int = {
    var depth = 0
    try {
      depth = augmentString(args(0)).toInt
      if (depth < 1) {
        println("Error: \"depth of the output\" should be 1 or higher")
        System.exit(0)
      }
    } catch {
      case e: RuntimeException =>
        println("Error: couldn't read \"depth of the output\"")
        System.exit(0)
    }
    depth
  }
}


