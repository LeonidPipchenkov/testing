package net.happiness.testing;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/salary-sum")
    public int getSumOfSalariesOfAllWorkers() {
        return workerService.getSumOfSalariesOfAllWorkers();
    }

    @PostMapping("/hire")
    public void hire(@RequestBody Worker worker) {
        workerService.hire(worker);
    }

}
