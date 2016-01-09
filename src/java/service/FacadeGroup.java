package service;

import data.Data;
import entity.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FacadeGroup {

    private List<Group> groups;
    private boolean isCached;

    public FacadeGroup() {
        groups = new ArrayList();
        isCached = false;
        cache();
    }

    /**
     * OBS: Refer to CA3 for more advanced BackgroundJobManager implementation
     * of timed events!
     *
     * if isCached is false, create Executor-service with reusable threads and
     * submit job: Scraper. Scraper will get groupInfo from the net and assign
     * to a future. Run through all futures and scan string to create
     * Group-object containing attributes from scanned string. Finally return a
     * list with Group-objects
     *
     * if isCached is true, just return the list with Group-objects.
     *
     * @return list with Group object
     */
    public List<Group> getGroups() {
        if (isCached == false) {
            List<Future<String>> groupFutures = new ArrayList();
            groups = new ArrayList();

            // Create executor-service with a threadpool of 4 reusable threads.
            ExecutorService executor = Executors.newFixedThreadPool(4);

            // For each url in Data.class, create instance of Scraper.class and 
            // submit the job to thread-pool and assign job to future.
            for (String url : Data.getUrls()) {
                Future<String> future = executor.submit(new Scraper(url));
                groupFutures.add(future);
            }

            executor.shutdown(); //orderly shutdown of executorservice so no new tasks can be submitted 

            // Run through list of futures and scan string
            // Create Group-object and fill out its attributes with data from scanned string.
            for (Future<String> future : groupFutures) {
                try {
                    String groupString = future.get();
                    Scanner scanner = new Scanner(groupString).useDelimiter("#");
                    String authors = scanner.next();
                    String cla = scanner.next();
                    String gro = scanner.next();
                    Group group = new Group(authors, cla, gro);
                    groups.add(group);
                } catch (InterruptedException | ExecutionException | NoSuchElementException ex) {
                }
            }
            isCached = true;
        }
        return groups;
    }

    /**
     * OBS: Refer to CA3 for more advanced BackgroundJobManager implementation
     * of timed events! Creates a thread with a timer which will set isCached to
     * false at every set interval
     */
    private void cache() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("#### Cache has been flaggd for refresh! ####");
                isCached = false;
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.SECONDS);
    }
}
