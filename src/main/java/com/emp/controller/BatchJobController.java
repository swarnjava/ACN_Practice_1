package com.emp.controller;

import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.batchservice.BatchJobService;

@RestController
@RequestMapping("/batch")
public class BatchJobController {

	@Autowired
	private BatchJobService batchJobService;
	
	@GetMapping("/testBatch")
	public ResponseEntity<List<ExitStatus>> testBatchJob()
	{
		List<ExitStatus> exitStatusList = batchJobService.runResortDetailsCacheRefreshJob("Web", "POINTS", "WEB00USER");
		
		return new ResponseEntity<>(exitStatusList,null,HttpStatus.OK);
	}
	
	@GetMapping("/testBatch2")
	public ResponseEntity<List<ExitStatus>> testBatchJob2()
	{
		List<ExitStatus> exitStatusList = batchJobService.runEmployeeDetailsJob("Web", "POINTS", "WEB00USER");
		
		return new ResponseEntity<>(exitStatusList,null,HttpStatus.OK);
	}
}
