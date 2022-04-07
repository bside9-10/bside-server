package com.bside.study.config;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public interface ApiDocumentationUtils {
    static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(modifyUris().scheme("https").host("api.bside9-10-api.site"), prettyPrint());
    }

    static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(modifyUris().scheme("https").host("api.bside9-10-api.site"), prettyPrint());
    }
}
