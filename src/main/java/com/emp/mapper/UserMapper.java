package com.emp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.emp.entity.UserInfo;
import com.emp.entity.UserSkills;
import com.emp.model.SkillModel;
import com.emp.model.UserModel;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

@Mappings({
		
		@Mapping(target = "userName",source = "userInfo.userName"),
		@Mapping(target = "userPassword",source = "userInfo.userPassword"),
		@Mapping(target = "userType",source = "userInfo.userType"),
		@Mapping(target = "fName",source = "userInfo.userDetail.fName"),
		@Mapping(target = "lName",source = "userInfo.userDetail.lName"),
		@Mapping(target = "address",source = "userInfo.userDetail.address")
	})
    @BeanMapping(qualifiedByName = "userSkillMap")
	public UserModel convertToUserModel(UserInfo userInfo);



@Named("userSkillMap")
@AfterMapping
default UserModel afterMapEmployeeModel(@MappingTarget UserModel userModel, UserInfo userInfo)
{
	List<SkillModel> skills=new ArrayList();
	
	for(UserSkills sk:userInfo.getSkillList())
	{
		SkillModel skm=new SkillModel();
		skm.setSkillName(sk.getSkillName());
		skm.setYearOfExperience(sk.getExpYears());
		skills.add(skm);
	}
	
	userModel.setSkillList(skills);
	
	return userModel;
}

}
