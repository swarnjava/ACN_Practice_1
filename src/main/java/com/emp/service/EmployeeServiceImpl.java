package com.emp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import com.emp.entity.EmployeeInfo;
import com.emp.mapper.EmployeeInfoMapper;
import com.emp.model.EmployeeModel;
import com.emp.repository.EmployeeInfoRepo;

@Service
public class EmployeeServiceImpl implements EmployeeServiceIntrfc {

	@Autowired
	private EmployeeInfoRepo empRepository;

	@Autowired
	private EmployeeInfoMapper empInfoMapper;

	@Override
	public List<EmployeeModel> getAllEmployees() {
		// TODO Auto-generated method stub
		List<EmployeeModel> empModelList = new ArrayList<>();
		List<EmployeeInfo> empList = empRepository.findAll();

		if (null != empList && empList.size() > 0) {
			empModelList = empList.stream().map(empInfo -> empInfoMapper.mapEmployeeModel(empInfo))
					.collect(Collectors.toList());
		}

		return empModelList;
	}

	@Override
	public EmployeeModel getEmployeeById(Integer empId) {
		// TODO Auto-generated method stub
		return empInfoMapper.mapEmployeeModel(empRepository.getOne(empId));
	}

	@Override
	public List<EmployeeModel> getEmployeeByAddress(String city) {
		// TODO Auto-generated method stub
		List<EmployeeModel> empModelList = new ArrayList<>();
		List<EmployeeInfo> empList = empRepository.findAll();
		if (null != empList && empList.size() > 0) {
			empModelList = empList.stream().filter(e -> e.getEmpAddress().equalsIgnoreCase(city))
					.map(empInfo -> empInfoMapper.mapEmployeeModel(empInfo)).collect(Collectors.toList());

		}
		return empModelList;
	}

	@Override
	public EmployeeModel saveEmployee(EmployeeModel em) {
		// TODO Auto-generated method stub
		
		EmployeeInfo empInfo = empRepository.save(empInfoMapper.mapEmployeeInfo(em));
		System.out.println("After Saving, ID: "+empInfo.getEmpId());
		return empInfoMapper.mapEmployeeModel(empInfo);
	}

}
