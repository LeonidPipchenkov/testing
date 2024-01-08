package net.happiness.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {

    @InjectMocks
    private WorkerService workerService;
    @Mock
    private WorkerRepository workerRepository;

    @Test
    void sumOfSalaries() {
        Worker worker1 = new Worker(1, "Дмитрий", 100_000);
        Worker worker2 = new Worker(1, "Дмитрий", 150_000);
        Mockito.when(workerRepository.getWorkers()).thenReturn(List.of(worker1, worker2));

        int sumOfSalariesOfAllWorkers = workerService.getSumOfSalariesOfAllWorkers();

        Assertions.assertEquals(250_000, sumOfSalariesOfAllWorkers);
    }

    @Test
    void hire_withLowSalary_throwsException() {
        Worker worker = new Worker(1, "Алексей", 15_000);
        Assertions.assertThrows(TooLowSalaryException.class, () -> workerService.hire(worker));
    }

    @Test
    void hire_withValidSalary_savesWorker() {
        Worker worker = new Worker(1, "Иван", 20_000);
        workerService.hire(worker);
        Mockito.verify(workerRepository, Mockito.times(1)).saveWorker(worker);
    }

}
