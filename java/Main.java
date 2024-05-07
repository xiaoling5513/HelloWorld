package Backpack01;

import java.util.Arrays;

//暴力计算01背包
public class Main {
    int MAXi = 5;
    int MAXw =12;
    int[][] record=new int[MAXw+1][MAXi+1];
    Node[] nodes ={new Node(13,5),new Node(11,2),new Node(10,4),new Node(5,1),new Node(15,7)};
    public int max(int a,int b){
        return a>b?a:b;
    }

    public static void main(String[] args) {
        Main as=new Main();
        int[] wt=new int[as.MAXi];
        int[] var=new int[as.MAXi];
        int i=0;
        for (Node node:as.nodes){
                wt[i]=node.weight;
                var[i]=node.price;
            i++;
        }
        System.out.print(as.record[0][0]);
        long startTime = System.nanoTime();     //获取开始时间

        System.out.print(as.Knapase(as.MAXw,wt,var,as.MAXi));

        long overTime = System.nanoTime();      //获取结束时间
        System.out.println("程序运行时间为："+(overTime-startTime)+"纳秒");
        long startTime2 = System.nanoTime();     //获取开始时间

        System.out.print(as.Knapsack_DP(as.MAXw,wt,var,as.MAXi));

        long overTime2 = System.nanoTime();      //获取结束时间
        System.out.println("程序运行时间为："+(overTime2-startTime2)+"纳秒");

    }
    /**
     *
     * @param W 背包容量
     * @param wt
     * @param var
     * @param n 物品个数
     */
    public int Knapase(int W,int[] wt,int[] var,int n){
        if (W==0||n==0){
            return 0;
        }
        if (record[W][n]!=0){
            return record[W][n];
        }
        if (wt[n-1]>W){
            return Knapase(W,wt,var,n-1);
        }else {
            int t=max(var[n-1]+Knapase(W-wt[n-1],wt,var,n-1),Knapase(W,wt,var,n-1));
            record[W][n]=t;
            return t;
        }
    }
    public int Knapsack_DP(int W,int[] wt,int[] var,int n){
        int K[][]=new int[n+1][W+1];
        int i,w;
        for (i=0;i<=n;i++){
            for (w=0;w<=W;w++){
                if (i==0||w==0){
                    K[i][w]=0;
                }else if (wt[i-1]<=w){
                    K[i][w] = max(var[i-1]+K[i-1][w-wt[i-1]],K[i-1][w]);
                }else {
                    K[i][w]=K[i-1][w];
                }
            }
        }
        return K[n][W];
    }
}
