package com.example.getandsetResults.service;

import com.example.getandsetResults.model.order.AnalysisInOrder;
import com.example.getandsetResults.model.order.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class ConverterServiceTest {

    private OrderResponse orderResponse;

    @BeforeEach
    void setUp() {
        List<AnalysisInOrder> analysis = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AnalysisInOrder item = new AnalysisInOrder(1L + i, "analysis" + i,
                    23.1 * i, "TestD");
            analysis.add(item);
        }
        orderResponse = new OrderResponse(1L,
                Timestamp.valueOf(LocalDateTime.now()),
                analysis,
                "TestSurname",
                "TestName");
    }

    @Test
    void convertItem() throws IOException {
        ConverterService service = new ConverterService();
        service.createReport(orderResponse);
    }

}