package org.study.actor


import akka.actor.ActorSystem
import akka.testkit.ImplicitSender
import akka.testkit.TestKit
import akka.actor.Props
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll
import org.scalatest.WordSpecLike
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PongActorTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
    with WordSpecLike with Matchers with BeforeAndAfterAll {
  
  def this() = this(ActorSystem("PongActorTest"))
  
  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }
  
  "PongActor" must {
    "send back Pong upon receiving Ping" in {
      val pong = system.actorOf(Props[PongActor])
      pong ! "Ping"
      expectMsg("Pong")
    }
  }
}