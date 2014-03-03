package com.ys.spring1.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: yashar
 * Date: 2014-03-01
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController {

    @Inject
    ObjectMapper objectMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() {
        return Calendar.getInstance().getTime().toString();
    }

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> showSample() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("LOCAL_TIME", Calendar.getInstance().getTimeInMillis());
        map.put("GMT_TIME", Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis());
        map.put("JSON", objectMapper.writeValueAsString(map));
        return map;
    }

}