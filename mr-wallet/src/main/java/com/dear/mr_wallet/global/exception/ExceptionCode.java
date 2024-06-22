package com.dear.mr_wallet.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    HISTORY_NOT_FOUND(415, "존재하지 않는 기록입니다."),
    MEMBER_NOT_FOUND(415, "존재하지 않는 회원입니다."),
    CATEGORY_NOT_FOUND(415, "존재하지 않는 카테고리입니다."),

    UNAUTHORIZED_FOR_UPDATE(403, "수정 권한이 없습니다."),
    UNAUTHORIZED_FOR_DELETE(403, "삭제 권한이 없습니다."),
    INVALID_ACCESS(403, "유효하지 않은 접근입니다."),

    EMAIL_ALREADY_EXIST(504, "이미 존재하는 이메일입니다."),
    NICKNAME_ALREADY_EXIST(504, "이미 존재하는 닉네임입니다."),

    INVALID_EMAIL_CODE(403, "유효하지 않은 인증 코드입니다."),
    ALREADY_EXPIRED_EMAIL_CODE(403, "인증 코드가 만료되었습니다."),
    IMPOSSIBLE_CHANGE_SAME_PASSWORD(504, "같은 비밀번호로는 변경할 수 없습니다.");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
