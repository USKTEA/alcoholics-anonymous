package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OptimisticLockStockServiceTest {
    @Autowired
    private OptimisticLockStockService optimisticLockStockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void setup() {
        Stock stock = new Stock(1L, 100L);

        stockRepository.saveAndFlush(stock);
    }

    @AfterEach
    public void clear() {
        stockRepository.deleteAll();
    }

    @Test
    public void stock_decrease() {
        List<Stock> stocks = stockRepository.findAll();
        Long id = stocks.get(0).id();

        optimisticLockStockService.decrease(id, 1L);

        Stock stock = stockRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        assertEquals(99, stock.quantity());
    }

    @Test
    public void 동시에_100개_요청() throws InterruptedException {
        List<Stock> stocks = stockRepository.findAll();
        Long id = stocks.get(0).id();

        int treadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(treadCount);

        for (int i = 0; i < treadCount; i += 1) {
            executorService.submit(() -> {
                try {
                    optimisticLockStockService.decrease(id, 1L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Stock stock = stockRepository.findById(id).orElseThrow();

        assertEquals(0, stock.quantity());
    }
}
