package info.heleno;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Heleno
 */
public class App {

    public static void main(String[] args) {
        if (args.length >= 1) {
            try {
                int filesAmount = Integer.parseInt(args[0]);
                int biggestAmount = 0;
                List<DataFile> files = new LinkedList<>();
                for (int i = 1; i <= filesAmount; i++) {
                    DataFile file = new DataFile(args[i]);
                    List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
                    file.setDataPointsAmount(lines.size());
                    if (lines.size() > biggestAmount) {
                        biggestAmount = lines.size();
                    }
                    for (String line : lines) {
                        file.addDataPoint(Double.parseDouble(line));
                    }
                    files.add(file);
                }
                List<String> outFiles = new LinkedList<>();
                for (DataFile file : files) {
                    String fileName = file.getPath().substring(0, file.getPath().lastIndexOf("."));
                    File output = new File(fileName + "-out");
                    Files.writeString(output.toPath(), file.getPoints(biggestAmount), Charset.defaultCharset());
                    outFiles.add(output.getName());

                }

                if (!files.isEmpty()) {
                    File output = new File("plotScript.gpl");
                    StringBuilder builder = new StringBuilder();
                    builder.append("		set xlabel 'time to target solution'\n");
                    builder.append("		set size ratio 1\n");
                    builder.append("		set ylabel 'cumulative probability'\n");
                    builder.append("		                set yrange [0:1]\n");
                    builder.append("										set xrange [-1:]\n");
                    builder.append("		set key right bottom\n");
                    builder.append("                set grid\n");
                    builder.append("		set title 'TTT for XXXXX'\n");
                    builder.append("#    set logscale x 2\n");
                    builder.append("		set terminal png butt enhanced\n");
                    builder.append("		set xtics font \", 10\"\n");
                    builder.append("		set output 'output.png'\n");
                    builder.append("plot \"").append(outFiles.get(0)).append("\" t \"APPROACH1\" w line lw 3");
                    for (int i=1; i<outFiles.size();i++) {
                        builder.append(",\"").append(outFiles.get(i)).append("\" t \"APPROACH").append(i+1).append("\" w line lw 3");
                    }
                    builder.append("\n");
                    builder.append("		quit");
                    Files.writeString(output.toPath(), builder.toString(), Charset.defaultCharset());

                }

            } catch (IOException ex) {

            }

        }else{
            System.out.println("Arguments: amount_of_files path_to_file1 path_to_fileN");
            System.out.println("Example:");
            System.out.println("java -jar multiTTT 3 file1.dat file2.dat file3.dat");
        }
    }
}
