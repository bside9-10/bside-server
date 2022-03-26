package com.bside.study.user.controller;

import com.bside.study.config.ApiDocumentationUtils;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureRestDocs
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("junit")
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void before_signup() throws Exception {
        JSONObject body = new JSONObject();
        body.put("name", "십가능");
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
    void 사용자_회원가입() throws Exception {
        JSONObject body = new JSONObject();
        body.put("name", "홍길동");
        body.put("email", "hong@gmail.com");
        body.put("password", "test1234");
        body.put("role", "USER");

        ResultActions result = mockMvc.perform(
                post("/api/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body.toString())
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AuthController.class))
                .andExpect(handler().methodName("registerUser"))
                .andExpect(jsonPath("$.success", is(true)))
                .andDo(document("user-signup",
                                ApiDocumentationUtils.getDocumentRequest(),
                                ApiDocumentationUtils.getDocumentResponse(),
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("사용자 이름"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("사용자 이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("사용자 비밀번호"),
                                        fieldWithPath("role").type(JsonFieldType.STRING).description("사용자 권한")
                                ),
                                responseFields(
                                        fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 여부"),
                                        fieldWithPath("response.id").type(JsonFieldType.NUMBER).description("사용자 pk"),
                                        fieldWithPath("response.name").type(JsonFieldType.STRING).description("사용자 이름"),
                                        fieldWithPath("response.email").type(JsonFieldType.STRING).description("사용자 이메일"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).description("에러")
                                )
                        )
                );
    }

    @Test
    void 사용자_로그인() throws Exception {
        JSONObject body = new JSONObject();
        body.put("email", "bside@gmail.com");
        body.put("password", "test1234");

        mockMvc.perform(
                        post("/api/v1/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(body.toString())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AuthController.class))
                .andExpect(handler().methodName("authenticateUser"))
                .andExpect(jsonPath("$.success", is(true)))
                .andDo(document("user-login",
                                ApiDocumentationUtils.getDocumentRequest(),
                                ApiDocumentationUtils.getDocumentResponse(),
                                requestFields(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("사용자 이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("사용자 비밀번호")
                                ),
                                responseFields(
                                        fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 여부"),
                                        fieldWithPath("response.accessToken").type(JsonFieldType.STRING).description("토큰"),
                                        fieldWithPath("response.tokenType").type(JsonFieldType.STRING).description("토큰 타입"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).description("에러")
                                )
                        )
                );
    }

    @Test
    void 사용자_내정보_조회() throws Exception {
        mockMvc.perform(
                        get("/api/v1/users/me")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJic2lkZUBnbWFpbC5jb20iLCJpYXQiOjE2NDgyOTgyOTYsImV4cCI6MTY0OTE2MjI4Nn0.XdmLA8SzhZB4DHKbiVtDd9NHcg3KLTPHUpSZg0567yUgvB_YD_iEDYzJerEeNNy-mRr5i9QSccWqaX-QIenj1w")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("getCurrentUser"))
                .andExpect(jsonPath("$.success", is(true)))
                .andDo(document("user-me",
                                ApiDocumentationUtils.getDocumentRequest(),
                                ApiDocumentationUtils.getDocumentResponse(),
                                requestHeaders(headerWithName("Authorization").description("Json Web Token")),
                                responseFields(
                                        fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 여부"),
                                        fieldWithPath("response.id").type(JsonFieldType.NUMBER).description("사용자 id"),
                                        fieldWithPath("response.name").type(JsonFieldType.STRING).description("사용자 이름"),
                                        fieldWithPath("response.email").type(JsonFieldType.STRING).description("사용자 이메일"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).description("에러")
                                )
                        )
                );
    }

}