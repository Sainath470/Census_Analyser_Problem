package com.censusAnalyserProgram.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IndianCensusAnalyserTest {

    private static final String ORIGINAL_FILE_PATH = "C:\\Users\\ACER\\Desktop\\New folder\\Census_Analyser_Problem\\src\\main\\resources\\india-districts-census-2011.csv";

    @Test
    public void givenCorrectOrNote_Checked(){
        try{
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            int numberOfRecords = censusAnalyser.loadIndiaCensusData(ORIGINAL_FILE_PATH);
            Assert.assertEquals(640, numberOfRecords);
        } catch (CensusAnalyserException e){}
    }



}