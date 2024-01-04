package com.study.companytracker.dto;

import com.study.companytracker.util.enums.ResponseMessage;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericRestResponse<T> {

    private T data;
    private ResponseMessage responseMessage;
    private Long responseCode;
    private String errorMessage;
}
