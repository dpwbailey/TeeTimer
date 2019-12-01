package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class intervalSchedulerRevised {
    private static Job[][] jobs;    //array of jobs. Each job is [id, startTime, finishTime, value]
    private static int[] memo;        //memoization array
    public static ArrayList<Integer> includedJobs = new ArrayList<Integer>();        //holds jobs in optimal solution. The id's are id's of the sorted jobs, so must be converted to the original ID's by getJobInfo

    static class Job {

        int id, start, finish, profit;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getFinish() {
            return finish;
        }

        public void setFinish(int finish) {
            this.finish = finish;
        }

        public int getProfit() {
            return profit;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        // Constructor
        public Job(int id, int start, int finish, int profit) {
            this.id = id;
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
    }

    public static void calcSchedule(Job[][] inputJobs) {
        jobs = inputJobs;
        memo = new int[jobs.length];    //create memoization array

        Arrays.sort(jobs, (a, b) -> Integer.compare(a[2].finish, b[2].finish));    //Sort jobs by finish time

        memo[0] = 0;        //base case with no jobs selected

        for (int i = 1; i < jobs.length; i++) {
            memo[i] = Math.max(jobs[i][3].getProfit() + memo[latestCompatible(i)], memo[i - 1]);        //add max value if job is included or if it's not included
        }

        System.out.println("Memoization array: " + Arrays.toString(memo));
        System.out.println("Maximum profit from the optimal set of jobs = " + memo[memo.length - 1]);

        findSolutionIterative(memo.length - 1);        //Recursively find solution & update includedJobs
        System.out.println("\nJobs Included in optimal solution:");
        for (int i = includedJobs.size() - 1; i >= 0; i--) {        //Loop backwards to display jobs in increasing order of their ID's
            System.out.println(getJobInfo(includedJobs.get(i)));
        }
    }

    //Find the index of the job finishing before job i starts (uses jobs[][] array sorted by finish time)
    private static int latestCompatible(int i) {
        int low = 0, high = i - 1;

        while (low <= high) {        //Iterative binary search
            int mid = (low + high) / 2;        //integer division (floor)
            if (jobs[mid][2].getFinish() <= jobs[i][1].getStart()) {
                if (jobs[mid + 1][2].finish <= jobs[i][1].start)
                    low = mid + 1;
                else
                    return mid;
            } else
                high = mid - 1;
        }
        return 0;    //No compatible job was found. Return 0 so that value of placeholder job in jobs[0] can be used
    }

    //Iterative version of the recursive code to retrace & find the optimal solution
    public static void findSolutionIterative(int j) {
        while (j > 0) {    //Stops when j==0
            int compatibleIndex = latestCompatible(j);    //find latest finishing job that's compatible with job j
            if (jobs[j][3].profit + memo[compatibleIndex] > memo[j - 1]) {    //Case where job j was included (from optimal substructure)
                includedJobs.add(j);    //add job index to solution
                j = compatibleIndex;        //update j to the next job to consider
            } else {    //case where job j was NOT included, remove job j from the possible jobs in the solution & look at jobs 1 to (j-1)
                j = j - 1;
            }
        }
    }

    //convert a time in the format xx:yy:zz to seconds
    public static int convertDurationToInteger(String timeAsString) {
        String[] durationStringArray = timeAsString.split(":");
        int hours = Integer.parseInt(durationStringArray[0]);
        int minutes = Integer.parseInt(durationStringArray[1]);
        int seconds = Integer.parseInt(durationStringArray[2]);
        int totalTime = seconds + (60 * minutes) + (3600 * hours);
        return totalTime;
    }

    public static Job[][] convertAppointmentArrayListToJobs(ArrayList<Appointment> apArL) {
        Job[][] jobArray = new Job[apArL.size()][];
        Object[] objArray = apArL.toArray();

        for (int i = 0; i < objArray.length; i++) {
            System.out.println("The objArray: " + objArray[i]);
        }

        /*for (int i = 1; i < apArL.size(); i++) {
            Appointment currApp = apArL.get(i);
            if (jobArray.length == 0) {
                jobArray[i].start = convertDurationToInteger(currApp.getPreferredTime());
            } else {
                jobArray[i].start = jobArray[i - 1].finish + (9 * 60); // 9 minutes after the previous job
            }
            jobArray[i].finish = convertDurationToInteger(currApp.getAverageRoundDuration());

            // jobArray[i] = apArL.get(i);
        }*/
        System.out.println("Job Array: " + Arrays.toString(jobArray));
        return jobArray;
    }

    public static Job[][] generateSampleData() {
        Job[][] sampleData = new Job[64][4];
        Random random = new Random();
        for (int i = 0; i < sampleData.length; i++) {
            for (int j = 0; j < sampleData[i].length; j++) {

                sampleData[i][j] = new Job((4 * i) + j - 4, random.nextInt(9), random.nextInt(9) + 1, 1);
                while (!(sampleData[i][j].getStart() < sampleData[i][j].getFinish())) {
                    sampleData[i][j].setStart(random.nextInt(9));
                    sampleData[i][j].setFinish(random.nextInt(9));


                }
            }
        }
        return sampleData;
    }

    //Recursive method to retrace the memoization array & find optimal solution
    private void findSolutionRecursive(int j) {
        if (j == 0) {    //base case
            return;
        } else {
            int compatibleIndex = latestCompatible(j);    //find latest finishing job that's compatible with job j
            if (jobs[j][3].profit + memo[compatibleIndex] > memo[j - 1]) {    //Case where job j was included (from optimal substructure)
                includedJobs.add(j);    //add job index to solution
                findSolutionRecursive(compatibleIndex);    //recursively find remaining jobs starting the the latest compatible job
            } else {    //case where job j was NOT included, remove job j from the possible jobs in the solution
                findSolutionRecursive(j - 1);
            }
        }
    }

    //Get a human-readable String representing the job & its 4 parts
    private static String getJobInfo(int jobIndex) {
        return "Job " + jobs[jobIndex][0].getId() + ":  Time (" + jobs[jobIndex][1].getStart() + "-" + jobs[jobIndex][2].getFinish() + ") Value=" + jobs[jobIndex][3].getProfit();
    }


    public static void main(String args[]) {
        intervalSchedulerRevised scheduler = new intervalSchedulerRevised();
        Job[][] inputJobs = generateSampleData();
       /* Job[][] inputJobs = new Job[5][4];
        for (int i = 0; i < inputJobs.length; i++)
            for (int j = 0; j < inputJobs[i].length; j++) {
                inputJobs[i][j] = new Job(i + 1, i + 1, 2 * i + 1, j + 1);
            }

        */
        scheduler.calcSchedule(inputJobs);
    }

    ;

}
