package dzholdoshbaev.laboratory.service.impl;




import dzholdoshbaev.laboratory.model.Authorities;
import dzholdoshbaev.laboratory.repository.AuthoritiesRepository;
import dzholdoshbaev.laboratory.service.AuthoritiesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {
    private final AuthoritiesRepository authoritiesRepository;


    @Override
    public List<Authorities> getAllAuthorities() {
       log.info("Authorities det all");
       return authoritiesRepository.findAll();
    }
}
