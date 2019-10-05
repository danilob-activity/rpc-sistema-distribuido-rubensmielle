import java.util.*;
import java.net.*;
import java.lang.*;
class RPCServer
{
    DatagramSocket ds;
    DatagramPacket dp;
    String str,methodName,result;
    float val1,val2;
    RPCServer()
    {
        
        try
        {
            ds=new DatagramSocket(1200);
            byte b[]=new byte[4096];
            while(true)
            {
            result = "(Mielle) ";
            dp=new DatagramPacket(b,b.length);
            ds.receive(dp);
            str=new String(dp.getData(),0,dp.getLength());
            if(str.equalsIgnoreCase("q"))
            {
                System.exit(1);
            }
            else
            {
                StringTokenizer st = new StringTokenizer(str," ");
                int i=0;
                while(st.hasMoreTokens())
                {
                    String token=st.nextToken();
                    methodName=token;
                    val1 = Float.parseFloat(st.nextToken());
                    val2 = Float.parseFloat(st.nextToken());
                }
            }
            System.out.println(str);
            //InetAddress ia = InetAddress.getLocalHost();
            if(methodName.equalsIgnoreCase("add"))
            {
                result += "" + add(val1,val2);
            }
            else if(methodName.equalsIgnoreCase("sub"))
            {
                result += "" + sub(val1,val2);
            }
            else if(methodName.equalsIgnoreCase("mul"))
            {
                result += "" + mul(val1,val2);
            }
            else if(methodName.equalsIgnoreCase("div"))
            {
                result += "" + div(val1,val2);
            }
            else if(methodName.equalsIgnoreCase("mod"))
            {
                result += "" + mod(val1,val2);
            }
            else if(methodName.equalsIgnoreCase("pow"))
            {
                result += "" + pow(val1,val2);
            }
            byte b1[]=result.getBytes();
            DatagramSocket ds1 = new DatagramSocket();
            DatagramPacket dp1 = new DatagramPacket(b1,b1.length,dp.getAddress(),1300);
            System.out.println("result : "+result+"\n");
            ds1.send(dp1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public float add(float val1, float val2)
    {
        return val1+val2;
    }
    public float sub(float val3, float val4)
    {
        return val3-val4;
    }
    public float mul(float val3, float val4)
    {
        return val3*val4;
    }
    public float div(float val3, float val4)
    {
        return val3/val4;
    }
    public float mod(float val3, float val4)
    {
        return val3%val4;
    }
    public double pow(double val3, double val4)
    {
        return Math.pow(val3,val4);
    }
    public static void main(String[] args)
    {
        new RPCServer();
    }
}
