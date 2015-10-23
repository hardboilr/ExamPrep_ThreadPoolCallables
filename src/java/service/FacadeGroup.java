package service;

import data.Data;
import entity.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;
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

    public List<Group> getGroups() {
        if (isCached == false) {
            groups = new ArrayList();
            ExecutorService executor = Executors.newFixedThreadPool(4);
            List<Future<String>> urlList = new ArrayList();
            for (String url : Data.getUrls()) {
                Future<String> future = executor.submit(new Scraper(url));
                urlList.add(future);
            }
            executor.shutdown();

            for (Future<String> future : urlList) {
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

    private void cache() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                isCached = false;
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 60, TimeUnit.MINUTES);
    }
}
