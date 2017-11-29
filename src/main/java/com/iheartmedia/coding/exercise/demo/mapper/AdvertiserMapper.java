package com.iheartmedia.coding.exercise.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.SelectKey;
import com.iheartmedia.coding.exercise.demo.domain.Advertiser;
import java.util.List;

/**
 * @author Jon Roberts
 */
@Mapper
public interface AdvertiserMapper {
	// adapted from https://dzone.com/articles/spring-boot-working-with-mybatis
	@Select("select top 1 * from advertiser where name like #{name}")
	Advertiser findByName(@Param("name") String name);
	
	@Select("select top 1 * from advertiser where id like #{id}")
	Advertiser findById(@Param("id") Long id);	

    @Insert("insert into advertiser (name, contactName, creditLimit) values (#{name}, #{contactName}, #{creditLimit});")
    @SelectKey(statement="call identity()", keyProperty="id", before=false, resultType=Long.class)
    void create(Advertiser adv);
    
    @Select("select id, name, contactName, creditLimit from advertiser")
    List<Advertiser> findAll();
    
    @Update("update advertiser set name= #{name}, contactName = #{contactName}, creditLimit = #{creditLimit} where id = #{id}")
    void update(Advertiser adv);
	
}
