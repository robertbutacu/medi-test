package com.medi.test.meditest.controllers;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.services.contracts.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/v1/domains")
public class DomainsController {

    @Autowired
    private IDomainService domainService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getAllDomains() {

        List<DomainDto> domains = domainService.getAllDomains();

        if (domains.size() == 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(domains, HttpStatus.OK);
    }
}