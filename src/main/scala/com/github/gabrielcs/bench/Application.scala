package com.github.gabrielcs.bench

import scala.util.Try
import scala.util.Failure
import scala.util.Success

object Application extends App {
  val ownerName = "10sheet"
  val repositoryName = "interview-repo"

  val depth = getDepth(args)
  val reader = new GithubRepositoryReader(ownerName, repositoryName, depth)
  val repository = reader.read
  GithubRepositoryPrinter.printRepository(repository)

  def getDepth(args: Array[String]): Int = {
    val depth = Try(args(0).toInt)
    
    depth match {
      case Failure(thrown) => {
        println("Error: couldn't read \"depth of the output\"")
        System.exit(0)
      }
      case Success(s) => {
        if (s < 1) {
        	println("Error: \"depth of the output\" should be 1 or higher")
        	System.exit(0)
        }
      }
    }
    
    depth.get
  }
}


