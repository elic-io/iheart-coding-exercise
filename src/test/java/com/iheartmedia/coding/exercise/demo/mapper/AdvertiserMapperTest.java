package com.iheartmedia.coding.exercise.demo.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import com.iheartmedia.coding.exercise.demo.domain.Advertiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
public class AdvertiserMapperTest {

    @Autowired
    private AdvertiserMapper advertiserMapper;

    @Test
    public void findByNameTest() {
        Advertiser adv = advertiserMapper.findByName("Super Soaker Inc.");
        assertThat(adv.getContactName()).isEqualTo("Lonnie Johnson");
        // note that feeding a double to the BigDecimal constructor is not precise
        // https://stackoverflow.com/questions/1605910/data-type-to-represent-a-big-decimal-in-java
        assertThat(adv.getCreditLimit()).isEqualTo(new BigDecimal("2000000.00"));
    }

    @Test
    public void findByIdTest() {
        Advertiser adv = advertiserMapper.findById(1L);
        assertNotNull(adv);
    }

    @Test
    public void findAllAdvertisersTest() {
        List<Advertiser> advertisers = advertiserMapper.findAll();
        assertNotNull(advertisers);
        assertTrue(!advertisers.isEmpty());
    }
    
    @Test
    public void createAdvertiserTest() {
        Advertiser adv = new Advertiser("Cranberries", "Ocean Spray", new BigDecimal("100000.00"));
        advertiserMapper.create(adv);
        Advertiser newAdv = advertiserMapper.findById(adv.getId());
        assertEquals("Cranberries", newAdv.getName());
        assertEquals("Ocean Spray", newAdv.getContactName());
        assertEquals(new BigDecimal("100000.00"), newAdv.getCreditLimit());
    }

    @Test
    public void updateAdvertiserTest() {
    	//TODO: should this move to some type of harness?
        Advertiser adv = new Advertiser("Sugar", "Ray Leonard", new BigDecimal("10000000.00"));
        advertiserMapper.create(adv);
        
        adv.setName("Héctor");
        adv.setContactName("Camacho");
        adv.setCreditLimit(new BigDecimal("10.00"));
        advertiserMapper.update(adv);
        Advertiser newAdv = advertiserMapper.findById(adv.getId());
        assertEquals("Héctor", newAdv.getName());
        assertEquals("Camacho", newAdv.getContactName());
        assertEquals(new BigDecimal("10.00"), newAdv.getCreditLimit());
    }
    
}
