package akka1;

import com.typesafe.config.ConfigFactory;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ActorSystem;
import akka.actor.ActorSelection;

public class Actor1 extends AbstractActor {

    static public Props props() {
        return Props.create(Actor1.class, Actor1::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> {
                    if (msg.equals("Hi from Actor2")) {
                        System.out.println("Received: " + msg);
                        getContext().getSystem().terminate();
                    }
                })
                .build();
    }

    public static void main(String[] args) {
    	ActorSystem system = ActorSystem.create("Actor1", ConfigFactory.load("application.conf"));
        final ActorRef actor1 = system.actorOf(Actor1.props(), "actor1");

        ActorSelection actor2 = system.actorSelection("akka://actor-system2@127.0.0.1:2552/user/actor2");
        actor2.tell("Hi from Actor1", actor1);
    }
}
