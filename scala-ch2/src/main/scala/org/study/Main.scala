package org.study

import akka.actor.ActorSystem
import akka.actor.Props
import org.study.actor.PingActor
import akka.actor.ActorRef
import org.study.actor.PongActor

/**
 * Main object for Ping Pong demo
 * @author Baekjun Lim
 */
object Main extends App {
  
  override def main(args: Array[String])  {
    val system = ActorSystem("TestSystem")
    val ping = system.actorOf(Props[PingActor]) 
    implicit val pong = system.actorOf(Props[PongActor]) 
    ping ! "Pong"
  }
    
}