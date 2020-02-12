import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.StrictMath.abs;

public class Sensors {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        String fileInfo = readFile();
        int[][] matrix= setMatrix(fileInfo);
        printMatrix(matrix);


        System.out.println("Set neighbour distance");
        int distance = scanner.nextInt();
        System.out.println("Set max error");
        int error =  scanner.nextInt();

        checkForErrors(matrix,fileInfo,distance,error);


    }

    private static void checkForErrors(int[][] matrix, String sensorLocation,int distance, int error) {


        StringBuilder errors = new StringBuilder();


        int row,column,value;

        String[] info= sensorLocation.split("\n");

        for (String s : info) {

            row = Integer.parseInt(s.split(",")[0]);
            column = Integer.parseInt(s.split(",")[1]);
            value = Integer.parseInt(s.split(",")[2].replace("\r",""));


            if(abs(matrix[row+distance][column] - value) > error && matrix[row+distance][column] != 0){
                errors.append(" Error in : (")
                        .append(row).append(",")
                        .append(column).append(")")
                        .append(System.lineSeparator());
                continue;
            }

            if(abs(matrix[row+distance][column+distance] - value) > error && matrix[row+distance][column+distance] != 0){
                errors.append(" Error in : (")
                        .append(row).append(",")
                        .append(column).append(")")
                        .append(System.lineSeparator());
                continue;
            }
            if(abs(matrix[row+distance][column-distance] ) > error && matrix[row+distance][column-distance] != 0){
                errors.append(" Error in : (")
                        .append(row).append(",")
                        .append(column).append(")")
                        .append(System.lineSeparator());
                continue;
            }
            if(abs(matrix[row-distance][column] - value) > error && matrix[row-distance][column] != 0){
                errors.append(" Error in : (")
                        .append(row).append(",")
                        .append(column).append(")")
                        .append(System.lineSeparator());
                continue;
            }
            if(abs(matrix[row-distance][column+distance] - value) > error && matrix[row-distance][column+distance] != 0){
                errors.append(" Error in : (")
                        .append(row).append(",")
                        .append(column).append(")")
                        .append(System.lineSeparator());
                continue;
            }
            if(abs(matrix[row-distance][column-distance] - value) > error && matrix[row-distance][column-distance] != 0){
                errors.append(" Error in : (")
                        .append(row).append(",")
                        .append(column).append(")")
                        .append(System.lineSeparator());
                continue;
            }

            if(abs(matrix[row][column+distance] - value) > error && matrix[row][column+distance] != 0){
                errors.append(" Error in : (")
                        .append(row).append(",")
                        .append(column).append(")")
                        .append(System.lineSeparator());
                continue;
            }
            if(abs(matrix[row][column-distance] - value) > error && matrix[row][column-distance] != 0){
                errors.append(" Error in : (")
                        .append(row).append(",")
                        .append(column).append(")")
                        .append(System.lineSeparator());

            }


        }

        System.out.println(errors);

    }

    public static String readFile() throws IOException {

        String path = "src/sensor.txt";
        StringBuilder sb = new StringBuilder();


        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line;

        while((line=reader.readLine()) !=null){

            sb.append(line).append(System.lineSeparator());

        }

        return sb.toString().trim();


    }

    public static void printMatrix(int[][] matrix) {

        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {

                System.out.print(matrix[i][j]+" ");

            }
            System.out.println();

        }
    }


    public static int[][] setMatrix(String sensors){

        String[] info= sensors.split("\n");


        int[][] matrix = new int[10][10];



        for(int i=0;i<10;i++) {

            for (int j = 0; j < 10; j++) {

                for (String s : info) {


                    int row = Integer.parseInt(s.split(",")[0]);

                    int column = Integer.parseInt(s.split(",")[1]);

                    int value = Integer.parseInt(s.split(",")[2].replace("\r", ""));


                    if (row == i && column == j) {

                        matrix[i][j] = value;
                        break;


                    } else {
                        matrix[i][j] = 0;
                    }


                }

            }
        }


        return matrix;
    }
}
