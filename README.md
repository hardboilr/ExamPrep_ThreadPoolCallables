#Notes

Maybe come back to question 5: "Scheduled refresh of cache", and implement something cooler along the lines of the solution from Semesterproject's Reservation System.

#General part

>Explain in general about Threads and reasons to use them in modern programming languages

Threads can be used to write non-blocking code which can useful if you want the program to do more than one thing at a time. Threads can be used to maintain the flow and responsiveness of an application, both in single- or multiusers scenarios. 

We typically want multithreaded functionality in GUI-applications to keep the GUI responsive at all times. 

When CPU is bottlenecked compared to IO (human input: mouse, keyboard etc.).

If executing independent tasks, fx. in a multiuser scenario, produce-consume and more.

>Explain why Thread Programming, even when you know the language structures are considered a (very) hard 
discipline

Multithreaded applications will no longer execute single lines of code in a sequantial fashion. Instead you have multiple lines of codes executing simultaneously and in different parts of the code. This alone requires more of the programmer. When different parts of the code are accessing a single resource, one also have to handle potential deadlock situations and possible data inconsistencies.      

>Explain the benefits from using a Thread Pool

Allocating and deallocating (read: creating and destroying threads) creates a significant memory management overhead. A thread pool re-uses a set amount of worker threats / executors to alleviate this problem. 

>Explain ways to handle "returned values" from Threads 

A class implementing a "runnable" (implements Runnable) has the run() which is a void. Instead a callable can be used (implements Callable<Object>), which has the Object call() with a return. Therefore use callables if you want the thread to return something.

Refer to Semesterproject: OnlineReservationSystem -> GetFlights.class + FlightFacade.class for example of Response-object being returned from Callable.