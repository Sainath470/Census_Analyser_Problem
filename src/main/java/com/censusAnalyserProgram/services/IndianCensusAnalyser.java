package com.censusAnalyserProgram.services;

import java.io.IOException;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class IndianCensusAnalyser {

        public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
            try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                CsvToBeanBuilder<StateCensusAnalyser> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                csvToBeanBuilder.withType(StateCensusAnalyser.class);
                csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                CsvToBean<StateCensusAnalyser> csvToBean = csvToBeanBuilder.build();
                Iterator<StateCensusAnalyser> censusCSVIterator = csvToBean.iterator();
                Iterable<StateCensusAnalyser> csvIterable = () -> censusCSVIterator;
                int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
                return numOfEntries;
            } catch (IOException e) {
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException
                        .ExceptionType
                        .CENSUS_FILE_PROBLEM);
            } catch (IllegalStateException e) {
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException
                        .ExceptionType
                        .UNABLE_TO_PARSE);
            }
        }

        public static void main(String[] args) throws CensusAnalyserException {
            IndianCensusAnalyser indianCensusAnalyser = new IndianCensusAnalyser();
            int number = indianCensusAnalyser.loadIndiaCensusData("C:\\Users\\ACER\\Desktop\\New folder\\Census_Analyser_Problem\\src\\main\\resources\\india-districts-census-2011.csv");
            System.out.println(number);
        }
}
