package com.bside.study.goal.controller;

import com.bside.study.config.ApiDocumentationUtils;
import com.bside.study.user.controller.UserController;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureRestDocs
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("junit")
@Transactional
class GoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void before_signup() throws Exception {
        JSONObject body = new JSONObject();
        body.put("name", "?????????");
        body.put("email", "bside@gmail.com");
        body.put("password", "test1234");
        body.put("role", "USER");

        mockMvc.perform(
                post("/api/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body.toString())
        );
    }

    @Test
    void ??????_????????????() throws Exception {
        mockMvc.perform(
                        get("/api/v1/goals")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJic2lkZUBnbWFpbC5jb20iLCJpYXQiOjE2NDgyOTgyOTYsImV4cCI6MTY0OTE2MjI4Nn0.XdmLA8SzhZB4DHKbiVtDd9NHcg3KLTPHUpSZg0567yUgvB_YD_iEDYzJerEeNNy-mRr5i9QSccWqaX-QIenj1w")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(GoalController.class))
                .andExpect(handler().methodName("findGoalsByUserId"))
                .andExpect(jsonPath("$.success", is(true)))
                .andDo(document("goal-list",
                                ApiDocumentationUtils.getDocumentRequest(),
                                ApiDocumentationUtils.getDocumentResponse(),
                                requestHeaders(headerWithName("Authorization").description("Json Web Token")),
                                responseFields(
                                        fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("response[].id").type(JsonFieldType.NUMBER).description("?????? pk"),
                                        fieldWithPath("response[].name").type(JsonFieldType.STRING).description("????????? ??????"),
                                        fieldWithPath("response[].category").type(JsonFieldType.STRING).description("?????? ????????????"),
                                        fieldWithPath("response[].detail").type(JsonFieldType.STRING).description("?????? ???????????? ??????"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).description("??????")
                                )
                        )
                );
    }

    @Test
    void ??????_????????????_??????() throws Exception {
        mockMvc.perform(
                        get("/api/v1/goals/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJic2lkZUBnbWFpbC5jb20iLCJpYXQiOjE2NDgyOTgyOTYsImV4cCI6MTY0OTE2MjI4Nn0.XdmLA8SzhZB4DHKbiVtDd9NHcg3KLTPHUpSZg0567yUgvB_YD_iEDYzJerEeNNy-mRr5i9QSccWqaX-QIenj1w")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(GoalController.class))
                .andExpect(handler().methodName("findGoalCategories"))
                .andExpect(jsonPath("$.success", is(true)))
                .andDo(document("goal-category-list",
                                ApiDocumentationUtils.getDocumentRequest(),
                                ApiDocumentationUtils.getDocumentResponse(),
                                requestHeaders(headerWithName("Authorization").description("Json Web Token")),
                                responseFields(
                                        fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("response[].id").type(JsonFieldType.NUMBER).description("?????? ???????????? pk"),
                                        fieldWithPath("response[].category").type(JsonFieldType.STRING).description("?????? ????????????"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).description("??????")
                                )
                        )
                );
    }

    @Test
    void ??????_????????????_??????_??????() throws Exception {
        mockMvc.perform(
                        get("/api/v1/goals/categories/details")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJic2lkZUBnbWFpbC5jb20iLCJpYXQiOjE2NDgyOTgyOTYsImV4cCI6MTY0OTE2MjI4Nn0.XdmLA8SzhZB4DHKbiVtDd9NHcg3KLTPHUpSZg0567yUgvB_YD_iEDYzJerEeNNy-mRr5i9QSccWqaX-QIenj1w")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(GoalController.class))
                .andExpect(handler().methodName("findGoalCategoryDetails"))
                .andExpect(jsonPath("$.success", is(true)))
                .andDo(document("goal-category-detail-list",
                                ApiDocumentationUtils.getDocumentRequest(),
                                ApiDocumentationUtils.getDocumentResponse(),
                                requestHeaders(headerWithName("Authorization").description("Json Web Token")),
                                responseFields(
                                        fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("response[].id").type(JsonFieldType.NUMBER).description("?????? ???????????? pk"),
                                        fieldWithPath("response[].category").type(JsonFieldType.STRING).description("?????? ????????????"),
                                        fieldWithPath("response[].detail").type(JsonFieldType.STRING).description("?????? ???????????? ??????"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).description("??????")
                                )
                        )
                );
    }

}