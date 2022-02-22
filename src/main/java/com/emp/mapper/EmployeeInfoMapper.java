package com.emp.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.emp.entity.EmployeeInfo;
import com.emp.model.EmployeeModel;
import org.mapstruct.Mappings;

@Component
@Mapper(componentModel = "spring")
public interface EmployeeInfoMapper {

@Mappings({
		
		@Mapping(target = "employeeID",source = "empInfo.empId"),
		@Mapping(target = "employeeName",source = "empInfo.empName"),
		@Mapping(target = "employeeAddress",source = "empInfo.empAddress"),
		@Mapping(target = "employeePhone",source = "empInfo.empPhone")
	})
	EmployeeModel mapEmployeeModel(EmployeeInfo empInfo);


@Mappings({
	
	@Mapping(target = "empId",source = "model.employeeID"),
	@Mapping(target = "empName",source = "model.employeeName"),
	@Mapping(target = "empAddress",source = "model.employeeAddress"),
	@Mapping(target = "empPhone",source = "model.employeePhone")
})
EmployeeInfo mapEmployeeInfo(EmployeeModel model);
}
