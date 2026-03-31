package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV2 {

    public static void main(String[] args) throws IOException {     //                                                              append 옵션 : 실행될 때 기존 문서 끝에 이어서 저장할지 여부.
        FileOutputStream fos = new FileOutputStream(FILE_NAME);     // FileOutputStream fos = new FileOutputStream("temp/hello.dat", true);
        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BUFFER_SIZE];  // BUFFER_SIZE : 8192 (8KB)     많은 데이터를 한 번에 전달하면 성능을 최적화 할 수 있지만,
        int bufferIndex = 0;                    //                              디스크나 시스템에서 데이터를 읽고 쓰는 기본 단위가 보통 4KB 또는 8KB 이므로 => 8KB 부터는 성능 차이가 나지 않는다.

        for (int i = 0; i < FILE_SIZE; i++) {   // FILE_SIZE : 10 * 1024 * 1024; (10MB)
            buffer[bufferIndex++] = 1;

            // 버퍼가 가득 차면 쓰고, 버퍼를 비운다.
            if (bufferIndex == BUFFER_SIZE) {   // BUFFER_SIZE 가 8192 만큼 찰 때까지 모아서, buffer("temp/buffered.dat")에 입력시킨다.
                fos.write(buffer);
                bufferIndex = 0;
            }
        }

        // 끝 부분에 오면 버퍼가 가득차지 않고, 남아있을 수 있다. 버퍼에 남은 부분 쓰기
        if (bufferIndex > 0) {
            fos.write(buffer, 0, bufferIndex);
        }

        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
