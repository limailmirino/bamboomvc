package it.bamboolab.utils;

import it.bamboolab.model.Group;

/**
 * Created by N4N on 08/06/2015.
 */
public class ActionedGroup {
    public enum Action {ADD, DELETE, IGNORE};

    private Action action;
    private Group group;

    public Action getAction() {
        return action;
    }

    public void setAction(Action val) {
        this.action = val;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group val) {
        this.group = val;
    }

}
