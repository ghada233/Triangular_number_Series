import java.util.Scanner;
import java.util.concurrent.*;

public class Triangular{
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {

            System.out.println("**Triangular number Series with java**");
            System.out.println("Enter number of threads ");
             int Threads_Count=input.nextInt(); //read the threads from the user
            System.out.print("Enter positive number (n): ");
            int n=input.nextInt(); //read the number from the user
           

            ExecutorService executor=Executors.newFixedThreadPool(Threads_Count); //create thread pool with 4 threads

            long startTime =System.currentTimeMillis();//calculate current time for the start time

            for(int threadRank=0; threadRank<4; threadRank++){
                executor.execute(new Triangular_number_Series(threadRank, n,Threads_Count)); //execute each thread
            }

            executor.shutdown();//shutdown the executor

            while(!executor.isTerminated()){} //check whether the executor is terminated or not

            long endTime=System.currentTimeMillis();//calculate current time for the end time

            System.out.println("The Time elapsed is: "+(endTime-startTime)+" milliseconds.");//print the time elapsed
        }//end try
    }//end main 
}//end Triangular class

class Triangular_number_Series implements Runnable {
    int threadRank;
    int n;
    int Threads_Count;

    Triangular_number_Series(int threadRank, int n,int Threads_Count){
        this.n=n;
        this.threadRank=threadRank;
        this.Threads_Count=Threads_Count;
    }

    @Override
    public void run() {
        for(int i=threadRank; i<n; i+=Threads_Count){
            System.out.printf("(Thread:%d) (Itration:%d) (Dots:%d)\n", threadRank, i, (i*(i+1))/2);//each thread print its rank and iteration it take and the number of dots in the triangular
        }//end for loop
    }//end run()
}//end Triangular_number_Series task
