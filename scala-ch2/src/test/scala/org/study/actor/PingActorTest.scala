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
class PingActorTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
    with WordSpecLike with Matchers with BeforeAndAfterAll {
  
  def this() = this(ActorSystem("PingActorTest"))
  
  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }
  
  "PingActor" must {
    "send back Ping upon receiving Pong" in {
      val ping = system.actorOf(Props[PingActor])
      ping ! "Pong"
      expectMsg("Ping")
    }
  }
}