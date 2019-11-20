package sample;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

// Java program for Weighted Job Scheduling in O(nLogn)
// time


// Class to represent a job
class Job extends intervalScheduler {

  int start, finish, profit;

  Job(Appointment appointment) {
    //TODO: below constructor
    this.start = convertDurationToInteger(appointment.getAverageRoundDuration());
  }

  // Constructor
  Job(int start, int finish, int profit) {
    this.start = start;
    this.finish = finish;
    this.profit = profit;
  }
}

// Used to sort job according to finish times
class JobComparator implements Comparator<Job> {

  public int compare(Job a, Job b) {
    return a.finish < b.finish ? -1 : a.finish == b.finish ? 0 : 1;
  }
}

public class intervalScheduler {

  /* A Binary Search based function to find the latest job
    (before current job) that doesn't conflict with current
    job.  "index" is index of the current job.  This function
    returns -1 if all jobs before index conflict with it.
    The array jobs[] is sorted in increasing order of finish
    time. */
  static public int binarySearch(Job jobs[], int index) {
    // Initialize 'lo' and 'hi' for Binary Search
    int lo = 0, hi = index - 1;

    // Perform binary Search iteratively
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      if (jobs[mid].finish <= jobs[index].start) {
        if (jobs[mid + 1].finish <= jobs[index].start) {
          lo = mid + 1;
        } else {
          return mid;
        }
      } else {
        hi = mid - 1;
      }
    }

    return -1;
  }

  public static int generateSampleData() {
    Job[] sampleData = new Job[324];
    Random random = new Random();
    for (int i = 0; i < sampleData.length; i++) {

      sampleData[i] = new Job(random.nextInt(9), random.nextInt(9) + 1, 1);
    }

    return schedule(sampleData);
  }


  // The main function that returns the maximum possible
  // profit from given array of jobs
  static public int schedule(Job jobs[])
  //static public int[] schedule(Job jobs[])
  {
    // Sort jobs according to finish time
    Arrays.sort(jobs, new JobComparator());

    // Create an array to store solutions of subproblems.
    // table[i] stores the profit for jobs till jobs[i]
    // (including jobs[i])
    int n = jobs.length;
    int table[] = new int[n];
    table[0] = jobs[0].profit;

    // Fill entries in M[] using recursive property
    for (int i = 1; i < n; i++) {
      // Find profit including the current job
      int inclProf = jobs[i].profit;
      int l = binarySearch(jobs, i);
      if (l != -1) {
        inclProf += table[l];
      }

      // Store maximum of including and excluding
      table[i] = Math.max(inclProf, table[i - 1]);
    }
    System.out.println("Table: " + Arrays.toString(table));
    return table[n - 1];
  }

  public int convertDurationToInteger(String timeAsString) {

    String[] durationStringArray = timeAsString.split(":");

    int hours = Integer.parseInt(durationStringArray[0]);
    int minutes = Integer.parseInt(durationStringArray[1]);
    int seconds = Integer.parseInt(durationStringArray[2]);
    int totalTime = seconds + (60 * minutes) + (3600 * hours);
    return totalTime;
  }

  public Job[] convertAppointmentArrayListToJobs(ArrayList<Appointment> apArL) {
    Job[] jobArray = new Job[apArL.size()];
    apArL.toArray();
    for (int i = 0; i < apArL.size(); i++) {
      Appointment currApp = apArL.get(i);
      if (jobArray.length == 0) {
        jobArray[i].start = convertDurationToInteger(currApp.getPreferredTime());
      } else if (jobArray.length > 0) {
        jobArray[i].start = jobArray[i - 1].finish + (9 * 60); // 9 minutes after the previous job
      }
      jobArray[i].finish = convertDurationToInteger(currApp.getAverageRoundDuration());

      // jobArray[i] = apArL.get(i);
    }
    return jobArray;
  }

  // Driver method to test above
  public static void main(String[] args) throws IOException {
    Job jobs[] = {new Job(1, 2, 50), new Job(3, 5, 20),
        new Job(6, 19, 100), new Job(2, 100, 200)};
    System.out.println("Sample:"
        + generateSampleData()); //generates random data for 18^2 values (1 group or single golfer on each hole, 18 holes per group/player
    System.out.println("Optimal profit is " + schedule(jobs));

    Appointment appointment = new Appointment("Joe Smith", "9:00:00", "3:00:00", 4);
    Appointment appointment2 = new Appointment("Jo Smith", "12:00:00", "3:00:00", 4);
    Appointment appointment3 = new Appointment("J Smith", "15:00:00", "3:00:00", 4);
    Job[] apptJobs = new Job[324];
    appointment.addToAppointmentArrayList(appointment.getAppointmentArrayList(), appointment2);
    System.out.println(appointment.appointmentArrayList.toString());
  }
}

