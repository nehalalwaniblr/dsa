package leet_again.array_strings;


public class GasStation {
    /*Bruteforce: lets try at every station if we can proceed
     * 1. check if gas[i]>cost[i] then only we can move ow continue to next station
     * 2. if you can move to next station then keep track of total gas you have while moving to next station i.e. add gas[i] and subtract cost[i]
     * 3. for moving in circle use i%2
     * 4. use i and j when you can move to next station; if i & j becomes equal that means you have reached back to same station
     *
     * */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int i;
        int j = 0;
        for (i = 0; i < n; i++) {
            if (gas[i] < cost[i]) {
                continue;
            }
            int currentGas = gas[i];
            j = (i + 1) % n;
            currentGas = currentGas + gas[j] - cost[i];
            while (i != j) {
                if (currentGas < cost[j])
                    break;
                int costForMovingFromThisj = cost[j];
                //move next
                j = (j + 1) % n;
                currentGas = currentGas + gas[j] - costForMovingFromThisj;
            }
            if (i == j)
                return i;
        }

        return -1;
    }
    /*greedy;optimal
    * 1. I can travel only when i have enough gas i.e. total gas when is less than total cost I cant travel
    * 2. Check if 1 is successful i.e. enough gas is there to spend
    * 3. Now iterate on all stations and calculate the totalGas as totalGas+gas[i]-cost[i]
    * 4. whenever totalGas becomes negative we'll try next station and reset totalGas to 0
    * */

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas =0;
        int totalCost =0;
        int n= gas.length;
        int result=0;
        for(int i=0;i<n;i++){
            totalGas+=gas[i];
            totalCost+=cost[i];
        }
        if(totalGas<totalCost)
            return -1;
        int gasAtAnyPoint =0;
        for(int i=0;i<n;i++){
            gasAtAnyPoint+=gas[i]-cost[i];
            if(gasAtAnyPoint<0){
                gasAtAnyPoint = 0; // reset as we cant move from this point
                result = i+1;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new GasStation().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(new GasStation().canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
    }
}
