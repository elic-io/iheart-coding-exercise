package com.iheartmedia.coding.exercise.demo.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import com.iheartmedia.coding.exercise.demo.domain.Advertiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

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

}
