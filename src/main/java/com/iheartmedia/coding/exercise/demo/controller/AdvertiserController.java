package com.iheartmedia.coding.exercise.demo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iheartmedia.coding.exercise.demo.domain.Advertiser;
import com.iheartmedia.coding.exercise.demo.mapper.AdvertiserMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(description = "This controller is for Advertisers.")
public class AdvertiserController {

	final private AdvertiserMapper advertiserMapper;

	public AdvertiserController(AdvertiserMapper advertiserMapper) {
		this.advertiserMapper = advertiserMapper;
	}	
	
	@RequestMapping(value = "/advertisers", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody Advertiser adv) {
		advertiserMapper.create(adv);
	    return ResponseEntity.ok(null);
    }	

	@RequestMapping(value = "/advertisers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody Advertiser adv) {
		adv.setId(id);
		advertiserMapper.update(adv);
	    return ResponseEntity.ok(null);
    }	

	/*
	@RequestMapping(value = "/advertisers", method = RequestMethod.GET)
    public ResponseEntity<List<Advertiser>>() {
		advertiserMapper.create(adv);
	    return ResponseEntity.ok(null);
    }	
	*/
}
