package com.iheartmedia.coding.exercise.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.iheartmedia.coding.exercise.demo.domain.Advertiser;

/**
 * @author Jon Roberts
 */
@Mapper
public interface AdvertiserMapper {
	
	@Select("select * from advertiser where name = #{name}")
	Advertiser findByName(@Param("name") String name);
	
}
