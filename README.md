# multiTTTPlot
Generate TTT plots for multiple input files

Requirements: 
  Java 1.8
  
  Maven 3.5?
  
  Gnuplot (I used 5.2, but it should work with other versions)
  

The idea of this simple project is to allow the generation of TTT Plots from multiple input files.

The original program, available at http://mauricio.resende.info/tttplots/ only allows 1 input file.

Reference for TTT plots: http://www2.ic.uff.br/~celso/artigos/tttplots.pdf

Instructions of use:

1. Clone the repository: git clone https://github.com/helenocampos/multiTTTPlot.git
2. Build the project: mvn install
3. Go to the target folder and the binary will be there (multiTTT.jar)


How to use the binary:
Arguments: number_of_input_files input_file_1 input_file_2 input_file_3

Example: java -jar multiTTT.jar 3 file1.dat file2.dat file3.dat

After you run the binary, it will generate all output files (suffix 'out') and a gnuplot script. If you want, you can edit the script.
If you just want to generate the plot without editing, just run 'gnuplot plotScript.gpl' and the plot will be generated with the name 'output.png'.

This project was developed for personal and controlled purposes. For this reason, code is not sanitized i.e. there are not enough checkings against exceptions and there are possibily lots of bugs.

Feel free to open an issue if you need help.
