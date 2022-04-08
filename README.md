# producer-consumer-router
This is an implementation of how to solve "Producer-Consumer Problem"(also called as "The Bounded-Buffer Problem") in Operating System.

With this router "single/multiple producers" can send data to "single/multiple consumers".

There are 4 kinds of routers: OneToOne, OneToMany, ManyToOne, ManyToMany.
"One" means a single producers/consumer and "Many" means multiple producers/consumers.

Producers and consumers can be run by mulitiple threads.

Does not support sending data by network.

# Implemetation and further variations

Implemented by using semaphores and array as a bounded buffer(circular using mod operation).

[Further variation 1]

Linked list could replace the array in restricted cases. Using linked list can lead to non-waiting producers and not using some of the semaphores. 

However it might decrease the performance due to the pointer operations. How to properly restrict the amount of "already sent but not yet received messages" is another overhead issue.

[Further variation 2]

Spinlock or Ticketlock(Sequencer/Eventcount) could replace the semaphores. 

Each has its own pros and cons, so what to choose depends on the specific situation.
