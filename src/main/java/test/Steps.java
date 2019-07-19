package test;

public class Steps {

    public static void main(String[] args) {
//        System.out.println(dp4(5));
//        System.out.println(dp3(5));
        feibo(20);
    }
    public static int dp3(int n){
        int[] dp=new int[n];

        for(int i=0;i<n;i++) {

            if (i > 2) {
                dp[i] = dp[i - 1] + dp[i - 2] +dp[i - 3];
            } else if (i == 2) {
                dp[2] = 4;
            }  else if (i == 1) {
                dp[1] = 2;
            } else if (i == 0) {
                dp[0] = 1;
            }
        }

        return dp[n-1];
    }

    public static int dp4(int n){
        int ans=0;
        int[] dp=new int[n];

        for(int i=0;i<n;i++) {
            ans=0;
            if (i == 0)
                dp[0] = 1;
            if (i == 1)
                dp[1] = 1;
            if (i == 2)
                dp[2] = 1;

            if (i > 0)
                ans += dp[i-1];
            if (i > 1)
                ans+=dp[i - 2] ;
            if (i > 2)
                ans+=dp[i - 3] ;
            dp[i]+=ans;
        }


        return dp[n-1];
    }

    public static void  feibo(int n){
        // 1 1 2 3 5
        int a ,b ,c=0 ,d=0;
        a = 1;
        b = 1;
        System.out.print(1+","+1+",");
        for (int i = 0;  ; i+=2) {
            c =a+b;
            a=b;
            b=c;
            if (c>20) {
                break;
            }
            System.out.print(c+",");

        }
    }

}
