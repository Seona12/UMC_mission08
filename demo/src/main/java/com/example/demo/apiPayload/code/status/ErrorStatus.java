package com.example.demo.apiPayload.code.status;

import com.example.demo.apiPayload.code.BaseErrorCode;
import com.example.demo.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "TEMP4001", "요청하신 음식 카테고리를 찾을 수 없습니다."),
    //Feat#1
    REGION_NOT_FOUND(HttpStatus.BAD_REQUEST, "REGION4001", "해당 지역을 찾을 수 없습니다."),

    //Feat#2 : 가게에 리뷰 추가하기
    STORE_NOT_FOUND (HttpStatus.BAD_REQUEST, "STORE4001",  "해당 가게를 찾을 수 없습니다."),


    //Feat#4 : 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    MEMBER_NOT_FOUND (HttpStatus.BAD_REQUEST, "MEMBER4001",    "사용자 정보가 없습니다."),
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001",   "해당 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_IN_PROGRESS(HttpStatus.BAD_REQUEST, "MISSION4002",   "이미 진행 중인 미션입니다."),

    //Feat#6 : 유저별 리뷰 페이징 조회


    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }


}
// errorreason -> [convert] ApiResponse
//exceptionAdvice에서 처리.