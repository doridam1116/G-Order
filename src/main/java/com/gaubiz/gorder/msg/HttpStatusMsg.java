package com.gaubiz.gorder.msg;

import java.util.Collections;
import java.util.List;

public interface HttpStatusMsg {
    public static final String  HTTP_OK =  "{code: 200, message: 요청 성공}";
    public static final String  HTTP_CREATED =  "{\"code\": \"201\", \"message\": \"생성 완료\"}";
    public static final List<String> HTTP_BAD_REQUEST = Collections.singletonList("{code: 400, message: 잘못된 요청, 요청 내용 확인 바랍니다.}");
    public static final String HTTP_UNAUTHORIZED = "{\"code\": \"401\", \"message\": \"인증되지 않음, 올바르게 재시도 바랍니다.\"}";
    public static final String HTTP_FORBIDDEN = "{\"code\": \"403\", \"message\": \"접근이 거부됨, 인증 후 다시 요청 바랍니다.\"}";
    public static final String HTTP_NOT_FOUND = "{\"code\": \"404\", \"message\": \"요청한 리소스를 찾을 수 없음, 확인 후 다시 요청 바랍니다.\"}";
    public static final String HTTP_SERVER_ERROR = "{\"code\": \"500\", \"message\": \"서버에서 에러가 발생했습니다. 잠시 후 다시 요청 해주세요.\"}";

}
