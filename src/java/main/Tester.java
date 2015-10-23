package main;

import entity.Group;
import java.util.List;
import service.FacadeGroup;

public class Tester {
    
    public static void main(String[] args) {
        FacadeGroup facade = new FacadeGroup();
        List<Group> groups = facade.getGroups();
        for (Group group : groups) {
            System.out.println(group.getAuthors() + "," + group.getCla() + "," + group.getGroup());
        }
    }
    
}
