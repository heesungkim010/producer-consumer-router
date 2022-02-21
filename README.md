# producer-consumer-router

With this router "single/multiple producers" can send data to "single/multiple consumers" without mutual exclusion, synchronization problems.

There are 4 kinds of routers: OneToOne, OneToMany, ManyToOne, ManyToMany.
"One" means single producers/consumers and "Many" means multiple producers/consumers.

Producers and consumers can be run by mulitiple threads.

Only used for a single process.
Does not support sending data by network.

# Implemetation and further variations

Implemented by using semaphores and array as a buffer(circular using div operation).

Linked list could replace the array in restricted cases. Using linked list can lead to non-waiting producers and not using some of the semaphores. 

However it might decrease the performance due to the pointer operations. How to properly restrict the amount of "already sent but not yet received messages" is another overhead issue.
