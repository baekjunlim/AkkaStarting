package org.study.actor

import akka.event.Logging
import akka.actor.Props
import akka.actor.Actor

class PingActor extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case msg @ "Pong" => { 
      log.info("Ping received {}", msg); 
      Thread.sleep(1000); // Don't do this in real code!!
      sender ! "Ping"
    }
  }
}