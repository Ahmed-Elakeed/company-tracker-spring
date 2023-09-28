package com.study.companytracker.dto;

import com.study.companytracker.util.enums.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
