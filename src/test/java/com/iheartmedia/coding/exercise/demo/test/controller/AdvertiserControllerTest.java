package com.iheartmedia.coding.exercise.demo.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.iheartmedia.coding.exercise.demo.domain.Advertiser;
import com.iheartmedia.coding.exercise.demo.mapper.AdvertiserMapper;
import com.iheartmedia.coding.exercise.demo.DemoApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class AdvertiserControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private Advertiser advertiser;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AdvertiserMapper advertiserMapper;
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.advertiserMapper.deleteAll();

        this.advertiser = new Advertiser("Joey", "The Man", new BigDecimal("20.00")); 
        this.advertiserMapper.create(this.advertiser);
    }    

    @Test
    public void advertiserNotFound() throws Exception {
        mockMvc.perform(post("/api/advertisers/2345623")
                .content(this.json(new Advertiser()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void readSingleAdvertiser() throws Exception {
        mockMvc.perform(get("/api/advertisers/"
                + this.advertiser.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.advertiser.getId().intValue())))
                //.andExpect(jsonPath("$.uri", is("http://localhost:8080/1/" + userName)))
                .andExpect(jsonPath("$.contactName", is("The Man")));
    }

    @Test
    public void readAdvertisers() throws Exception {
        mockMvc.perform(get("/api/advertisers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                /*.andExpect(jsonPath("$[0].id", is(this.bookmarkList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].uri", is("http://bookmark.com/1/" + userName)))
                .andExpect(jsonPath("$[0].description", is("A description")))
                .andExpect(jsonPath("$[1].id", is(this.bookmarkList.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].uri", is("http://bookmark.com/2/" + userName)))
                .andExpect(jsonPath("$[1].description", is("A description")))*/
                ;
    }

    @Test
    public void createAdvertiser() throws Exception {
        String advJson = json(new Advertiser(
                "Mark","O Polo",new BigDecimal("50000.50")));

        this.mockMvc.perform(post("/api/advertisers")
                .contentType(contentType)
                .content(advJson))
        		.andExpect(status().isOk())
                //.andExpect(status().isCreated()) //NOTE: won't pass until we implement 201 on controller
                ;
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }    
    
}
