package chengco.validation.demo.service;

import chengco.validation.demo.repositroy.DemoRepository;
import org.springframework.stereotype.Component;

@Component
public class DemoService {
    private final DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }
}
