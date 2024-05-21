package com.luke.peach;

import com.luke.peach.job.CurrencyJob;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class PeachApplicationTests {

    @Autowired
    private CurrencyJob cell;
    @Test
    void contextLoads() throws IOException {
        cell.cell(12);
    }

}
