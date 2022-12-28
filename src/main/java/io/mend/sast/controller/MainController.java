package io.mend.sast.controller;

import io.mend.sast.exception.InvalidDomainException;
import io.mend.sast.model.DomainTestRequest;
import io.mend.sast.service.DomainTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    private DomainTestService domainTestService;

    @RequestMapping(method=RequestMethod.POST, value="/test-domain", consumes="application/json")
    public ResponseEntity<String> testDomain(@RequestBody DomainTestRequest request) {
        try {
            String result = domainTestService.testDomain(request.getDomainName());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(InvalidDomainException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

