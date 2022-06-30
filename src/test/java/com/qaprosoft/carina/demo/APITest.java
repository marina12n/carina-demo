package com.qaprosoft.carina.demo;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.demo.api.myapi.GetWeather;
import com.qaprosoft.carina.demo.api.myapi.jsonPlaceHolder.*;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class APITest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void testGetPosts() {
        GetPostMethod api = new GetPostMethod();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse();
    }

    @Test
    public void testGetSpecificPost() {
        GetSpecificPostMethod api = new GetSpecificPostMethod();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse();
    }

    @Test
    public void testCreatingNewPost() {
        PostMethod api = new PostMethod();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.validateResponse();
    }

    @Test
    public void testDelete() {
        DeleteMethod api = new DeleteMethod();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse();
    }

    @Test
    public void testGetFirstComment() {
        GetFirstCommentMethod api = new GetFirstCommentMethod();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse();
    }

    @Test
    public void testGetSecondComment() {
        GetSecondCommentMethod api = new GetSecondCommentMethod();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse();
    }

    @Test
    public void testGetCommentsPost() {
        GetCommentsPost api = new GetCommentsPost();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse();
    }

    @Test
    public void testGetPostThird() {
        GetPostThird api = new GetPostThird();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse();
    }

    @Test
    public void testDeleteSecond() {
        DeleteSecondMethod api = new DeleteSecondMethod();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse();
    }

    @Test
    public void testDeleteThird() {
        DeleteThirdMethod api = new DeleteThirdMethod();
        api.callAPIExpectSuccess();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.validateResponse();
    }

//    @Test()
//    public void testGetWeather() {
//      GetWeather api = new GetWeather();
//      api.callAPIExpectSuccess();
//      api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
//      api.validateResponseAgainstSchema("api/weather/_get/rs.schema");
//}
}
