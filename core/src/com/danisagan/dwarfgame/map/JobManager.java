package com.danisagan.dwarfgame.map;

import com.danisagan.dwarfgame.entities.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 2/13/16.
 */
public class JobManager {
    private List<Job> jobList = new ArrayList<Job>();

    public void addNewJob(Job job) {
        jobList.add(job);
    }

    public boolean assignJob(Entity worker) {
        for(Job j: jobList) {
            if(j.getWorker() == worker) {
                return false;
            } else if(j.getWorker() == null) {
                j.setWorker(worker);
                worker.setCurrentJob(j);
                return true;
            }
        }
        return false;
    }

    public boolean finishJob(Entity worker) {
        for(Job j: jobList) {
            if(j.getWorker() == worker) {
                worker.setCurrentJob(null);
                jobList.remove(j);
                return true;
            }
        }
        return false;
    }

    public int getJobCount() {
        return this.jobList.size();
    }

    public List<Job> getJobList() {
        return jobList;
    }
}
