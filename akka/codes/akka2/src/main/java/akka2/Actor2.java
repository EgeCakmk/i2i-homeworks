package akka2;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ActorSystem;

public class Actor2 extends AbstractActor {

    static public Props props() {
        return Props.create(Actor2.class, Actor2::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> {
                    if (msg.equals("Hi from Actor1")) {
                        System.out.println("Received: " + msg);
                        getSender().tell("Hi from Actor2", getSelf());
                    }
                })
                .build();
    }

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("actor-system2");
        final ActorRef actor2 = system.actorOf(Actor2.props(), "actor2");
    }
}
