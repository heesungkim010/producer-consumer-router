# producer-consumer-router

With this router "single/multiple producers" can send data to "single/multiple consumers" without mutual exclusion, synchronization problems.

Producers and consumers can be run by mulitiple threads.

Only used for a single process.
Does not support sending data by network.

# Implemetation and further variations

Implemented by using semaphore and array(circular using div operation)

Linked list could replace the array in restricted cases. Using linked list can lead to not using some of the semaphores and non-waiting producers. However it might decrease the performance due to the pointer operations. How to properly restrict the amount of "already sent but not yet received messages" is another over-head issue.
