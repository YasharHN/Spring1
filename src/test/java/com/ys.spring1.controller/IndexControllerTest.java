package com.ys.spring1.controller;

import com.ys.spring1.AppInitializer;
import com.ys.spring1.config.AppConfig;
import com.ys.spring1.config.WebMvcConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created with IntelliJ IDEA.
 * User: yashar
 * Date: 2014-03-01
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppInitializer.class, AppConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class IndexControllerTest {

    @Inject
    protected WebApplicationContext wac;

    @Inject
    protected ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testShowIndex() throws Exception {
        String result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertNotNull(result);
    }

    @Test
    public void testShowSample() throws Exception {
        mockMvc.perform(get("/sample"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("LOCAL_TIME").exists());
    }
}
