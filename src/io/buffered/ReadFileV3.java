package io.buffered;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.buffered.BufferedConst.FILE_NAME;

public class ReadFileV3 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        BufferedInputStream bis = new BufferedInputStream(fis, BUFFER_SIZE);
        long startTime = System.currentTimeMillis();

        int fileSize = 0;
        int data;

        /*
                                         bis.read()     <==( BUFFER_SIZE 만큼, 데이터를 버퍼에 담아놓는다. )==   "temp/buffered.dat"
            data    <==( 1byte 반환 )==   bis.read()     ( 버퍼에 (BUFFER_SIZE 만큼) 담아놓은 데이터가 모두 반환될 때까지, "temp/buffered.dat" 는 조회 하지도 않는다.
            data                         bis.read()     <==( read() 를 호출하려다가 버퍼가 비어 있는 것 확인하면 (BUFFER_SIZE 만큼) "temp/buffered.dat" 에서 데이터 조회 후 버퍼에 담아둔다.
            ( 반복 )
        */
        while ((data = bis.read()) != -1) {
            fileSize++;
        }
        bis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + FILE_NAME);
        System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
