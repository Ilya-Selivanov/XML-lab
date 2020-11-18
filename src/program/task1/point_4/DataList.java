package program.task1.point_4;

import program.task1.point_4.Data;

import java.util.ArrayList;

public class DataList {

    private double k, b;

    private ArrayList<Data> dataArray;


    public void addData(Data data){
        double x = data.getX();
        double y = data.getX();
        dataArray.add(data);
    }

    public DataList(){
        dataArray = new ArrayList();
    }

    public void calculateCoefficient(){
        k = (getSumMultiplicationXY()-(getSumX()*getMeanY()))/(getAmountSquaredX()-(getSumX()*getMeanX()));
        b = getMeanY() - (getK()*getMeanX());
    }

    private double getMeanX(){
        double sum = 0;
        for (int i = 0; i < dataArray.size(); i++) {
            sum += dataArray.get(i).getX();
        }
        sum /= dataArray.size();
        return sum;
    }

    private double getMeanY(){
        double sum = 0;
        for (int i = 0; i < dataArray.size(); i++) {
            sum += dataArray.get(i).getY();
        }
        sum /= dataArray.size();
        System.out.println(sum);
        return sum;
    }

    private double getSumMultiplicationXY(){
        double sum = 0;
        for (int i = 0; i < dataArray.size(); i++) {
            sum += dataArray.get(i).getX()*dataArray.get(i).getY();
        }
        return sum;
    }

    private double getSumX(){
        double sum = 0;
        for (int i = 0; i < dataArray.size(); i++) {
            sum += dataArray.get(i).getX();
        }
        return sum;
    }

    private double getAmountSquaredX(){
        System.out.println("amount squared x");
        double sum = 0;
        for (int i = 0; i < dataArray.size(); i++) {
            sum += dataArray.get(i).getX()*dataArray.get(i).getX();
        }
        System.out.println(sum);
        return sum;
    }

    public double getB() {
        return b;
    }

    public double getK() {
        return k;
    }

    public String  toString(){
        String str = "k: " + k + "\t" + "b: " + b;
        return str;
    }

    public int size(){
        return dataArray.size();
    }

    public Data get(int i){
        return dataArray.get(i);
    }
}
